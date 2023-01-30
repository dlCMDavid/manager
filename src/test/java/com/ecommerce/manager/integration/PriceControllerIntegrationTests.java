package com.ecommerce.manager.integration;

import com.ecommerce.manager.ManagerApplication;
import com.ecommerce.manager.apiContracts.request.SearchByBrandAndProductAndApplyTime;
import com.ecommerce.manager.entity.Price;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(classes = ManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PriceControllerIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @ParameterizedTest
    @MethodSource("provideSearchParams")
    public void SearchByBrandAndProductAndApplyTimeTest(SearchByBrandAndProductAndApplyTime body, int resultCount) throws JsonProcessingException {

        mapper.registerModule(new JavaTimeModule());

        String url = "http://localhost:" + port + "/api/price";

        HttpEntity<SearchByBrandAndProductAndApplyTime> entity = new HttpEntity<>(body, new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class);

        assertSame(response.getStatusCode(), HttpStatus.OK);

        var responseObject = mapper.readValue(response.getBody(), new TypeReference<List<Price>>(){});

        assertSame(resultCount, responseObject.size());
    }

    private static Stream<Arguments> provideSearchParams() {
        return Stream.of(
                Arguments.of(new SearchByBrandAndProductAndApplyTime(1L, 35455L, LocalDateTime.of(2020,6,14,10,0,0)), 1),
                Arguments.of(new SearchByBrandAndProductAndApplyTime(1L, 35455L, LocalDateTime.of(2020,6,14,16,0,0)), 2),
                Arguments.of(new SearchByBrandAndProductAndApplyTime(1L, 35455L, LocalDateTime.of(2020,6,14,21,0,0)), 1),
                Arguments.of(new SearchByBrandAndProductAndApplyTime(1L, 35455L, LocalDateTime.of(2020,6,15,10,0,0)), 2),
                Arguments.of(new SearchByBrandAndProductAndApplyTime(1L, 35455L, LocalDateTime.of(2020,6,16,21,0,0)), 2)
        );
    }
}
