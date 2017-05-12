package boucan;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nassim B on 04/05/17.
 */
public class InnovClientImplTCP extends InnovClient {
    private Socket socket; // Socket de connexion au serveur
    //private ObjectOutputStream objectOutputStream; // Flux de sortie vers le client
    private PrintWriter out;
    //private ObjectInputStream objectInputStream;   // Flux d'entrée depuis le client
    private BufferedReader in;
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
            System.out.println("IP : " + this.ip);
            System.out.println("Port : " + this.port);
            this.socket = new Socket(this.ip, this.port);
            System.out.println("Connected to "+ip+":"+port);
            //this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.out = new PrintWriter(socket.getOutputStream(),true);
            //this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //    System.out.println(((String)objectInputStream.readObject()));
                this.out.write(new String("AINI 1.0\n" +
                        "ADD IDEA\n" +
                        "BEGIN\n" +
                        "{\"desc\":\"desc idea\", \"idStudent\":\"idStudent\",\"mailStudent\":\"email\",\"idIdea\":\"IDidea\"}\n" +
                        "END"));
            //System.out.println(this.in.readLine());

            System.out.printf("###> ");
        } catch (IOException e) {
            e.printStackTrace();
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
               // toSend = this.parseInput();
                this.out.write(this.input);
                System.out.println(this.in.readLine());
                //this.processResponse();
                //if (toSend instanceof String) break;
                //System.out.printf("###>");
            } catch (UnrecognizedCommandException e) {
                System.out.println("Sorry, this command is undefined");
                System.out.printf("###>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
