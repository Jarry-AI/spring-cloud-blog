package com.blog.register.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 * 密码加密
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-11-17
 */
public class BcryptUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "secret";
        String encoderPass = BCrypt.hashpw(pass,BCrypt.gensalt());
        boolean is = encoder.matches(pass,encoderPass);
        System.out.println(encoderPass);
        System.out.println(is);
    }

}
