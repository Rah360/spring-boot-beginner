package come.rahullearnings.service;

import org.springframework.stereotype.Service;

@Service
public class ConsoleNotificationService implements NotificationService{

    @Override
    public void notify(String msg) {
        System.out.println("[CONSOLE LOG]: " + msg);
    }
}
