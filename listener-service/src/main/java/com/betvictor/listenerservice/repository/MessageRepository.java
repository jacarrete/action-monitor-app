package com.betvictor.listenerservice.repository;

import com.betvictor.listenerservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
