import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            // ایجاد سرور روی پورت 1234
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("سرور راه‌اندازی شد. منتظر اتصال کلاینت...");

            // پذیرش اتصال از کلاینت
            Socket clientSocket = serverSocket.accept();
            System.out.println("کلاینت متصل شد.");

            // ایجاد ورودی و خروجی برای ارتباط
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String inputLine;
            System.out.println("برای شروع چت پیام خود را بنویسید...");

            while (true) {
                // خواندن از کنسول و ارسال به کلاینت
                System.out.print("سرور: ");
                String serverMessage = consoleInput.readLine();
                out.println(serverMessage);

                // اگر کاربر "خداحافظ" بنویسد، ارتباط قطع می‌شود
                if (serverMessage.equalsIgnoreCase("خداحافظ")) {
                    break;
                }

                // دریافت پیام از کلاینت و نمایش آن
                inputLine = in.readLine();
                if (inputLine != null) {
                    System.out.println("کلاینت: " + inputLine);
                }
            }

            // بستن اتصال‌ها
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("خطا در سرور: " + e.getMessage());
        }
    }
}