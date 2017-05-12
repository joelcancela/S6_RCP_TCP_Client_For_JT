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
public class MainCliet {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scan = new Scanner(System.in);
            String toSend;
            System.out.println("##>");
            while ((toSend = scan.next()) != null) {
                out.println(toSend);
                out.println(new String("AINI 1.0\n"+
                "ADD IDEA\n"+
                "BEGIN\n"+
                "{\"desc\":\"desc idea\", \"idStudent\":\"idStudent\",\"mailStudent\":\"email\",\"idIdea\":\"IDidea\"}\n" +
                "END\n"));
                out.flush();
                System.out.println("##>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
