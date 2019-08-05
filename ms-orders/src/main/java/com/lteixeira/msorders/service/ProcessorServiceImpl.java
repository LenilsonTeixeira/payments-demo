package com.lteixeira.msorders.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lteixeira.msorders.dto.OrderInfoDTO;
import com.lteixeira.msorders.mapper.OrderInfoMapper;
import com.lteixeira.msorders.model.Message;
import com.lteixeira.msorders.model.OrderInfo;
import com.lteixeira.msorders.repository.OrderInfoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
public class ProcessorServiceImpl implements ProcessorService{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderInfoMapper mapper;

    @Override
    public void process(Message message) throws IOException {

        OrderInfoDTO orderInfoDTO = objectMapper.readValue(message.getPayload(),OrderInfoDTO.class);

        final OrderInfo orderInfo = mapper.convertToModel(orderInfoDTO);

        log.info("Salvando remessa: {}",orderInfo);

        orderInfoRepository.save(orderInfo);
    }
}
