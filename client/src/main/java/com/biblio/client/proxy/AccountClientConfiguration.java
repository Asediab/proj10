package com.biblio.client.proxy;

import feign.RequestInterceptor;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import java.util.Arrays;

public class AccountClientConfiguration {

    @Bean
    RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    }


    private OAuth2ProtectedResourceDetails resource() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername("asediaboli@mail.ru");
        resourceDetails.setPassword("user");
        resourceDetails.setAccessTokenUri("http://localhost:9096/uaa/oauth/token");
        resourceDetails.setClientId("browser");
        resourceDetails.setGrantType("password");
        resourceDetails.setScope(Arrays.asList("ui"));
        return resourceDetails;
    }
}
