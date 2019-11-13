package BIF.SWE1;

import BIF.SWE1.interfaces.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ResponseManager implements Response
{
    private int StatusCode = -1;
    private Map<String, String> Headers;
    private String ContentType;
    private String[] ContentLines;
    private boolean hasContent = false;
    private String ServerHeader = "BIF-SWE1-Server";

    ResponseManager()
    {
        this.Headers = new HashMap<>();
    }

    @Override
    public Map<String, String> getHeaders()
    {
        return this.Headers;
    }

    @Override
    public int getContentLength()
    {
        if(!this.hasContent)
            return 0;
        return this.ContentLines.length;
    }

    @Override
    public String getContentType()
    {
        return this.ContentType;
    }

    @Override
    public void setContentType(String contentType)
    {
        this.ContentType = contentType;
    }

    @Override
    public int getStatusCode()
    {
        if (this.StatusCode == -1)
            throw new IllegalArgumentException();
        else return this.StatusCode;
    }

    @Override
    public void setStatusCode(int status)
    {
        this.StatusCode = status;
    }

    @Override
    public String getStatus()
    {
        switch (this.StatusCode)
        {
            case 200:
                return "200 OK";
            case 404:
                return "404 Not Found";
            case 500:
                return "500 INTERNAL SERVER ERROR";
            default:
                return "";
        }

    }

    @Override
    public void addHeader(String header, String value)
    {
        this.Headers.put(header, value);
    }

    @Override
    public String getServerHeader()
    {
        return this.ServerHeader;
    }

    @Override
    public void setServerHeader(String server)
    {
        this.ServerHeader = server;
    }

    @Override
    public void setContent(String content)
    {
        this.ContentLines = content.split("\\n");
        this.hasContent = true;
    }

    @Override
    public void setContent(byte[] content)
    {

    }

    @Override
    public void setContent(InputStream stream)
    {

    }

    @Override
    public void send(OutputStream network)
    {
        if (this.StatusCode != 404 && !this.hasContent)
            throw new IllegalArgumentException(); //What even is this meme and why does it work
        OutputStreamWriter writer = new OutputStreamWriter(network, StandardCharsets.UTF_8);
        try
        {
            writer.write("HTTP/1.1 " + this.getStatus() + "\n");
            if (this.hasContent)
            {
                for (String str : this.ContentLines)
                {
                    writer.write(str);
                }
                writer.close();
            }
            else throw new IOException();
        }
        catch (IOException exec)
        {
           System.out.println("Error writing response.");
        }
        finally
        {
            try
            {
             writer.close();
            }
            catch (IOException exec)
            {
                System.out.println("DAS IS DOCH FALSCH OIDA");
            }
        }


    }

}
