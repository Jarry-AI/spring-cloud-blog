package com.example.easyexcel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * <p>
 * oauth2.0配置
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-06-07
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {


    // 授权服务器
    public AuthorizationServer() {
        super();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    // 授权服务安全配置(令牌端点的安全约束)
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    // 客服端详情配置
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用内存方式配置
        clients.inMemory()
                .withClient("c1")
                .secret("secret")
                .resourceIds("res1","p1")
                .authorizedGrantTypes("authorization_code","password")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://www.baidu.com");

    }



    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Override
    // 授权服务访问端点配置(配置令牌（token）的访问端点和令牌服务(token services))
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        //设置授权码模式的授权码如何 存取，暂时采用内存方式
         return new InMemoryAuthorizationCodeServices();
    }

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Bean
    public AuthorizationServerTokenServices tokenService(){

        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenStore);
        services.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        services.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期
        return services;
    }


}
