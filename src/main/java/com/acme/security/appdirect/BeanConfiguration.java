package com.acme.security.appdirect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

@Configuration
@ComponentScan
public class BeanConfiguration extends BaseProtectedResourceDetails {
    @Value("${appdirect.oauth.consumer.key}")
    private String appDirectOAuthConsumerKey;

    @Value("${appdirect.oauth.consumer.secret}")
    private String appDirectOAuthConsumerSecret;

    @Bean
    public OAuthRestTemplate getAppDirectRestTemplate(){
        BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
        resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(appDirectOAuthConsumerSecret));
        resourceDetails.setConsumerKey(appDirectOAuthConsumerKey);

        return new OAuthRestTemplate(resourceDetails);
    }
}
