package messaging.ibm.api.service;


import messaging.ibm.api.mq.MqReceiver;
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

