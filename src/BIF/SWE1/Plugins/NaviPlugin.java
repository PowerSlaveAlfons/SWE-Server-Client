package BIF.SWE1.Plugins;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

/**
 * Does a whole lot of nothing so far.
 */
public class NaviPlugin implements Plugin
{
    @Override
    public float canHandle(Request req)
    {
        return 0;
    }

    @Override
    public Response handle(Request req)
    {
        return null;
    }
}
