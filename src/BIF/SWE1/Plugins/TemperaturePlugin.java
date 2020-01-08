package BIF.SWE1.Plugins;

import BIF.SWE1.Helpers.H2DB;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.sql.SQLException;

public class TemperaturePlugin implements Plugin
{
    private String PathReq;
    private String ID;

    @Override
    public float canHandle(Request req)
    {
        this.ID = req.getUrl().getRawUrl().substring(0, req.getUrl().getRawUrl().indexOf("/"));
        this.PathReq = req.getUrl().getPath();
        if (ID.equals("Temp"))
            return 0.9f;
        else
            return 0;
    }

    @Override
    public Response handle(Request req)
    {
        System.out.println("Temp Plugin is handling");
        System.out.println(PathReq);

        String year = this.PathReq.split("/")[1].split("-")[0];
        String month = this.PathReq.split("/")[1].split("-")[1];
        String day = this.PathReq.split("/")[1].split("-")[2];


        String date = this.PathReq.split("/")[1];
        try
        {
            String responseText = H2DB.select("select * from TEMPERATURE where DATE = '" + date + "';");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
