package io.springbatch.springbatchlecture.service;

import io.springbatch.springbatchlecture.batch.domain.ApiResponseVO;
import io.springbatch.springbatchlecture.batch.domain.ProductVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService3 extends AbstractApiService{

    @Override
    public void doApiService(RestTemplate restTemplate, List<ProductVO> productList, HttpEntity<String> reqEntity){

        ApiInfoVO apiInfo = ApiInfoVO.builder()
                .uri("http://localhost:8080/api/product/3")
                .contentType("application/json")
                .method("POST")
                .productList(productList)
                .build();

        ResponseEntity<String> response = restTemplate.postForEntity(apiInfo.getUri(), reqEntity, String.class);

        int statusCodeValue = response.getStatusCodeValue();
        ApiResponseVO apiResponseVO = new ApiResponseVO(statusCodeValue + "", response.getBody(), null);
    }
}
