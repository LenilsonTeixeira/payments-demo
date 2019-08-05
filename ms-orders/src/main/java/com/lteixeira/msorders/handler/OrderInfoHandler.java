package com.lteixeira.msorders.handler;

import com.lteixeira.msorders.dto.OrderInfoDTO;
import com.lteixeira.msorders.mapper.OrderInfoMapper;
import com.lteixeira.msorders.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class OrderInfoHandler {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderInfoMapper mapper;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                .body(fromPublisher(orderInfoRepository.findAll()
                        .map(mapper::convertToDTO), OrderInfoDTO.class));
    }
}
