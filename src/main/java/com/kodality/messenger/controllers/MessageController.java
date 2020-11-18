package com.kodality.messenger.controllers;

import com.kodality.messenger.entities.Message;
import com.kodality.messenger.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody Message message){
        messageService.createMessage(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("/findById/{id}")
    public Optional<Message> findMessageById(@PathVariable Long id){
        return messageService.findMessageById(id);
    }

    @GetMapping("/findBySender/{name}")
    public List<Message> findMessagesBySender(@PathVariable String name){
        return messageService.findMessagesBySenderName(name);
    }

    @GetMapping("/findByReceiver/{name}")
    public List<Message> findMessagesByReceiver(@PathVariable String name){ return messageService.findMessagesByReceiverName(name);}

    @GetMapping("/findBySenderOrReceiver/{sender}/{receiver}")
    public List<Message> findMessagesBySenderOrReceiver(@PathVariable String sender, @PathVariable String receiver){ return messageService.findMessagesBySenderNameOrReceiverName(sender, receiver);}

    @GetMapping("/findByDate/{yyyy_mm_dd}")
    public List<Message> findMessagesByDate(@PathVariable String yyyy_mm_dd){ return messageService.findMessagesByDateCreated(yyyy_mm_dd);}

    @GetMapping("/findByReadStatus/{wasRead}")
    public List<Message> findMessagesByReadStatus(@PathVariable Boolean wasRead){ return messageService.findMessagesByWasRead(wasRead);}

    //update message status (read, unread)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @PathVariable Boolean wasRead){
        messageService.changeMessageReadStatus(id, wasRead);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //delete message
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id){
        messageService.deleteMessageById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
