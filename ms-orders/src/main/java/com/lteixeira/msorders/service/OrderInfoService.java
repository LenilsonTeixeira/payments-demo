package com.lteixeira.msorders.service;

import com.lteixeira.msorders.dto.OrderInfoDTO;

import java.util.List;

public interface OrderInfoService {
    List<OrderInfoDTO> findAll();
}
