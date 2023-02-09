package gb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class WebServer {
    public static void main(String[] args) {
      try (ServerSocket serverSocet = new ServerSocket(8088)){
          // встроенная розетка такая
          while (true) {
              Socket socet = serverSocet.accept(); // розетка

              //      localhost:8088  в браузере нужно ввести после заупска программы
              System.out.println("New client connected");
              BufferedReader input = new BufferedReader(
                      new InputStreamReader(socet.getInputStream(), StandardCharsets.UTF_8));
              //  считываем поток пакетов с байтами в виде кодировки UTF_8
              PrintWriter output = new PrintWriter(socet.getOutputStream());


              while (!input.ready()) ;  // пока в буфере нет данных будем висеть
              while (input.ready()) {
                  System.out.println(input.readLine());
              }

              output.println("HTTP/1.1 200 ок  ништячек случился");
              output.println("Content-Type: text/html; charset=utf8");
              output.println();
              output.println("Hello from server!!!!!!!!!!!!!!!!!!</h1>");
              output.flush();    // пихнет все

              input.close();   //  закрываем потоки
              output.close();
          }
      } catch (IOException e){
          e.printStackTrace();
      }
    }
}
