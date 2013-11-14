public class RequestHandler {

    public Request request;

    public RequestHandler(Request request) {
        this.request = request;
    }

    public Response call() {
        int code = getCode();
        String headers = getHeaders();
        return new Response(code, headers, "<html><body><h1>Hello World</h1></body></html>");
    }

    public int getCode() {
        return 200;
    }

    public String getHeaders() {
        if ("OPTIONS".equals(request.method) || "/method_options".equals(request.route)) {
            return "Allow: GET,HEAD,POST,OPTIONS,PUT";
        }
        else {
            return "";
        }
    }
}
