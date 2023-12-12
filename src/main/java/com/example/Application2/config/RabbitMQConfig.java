package com.example.Application2.config;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue app2Queue() {
        return new Queue("app2Queue", false);
    }

    @Bean
    public FanoutExchange app1Exchange() {
        return new FanoutExchange("app1Exchange");
    }

    @Bean
    public Binding binding(Queue app2Queue, FanoutExchange app1Exchange) {
        return BindingBuilder.bind(app2Queue).to(app1Exchange);
    }
}

