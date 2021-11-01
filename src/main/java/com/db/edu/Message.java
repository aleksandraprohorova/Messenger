package com.db.edu;

import java.io.Serializable;
import java.util.Date;

public interface Message extends Serializable {

    String getBody();
    String getDateValue();
    String getIdentifier();
}