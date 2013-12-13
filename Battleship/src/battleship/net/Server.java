/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andri
 */
public class Server {
    private Connection aConnection;
    private static Server theServer;

    ServerSocket serverSocket;
    int port;
    ExecutorService threadPool;


    public Server(int port) {
        this.port = port;
    }

    public void runServer() throws IOException {
        //find free port
        int errCounter = 0;
        while (serverSocket == null && errCounter++ < 100) {
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException ex) {
                port++;
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (serverSocket == null) {
            throw new IOException();
        }
        int poolSize = 2;
        threadPool = Executors.newFixedThreadPool(poolSize);
        Thread serverThread = new Thread(new NetworkService(threadPool, serverSocket));
        System.out.println("Start NetworkService(Multiplikation), Thread: " + Thread.currentThread());
        serverThread.start();

        //reagiert auf Strg+C, der Thread(Parameter) darf nicht gestartet sein
        Runtime.getRuntime().addShutdownHook(
            new Thread() {
                public void run() {
                    System.out.println("Strg+C, pool.shutdown");
                    threadPool.shutdown();  //keine Annahme von neuen Anforderungen
                    try {
                        //warte maximal 4 Sekunden auf Beendigung aller Anforderungen
                        threadPool.awaitTermination(4L, TimeUnit.SECONDS);
                        if (!serverSocket.isClosed()) {
                            System.out.println("ServerSocket close");
                            serverSocket.close();
                        }
                    } catch (IOException ex) {
                    } catch (InterruptedException ei) {
                    }
                }
            }
        );
    }
}

//Thread bzw. Runnable zur Entgegennahme der Client-Anforderungen
class NetworkService implements Runnable { 

    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkService(ExecutorService pool,
            ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.pool = pool;
    }

    public void run() { // run the service
        try {
            //Endlos-Schleife: warte auf Client-Anforderungen
            //Abbruch durch Strg+C oder Client-Anforderung 'Exit',
            //dadurch wird der ServerSocket beendet, was hier zu einer IOException
            //führt und damit zum Ende der run-Methode mit vorheriger Abarbeitung der
            //finally-Klausel.
            while (true) {
                /*
                 Zunächst wird eine Client-Anforderung entgegengenommen(accept-Methode).
                 Der ExecutorService pool liefert einen Thread, dessen run-Methode
                 durch die run-Methode der Handler-Instanz realisiert wird.
                 Dem Handler werden als Parameter übergeben:
                 der ServerSocket und der Socket des anfordernden Clients.
                 */
                Socket clientSocket = serverSocket.accept();  //warten auf Client-Anforderung

                //starte den Handler-Thread zur Realisierung der Client-Anforderung
                pool.execute(new MessageHandler(serverSocket, clientSocket));
            }
        } catch (IOException ex) {
            System.out.println("--- Interrupt NetworkService-run");
        } finally {
            System.out.println("--- Ende NetworkService(pool.shutdown)");
            pool.shutdown();  //keine Annahme von neuen Anforderungen
            try {
                //warte maximal 4 Sekunden auf Beendigung aller Anforderungen
                pool.awaitTermination(4L, TimeUnit.SECONDS);
                if (!serverSocket.isClosed()) {
                    System.out.println("--- Ende NetworkService:ServerSocket close");
                    serverSocket.close();
                }
            } catch (IOException e) {
            } catch (InterruptedException ei) {
            }
        }
    }
}



//Thread bzw. Runnable zur Realisierung der Client-Anforderungen
class MessageHandler implements Runnable {  //oder 'extends Thread'

    private final Socket client;
    private final ServerSocket serverSocket;
    private Connection connection;
    MessageHandler(ServerSocket serverSocket, Socket client) { //Server/Client-Socket
        this.client = client;
        this.serverSocket = serverSocket;
    }

    public void run() {
        StringBuffer sb = new StringBuffer();
        PrintWriter out = null;
        try {
            // read and service request on client
            System.out.println("running service, " + Thread.currentThread());
            out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader bufferedReader
                    = new BufferedReader(
                            new InputStreamReader(
                                    client.getInputStream()));
            char[] buffer = new char[100];
            int anzahlZeichen = bufferedReader.read(buffer, 0, 100); // blockiert bis Nachricht empfangen
            String message = new String(buffer, 0, anzahlZeichen);
            String[] werte = message.split("\\s");  //Trennzeichen: whitespace
            if (werte[0].compareTo("Exit") == 0) {
                out.println("Server ended");
                if (!serverSocket.isClosed()) {
                    System.out.println("--- Ende Handler:ServerSocket close");
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                    }
                }
            } else {  //normale Client-Anforderung
                for (int i = 0; i < werte.length; i++) {
                    String rt = getWday(werte[i]);  //ermittle den Wochentag
                    sb.append(rt + "\n");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        } catch (IOException e) {
            System.out.println("IOException, Handler-run");
        } finally {
            out.println(sb);  //Rückgabe Ergebnis an den Client
            if (!client.isClosed()) {
                System.out.println("****** Handler:Client close");
                try {
                    client.close();
                } catch (IOException e) {
                }
            }
        }
    }  //Ende run

    String getWday(String s) {  //Datum mit Wochentag
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd.MM.yyyy");
        String res = "";
        try {
            //Parameter ist vom Typ Date
            res = sdf.format(DateFormat.getDateInstance().parse(s));
        } catch (ParseException p) {
        }
        return res;
    }
}
