package com.blog.register.dao;

import com.blog.register.model.PermissionDto;
import com.blog.register.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-09-28
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取信息
     * @param username 用户名
     * @return 用户详情
     */
    public UserDto getUserByUserName(String username) {

        String sql = "select * from t_user where username = ?";

        List<UserDto> user = jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(UserDto.class));

        return user.get(0);

    }

    public List<String> getPermission(String userId){
        String sql = "SELECT * FROM t_permission WHERE id in\n" +
                "(SELECT permission_id FROM t_role_permission WHERE role_id in \n" +
                "(SELECT role_id FROM t_user_role WHERE user_id = ?))";

        List<PermissionDto> list = jdbcTemplate.query(sql,new Object[]{userId},new BeanPropertyRowMapper<>(PermissionDto.class));

        List<String> permissionList = new ArrayList<>();

        list.forEach(x->permissionList.add(x.getCode()));

        return permissionList;

    }


}
