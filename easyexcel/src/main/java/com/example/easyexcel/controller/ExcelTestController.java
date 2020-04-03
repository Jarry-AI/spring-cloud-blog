package com.example.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.easyexcel.model.User;
import com.example.easyexcel.model.UserExcel;
import com.example.easyexcel.utils.ExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZhenHX
 * @description 使用easyExcel
 * @date 2020-04-03 15:54
 */
@RestController
public class ExcelTestController {
    private static final Integer MAX_USER_IMPORT = 1000;


    /**
     * 导入：同步读，单sheet
     * 注意：这里为了介绍 excel导入导出，代码都写在 controller，实际项目开发中，校验和处理代码建议放到 service
     */
//    @PostMapping("/excel/import")
//    public void importData(MultipartFile file) throws Exception {
//        List<UserExcel> userExcelList = null;
//        // 1.excel同步读取数据
//        try {
//            userExcelList = EasyExcel.read(new BufferedInputStream(file.getInputStream())).head(UserExcel.class).sheet().doReadSync();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // 2.检查是否大于1000条
//        if (userExcelList.size() > MAX_USER_IMPORT) {
//            throw new Exception();
//        }
//        // 3.导入校验所有行列格式
//        checkImportData(userExcelList);
//        // 4.将 userExcelList 转成 userList
//        List<User> userList = userExcelList2UserList(userExcelList);
//        // 5.入库操作
////        userService.batchInsertOrUpdate(userList);
//    }

    /**
     * 下载Excel模板
     */
    @GetMapping("/excel/template")
    public void downloadTemplate(HttpServletResponse response) {
        String fileName = "导入用户模板";
        String sheetName = "导入用户模板";
        List<UserExcel> userList = new ArrayList<>();
        userList.add(new UserExcel("saysky", "言曌", "123456", "847064370@qq.com", "http://xxx.com/xx.jpg", "0", "2017-12-31 12:13:14"));
        userList.add(new UserExcel("qiqi", "琪琪", "123456", "666666@qq.com", "http://xxx.com/xx.jpg", "0", "2018-5-20 13:14:00"));
        try {
            ExcelUtils.writeExcel(response, userList, fileName, sheetName, UserExcel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出
     */
    @GetMapping("/excel/export")
    public static void main(HttpServletResponse response) {
        String fileName = "用户列表";
        String sheetName = "用户列表";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> userList = new ArrayList<>();
        User user1 = new User(1L,"lisi","历史","123","123456@163.com","aaa",
                2,new Date(),"admin",new Date(),"admin2");
        userList.add(user1);


        List<UserExcel> userExcelList = new ArrayList<>();


        for (User user : userList) {
            UserExcel userExcel = UserExcel.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .avatar(user.getAvatar())
                    .status(String.valueOf(user.getStatus()))
                    .createdTime(dateFormat.format(user.getCreatedTime())).build();
            userExcelList.add(userExcel);
        }
        try {
            ExcelUtils.writeExcel(response, userExcelList, fileName, sheetName, UserExcel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




//    private void checkImportData(List<UserExcel> userExcelList) throws Exception {
//        // 行号从第2行开始
//        int rowNo = 2;
//        // 遍历校验所有行和列
//        for (UserExcel userExcel : userExcelList) {
//            // 1.校验用户名
//            String username = userExcel.getUsername();
//            if (StringUtils.isEmpty(username)) {
//                throw new Exception("用户名");
//            }
//            if (username.length() > 20 || username.length() < 4) {
//                throw new Exception("用户名");
//            }
//            // 2.校验密码
//            String password = userExcel.getPassword();
//            if (StringUtils.isEmpty(password)) {
//                throw new Exception("密码");
//            }
//            if (password.length() > 100 || password.length() < 6) {
//                throw new Exception("密码");
//            }
//            // 3.校验昵称
//            String nickname = userExcel.getNickname();
//            if (StringUtils.isEmpty(nickname)) {
//                throw new Exception("昵称");
//            }
//            if (nickname.length() > 20 || nickname.length() < 2) {
//                throw new Exception("昵称");
//            }
//            // 4.校验Email
//            String email = userExcel.getEmail();
//            if (StringUtils.isEmpty(email)) {
//                throw new Exception("邮箱");
//            }
//            // 5.校验状态
//            String status = userExcel.getStatus();
//            if (StringUtils.isEmpty(status)) {
//                throw new Exception("状态");
//            }
//            if (!"0".equals(status) && !"1".equals(status)) {
//                throw new Exception("状态");
//            }
//            // 6.校验注册时间
//            String createdTime = userExcel.getCreatedTime();
//            if (StringUtils.isEmpty(createdTime)) {
//                throw new Exception("注册时间");
//            }
//
//        }
//    }
    /**
     * userExcelList转成userList
     *
     * @param userExcelList
     * @return
     */
//    private List<User> userExcelList2UserList(List<UserExcel> userExcelList) throws ParseException {
//        Date now = new Date();
//        List<User> userList = new ArrayList<>();
//        for (UserExcel userExcel : userExcelList) {
//            User user = User.builder()
//                    .username(userExcel.getUsername())
//                    .password(userExcel.getPassword())
//                    .nickname(userExcel.getNickname())
//                    .email(userExcel.getEmail())
//                    .avatar(userExcel.getAvatar())
//                    .status(Integer.valueOf(userExcel.getStatus()))
//                    .createdTime(DATE_TIME_FORMAT.parse(userExcel.getCreatedTime())).build();
//            user.setCreatedBy("import");
//            user.setUpdatedBy("import");
//            user.setUpdatedTime(new LocalDateTime());
//            userList.add(user);
//        }
//        return userList;
//    }

}
