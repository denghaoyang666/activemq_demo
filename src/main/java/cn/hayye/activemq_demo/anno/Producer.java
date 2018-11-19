package cn.hayye.activemq_demo.anno;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class Producer {

    @Autowired
    JmsMessagingTemplate template;

    public void sendQueueText(String destinationName,String text){
        System.out.println(">>>>>发送至"+destinationName+" 内容："+text);
        Destination destination = new ActiveMQQueue(destinationName);
        template.convertAndSend(destination,text);
    }

    public void sendTopicText(String destinationName,String text){
        System.out.println(">>>>>发送至"+destinationName+" 内容："+text);
        Destination destination = new ActiveMQTopic(destinationName);
        template.convertAndSend(destination,text);
    }
}
