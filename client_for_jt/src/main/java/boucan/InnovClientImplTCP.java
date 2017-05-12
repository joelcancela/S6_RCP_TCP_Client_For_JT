package boucan;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nassim B on 04/05/17.
 */
public class InnovClientImplTCP extends InnovClient {
    private Socket socket; // Socket de connexion au client
    private ObjectOutputStream objectOutputStream; // Flux de sortie vers le client
    private ObjectInputStream objectInputStream;   // Flux d'entrée depuis le client
    private Scanner scanner = new Scanner(System.in);
    private String input;
    private List<String> ideas;

    public void start() {

        System.out.println("Client lancé");
        connect();
        send();

    }

    @Override
    protected String parseInput() {
        return null;
    }

    @Override
    protected String inputProject() {
        return null;
    }

    @Override
    public void processResponse() {

    }

    public InnovClientImplTCP(String ip, int port) {
        super(ip, port);
    }

    protected void connect() {
        try {
            this.socket = new Socket(this.ip, this.port);
            System.out.println("Connected to "+ip+":"+port);
            this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            try {
                System.out.println(((String)objectInputStream.readObject()));
            } catch (ClassNotFoundException e) {
            }
            System.out.printf("###> ");
        } catch (IOException e) {
            System.out.println("Connection refused");
        }
    }

    protected String getInput() {
        this.input = this.scanner.nextLine();
        return this.input;
    }

    protected void send() {
        while (this.getInput() != null) {

            try {
                toSend = this.parseInput();
                this.objectOutputStream.writeObject(toSend);
                this.processResponse();
                if (toSend instanceof String) break;
                System.out.printf("###>");
            } catch (UnrecognizedCommandException e) {
                System.out.println("Sorry, this command is undefined");
                System.out.printf("###>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
