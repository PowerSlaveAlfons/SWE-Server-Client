package BIF.SWE1;

import BIF.SWE1.interfaces.*;

import javax.print.attribute.standard.MediaSize;
import java.io.InputStream;
import java.util.*;

/**
 * An URL object, takes care of seperating the URL into it's fragments, paths, folders and whatnot
 */

public class URLManager implements Url
{

    private String rawUrl;
    private String Path;
    private String Fragment;
    private String FileName;
    private String Extension;
    private HashMap<String, String> Parameters;
    private String[] Segments;
    private boolean hasParameters = false;
    private boolean hasFragment = false;

    /**
     * Constructor, when creating the URL, a lot of the seperating is already being done
     * @param rawUrlIn The URL as a String
     */
    URLManager(String rawUrlIn)
    {
        this.rawUrl = rawUrlIn;
        this.Parameters = new HashMap<>();
        if (this.rawUrl != null)
        {
            String[] AuxUrl = this.rawUrl.split("\\?");
            if (AuxUrl.length == 1)
            {
                this.Parameters.put("", "");
                this.hasParameters = false; //redundant, I know, but eh0
            }
            else
            {
                String[] OtherAuxUrl = AuxUrl[1].split("&");
                for (String str : OtherAuxUrl)
                {
                    this.Parameters.put(str.split("=")[0], str.split("=")[1]);
                }
                this.hasParameters = true;
            }
            AuxUrl = rawUrl.split("#");
            if (AuxUrl.length == 2)
            {
                this.Fragment = AuxUrl[1];
            }
            this.Path = AuxUrl[0].split("\\?")[0];
            this.getSegments();
            this.getFileName();
            this.getExtension();
        }
    }

    /**
     * Returns the raw URL
     * @return String
     */

    @Override
    public String getRawUrl()
    {
        return this.rawUrl;
    }

    /**
     * Returns the Path, for example if a file is in a folder
     * @return String
     */
    @Override
    public String getPath()
    {
        if (this.rawUrl == null)
            return "";
        return this.Path;
    }

    /**
     * Returns all parameters
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> getParameter()
    {
        return this.Parameters;
    }

    /**
     * Returns the number of Parameters
     * @return int
     */
    @Override
    public int getParameterCount()
    {
        if (!hasParameters)
            return 0;
        return this.Parameters.size();
    }

    /**
     * Returns all Segments as a String Array.
     * @return String[]
     */
    @Override
    public String[] getSegments()
    {
        if (this.Segments == null)
        {
            String AuxString = this.Path.substring(1);

            this.Segments = AuxString.split("/");
        }
        return this.Segments;
    }

    /**
     * Returns the filename
     * @return String
     */
    @Override
    public String getFileName()
    {
        if (this.Segments != null)
            this.FileName =  this.Segments[this.Segments.length-1];
        else this.FileName = "";
        return this.FileName;
    }

    /**
     * Returns the extension
     * @return String
     */
    @Override
    public String getExtension()
    {
        if (this.FileName != null && this.FileName.split("\\.").length > 1)
            this.Extension = this.FileName.split("\\.")[1];
        else this.Extension = "";
        return this.Extension;
    }

    /**
     * Returns the Fragment
     * @return String
     */
    @Override
    public String getFragment()
    {
        return this.Fragment;
    }
}
