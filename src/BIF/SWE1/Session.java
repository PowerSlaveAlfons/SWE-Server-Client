package BIF.SWE1;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.net.Socket;

/**
 * A Session represents one client-server connection and is used for multithreading.
 */
public class Session implements Runnable{
    private Socket clientSocket;

    /**
     * @param clientSocket A Session ist started by giving it a socket that represents the client.
     */
    Session(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Receive the HTTP request and responses it.
     */
    @Override
    public void run() {
        try{
                // send 404 Response
                String mainPage = "<!DOCTYPE html>\n<html>\n<head>\n<title>SWE Webserver</title>\n\n</head>\n<body>\n\n<h1>No Plugin found!</h1>\n<p>You tried to use a unknown plugin!</p>";
                Response response = new ResponseManager();
                response.setStatusCode(404);
                response.setContent(mainPage);
                response.send(clientSocket.getOutputStream());
                System.out.println("LOG: 404 send");


            clientSocket.close();
            System.out.println("LOG: close socket");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}