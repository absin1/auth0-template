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

@WebServlet(urlPatterns = "/api/v1/auth/login")
public class AuthLogin extends HttpServlet {

	private static final long serialVersionUID = -8691634631855324631L;
	private AuthenticationController authenticationController;
	private String domain;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		domain = config.getServletContext().getInitParameter("com.auth0.domain");
		try {
			authenticationController = AuthenticationControllerProvider.getInstance(config);
		} catch (UnsupportedEncodingException e) {
			throw new ServletException(
					"Couldn't create the AuthenticationController instance. Check the configuration.", e);
		}
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		String redirectUri = "https://" + req.getServerName();
		if ((req.getScheme().equals("http") && req.getServerPort() != 80)
				|| (req.getScheme().equals("https") && req.getServerPort() != 443)) {
			redirectUri += ":" + req.getServerPort();
		}
		redirectUri += "/template/api/v1/auth/callback";

		String authorizeUrl = authenticationController.buildAuthorizeUrl(req, res, redirectUri).build();
		res.sendRedirect(authorizeUrl);
	}
}
