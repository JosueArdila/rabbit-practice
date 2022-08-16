package com.sofka.broker.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/broker/")
public class RabbitMQDirectWebController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/direct/producer")
    public String producerPisoUno(@RequestParam("messageData") String messageData) {
        amqpTemplate.convertAndSend("direct-exchange", "pisouno", messageData);
        return "Message sent to the RabbitMQ Successfully";
    }

    @GetMapping(value = "/topic/producer-impar")
    public String producerPisosImpares(@RequestParam("messageData") String messageData) {
        amqpTemplate.convertAndSend("topic-exchange", "impar", messageData);
        return "Message sent to the RabbitMQ Successfully";
    }

}
