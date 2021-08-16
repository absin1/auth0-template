package ai.salesken.template;

import java.util.HashMap;

public class RequestLog {
	String method, queryParams, body, clientIp, path, requestId;
	private HashMap<String, String> headers;

	public RequestLog(String method, String queryParams, String body, String clientIp, HashMap<String, String> headerMap, String path,
			String requestId) {
		super();
		this.method = method;
		this.queryParams = queryParams;
		this.body = body;
		this.clientIp = clientIp;
		this.headers = headerMap;
		this.path = path;
		this.requestId = requestId;
	}

}
