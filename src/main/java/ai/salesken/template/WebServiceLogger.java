package ai.salesken.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.SessionUtils;
import com.google.gson.GsonBuilder;

@WebFilter("/*")
public class WebServiceLogger implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(WebServiceLogger.class);

	public WebServiceLogger() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. Get the path params, query param, body, client ip, user agent, headers,
		// method
		// 2. Send all of these to google log
		// 3. Check whether the request is authorized or not
		// 4. Set a unique request id for tracing logs
		String requestId = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
		String method = null, queryParams = null, body = null, clientIp = null, path = null;
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			path = httpServletRequest.getRequestURL().toString();
			try {
				HttpServletResponse httpServletResponse = (HttpServletResponse) response;
				String accessToken = (String) SessionUtils.get(httpServletRequest, "accessToken");
				String idToken = (String) SessionUtils.get(httpServletRequest, "idToken");
				if (accessToken == null && idToken == null && !path.contains("login") && !path.contains("callback")) {
					httpServletResponse.sendRedirect("/template/api/v1/auth/login");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			httpServletRequest.setAttribute("request_uuid", requestId);
			queryParams = httpServletRequest.getQueryString();
			method = httpServletRequest.getMethod();
			clientIp = httpServletRequest.getRemoteAddr();
			Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
			HashMap<String, String> headerMap = new HashMap<String, String>();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String headerKey = headerNames.nextElement();
					String headerValue = httpServletRequest.getHeader(headerKey);
					headerMap.put(headerKey, headerValue);
				}
			}
			if (!path.contains("upload_file")) {
				body = "";
				String line = null;
				BufferedReader reader = httpServletRequest.getReader();
				while ((line = reader.readLine()) != null)
					body += line;
				reader.close();
				httpServletRequest.setAttribute("body", body);
			}
			RequestLog log = new RequestLog(method, queryParams, body, clientIp, headerMap, path, requestId);
			logger.info(new GsonBuilder().setPrettyPrinting().create().toJson(log));

		} catch (Exception e) {
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

}
