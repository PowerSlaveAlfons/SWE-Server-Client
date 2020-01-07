package BIF.SWE1.Plugins;

import BIF.SWE1.ResponseManager;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class ToLowerPlugin implements Plugin
{
    @Override
    public float canHandle(Request req)
    {
        String PathReq = req.getUrl().getRawUrl();
        if (PathReq.equals("ToLower"))
            return 0.9f;
        else
            return 0;
    }

    @Override
    public Response handle(Request req)
    {
        String ToConvert = req.getContentString().substring(9); //Yikes, this is a dirty hack

        System.out.println("ToConvert: " + ToConvert);
        Response resp = new ResponseManager();
        resp.setStatusCode(200);
        resp.setContentType("text");
        if (ToConvert.equals(""))
            resp.setContent("Bitte geben Sie einen Text ein");
        else
            resp.setContent(ToConvert.toLowerCase());
        return resp;
    }
}
