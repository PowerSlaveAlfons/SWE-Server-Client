package BIF.SWE1;

import BIF.SWE1.interfaces.*;

import javax.print.attribute.standard.MediaSize;
import java.io.InputStream;
import java.util.*;

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



    @Override
    public String getRawUrl()
    {
        return this.rawUrl;
    }


    @Override
    public String getPath()
    {
        if (this.rawUrl == null)
            return "";
        return this.Path;
    }

    @Override
    public Map<String, String> getParameter()
    {
        return this.Parameters;
    }

    @Override
    public int getParameterCount()
    {
        if (!hasParameters)
            return 0;
        return this.Parameters.size();
    }

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

    @Override
    public String getFileName()
    {
        if (this.Segments != null)
            this.FileName =  this.Segments[this.Segments.length-1];
        else this.FileName = "";
        return this.FileName;
    }

    @Override
    public String getExtension()
    {
        if (this.FileName != null && this.FileName.split("\\.").length > 1)
            this.Extension = this.FileName.split("\\.")[1];
        else this.Extension = "";
        return this.Extension;
    }

    @Override
    public String getFragment()
    {
        return this.Fragment;
    }
}
