import java.net.*;
import java.util.Scanner;
import java.io.*;

public class CurrencyConverter {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter base currency (e.g., USD, INR):");
        String base = scanner.next().toUpperCase();
        System.out.println("Enter target currency (e.g., EUR, JPY):");
        String target = scanner.next().toUpperCase();

        System.out.println("Enter amount to convert:");
        double amount = scanner.nextDouble();

        String urlStr = "https://v6.exchangerate-api.com/v6/c9f51ea6feab4d34408ec66c/latest/" + base;
        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) request.getContent()));
        String line;
        double rate = 1.0;
        boolean found = false;
        while ((line = in.readLine()) != null) {
            if (line.contains("\"" + target + "\":")) {
                String[] parts = line.trim().replace(",", "").replace("\"", "").split(":");
                if (parts.length == 2) {
                    rate = Double.parseDouble(parts[1]);
                    found = true;
                    break;
                }
            }
        }
        in.close();

        if (!found) {
            System.out.println("Could not retrieve the exchange rate.");
            return;
        }

        double converted = amount * rate;
        System.out.printf("%.2f %s = %.2f %s\n", amount, base, converted, target);
    }
}
