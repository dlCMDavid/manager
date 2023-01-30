package com.ecommerce.manager.repository;

import com.ecommerce.manager.entity.Price;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends CrudRepository<Price, Long> {

    @Query(value = "SELECT new Price(p.product.id, p.brand.id, p.priceList, p.startDateTime, p.endDateTime, p.price) " +
            "FROM Price p " +
            "WHERE p.product.id = :product AND p.brand.id = :brand AND p.startDateTime < :applicationTime AND :applicationTime < p.endDateTime")
    List<Price> SearchByBrandAndProductAndApplicationTime(
            @Param("product") Long productId,
            @Param("brand") Long brandId,
            @Param("applicationTime") LocalDateTime applicationTime);
}
