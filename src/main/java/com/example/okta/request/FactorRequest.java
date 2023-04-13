package com.example.okta.request;

import com.okta.sdk.resource.user.factor.FactorProvider;
import com.okta.sdk.resource.user.factor.FactorType;
import lombok.Data;

@Data
public class FactorRequest {
    private FactorType factorType;
    private FactorProvider factorProvider;
}
