package BIF.SWE1;

import BIF.SWE1.interfaces.*;

import javax.print.attribute.standard.MediaSize;
import java.io.InputStream;
import java.util.*;

public class URLManager implements Url
{

    private String rawUrl;
    private HashMap<String, String> Parameters;
    private boolean hasParameters = false;

    URLManager(String a)
    {
        this.rawUrl = a;
        this.Parameters = new HashMap<>();
        if (rawUrl != null) {
            String[] AuxUrl = rawUrl.split("\\?");
            System.out.println(AuxUrl.length);
            if (AuxUrl.length == 1) {
                this.Parameters.put("", "");
                this.hasParameters = false; //redundant, I know, but eh
            } else {
                String[] OtherAuxUrl = AuxUrl[1].split("&");
                for (String str : OtherAuxUrl) {
                    this.Parameters.put(str.split("=")[0], str.split("=")[1]);
                }
                this.hasParameters = true;
            }
        }
    }


    @Override
    public String getRawUrl() {
        return this.rawUrl;
    }

    @Override
    public String getPath() {
        if (this.rawUrl == null)
            return "";
        String AuxUrl = this.rawUrl;


        return AuxUrl.split("\\?", 2)[0];
    }

    @Override
    public Map<String, String> getParameter() {
        return this.Parameters;
    }

    @Override
    public int getParameterCount() {
        if (!hasParameters)
            return 0;
        return this.Parameters.size();
    }

    @Override
    public String[] getSegments() {
        return new String[0];
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public String getExtension() {
        return null;
    }

    @Override
    public String getFragment() {
        return null;
    }
}
