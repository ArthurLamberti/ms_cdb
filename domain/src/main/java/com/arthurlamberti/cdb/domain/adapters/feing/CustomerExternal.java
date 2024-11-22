package com.arthurlamberti.cdb.domain.adapters.feing;

import com.arthurlamberti.cdb.domain.adapters.models.CustomerExternalDomain;

public interface CustomerExternal {
    CustomerExternalDomain getCustomer(String customerId);
}
