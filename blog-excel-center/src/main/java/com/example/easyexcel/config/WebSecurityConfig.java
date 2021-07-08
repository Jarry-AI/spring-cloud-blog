package com.example.easyexcel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Spring Security配置类
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 用户认证配置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 指定用户认证时，默认从哪里获取认证用户信息
         */
        auth.userDetailsService(userDetailsService());
    }

    /**
     * Http安全配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 表单登录：使用默认的表单登录页面和登录端点/login进行登录
         * 退出登录：使用默认的退出登录端点/logout退出登录
         * 记住我：使用默认的“记住我”功能，把记住用户已登录的Token保存在内存里，记住30分钟
         * 权限：除了/toHome和/toUser之外的其它请求都要求用户已登录
         * 注意：Controller中也对URL配置了权限，如果WebSecurityConfig中和Controller中都对某文化URL配置了权限，则取较小的权限
         */
        http
                .formLogin()
                .successForwardUrl("/score/toHome")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(1800)
                .and()
                .authorizeRequests()
                .antMatchers("/toHome", "/toUser")
                .authenticated()
                .anyRequest()
                .permitAll();
    }

    /**
     * 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * BCryptPasswordEncoder：相同的密码明文每次生成的密文都不同，安全性更高
         */
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 封装用户信息
     */
    @Override
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("lisi").password("123").authorities("p1").roles("ROLE1").build());
        return manager;

    }
}
