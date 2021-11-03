package com.db.edu;

import com.db.edu.creater.MessageCreater;
import com.db.edu.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageCreatorTest {
    private MessageCreater messageCreatorSut;
    private String dateValue;
    private String identifier;

    @BeforeEach
    public void TestsPreparation(){
        messageCreatorSut = new MessageCreater();
        dateValue = new Date(System.currentTimeMillis()).toString();
        identifier = "123";

    }

    @Test
    public void shouldCreateCmdMessageWhenReceiveCommand() {
    String inputcommand  = "/hist";
    String resultCommand  = "hist";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, inputcommand);
        assertEquals(resultCommand, newMessage.getBody());
        assertEquals(dateValue, newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());

    }


    @Test
    public void shouldCreateDataMessageWhenReceiveMessage() {
        String inputMessage  = "/snd <HW!>";
        String resultMessage  = "HW!";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, inputMessage);
        assertEquals(resultMessage, newMessage.getBody());
        assertEquals(dateValue, newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateDataMessageWhenReceiveMessageIsEmpty() {
        String inputMessage  = "/snd  ";
        String resultMessage  = "Message can't be empty";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, inputMessage);
        assertEquals(resultMessage, newMessage.getBody());
        assertEquals(dateValue, newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateDataMessageWhenReceiveMessageIsLong() {
        String inputMessage  = "/snd <fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff  ";
        String resultMessage  = "Message can't be longer than 150 symbols.";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, inputMessage);
        assertEquals(resultMessage, newMessage.getBody());
        assertEquals(dateValue, newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateWrongDataMessageWhenMessageIsInvalid() {
        String inputMessage  = " ";
        String resultMessage  = "Wrong message!";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, inputMessage);
        assertEquals(resultMessage, newMessage.getBody());
        assertEquals(dateValue ,newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateWrongDataMessageWhenCommandIsInvalid() {
        String inputMessage  = "/snds";
        String resultMessage  = "Wrong message!";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, inputMessage);
        assertEquals(resultMessage, newMessage.getBody());
        assertEquals(dateValue ,newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateWrongDataMessageWithParser() {
        String inputMessage  = "/snd <df> <df>";
        String resultMessage  = "df df";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, inputMessage);
        assertEquals(resultMessage, newMessage.getBody());
        assertEquals(dateValue ,newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }


}
