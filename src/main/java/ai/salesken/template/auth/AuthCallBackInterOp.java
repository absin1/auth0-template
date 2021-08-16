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
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;

@WebServlet(urlPatterns = "/api/v1/auth/callback_inter")
public class AuthCallBackInterOp extends HttpServlet {
	private static final long serialVersionUID = 7491796757859723808L;
	private String redirectOnSuccess;
	private String redirectOnFail;
	private AuthenticationController authenticationController;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		redirectOnSuccess = "/template/home";
		redirectOnFail = "/template/api/v1/auth/login";

		try {
			authenticationController = AuthenticationControllerProvider.getInstance(config);
		} catch (UnsupportedEncodingException e) {
			throw new ServletException(
					"Couldn't create the AuthenticationController instance. Check the configuration.", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}

	private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			Tokens tokens = authenticationController.handle(req, res);
			SessionUtils.set(req, "accessToken", tokens.getAccessToken());
			SessionUtils.set(req, "idToken", tokens.getIdToken());
			System.out.println("sending to " + redirectOnSuccess);
			res.sendRedirect(redirectOnSuccess);
		} catch (IdentityVerificationException e) {
			e.printStackTrace();
			res.sendRedirect(redirectOnFail);
		}
	}
}
