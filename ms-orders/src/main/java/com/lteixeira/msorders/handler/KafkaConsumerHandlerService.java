package com.lteixeira.msorders.handler;

import com.lteixeira.msorders.model.Message;
import com.lteixeira.msorders.service.ProcessorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.ReceiverRecord;

@Log4j2
@Service
public class KafkaConsumerHandlerService {

    @Autowired
    private ProcessorService processorService;

    public void handle(final ReceiverRecord<String, Message> record) {

        try {
            processorService.process(record.value());

            record.receiverOffset().acknowledge();

        } catch (Exception e) {

            log.error("Erro ao tentar parsear a mensagem para JSON.", e);

        }

    }

}
