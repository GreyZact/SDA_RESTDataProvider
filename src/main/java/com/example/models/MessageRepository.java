package com.example.models;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by mike on 3/13/17.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
}
