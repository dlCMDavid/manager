package com.ecommerce.manager.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "Price")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    @Transient
    private Long brandId;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "price_list", nullable = false)
    private Long priceList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Transient
    private Long productId;

    @Column(name = "priority", nullable = false)
    private Long priority;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "curr", nullable = false, length = 3)
    private String curr;

    public Price(Long productId, Long brandId, Long priceList, LocalDateTime startDateTime, LocalDateTime endDateTime, float price) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.price = price;
    }
}
