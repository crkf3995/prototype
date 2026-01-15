import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {
        System.out.println("Client.java");

        String host = "127.0.0.1";
        int port = 5000;

        if (args.length >= 2) {
            host = args[0];
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid port, using default: " + port);
            }
        }

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connection established");
            System.out.println(socket.toString());

            try (OutputStream output = socket.getOutputStream();
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output))) {

                bw.write("Hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo Server :)");
                bw.newLine();
                bw.flush();
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Client error", e);
        }
    }
}
