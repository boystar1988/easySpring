
package com.jianzhong.demo.config;

import com.jianzhong.demo.constant.QueueConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueueConfig {

    @Bean
    public Queue queueUser()
    {
        return new Queue(QueueConstant.DIRECT_QUEUE_USER);
    }

    @Bean
    public Queue queueOrder()
    {
        return new Queue(QueueConstant.DIRECT_QUEUE_ORDER);
    }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(QueueConstant.DIRECT_EXCHANGE);
    }

    @Bean
    public Binding bindUser()
    {
        return BindingBuilder.bind(queueUser()).to(directExchange()).with(QueueConstant.DIRECT_QUEUE_USER);
    }

    @Bean
    public Binding bindOrder()
    {
        return BindingBuilder.bind(queueOrder()).to(directExchange()).with(QueueConstant.DIRECT_QUEUE_ORDER);
    }

}