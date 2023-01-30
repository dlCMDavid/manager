package com.ecommerce.manager.controller;

import com.ecommerce.manager.apiContracts.request.SearchByBrandAndProductAndApplyTime;
import com.ecommerce.manager.entity.Price;
import com.ecommerce.manager.exception.CustomValidationException;
import com.ecommerce.manager.service.price.PriceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/price")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService)
    {
        this.priceService = priceService;
    }

    @PostMapping
    public ResponseEntity<List<Price>> SearchByBrandAndProductAndApplyTime(
            @Valid @RequestBody SearchByBrandAndProductAndApplyTime body, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
             throw new CustomValidationException(bindingResult);
        }

        return new ResponseEntity<>(
                priceService.SearchByBrandAndProductAndApplicationTime(body.getProductId(), body.getBrandId(), body.getApplicationTime()),
                HttpStatus.OK);
    }
}
