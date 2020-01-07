package BIF.SWE1;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RequestManager implements Request
{

    private static ArrayList validMethods = new ArrayList<>(List.of("GET", "HEAD", "PUT", "CONNECT", "OPTIONS", "TRACE",
            "POST", "PATCH", "DELETE"));
    private InputStream inStream;
    private boolean isValid;
    private Url url;
    private String Method;
    private String ContentType;
    private Map<String, String> Headers;
    private int ContentLength;
    private String Content;

    RequestManager(InputStream in)
    {

        this.inStream = in;
        this.Headers = new HashMap<>();


        Scanner s = new Scanner(this.inStream).useDelimiter("\\A"); //credits to StackOverFlow
        String result = s.hasNext() ? s.next() : "";
        //System.out.println(result);

        String[] RequestLines = result.split("\\n");
        for (String str : RequestLines)
            System.out.println(str);
        this.Method = RequestLines[0].split(" ")[0].toUpperCase();
        this.isValid = validMethods.contains(this.Method);

        if (result.split(" ").length > 1)
        {
            this.url = new URLManager(result.split(" ")[1]);
            this.parseHeaders(RequestLines);
        }
        else
            this.isValid = false;
        if (this.Headers.containsKey("content-length"))
        {
            this.ContentLength = Integer.parseInt(this.Headers.get("content-length"));
        }
        if (this.Headers.containsKey("content-type"))
        {
            this.ContentType = this.Headers.get("content-type");
        }

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
        return this.ContentLength;
    }

    @Override
    public String getContentType()
    {
        return this.ContentType;
    }

    @Override
    public InputStream getContentStream()
    {
        return new ByteArrayInputStream(this.Content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String getContentString()
    {
        return this.Content;
    }

    @Override
    public byte[] getContentBytes()
    {
        return new byte[0];
    }

    private void parseHeaders(String[] Content)
    {
        boolean isContent = false;
        for (String str : Content)
        {
            if (!isContent) {
                if (str.split(":").length > 1)
                    this.Headers.put(str.split(":")[0].toLowerCase(), str.split(":")[1].trim().toLowerCase());
                if (str.trim().isEmpty())
                    isContent = true;
            }
            else
            {
                System.out.println("I've reached the body");
                this.Content += str;
            }

        }

    }

}
