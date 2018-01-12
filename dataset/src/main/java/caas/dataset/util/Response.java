package caas.dataset.util;

public class Response {
    public Response(int code) {
        this.code = code;

        if (code == 401) {
            message = "Login Fail";
        }

        if (code == 404) {
            message = "Resource Not Found";
        }

        if (code == 406) {
            message = "Resource Existed";
        }

        if (code == 412) {
            message = "Missing Param in Body";
        }
    }

    public Response(int code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int code;
    public String message;
    public Object result;
}
