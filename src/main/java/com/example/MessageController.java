package com.example;

import com.example.models.Message;
import com.example.models.MessageRepository;
import com.example.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mike on 3/13/17.
 */
@Controller
@RequestMapping(path = "/")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/")
    public
    @ResponseBody
    Iterable<Message> readMessages(@RequestParam Long userID) {
//        String tmp = "";
        List<Message> messageList = new LinkedList<>();

        for (Message message : messageRepository.findAll()) {
            if (message.getSenderId() == userID || message.getReciverId() == userID)
                messageList.add(message);
        }

        return messageList;
    }

    @PostMapping(path = "/")
    public
    @ResponseBody
    String sendMessage(@RequestParam Long reciverID, @RequestParam Long senderID, @RequestParam String content) {
        Message newMessage = new Message();
        newMessage.setSenderId(senderID);
        newMessage.setReciverId(reciverID);
        newMessage.setStatus(0);
        newMessage.setTimestamp(System.currentTimeMillis());
        newMessage.setContent(content);
        messageRepository.save(newMessage);
        return newMessage.toString();
    }

    @GetMapping(path = "/")
    public
    @ResponseBody
    String readMessage(@RequestParam Long messageID) {
        return messageRepository.findOne(messageID).toString();
    }
}
