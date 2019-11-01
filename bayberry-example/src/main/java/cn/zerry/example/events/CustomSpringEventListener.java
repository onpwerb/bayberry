package cn.zerry.example.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
    @Override
    public void onApplicationEvent(CustomSpringEvent customSpringEvent) {
        System.out.println("Received spring custom event -> " + customSpringEvent.getMessage());
    }
}
