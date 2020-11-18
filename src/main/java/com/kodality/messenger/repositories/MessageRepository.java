package com.kodality.messenger.repositories;
import com.kodality.messenger.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin("http://localhost:4200")
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findMessageById(Long id);
    List<Message> findMessagesBySenderName(String name);
    List<Message> findMessagesByReceiverName(String name);
    List<Message> findMessagesBySenderNameOrReceiverName(String sender, String receiver);
    List<Message> findMessagesByDateCreated(String yyyy_mm_dd);
    List<Message> findMessagesByWasRead(boolean wasRead);

}
