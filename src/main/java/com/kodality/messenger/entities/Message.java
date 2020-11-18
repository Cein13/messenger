package com.kodality.messenger.entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    private boolean wasRead;

    private String senderName;

    private String receiverName;

    private String dateCreated;

}
