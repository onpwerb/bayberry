package cn.zerry.example.events;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnoationDrivenContextStartedListener {

    @EventListener
    public void handleContextStart(ContextStartedEvent cse){
        System.out.println("Handling context started event");
    }
}
