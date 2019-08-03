package com.lteixeira.msorders.mapper;

import com.lteixeira.msorders.dto.OrderInfoDTO;
import com.lteixeira.msorders.model.OrderInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderInfoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrderInfoDTO convertToDTO(OrderInfo orderInfo){
        return this.modelMapper.map(orderInfo, OrderInfoDTO.class);
    }

    public OrderInfo convertToModel(OrderInfoDTO orderInfoDTO){
        return this.modelMapper.map(orderInfoDTO, OrderInfo.class);
    }

}
