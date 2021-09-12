package io.springbatch.springbatchlecture.service;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.domain.ApiResponseVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService3 extends AbstractApiService{

    @Override
    public void doApiService(RestTemplate restTemplate, HttpEntity<List<? extends ApiRequestVO>> reqEntity){

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/product/3", reqEntity, String.class);

        int statusCodeValue = response.getStatusCodeValue();
        ApiResponseVO apiResponseVO = new ApiResponseVO(statusCodeValue + "", response.getBody(), null);
    }
}
