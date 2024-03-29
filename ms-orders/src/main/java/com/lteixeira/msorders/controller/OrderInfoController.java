package com.lteixeira.msorders.controller;

import com.lteixeira.msorders.dto.OrderInfoDTO;
import com.lteixeira.msorders.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping
    public ResponseEntity<List<OrderInfoDTO>> findAll(){ return ResponseEntity.ok(orderInfoService.findAll()); }
}
