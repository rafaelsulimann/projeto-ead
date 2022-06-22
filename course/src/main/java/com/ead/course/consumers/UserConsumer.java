package com.ead.course.consumers;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ead.course.dtos.UserEventDto;
import com.ead.course.enums.ActionType;
import com.ead.course.services.UserService;

@Component
public class UserConsumer {

    @Autowired
    private UserService userService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${ead.broker.queue.userEventQueue.name}", durable = "true"), 
                    exchange = @Exchange(value = "${ead.broker.exchange.userEventExchange}", type = ExchangeTypes.FANOUT, 
                    ignoreDeclarationExceptions = "true")))
    public void listenUserEvent(@Payload UserEventDto userEventDto) {
        var userModel = userEventDto.convertToUserModel();

        switch(ActionType.valueOf(userEventDto.getActionType())){
            case CREATE:
                userService.save(userModel);
                break;
        }
    }
}
