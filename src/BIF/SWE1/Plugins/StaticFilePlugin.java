package BIF.SWE1.Plugins;

import BIF.SWE1.ResponseManager;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Takes a request with an URL ('/statics/...') and returns the file found at that URL; If no file was found
 * it returns the index.html
 */
public class StaticFilePlugin implements Plugin
{
    private final String directory = "statics/";

    private String FileReq;
    private String PathReq;

    /**
     * If a file can be found at the path specified by the Request, it feels suitable for handling
     * that request.
     * @param req The Request
     * @return canHandle score
     */
    @Override
    public float canHandle(Request req)
    {
        String auxFile = req.getUrl().getPath().substring(1);
        String auxPath = auxFile;

        File file = new File(auxPath);
        System.out.println("Path is " + auxPath);

        // on successful find, return 0.5f
        if (file.isFile() || auxFile.isEmpty()) {
            this.FileReq = auxFile;
            this.PathReq = auxPath;
            return 0.5f;
        } else {
            return 0.0f;
        }
    }

    /**
     * Returns the file at the URL of the request. Also sets content-type.
     * If it can't find a file, it returns the index.html
     * @param req The Request
     * @return Response
     */
    @Override
    public Response handle(Request req)
    {
        System.out.println("Static File Handling");

        if (this.FileReq.isEmpty())
            this.PathReq = directory + "index.html";
        else
            this.PathReq =  FileReq;

        byte[] content = getContent();


        String fileType = PathReq.substring(PathReq.lastIndexOf("."));

        Response Resp = new ResponseManager();

        Resp.setStatusCode(200);
        Resp.setContent(content);
        if (req.getUrl().getExtension().equals("html"))
            Resp.setContentType("text/html");
        else
            Resp.setContentType("file");

        return Resp;
    }

    private byte[] getContent() {
        try {
            Path fileLocation = Paths.get(PathReq);
            return Files.readAllBytes(fileLocation);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new byte[0];
    }
}
