package com.acme.security.appdirect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

@Configuration
public class BeanConfiguration extends BaseProtectedResourceDetails {
    @Value("${appdirect.oauth.consumer.key}")
    private static String appDirectOAuthConsumerKey;

    @Value("${appdirect.oauth.consumer.secret}")
    private static String appDirectOAuthConsumerSecret;

    @Bean
    public OAuthRestTemplate getAppDirectRestTemplate(){
        BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
        resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(appDirectOAuthConsumerSecret));
        resourceDetails.setConsumerKey(appDirectOAuthConsumerKey);

        return new OAuthRestTemplate(resourceDetails);
    }
}
