package cn.zerry.example.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(String message){
        System.out.println("publishing custom event.");
        publisher.publishEvent(new CustomSpringEvent(this, message));
    }
}
