package com.biblio.zuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }


//    @Bean
//    UserInfoRestTemplateCustomizer userInfoRestTemplateCustomizer(LoadBalancerInterceptor loadBalancerInterceptor) {
//        return template -> {
//            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//            interceptors.add(loadBalancerInterceptor);
//            AccessTokenProviderChain accessTokenProviderChain = Stream
//                    .of(new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
//                            new ResourceOwnerPasswordAccessTokenProvider(), new ClientCredentialsAccessTokenProvider())
//                    .peek(tp -> tp.setInterceptors(interceptors))
//                    .collect(Collectors.collectingAndThen(Collectors.toList(), AccessTokenProviderChain::new));
//            template.setAccessTokenProvider(accessTokenProviderChain);
//        };
//    }
}
