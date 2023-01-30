package com.ecommerce.manager.service.price;

import com.ecommerce.manager.entity.Price;
import com.ecommerce.manager.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository repository;

    public PriceServiceImpl(PriceRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<Price> SearchByBrandAndProductAndApplicationTime(Long productId, Long brandId, LocalDateTime applicationTime) {
        return repository.SearchByBrandAndProductAndApplicationTime(productId, brandId, applicationTime);
    }
}
