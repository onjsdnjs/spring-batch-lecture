package io.springbatch.springbatchlecture.service;

import io.springbatch.springbatchlecture.batch.domain.ApiInfo;
import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.domain.ApiResponseVO;
import io.springbatch.springbatchlecture.batch.domain.ProductVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService1 extends AbstractApiService{

    @Override
    public ApiResponseVO doApiService(RestTemplate restTemplate, ApiInfo apiInfo){

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/api/product/1", apiInfo, String.class);

        int statusCodeValue = response.getStatusCodeValue();
        ApiResponseVO apiResponseVO = new ApiResponseVO(statusCodeValue + "", response.getBody());

        return apiResponseVO;
    }
}
