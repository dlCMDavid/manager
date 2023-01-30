package com.ecommerce.manager.service.price;

import com.ecommerce.manager.entity.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    List<Price> SearchByBrandAndProductAndApplicationTime(Long productId, Long brandId, LocalDateTime ApplicationTime);
}
