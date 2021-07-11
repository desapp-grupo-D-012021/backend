package ar.edu.unq.desapp.grupoD.backenddesapptp.rabbitmq;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.NotificationMessage;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Suscription;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.SuscriptionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagingConsume {

    @Autowired
    private SuscriptionService service;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(NotificationMessage message) {
        service.getAllByImdbId(message.getImdbId()).forEach(m -> System.out.println("Message recieved from queue : " + message));
    }
}
