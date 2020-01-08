package BIF.SWE1.Plugins;

import BIF.SWE1.ResponseManager;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

/**
 * Takes a String and returns an all lowercase string
 */

public class ToLowerPlugin implements Plugin
{
    /**
     * Checks if ToLower is mentioned by the path, is available for handling if it is
     * @param req The Request
     * @return canHandle score
     */
    @Override
    public float canHandle(Request req)
    {
        String PathReq = req.getUrl().getRawUrl().substring(1);
        System.out.println("Path: " + PathReq);
        if (PathReq.equals("ToLower"))
            return 0.9f;
        else
            return 0;
    }

    /**
     * Actually converts the string that's in the content of the request
     * @param req The Request
     * @return Response
     */
    @Override
    public Response handle(Request req)
    {
        System.out.println("ToLower is handling");
        String ToConvert = req.getContentString(); //No clue what the text= signifies here ...


        System.out.println("ToConvert: " + ToConvert);
        Response resp = new ResponseManager();
        resp.setStatusCode(200);
        resp.setContentType("text/plain");
        //resp.setMethod(POST);
        if (ToConvert == null || ToConvert.equals(""))
            resp.setContent("Bitte geben Sie einen Text ein");
        else
            resp.setContent(ToConvert.toLowerCase());
        return resp;
    }
}
