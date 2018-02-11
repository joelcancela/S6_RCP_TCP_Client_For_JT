package boucan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Nassim B on 12/05/17.
 */
public class Main {

    protected static String inputProject() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\tProject Name : ");
        String name = scanner.nextLine();
        System.out.printf("\tCreator Name : ");
        String creator = scanner.nextLine();
        System.out.printf("\tCreator Mail : ");
        String mail = scanner.nextLine();
        System.out.printf("\tProject Description : ");
        String description = scanner.nextLine();

        return "AINI 1.0\nADD IDEA\nBEGIN\n{\"desc\":" + description + ", \"idStudent\":" + creator + ",\"mailStudent\":" + mail + ",\"idIdea\":" + name + "}\nEND";
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scan = new Scanner(System.in);
            String action;
            System.out.println("##>");
            while ((action = scan.next()) != null) {
                switch (action) {
                    case "add" :
                        out.println(Main.inputProject());
                        break;
                    case "list" :
                        out.println("AINI 1.0\nDISP IDEA\nBEGIN\n{\"desc\":\"rien\"}\nEND");
                        break;
                }
                out.flush();
                System.out.println("##>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
