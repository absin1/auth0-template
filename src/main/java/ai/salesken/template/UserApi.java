/**
 * 
 */
package ai.salesken.template;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author absin
 *
 */
@WebServlet(urlPatterns = "/api/v1/user")
public class UserApi extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);
	private static final long serialVersionUID = 6139722207195284260L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log();
	}

	public static void main(String[] args) {
		new UserApi().log();
	}

	private void log() {
		logger.trace("Some trace");
		logger.info("Some info");
		logger.debug("Some debug");
		logger.error("Some error");
		logger.warn("Some warn");
	}

}
