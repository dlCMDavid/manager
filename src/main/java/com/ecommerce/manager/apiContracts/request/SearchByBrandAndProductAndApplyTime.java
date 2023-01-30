package com.ecommerce.manager.apiContracts.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class SearchByBrandAndProductAndApplyTime {
    @NotNull(message = "BrandId is mandatory")
    private Long brandId;
    @NotNull(message = "ProductId is mandatory")
    private Long productId;
    @NotNull(message = "ApplicationTime is mandatory")
    private LocalDateTime applicationTime;
}
