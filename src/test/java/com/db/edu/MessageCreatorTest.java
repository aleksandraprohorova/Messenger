package com.db.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageCreatorTest {
    private MessageCreator messageCreatorSut;
    private Date dateValue;
    private String identifier;

    @BeforeEach
    public void TestsPreparation(){
        messageCreatorSut = new MessageCreator();
        dateValue = new Date(System.currentTimeMillis());
        identifier = "123";

    }

    @Test
    public void shouldCreateCmdMessageWhenReceiveCommand() {
    String Inputcommand  = "/hist";
    String ResultCommand  = "hist";

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
    public void shouldCreateWrongDataMessageWhenMessageIsInvalid() {
        String InputMessage  = " ";
        String ResultMessage  = "Wrong message!";

        Message newMessage = messageCreatorSut.createMessage(identifier, dateValue, InputMessage);
        assertEquals(ResultMessage, newMessage.getBody());
        assertEquals(dateValue ,newMessage.getDateValue());
        assertEquals(identifier, newMessage.getIdentifier());
    }


}
