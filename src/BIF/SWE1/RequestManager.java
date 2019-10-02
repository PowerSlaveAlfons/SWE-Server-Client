package BIF.SWE1;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class RequestManager implements Request
{

    private InputStream inStream;
    private boolean isValid = false;

    RequestManager(InputStream in)
    {
        this.inStream = in;

        Scanner s = new Scanner(in).useDelimiter("\\A"); //credits to StackOverFlow
        String result = s.hasNext() ? s.next() : "";
        System.out.println(result);
        if(result.charAt(0) == 'G') // THIS IS ABSOLUTELY NOT OKAY HOLY SHIT
            this.isValid = true;

    }

    @Override
    public boolean isValid()
    {
        return this.isValid;
    }

    @Override
    public String getMethod()
    {
        return null;
    }

    @Override
    public Url getUrl()
    {
        return null;
    }

    @Override
    public Map<String, String> getHeaders()
    {
        return null;
    }

    @Override
    public int getHeaderCount()
    {
        return 0;
    }

    @Override
    public String getUserAgent()
    {
        return null;
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
    public InputStream getContentStream()
    {
        return null;
    }

    @Override
    public String getContentString()
    {
        return null;
    }

    @Override
    public byte[] getContentBytes()
    {
        return new byte[0];
    }
}
