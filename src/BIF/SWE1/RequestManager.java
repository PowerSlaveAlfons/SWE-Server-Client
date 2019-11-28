package BIF.SWE1;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class RequestManager implements Request
{

    private static ArrayList validMethods = new ArrayList<>(List.of("GET", "HEAD", "PUT", "CONNECT", "OPTIONS", "TRACE",
            "POST", "PATCH", "DELETE"));
    private InputStream inStream;
    private boolean isValid;
    private Url url;
    private String Method;
    private Map<String, String> Headers;

    RequestManager(InputStream in)
    {
        this.inStream = in;

        this.Headers = new HashMap<>();


        Scanner s = new Scanner(this.inStream).useDelimiter("\\A"); //credits to StackOverFlow
        String result = s.hasNext() ? s.next() : "";
        System.out.println(result);

        String[] RequestLines = result.split("\\n");
        this.Method = RequestLines[0].split(" ")[0].toUpperCase();
        this.isValid = validMethods.contains(this.Method);

        if (result.split(" ").length > 1)
        {
            this.url = new URLManager(result.split(" ")[1]);
            this.parseHeaders(RequestLines);
        }
        else
            this.isValid = false;

    }

    @Override
    public boolean isValid()
    {
        return this.isValid;
    }

    @Override
    public String getMethod()
    {
        return this.Method;
    }

    @Override
    public Url getUrl()
    {
        return this.url;
    }

    @Override
    public Map<String, String> getHeaders()
    {
        return this.Headers;
    }

    @Override
    public int getHeaderCount()
    {
        for (Map.Entry<String, String> entry : this.Headers.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("Size:" + this.Headers.size());
        return this.Headers.size();
    }

    @Override
    public String getUserAgent()
    {
        return this.Headers.get("user-agent");
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
        return this.inStream;
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

    private void parseHeaders(String[] Content)
    {
        for (String str : Content)
        {
            if (str.split(":").length > 1)
                this.Headers.put(str.split(":")[0].toLowerCase(), str.split(":")[1].substring(1).toLowerCase());
        }
    }

}
