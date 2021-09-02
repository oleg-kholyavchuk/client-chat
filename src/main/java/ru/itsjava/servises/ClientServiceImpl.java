package ru.itsjava.servises;

import lombok.SneakyThrows;
import ru.itsjava.domain.User;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {

    public final static int PORT = 8081;
    public final static String HOST = "localhost";
    private User user;

    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWrite =
                    new PrintWriter(socket.getOutputStream());

            MessageInputService messageInputService =
                    new MessageInputServiceImpl(System.in);

            System.out.println("Введите свой логин");
            String login = messageInputService.getMessage();

            System.out.println("Введите сой пароль");
            String password = messageInputService.getMessage();

            user = new User(login, password);


            serverWrite.println("!autho!" + login + ":" + password);
            serverWrite.flush();

            //String consoleMessage = messageInputService.getMessage();
            while (true) {
                String consoleMessage = messageInputService.getMessage();
                //consoleMessage = consoleMessage.strip()
                //if (consoleMessage.contains(user.getName())) {


                    if (!consoleMessage.equals("Exit")) {
                        serverWrite.println(consoleMessage);
                        serverWrite.flush();
                    } else {
                        System.out.println("Вышли из чата");
                        break;
                    }
               // }
            }
        }
    }

//    private void sendMassage(BufferedReader consoleReader, PrintWriter serverWriter) throws IOException {
//
//        String string;
//        while ((string = consoleReader.readLine()) != null) {
//            println(serverWriter, user.getName() + ":" + string);
//        }
//    }
}
