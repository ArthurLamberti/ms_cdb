package com.arthurlamberti.cdb.infrastructure.customer;

import com.arthurlamberti.cdb.domain.adapters.feing.CustomerExternal;
import com.arthurlamberti.cdb.domain.adapters.models.CustomerExternalDomain;
import com.arthurlamberti.cdb.infrastructure.customer.feign.CustomerFeign;
import com.arthurlamberti.cdb.infrastructure.customer.models.CustomerResponse;

public class CustomerGateway implements CustomerExternal {

    private final CustomerFeign customerFeign;

    public CustomerGateway(CustomerFeign customerFeign) {
        this.customerFeign = customerFeign;
    }

    @Override
    public CustomerExternalDomain getCustomer(String customerId) {
        final var customerResponse = customerFeign.getCustomerById(customerId);
        return customerResponse.toAggregate();
    }
}
