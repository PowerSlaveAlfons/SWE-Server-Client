package BIF.SWE1.Plugins;

import BIF.SWE1.ResponseManager;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.Url;

public class TestPlugin implements Plugin
{
    private Url url;
    private Request request;
    private Response response;

    public TestPlugin()
    {

    }


    @Override
    public float canHandle(Request req)
    {
        this.url = req.getUrl();
        return 0;
    }

    @Override
    public Response handle(Request req)
    {
        System.out.println("TestPlugin is handling");
        String mainPage = "<!DOCTYPE html>\n<html>\n<head>\n<title>SWE Webserver</title>\n\n</head>\n<body>\n\n<h1>Test Plugin</h1>\n<p>This is a specific dynamic TestPlugin!</p>";
        Response response = new ResponseManager();
        response.setStatusCode(200);
        response.setContent(mainPage);
        return response;
    }
}
