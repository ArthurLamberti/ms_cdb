package com.arthurlamberti.cdb.infrastructure.customer.feign;

import com.arthurlamberti.cdb.infrastructure.customer.models.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "CustomerFeign",
        url = "http://127.0.0.1:8080/api"
)
public interface CustomerFeign {

    @GetMapping("/customers/{customerId}")
    CustomerResponse getCustomerById(@PathVariable String customerId);

}
