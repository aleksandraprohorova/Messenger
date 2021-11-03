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
    String Inputcommand  = "/hist";
    String ResultCommand  = "hist";
    Client client = new Client();

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, Inputcommand);
        assertEquals(ResultCommand, newMessage.getBody());
        assertEquals(dateValue, newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());

    }


    @Test
    public void shouldCreateDataMessageWhenReceiveMessage() {
        String InputMessage  = "/snd <HW!>";
        String ResultMessage  = "HW!";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, InputMessage);
        assertEquals(ResultMessage, newMessage.getBody());
        assertEquals(dateValue, newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateDataMessageWhenReceiveMessageIsEmpty() {
        String InputMessage  = "/snd  ";
        String ResultMessage  = "Message can't be empty";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, InputMessage);
        assertEquals(ResultMessage, newMessage.getBody());
        assertEquals(dateValue, newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateDataMessageWhenReceiveMessageIsLong() {
        String InputMessage  = "/snd <fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff  ";
        String ResultMessage  = "Message can't be longer than 150 symbols.";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, InputMessage);
        assertEquals(ResultMessage, newMessage.getBody());
        assertEquals(dateValue, newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateWrongDataMessageWhenMessageIsInvalid() {
        String InputMessage  = " ";
        String ResultMessage  = "Wrong message!";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, InputMessage);
        assertEquals(ResultMessage, newMessage.getBody());
        assertEquals(dateValue ,newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }

    @Test
    public void shouldCreateWrongDataMessageWhenCommandIsInvalid() {
        String InputMessage  = "/snds";
        String ResultMessage  = "Wrong message!";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, InputMessage);
        assertEquals(ResultMessage, newMessage.getBody());
        assertEquals(dateValue ,newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }


}
