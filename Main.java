package com.mycompany.chatapp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Login login = new Login();

        // REGISTER
        System.out.println("=== REGISTER ===");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter SA cell number: ");
        String cell = input.nextLine();

        System.out.println(login.registerUser(username, password, cell));

        // LOGIN
        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password: ");
        String loginPass = input.nextLine();

        if (login.loginUser(loginUser, loginPass)) {

            System.out.println("Welcome to QuickChat.");

            System.out.print("How many messages would you like to send? ");
            int numMessages = input.nextInt();
            input.nextLine();

            int option;

            do {

                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");

                System.out.print("Choose option: ");
                option = input.nextInt();
                input.nextLine();

                switch (option) {

                    case 1 -> {
                        for (int i = 1; i <= numMessages; i++) {

                            System.out.println("\nMessage " + i);

                            System.out.print("Enter recipient number: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter message: ");
                            String messageText = input.nextLine();

                            Message msg = new Message(i, recipient, messageText);

                            System.out.println(msg.checkRecipientCell());
                            System.out.println(msg.checkMessageLength());

                            if (msg.checkMessageLength().equals("Message ready to send.")) {

                                System.out.println("\n1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message");

                                int sendOption = input.nextInt();
                                input.nextLine();

                                System.out.println(msg.sentMessage(sendOption));

                                System.out.println("\n" + msg.printMessages());
                            }
                        }
                    }

                    case 2 -> System.out.println("Coming Soon.");

                    case 3 -> System.out.println("Goodbye!");

                    default -> System.out.println("Invalid option.");
                }

            } while (option != 3);

            System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());

        } else {

            System.out.println("Username or password incorrect.");
        }
    }
}