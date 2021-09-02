package ru.itsjava.servises;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itsjava.domain.User;

import java.net.Socket;

@Data
@RequiredArgsConstructor
public class SocketRunnable implements Runnable {
    private final Socket socket;
    private User user;


    @SneakyThrows
    @Override
    public void run() {

        MessageInputService messageInputService =
                new MessageInputServiceImpl(System.in);

        String login = messageInputService.getMessage();

        MessageInputService serverReader = new MessageInputServiceImpl(socket.getInputStream());

        String serverMassage;
        String serverName;

        while ((serverMassage = serverReader.getMessage()) != null) {

            serverName = serverMassage.split(":")[0];
            user = new User(login, "");

            if (!serverName.equals(user.getName())) {
                System.out.println(serverMassage);
            }
        }
    }
}
