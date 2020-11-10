package com.blog.register.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 配置资源服务器
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-09-21
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 配置访问
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest().and().authorizeRequests()
                .antMatchers("/getSchool/*").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * 判断请求路径是否有access_token或者请求头带authorization
     */
    private static class HttpOauthMatcher implements RequestMatcher {

        @Override
        public boolean matches(HttpServletRequest httpRequest) {
            // 判断请求路径中是否带参数access_token
            if (httpRequest.getParameter(OAuth2AccessToken.ACCESS_TOKEN) != null) {
                return true;
            }
            // 判断请求头
            String authorization = httpRequest.getHeader("Authorization");
            if (authorization != null) {
                return authorization.startsWith(OAuth2AccessToken.BEARER_TYPE);
            }
            return false;
        }
    }



}
