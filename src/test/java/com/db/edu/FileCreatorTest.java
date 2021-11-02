package com.db.edu;

import com.db.edu.creater.FileCreater;
import com.db.edu.message.Message;
import com.db.edu.message.SndMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FileCreatorTest {

    private Message sndMessage;
    private FileCreater fileHandler;

    @BeforeEach
    public void TestsPreparation(){

        try {
            fileHandler = new FileCreater();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sndMessage = mock(SndMessage.class);

    }

    @Test
    public void shouldReturnLineToWriteInFile() {

        assertEquals(sndMessage.getIdentifier() + " " + sndMessage.getDateValue() + " "
                + sndMessage.getBody() + System.lineSeparator(), fileHandler.getLineToWrite(sndMessage));
    }



}



