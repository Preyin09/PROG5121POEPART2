package com.mycompany.chatapp;

import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public final class Message {

    private static int totalMessages = 0;

    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String message;
    private final String messageHash;

    // Constructor
    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generate random 10 digit message ID
    private String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // Check Message ID length
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Validate recipient number
    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Validate message length
    public String checkMessageLength() {

        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Create Message Hash
    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageID.substring(0, 2) + ":" +
                messageNumber + ":" +
                firstWord.toUpperCase() +
                lastWord.toUpperCase();
    }

    // Send message options
    public String sentMessage(int choice) {

        switch (choice) {

            case 1 -> {
                totalMessages++;
                return "Message successfully sent.";
            }

            case 2 -> {
                return "Press 0 to delete the message.";
            }

            case 3 -> {
                storeMessage();
                return "Message successfully stored.";
            }

            default -> {
                return "Invalid option.";
            }
        }
    }

    // Print message details
    public String printMessages() {

        return "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + message;
    }

    // Total sent messages
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Store message into JSON file
    public void storeMessage() {

        JSONArray messageList = new JSONArray();

        JSONObject messageDetails = new JSONObject();

        messageDetails.put("MessageID", messageID);
        messageDetails.put("MessageHash", messageHash);
        messageDetails.put("Recipient", recipient);
        messageDetails.put("Message", message);

        messageList.add(messageDetails);

        try (FileWriter file = new FileWriter("storedMessages.json")) {

            file.write(messageList.toJSONString());
            file.flush();

        } catch (IOException e) {
            System.out.println("Error storing message.");
        }
    }
}