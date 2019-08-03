package com.lteixeira.msorders.service.impl;

import com.lteixeira.msorders.dto.OrderInfoDTO;
import com.lteixeira.msorders.exception.OrderInfoException;
import com.lteixeira.msorders.mapper.OrderInfoMapper;
import com.lteixeira.msorders.model.OrderInfo;
import com.lteixeira.msorders.repository.OrderInfoRepository;
import com.lteixeira.msorders.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfoDTO> findAll() {
        return this.orderInfoRepository.findAll().stream()
                .map(orderInfoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public void save(OrderInfoDTO orderInfoDTO) throws IllegalAccessException {
        Optional<OrderInfo> order = Optional.ofNullable(orderInfoMapper.convertToModel(orderInfoDTO));
        if(order.isPresent()){
            this.orderInfoRepository.save(order.get());
        }

        throw new OrderInfoException("Error in saving order");

    }
}
