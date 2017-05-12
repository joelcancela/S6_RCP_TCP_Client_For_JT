package boucan;

import java.util.Scanner;

/**
 * Main App
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ip = entrerIp(scanner);
        int port = entrerPort(scanner);
        InnovClient innovClient = new InnovClientImplTCP(ip, port);
        innovClient.start();
    }

    private static int entrerPort(Scanner scanner) {
        System.out.println("Enter port: ");
        int choice = scanner.nextInt();
        return choice;
    }

    private static String entrerIp(Scanner scanner) {
        System.out.println("Enter ip: ");
        String IPADDRESS_PATTERN =
                "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        String choice = null;
        while (true) {
            choice = scanner.nextLine();
            if (choice.matches(IPADDRESS_PATTERN) || choice.equals("localhost")) {
                break;
            }
        }
        return choice;
    }
}
