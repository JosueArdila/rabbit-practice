package com.sofka.broker.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig {

    @Bean
    Queue pisoUno() {
        return new Queue("queue.impar.piso1", false);
    }

    @Bean
    Queue pisoDos() {
        return new Queue("queue.par.piso2", false);
    }

    @Bean
    Queue pisoTres() {
        return new Queue("queue.impar.piso3", false);
    }

    @Bean
    Queue pisoCuatro() {
        return new Queue("queue.par.piso4", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    TopicExchange exchangeImpar() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding pisoUnoBinding(Queue pisoUno, DirectExchange exchange) {
        return BindingBuilder.bind(pisoUno).to(exchange).with("pisouno");
    }

    @Bean
    Binding pisoDosBinding(Queue pisoDos, DirectExchange exchange) {
        return BindingBuilder.bind(pisoDos).to(exchange).with("pisodos");
    }

    @Bean
    Binding pisoTresBinding(Queue pisoTres, DirectExchange exchange) {
        return BindingBuilder.bind(pisoTres).to(exchange).with("pisotres");
    }

    @Bean
    Binding pisoCuatroBinding(Queue pisoCuatro, DirectExchange exchange) {
        return BindingBuilder.bind(pisoCuatro).to(exchange).with("pisocuatro");
    }

    @Bean
    Binding pisoUnoTopicBinding(Queue pisoUno, TopicExchange exchange) {
        return BindingBuilder.bind(pisoUno).to(exchange).with("impar");
    }

    @Bean
    Binding pisoTresTopicBinding(Queue pisoTres, TopicExchange exchange) {
        return BindingBuilder.bind(pisoTres).to(exchange).with("impar");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
