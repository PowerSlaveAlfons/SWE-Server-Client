package BIF.SWE1.Plugins;

import BIF.SWE1.ResponseManager;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

/**
 * Is called when no other plugin was found to handle the request, puts out an error
 */
public class AllElseFailsPlugin implements Plugin
{
    /**
     * Always returns 0.1f.
     * @param req The Request
     * @return canHandle score
     */
    @Override
    public float canHandle(Request req)
    {
        return 0.1f;
    }

    /**
     * Creates a response with the error message
     * @param req The Request
     * @return Response
     */
    @Override
    public Response handle(Request req)
    {
        System.out.println("All Else Fails handling");
        String errorPage = "<!DOCTYPE html>\n<html>\n<head>\n<title>SWE Webserver</title>\n\n</head>\n<body>\n\n<h1>404</h1>\n<p>No plugin or static file found.</p>";
        Response Resp = new ResponseManager();
        Resp.setStatusCode(404);
        Resp.setContentType("text/html");
        Resp.setContent(errorPage);
        return Resp;
    }
}
