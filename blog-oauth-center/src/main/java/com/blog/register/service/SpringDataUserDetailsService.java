package com.blog.register.service;

import com.blog.register.dao.UserDao;
import com.blog.register.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-09-27
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println("username:" + s);

        UserDto userDto = userDao.getUserByUserName(s);

        if(userDto == null){ return null; }

        List<String> permissionList = userDao.getPermission(userDto.getId());

        String[] array = new String[permissionList.size()];

        permissionList.toArray(array);

        UserDetails user = User.withUsername(userDto.getFullname())
                .password(userDto.getPassword()).authorities(array).build();

        return user;
    }
}
