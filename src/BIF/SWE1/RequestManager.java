package BIF.SWE1;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Implements the Request Interface and handles all things Request
 */

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
    private BufferedReader reader;
    private InputStreamReader InReader;

    /**
     * Constructor
     * @param in InputStream
     */
    RequestManager(InputStream in)
    {

        String result = "";
        String strCurrentLine;
        this.inStream = in;
        this.Headers = new HashMap<>();
        this.InReader = new InputStreamReader(in);
        reader = new BufferedReader(this.InReader);
        System.out.print("MEMES");
        try{ while ((strCurrentLine = this.reader.readLine()) != null) {
            result += strCurrentLine + "\r\n";}
            System.out.print("MEMES2");
        }
        catch(IOException e) {System.out.println(e.getMessage());};

        System.out.print("Memes escaped");

       // Scanner s = new Scanner(this.inStream).useDelimiter("\\A"); //credits to StackOverFlow
       // String result = s.hasNext() ? s.next() : "";
        //System.out.println(result);


        if (result.split("\\n\\s*\\n", 2).length > 1)
        {
            this.Content = result.split("\\n\\s*\\n", 2)[1];
        }

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

    /**
     * Returns if the Request is valid or not
     * @return boolean
     */
    @Override
    public boolean isValid()
    {
        return this.isValid;
    }

    /**
     * Returns the Method of the Request
     * @return String
     */
    @Override
    public String getMethod()
    {
        return this.Method;
    }

    /**
     * Returns the URL of the Request
     * @return URL
     */
    @Override
    public Url getUrl()
    {
        return this.url;
    }

    /**
     * Returns all headers in a Map
     * @return Map<String, String>
     */

    @Override
    public Map<String, String> getHeaders()
    {
        return this.Headers;
    }

    /**
     * Returns how many headers are in a request
     * @return int
     */

    @Override
    public int getHeaderCount()
    {
        return this.Headers.size();
    }

    /**
     * Returns the User-Agent, as in who created the Request
     * @return String
     */

    @Override
    public String getUserAgent()
    {
        return this.Headers.get("user-agent");
    }

    /**
     * Returns the length of the content
     * @return int
     */

    @Override
    public int getContentLength()
    {
        return this.ContentLength;
    }

    /**
     * Returns the type of the content the request needs in order to be displayed by a browser
     * @return String
     */

    @Override
    public String getContentType()
    {
        return this.ContentType;
    }

    /**
     * Returns a Stream of the Content
     * @return ByteArrayInputStream
     */

    @Override
    public InputStream getContentStream()
    {
        return new ByteArrayInputStream(this.Content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Returns the Content in form of a String
     * @return String
     */

    @Override
    public String getContentString()
    {
        return this.Content;
    }

    /**
     * Returns the Content in form of a ByteArray
     * @return ByteArray
     */

    @Override
    public byte[] getContentBytes()
    {
        return Content.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Parses the headers for internal use
     * @param Content The Content
     */

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
                //this.Content += str;
            }

        }

    }

}
