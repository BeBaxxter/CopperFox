import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Chatterbox {

    public static void main (String args[]) throws IOException {

        Chatterbox chatterbox = new Chatterbox();
        Socket socket = chatterbox.createConnectionToServer("2001:16b8:4570:5800:91f9:3143:2bcd:d7ca", 8050);


        /**
         * runnable for waitForInputFromClient
         **/
        Runnable RunnableUserInput = new Runnable() {
            @Override
            public void run() {
                chatterbox.waitForInputFromClient(socket);
            }
        };

        /**
         * runnable for readMessagesFromServer
         */
        Runnable RunnableReadMessageFromServer = new Runnable() {
            @Override
            public void run() {
                try {
                    chatterbox.readMessagesFromServer(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        /**
         *  Scheduler that created threads and call the runnable
         */
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(RunnableUserInput, 0, 200, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(RunnableReadMessageFromServer, 0, 200, TimeUnit.MILLISECONDS);

    }

    /**
     * Creates a new connection to given host, port
     * @param host url of target
     * @param port of target
     *
     * @return Socket, if connection was successful
     * @return null if connection was not successful
     */
    public Socket createConnectionToServer(String host, int port) {

        try {
            System.out.println("Create new connection, please wait...");
            Socket socket = new Socket(host, port);
            System.out.println("New connection created...");

            return socket;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * wait for input from client
     * @param socket object
     */
    public void waitForInputFromClient(Socket socket) {
        System.out.println("Use the console to send messages.");
        Scanner scanner = new Scanner(System.in);
        try {
            String line = scanner.nextLine();
            try {
                writeMessageToSocket(socket, line);
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * writes a message to given socket
     * @param socket object
     * @param line written line
     */
    public void writeMessageToSocket(Socket socket, String line) throws IOException {

        OutputStream outStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream);
        out.print(line + "\n");
        out.flush();
        System.out.println("Message was send");
    }

    /**
     * reads messages that are send from server
     * @param socket object
     * @throws IOException
     */
    public void readMessagesFromServer(Socket socket) throws IOException {
        InputStream inStream = socket.getInputStream();
        Scanner inScanner = new Scanner(inStream);

        System.out.println(inScanner.nextLine());
    }

}