package com.augusto.rabbitmq.producerqueue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MessageDTO implements Serializable {
    LocalDateTime date;
    String message;
}
