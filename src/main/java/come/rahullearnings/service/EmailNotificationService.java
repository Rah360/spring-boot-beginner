package come.rahullearnings.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailNotificationService implements NotificationService{
    @Override
    public void notify(String msg) {
        System.out.println("[EMAIL SENT]: Sending mail to Rahul: " + msg );
    }
}
