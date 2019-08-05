package com.lteixeira.apicustomers.service.impl;

import com.lteixeira.apicustomers.dto.PurchaseOrderDTO;
import com.lteixeira.apicustomers.event.enumeration.PurchaseOrderPublishActionEnum;
import com.lteixeira.apicustomers.event.service.PurchaseOrderPublisherService;
import com.lteixeira.apicustomers.exception.NotFoundException;
import com.lteixeira.apicustomers.exception.PurchaseException;
import com.lteixeira.apicustomers.mapper.PurchaseOrderMapper;
import com.lteixeira.apicustomers.model.PurchaseOrder;
import com.lteixeira.apicustomers.repository.PurchaseOrderRepository;
import com.lteixeira.apicustomers.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderPublisherService purchaseOrderPublisherService;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO) {
        try {
            PurchaseOrder purchaseOrder = purchaseOrderMapper.convertToModel(purchaseOrderDTO);

            final PurchaseOrderDTO purchaseOrderDb =  purchaseOrderMapper.convertToDTO(this.purchaseOrderRepository.save(purchaseOrder));

            purchaseOrderPublisherService.accept(purchaseOrderMapper.convertToModel(purchaseOrderDb), PurchaseOrderPublishActionEnum.CREATE.CREATE);

            return purchaseOrderDb;

        }catch (Exception e){
            throw new PurchaseException("Erro ao salvar ordem de compra");
        }
    }

    @Override
    public List<PurchaseOrderDTO> findAll() {
        return this.purchaseOrderRepository.findAll().stream()
                .map(purchaseOrderMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Integer id, PurchaseOrderDTO purchaseOrderDTO) {
        this.findById(id);

        final PurchaseOrder purchaseOrder = purchaseOrderRepository.save(purchaseOrderMapper.convertToModel(purchaseOrderDTO));

        purchaseOrderPublisherService.accept(purchaseOrder, PurchaseOrderPublishActionEnum.UPDATE);
    }

    @Override
    public void deleteById(Integer id) {
        this.findById(id);

        purchaseOrderRepository.deleteById(id);

        purchaseOrderPublisherService.accept(PurchaseOrder.builder().id(id).build(), PurchaseOrderPublishActionEnum.DELETE);
    }

    @Override
    public Optional<PurchaseOrderDTO> findById(Integer id) {
        Optional<PurchaseOrder> purchaseDb = purchaseOrderRepository.findById(id);
        if(purchaseDb.isPresent()){
            return purchaseOrderRepository.findById(id)
                    .map(purchaseOrderMapper::convertToDTO);
        }else{
            throw new NotFoundException("Ordem de compra não encontrado");
        }
    }
}
