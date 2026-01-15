import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        System.out.println("Server.java");

        int port = 5000;
        if (args.length >= 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid port, using default: " + port);
            }
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server opened on port " + port);
            System.out.println(serverSocket.toString());

            Socket socket = serverSocket.accept();

            System.out.println("Connection established");
            System.out.println(socket.toString());

            try (InputStream input = socket.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(input))) {

                String line = br.readLine();
                if (line != null) {
                    System.out.println(line);
                }
            }


        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Server error", e);
        }
    }
}
