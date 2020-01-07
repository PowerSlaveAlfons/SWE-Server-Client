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

public class StaticFilePlugin implements Plugin
{
    private final String directory = "statics/";

    private String FileReq;
    private String PathReq;

    @Override
    public float canHandle(Request req)
    {
        String auxFile = req.getUrl().getPath().substring(1);
        String auxPath = "s" + auxFile;

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

    @Override
    public Response handle(Request req)
    {
        System.out.println("Static File Handling");

        if (this.FileReq.isEmpty())
            this.PathReq = directory + "index.html";
        else
            this.PathReq =  "s" +  FileReq;

        byte[] content = getContent();


        String fileType = PathReq.substring(PathReq.lastIndexOf("."));

        Response Resp = new ResponseManager();
        Resp.setContentType("file");
        Resp.setStatusCode(200);
        Resp.setContent(content);

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
