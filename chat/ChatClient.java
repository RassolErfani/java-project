import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            // اتصال به سرور روی localhost و پورت 1234
            Socket socket = new Socket("localhost", 1234);
            System.out.println("به سرور متصل شدید.");

            // ایجاد ورودی و خروجی برای ارتباط
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String inputLine;
            System.out.println("برای شروع چت پیام خود را بنویسید...");

            while (true) {
                // دریافت پیام از سرور و نمایش آن
                inputLine = in.readLine();
                if (inputLine != null) {
                    System.out.println("سرور: " + inputLine);
                }

                // اگر سرور "خداحافظ" گفته باشد، ارتباط قطع می‌شود
                if (inputLine != null && inputLine.equalsIgnoreCase("خداحافظ")) {
                    break;
                }

                // خواندن از کنسول و ارسال به سرور
                System.out.print("کلاینت: ");
                String clientMessage = consoleInput.readLine();
                out.println(clientMessage);

                // اگر کاربر "خداحافظ" بنویسد، ارتباط قطع می‌شود
                if (clientMessage.equalsIgnoreCase("خداحافظ")) {
                    break;
                }
            }

            // بستن اتصال‌ها
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("خطا در کلاینت: " + e.getMessage());
        }
    }
}