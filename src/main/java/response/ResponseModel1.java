package response;

public class ResponseModel1 {
	String message;

	String url = "http://localhost:8080/api/tickets/";

	public ResponseModel1(String message) {
		super();
		this.message = message;
	}

	public ResponseModel1(String message, String url) {
		super();
		this.message = message;
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
