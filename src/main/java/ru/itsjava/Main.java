package ru.itsjava;


import ru.itsjava.servises.ClientService;
import ru.itsjava.servises.ClientServiceImpl;

public class Main {

    public static void main(String[] args) {

        ClientService clientService = new ClientServiceImpl();
        clientService.start();


    }
}
