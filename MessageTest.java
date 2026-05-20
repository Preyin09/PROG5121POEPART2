package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message ready to send.",
                msg.checkMessageLength()
        );
    }

    @Test
    public void testRecipientSuccess() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientFailure() {

        Message msg = new Message(
                0,
                "08575975889",
                "Hello"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testMessageHash() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hi tonight"
        );

        String hash = msg.createMessageHash();

        assertTrue(hash.contains(":0:HITONIGHT"));
    }

    @Test
    public void testSentMessage() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Message successfully sent.",
                msg.sentMessage(1)
        );
    }

    @Test
    public void testStoredMessage() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Message successfully stored.",
                msg.sentMessage(3)
        );
    }
}