package com.augusto.rabbitmq.producerqueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/producer")
public class ProducerController {

    @Autowired
    ProducerQueueSender producerQueueSender;

    @PostMapping
    public ResponseEntity send(@RequestBody MessageDTO messageDTO) {
        producerQueueSender.send(messageDTO);
        return ResponseEntity.ok(messageDTO);
    }

}
