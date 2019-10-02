package BIF.SWE1;

import BIF.SWE1.interfaces.Response;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ResponseManager implements Response
{
    private int StatusCode = -1;
    private Map<String, String> Headers;
    @Override
    public Map<String, String> getHeaders()
    {
        return this.Headers;
    }

    @Override
    public int getContentLength()
    {
        return 0;
    }

    @Override
    public String getContentType()
    {
        return null;
    }

    @Override
    public void setContentType(String contentType)
    {

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
                return "404 NOT FOUND";
            case 500:
                return "500 INTERNAL SERVER ERROR";
        }
        return "";
    }

    @Override
    public void addHeader(String header, String value)
    {
        if (this.Headers == null)
        {
            this.Headers = new HashMap<>();
        }
        this.Headers.put(header, value);
    }

    @Override
    public String getServerHeader()
    {
        return null;
    }

    @Override
    public void setServerHeader(String server)
    {

    }

    @Override
    public void setContent(String content)
    {

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

    }
}
