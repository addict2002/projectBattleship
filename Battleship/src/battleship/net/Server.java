/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.net;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andri
 */
public class Server {
    public Connection aConnection;

    ServerSocket serverSocket;
    
    public Server(Connection aConnection) {
        this.aConnection=aConnection;
    }

    public void runServer() throws IOException {
        //find free port
        int errCounter = 0;
        while (serverSocket == null && errCounter++ < 800) {
            try {
                serverSocket = new ServerSocket(aConnection.port);
            } catch (IOException ex) {
                aConnection.port++;
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (serverSocket == null) {
            throw new IOException();
        }
        
        
        Thread serverThread = new Thread(new NetworkService(aConnection, serverSocket));
        System.out.println("Start NetworkService(Multiplikation), Thread: " + Thread.currentThread());
        serverThread.start();
        aConnection.running=true;
        //reagiert auf Strg+C, der Thread(Parameter) darf nicht gestartet sein
        Runtime.getRuntime().addShutdownHook(
            new Thread() {
                public void run() {
                    System.out.println("Strg+C, pool.shutdown");
                    aConnection.interrupt();
                    
                }
            }
        );
    }
}

//Thread bzw. Runnable zur Entgegennahme der Client-Anforderungen
class NetworkService implements Runnable { 

    private final ServerSocket serverSocket;
    private Connection aConnection;
    
    public NetworkService(Connection aConnection, ServerSocket serverSocket) {
        this.aConnection=aConnection;
        this.serverSocket = serverSocket;
    }

    public void run() { // run the service
        try {
            //Endlos-Schleife: warte auf Client-Anforderungen
            //Abbruch durch Strg+C oder Client-Anforderung 'Exit',
            //dadurch wird der ServerSocket beendet, was hier zu einer IOException
            //führt und damit zum Ende der run-Methode mit vorheriger Abarbeitung der
            //finally-Klausel.
//            while (true) {
                /*
                 Zunächst wird eine Client-Anforderung entgegengenommen(accept-Methode).
                 Der ExecutorService pool liefert einen Thread, dessen run-Methode
                 durch die run-Methode der Handler-Instanz realisiert wird.
                 Dem Handler werden als Parameter übergeben:
                 der ServerSocket und der Socket des anfordernden Clients.
                 */
                aConnection.socket = serverSocket.accept();  //warten auf Client-Anforderung

                //starte den Handler-Thread zur Realisierung der Client-Anforderung
                
                
                
                aConnection.messageListener=new MessageListener(aConnection);
                aConnection.messageSender=new MessageSender(aConnection);
                aConnection.messageListener.start();
                aConnection.messageSender.start();
                
//            }
        } catch (IOException ex) {
            System.out.println("--- Interrupt NetworkService-run");
        } finally {
            System.out.println("--- Ende NetworkService(pool.shutdown)");
            
        }
    }
}
