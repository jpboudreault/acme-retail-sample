package com.acme.security;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.Map;

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

    @Bean
    public ConsumerDetailsService consumerDetailsService() {
        InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();

        BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
        consumerDetails.setConsumerKey(appDirectOAuthConsumerKey);
        consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(appDirectOAuthConsumerSecret));
        consumerDetails.setRequiredToObtainAuthenticatedToken(false);

        Map<String, BaseConsumerDetails> consumerDetailsStore = Maps.newHashMap();
        consumerDetailsStore.put(appDirectOAuthConsumerKey, consumerDetails);

        consumerDetailsService.setConsumerDetailsStore(consumerDetailsStore);

        return consumerDetailsService;
    }

    @Bean
    OAuthProviderProcessingFilter oAuthProviderProcessingFilter() {
        List<RequestMatcher> requestMatchers = Lists.newArrayList();
        requestMatchers.add(new AntPathRequestMatcher("/api/app-direct/**"));

        ProtectedResourceProcessingFilter filter = new CustomResourceProcessingFilter(requestMatchers);

        filter.setConsumerDetailsService(consumerDetailsService());
        filter.setTokenServices(providerTokenServices());
        return filter;
    }

    @Bean
    public OAuthProviderTokenServices providerTokenServices() {
        return new InMemoryProviderTokenServices();
    }
}
