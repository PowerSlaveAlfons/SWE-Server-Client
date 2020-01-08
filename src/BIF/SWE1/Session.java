package BIF.SWE1;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.net.Socket;

/**
 * One Session per connection makes this thing usable by multiple users at the same time
 */
public class Session implements Runnable{
    private Socket clientSocket;
    private final PluginManagerCreator Pluggy;

    /**
     * @param clientSocket A Session ist started by giving it a socket that represents the client.
     */
    Session(Socket clientSocket, PluginManagerCreator PlugIn) {
        this.clientSocket = clientSocket;
        this.Pluggy = PlugIn;
        System.out.println("Constructor called");
    }

    /**
     * Handles Requests and Responses
     */
    @Override
    public void run() {
        try{

            // send 404 Response
                /*String mainPage = "<!DOCTYPE html>\n<html>\n<head>\n<title>SWE Webserver</title>\n\n</head>\n<body>\n\n<h1>No Plugin found!</h1>\n<p>You tried to use a unknown plugin!</p>";
                Response response = new ResponseManager();
                response.setStatusCode(404);
                response.setContent(mainPage);
                response.send(clientSocket.getOutputStream());
                System.out.println("LOG: 404 send");*/

                // get HTTP-Request by InputStream of the clientSocket
            System.out.println("LOG " + this.hashCode() + ": received a request.");
                Request request = new RequestManager(clientSocket.getInputStream());
            System.out.println("LOG " + this.hashCode() + ": handled a request.");

                // iterate through mountedPlugins and validate which can handle the response
                Plugin plugin = this.Pluggy.getBestHandlePlugin(request);
                // System.out.println("LOG " + this.hashCode() + ": working plugin: " + plugin);


                if (plugin != null) {
                    // get response-string of IPlugin and send it
                    plugin.handle(request).send(clientSocket.getOutputStream());
                    System.out.println("LOG " + this.hashCode() + ": response send.");
                }


            clientSocket.close();
            System.out.println("LOG: close socket");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}