package com.example.demo.service;


import com.example.demo.mq.MqReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


@Service
public class MqReceiverService {

    @Autowired
    private MqReceiver mqReceiver;

    @JmsListener(destination = "TEST.QUEUE")
    public void onMessage(javax.jms.Message message) {
        mqReceiver.receiveMessage(message);
    }
}

