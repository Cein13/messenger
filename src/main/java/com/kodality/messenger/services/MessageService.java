package com.kodality.messenger.services;

import com.kodality.messenger.entities.Message;
import com.kodality.messenger.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    private MessageRepository messageRepository;

    public void createMessage(@Valid Message message){
        messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> findMessageById(Long id) {
        return messageRepository.findMessageById(id);
    }

    public List<Message> findMessagesBySenderName(String name) { return messageRepository.findMessagesBySenderName(name); }

    public List<Message> findMessagesByReceiverName(String name){ return messageRepository.findMessagesByReceiverName(name); }

    public List<Message> findMessagesBySenderNameOrReceiverName(String sender, String receiver){ return messageRepository.findMessagesBySenderNameOrReceiverName(sender, receiver);}

    public List<Message> findMessagesByDateCreated(String yyyy_mm_dd){ return messageRepository.findMessagesByDateCreated(yyyy_mm_dd);}

    public List<Message> findMessagesByWasRead(boolean wasRead){ return messageRepository.findMessagesByWasRead(wasRead);}

    public void changeMessageReadStatus(Long id, Boolean wasRead) {
        findMessageById(id).ifPresent(message -> {
            message.setWasRead(wasRead);
            messageRepository.saveAndFlush(message);});}

    public void deleteMessageById(Long id) { findMessageById(id).ifPresent(message -> {messageRepository.delete(message);});
    }

}
