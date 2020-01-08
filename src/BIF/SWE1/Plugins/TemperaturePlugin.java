package BIF.SWE1.Plugins;

import BIF.SWE1.Helpers.H2DB;
import BIF.SWE1.Helpers.Sensor;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.sql.SQLException;

/**
 * Plugin to handle a temperature history.
 */

public class TemperaturePlugin implements Plugin
{
    private String PathReq;
    private String ID;
    private Sensor sensor;

    /**
     * Constructor to start a new Sensor to automatically periodically create and save Temperature data
     */
    public TemperaturePlugin()
    {
        this.sensor = new Sensor();
        //This should only execute once in order to only have one sensor running
        Thread th = new Thread(sensor);
        th.setName("Temperature");
        th.start();
    }

    /**
     * If it can find Temp at the beginning of the Request URL, it's going to handle that request.
     * @param req The Request
     * @return canHandle score
     */
    @Override
    public float canHandle(Request req)
    {
       //this.ID = req.getUrl().getRawUrl().substring(0, req.getUrl().getRawUrl().indexOf("/"));
        this.PathReq = req.getUrl().getPath().substring(1);
        if (PathReq.startsWith("Temp"))
            return 0.9f;
        else
            return 0;
    }

    /**
     * Querys the database for a given (REST?) URL to check if there's a temperature value for the
     * given date
     * @param req The Request
     * @return Response
     */
    @Override
    public Response handle(Request req)
    {
        System.out.println("Temp Plugin is handling");
        System.out.println(PathReq);
        String date = "";
        if(this.PathReq.split("/").length > 1)
            date = this.PathReq.split("/")[1];
        try
        {
            String responseText = H2DB.select("select * from TEMPERATURE where DATE = '" + date + "';");
            System.out.println(responseText);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }


        return null;
    }
}
