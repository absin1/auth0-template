package ai.salesken.template.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.AuthenticationController;

@WebServlet(urlPatterns = "/api/v1/auth/logout")
public class AuthLogout extends HttpServlet {

	private static final long serialVersionUID = 4793172293610020599L;
	private String domain;
	private String clientId;

	@Override
	public void init(ServletConfig config) {
		domain = config.getServletContext().getInitParameter("com.auth0.domain");
		clientId = config.getServletContext().getInitParameter("com.auth0.clientId");
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession() != null) {
			request.getSession().invalidate();
		}
		String returnUrl = String.format("%s://%s", request.getScheme(), request.getServerName());
		if ((request.getScheme().equals("http") && request.getServerPort() != 80)
				|| (request.getScheme().equals("https") && request.getServerPort() != 443)) {
			returnUrl += ":" + request.getServerPort();
		}
		returnUrl += "/template/api/v1/auth/login";
		String logoutUrl = String.format("https://%s/v2/logout?client_id=%s&returnTo=%s", domain, clientId, returnUrl);
		response.sendRedirect(logoutUrl);
	}
}