package BIF.SWE1;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.Url;

public class TestPlugin implements Plugin {

    private Url url;
    private Request request;
    private Response response;

    TestPlugin() {

    }


    @Override
    public float canHandle(Request req) {
        this.url = req.getUrl();
        System.out.println("THIS IS THE PATH YOU MONKEY: " + this.url.getPath());
        if (this.url.getParameter().containsKey("test_plugin"))
            return (float)0.5; //This is pain
        if (this.url.getPath().contains("test") || this.url.getPath().contains("1"))
            return (float)0.5;
        if (this.url.getPath() == ("/"))
            return (float)0.69;

        else return 0;
    }

    @Override
    public Response handle(Request req) {
        this.url = req.getUrl();
        this.response = new ResponseManager();
        this.response.setStatusCode(200);
        this.response.setContent("Ich bin eine Response und du bist keine.");

        return this.response;
    }
}
