package BIF.SWE1;

import BIF.SWE1.interfaces.Response;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements the Response interface and handles all things Response
 */
public class ResponseManager implements Response
{
    private int StatusCode = -1;
    private Map<String, String> Headers;
    private String ContentType;
    private String[] ContentLines;
    private boolean hasContent = false;
    private String ServerHeader = "BIF-SWE1-Server";
    private byte[] ContentByteArray;


    /**
     * Constructor
     */
    public ResponseManager()
    {
        this.Headers = new HashMap<>();
    }

    /**
     * Returns all saved headers
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> getHeaders()
    {
        return this.Headers;
    }

    /**
     * Returns length of the saved content
     * @return int
     */
    @Override
    public int getContentLength()
    {
        if(!this.hasContent)
            return 0;
        return this.ContentByteArray.length;
    }

    /**
     * Returns type of the saved content
     * @return String
     */
    @Override
    public String getContentType()
    {
        return this.ContentType;
    }

    /**
     * Sets the content type of the response.
     * @param contentType
     */
    @Override
    public void setContentType(String contentType)
    {
        this.ContentType = contentType;
    }

    /**
     * Returns the Status Code of the response as an int
     * @return int
     */
    @Override
    public int getStatusCode()
    {
        if (this.StatusCode == -1)
            throw new IllegalArgumentException();
        else return this.StatusCode;
    }

    /**
     * Sets the Status code of the response
     * @param status
     */
    @Override
    public void setStatusCode(int status)
    {
        this.StatusCode = status;
    }

    /**
     * Returns the Status code of the response as a String (to Display in the browser)
     * @return String
     */
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

    /**
     * Adds a Header with type and value
     * @param header Left side of the header (Type of header)
     * @param value Right side of the header (Value of header)
     */
    @Override
    public void addHeader(String header, String value)
    {
        this.Headers.put(header, value);
    }

    /**
     * Returns the Header of type Server
     * @return String
     */
    @Override
    public String getServerHeader()
    {
        return this.ServerHeader;
    }

    /**
     * Sets the Header of type server
     * @param server Name of the server
     */
    @Override
    public void setServerHeader(String server)
    {
        this.ServerHeader = server;
    }

    /**
     * Sets Content of the Response
     * @param content Content in shape of a String
     */
    @Override
    public void setContent(String content)
    {
        this.ContentLines = content.split("\\n");
        this.ContentByteArray = content.getBytes(StandardCharsets.UTF_8);
        this.hasContent = true;
        System.out.println("Length: " + this.ContentByteArray.length);
        if(this.ContentType == null)
            this.ContentType = "text/plain";
    }

    /**
     * Sets Content of the Response
     * @param content Content in shape of a ByteArray
     */
    @Override
    public void setContent(byte[] content)
    {
        this.ContentByteArray = content;
        this.hasContent = true;
        this.ContentType = "application/octet-stream";
    }

    /**
     * Sets Content of the Response
     * @param stream Content in shape of an InputStream
     */
    @Override
    public void setContent(InputStream stream)
    {
        try
        {
            this.ContentByteArray = stream.readAllBytes();
        }
        catch (IOException e)
        {
            System.out.println("Error reading from stream");
        }
        this.hasContent = true;
        this.ContentType = "stream";
    }

    /**
     * Sends the Response
     * @param network Stream to send the response to
     */
    @Override
    public void send(OutputStream network) {
        if ((!this.hasContent && this.ContentType != null) || (this.hasContent && this.ContentType == null))
            throw new IllegalStateException("ContentType and/or Content nonsense.");

        try (OutputStreamWriter writer = new OutputStreamWriter(network, StandardCharsets.UTF_8)) {
            writer.write("HTTP/1.1 " + this.getStatus() + "\n");
            writer.write("Server: " + this.getServerHeader() + "\n");
            for (Map.Entry<String,String> entry : this.Headers.entrySet()) {
                writer.write(entry.getKey() + (": ") + (entry.getValue()) + ("\n"));
            }
            writer.write(getServerHeader() + "\n");
            writer.write("\n");
            if (this.hasContent && this.ContentType.contains("text")) {
                for (String str : this.ContentLines) {
                    writer.write(str);
                }
                writer.write("\n");
                writer.close();
            }
            else if (this.hasContent && this.ContentType.contains("file"))
            {
                for (byte b : this.ContentByteArray)
                {
                    writer.write(b);
                }
                writer.close();
            }
            else if (this.hasContent)
            {
                network.write(this.ContentByteArray);
                writer.close();
            }
            else throw new IOException();
        } catch (IOException exec) {
            System.out.println("Error writing response." + exec);
        }



    }

}
