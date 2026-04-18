package come.rahullearnings.service;

import come.rahullearnings.dto.MessagePayload;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public String processMessage(MessagePayload msg){
        String upperSender = msg.sender.toUpperCase();
        return "Service processed: " + msg.content + " from " + upperSender;
    }
    public String deleteMessage(Long id){
        if (id < 0) {
            throw new IllegalArgumentException("IDs cannot be negative!");
        }
        return "delete processed: " + id;
    }
}
