//package com.example.easyexcel.controller;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelReader;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.read.metadata.ReadSheet;
//import com.alibaba.excel.write.metadata.WriteSheet;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.example.easyexcel.entity.BksStudentTestscore;
//import com.example.easyexcel.model.*;
//import com.example.easyexcel.service.IStudentScoreService;
//import com.example.easyexcel.service.impl.BksStudentTestscoreServiceImpl;
//import com.example.easyexcel.service.impl.StudentScoreServiceImpl;
//import com.example.easyexcel.utils.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.annotations.Param;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author ZhenHX
// * description 使用easyExcel
// * @date 2020-04-03 15:54
// */
//@Api(tags = "使用easyExcel做数据导入导出")
//@RestController
//@RequestMapping("/dataProcessing")
//@Slf4j
//public class ExcelTestController {
//    private static final Integer MAX_USER_IMPORT = 1000;
//
//    @Autowired
//    private HttpServletResponse response;
//
//    @Autowired
//    private StudentScoreServiceImpl scoreService;
//
//    @Autowired
//    private BksStudentTestscoreServiceImpl testscoreService;
//
//    /**
//     * 设置导入的学校,考试批次
//     */
//    private static final String SCHOOL = "重庆市";
//    private static final String examName = "(文科)";
//
//    /**
//     * 导入：同步读，单sheet
//     * 注意：这里为了介绍 excel导入导出，代码都写在 controller，实际项目开发中，校验和处理代码建议放到 service
//     */
//    @ApiOperation(value = "导入数据",notes = "导入数据")
//    @ApiImplicitParam(name = "file",value = "文件对象",required = true,dataType = "org.springframework.web.multipart.MultipartFile")
//    @PostMapping("/import")
//    @Transactional(rollbackFor = Exception.class)
//    public void importData(MultipartFile file) throws Exception {
//
//        // 有个很重要的点 ScienceScoreListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
//        // 写法1：
////        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
////        EasyExcel.read(file.getInputStream(), BksStudentTestscore.class, new ScienceScoreListener()).sheet().doRead();
//        // 写法2：
////        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
////        ExcelReader excelReader = EasyExcel.read(file.getInputStream(), ScienceStudentScore.class, new ScienceScoreListener()).build();
////        ReadSheet readSheet = EasyExcel.readSheet(0).build();
////        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
////        excelReader.finish();
//
//
////        List<UserExcel> userExcelList = null;
//        // 1.excel同步读取数据
//        try {
//            log.info("============开始导入中.....===========");
//            Long start = System.currentTimeMillis();
//            log.info("开始时间:"+LocalDateTime.now());
//            List<BksStudentTestscore> studentTestscores = EasyExcel.read(new BufferedInputStream(file.getInputStream())).head(BksStudentTestscore.class).sheet().doReadSync();
//            log.info("开始执行插入时间:"+LocalDateTime.now());
//            log.info("===开始插入数据===共" + studentTestscores.size() + "条");
//
//
//            Boolean code = testscoreService.insertScore(studentTestscores);
////                    testscoreService.saveBatch(studentTestscores);
//            Long end = System.currentTimeMillis();
//            log.info("结束时间:"+LocalDateTime.now());
//            log.info("使用时间:"+((end - start)/1000)+ "秒");
//            log.info("===导入成功===");
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // 2.检查是否大于1000条
////        if (userExcelList.size() > MAX_USER_IMPORT) {
////            throw new Exception();
////        }
//
//        // 3.导入校验所有行列格式
////        checkImportData(userExcelList);
////         4.将 userExcelList 转成 userList
////        List<User> userList = userExcelList2UserList(userExcelList);
////        System.out.println(userList);
////         5.入库操作
////        userService.batchInsertOrUpdate(userList);
//    }
//
//    /**
//     * 多 sheet 导入
//     * @param file
//     * @throws ParseException
//     * @throws IOException
//     */
//    @PostMapping("/muchImport")
//    public void importDataByMoreSheet(MultipartFile file) throws Exception {
////        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        // 读取全部sheet
//        // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
////        EasyExcel.read(fileName, DemoData.class, new ScienceScoreListener()).doReadAll();
//        // 读取部分sheet
////        ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build();
//        // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
//
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    ExcelReader excelReader = EasyExcel.read(file.getInputStream(), ArtsStudentScore.class, new ArtsScoreListener()).build();
////                    ReadSheet readSheet = EasyExcel.readSheet(0).build();
////                    excelReader.read(readSheet);
////                    // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
////                    excelReader.finish();
////                } catch (Exception e){
////                    e.printStackTrace();
////                }
////            }
////        }).start();
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    ExcelReader excelReader = EasyExcel.read(file.getInputStream(), ScienceStudentScore.class, new ScienceScoreListener()).build();
////                    ReadSheet readSheet = EasyExcel.readSheet(1).build();
////                    excelReader.read(readSheet);
////                    // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
////                    excelReader.finish();
////                } catch (Exception e){
////                    e.printStackTrace();
////                }
////            }
////        }).start();
////        ReadSheet readSheet1 =
////                EasyExcel.readSheet(0).head(ArtsStudentScore.class).registerReadListener(new ArtsScoreListener()).build();
////        ReadSheet readSheet2 =
////                EasyExcel.readSheet(1).head(ScienceStudentScore.class).registerReadListener(new ScienceScoreListener()).build();
////         这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
////        excelReader.read(readSheet1, readSheet2);
////         这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
////        excelReader.finish();
//
//
////        List<UserExcel> userExcelList = new ArrayList();
//        // 1.excel同步读取数据
////        HashMap<String,Object> hashMap = new HashMap<>();
////        try {
////            ExcelReader excelReader1 = EasyExcel.read(new BufferedInputStream(file.getInputStream())).head(UserExcel.class).build();
////            List<ReadSheet> sheetList = excelReader.excelExecutor().sheetList();
//        List<StudentScore> studentlist = new ArrayList();
//
//
//            List<ArtsStudentScore> list1 = EasyExcel.read(new BufferedInputStream(file.getInputStream())).head(ArtsStudentScore.class).sheet(0).doReadSync();
//        List<StudentScore> studentlist1 = CopyUtils.copyList(list1,StudentScore.class);
//        System.out.println(studentlist1.size());
//        System.out.println(studentlist1.get(0));
//
////            hashMap.put("arts",list1);
//
//            List<ScienceStudentScore> list2 = EasyExcel.read(new BufferedInputStream(file.getInputStream())).head(ScienceStudentScore.class).sheet(1).doReadSync();
////            hashMap.put("science",list2);
//        List<StudentScore> studentlist2 = CopyUtils.copyList(list2,StudentScore.class);
//        System.out.println(studentlist2.size());
//        System.out.println(studentlist2.get(0));
//        studentlist.addAll(studentlist1);
//        studentlist.addAll(studentlist2);
//        System.out.println(studentlist.get(0));
//        System.out.println(studentlist.size());
//
//        Boolean code = scoreService.saveBatch(studentlist);
////        if (code) {
////            return "success";
////        } else {
////            return "fail";
////        }
//
//
//            // 导出
////        String fileName = "学生成绩表";
////        fileName = URLEncoder.encode(fileName, "UTF-8");
////        response.setContentType("application/vnd.ms-excel");
////        response.setCharacterEncoding("utf8");
////        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
////        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
////        WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "学生成绩表").head(StudentScore.class).build();
////
////        excelWriter.write(studentlist, writeSheet2);
////        excelWriter.finish();
//
//
//
//
////            List<UserExcel> childUserExcelList = new ArrayList();
////            for (ReadSheet sheet : sheetList) {
////                childUserExcelList = EasyExcel.read(new BufferedInputStream(file.getInputStream())).head(UserExcel.class).sheet(sheet.getSheetNo()).doReadSync();
////            }
////            userExcelList.addAll(childUserExcelList);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        // 2.检查是否大于1000条
////        if (userExcelList.size() > MAX_USER_IMPORT) {
////            throw new GlobalException(CodeMsg.OVER_MAX_USER_IMPORT_LIMIT.fillArgs(MAX_USER_IMPORT));
////        }
//        // 3.导入校验所有行列格式
////        checkImportData(userExcelList);
//        // 4.将 userExcelList 转成 userList
////        List<User> userList = userExcelList2UserList(userExcelList);
////        System.out.println(userList);
//        // 5.入库操作
////        userService.batchInsertOrUpdate(userList);
//    }
//
//
//    /**
//     * 下载Excel模板
//     */
//    @GetMapping("/excel/template")
//    public void downloadTemplate(HttpServletResponse response) {
//        String fileName = "导入用户模板";
//        String sheetName = "导入用户模板";
//        List<UserExcel> userList = new ArrayList();
//        userList.add(new UserExcel("saysky", "言曌", "123456", "847064370@qq.com", "http://xxx.com/xx.jpg", "0", "2017-12-31 12:13:14"));
//        userList.add(new UserExcel("qiqi", "琪琪", "123456", "666666@qq.com", "http://xxx.com/xx.jpg", "0", "2018-5-20 13:14:00"));
//        try {
//            ExcelUtils.writeExcel(response, userList, fileName, sheetName, UserExcel.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 导出
//     */
//    @GetMapping("/export")
//    public void export(HttpServletResponse response, @Param("examBatch") String examBatch,
//                            @Param("district") String district,@Param("school") String school) {
//        String fileName = "学生成绩";
//        String sheetName = "文科";
//        List<BksStudentTestscore> list = testscoreService.list(new LambdaQueryWrapper<BksStudentTestscore>()
//                .like(StringUtils.isNotEmpty(examBatch),BksStudentTestscore::getExamName,examBatch)
//                .eq(StringUtils.isNotEmpty(district),BksStudentTestscore::getDistrict,district)
//                .eq(StringUtils.isNotEmpty(school),BksStudentTestscore::getSchool,school));
//        System.out.println(list.size());
//        System.out.println(list.get(0));
//
//
//
////        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        List<User> userList = new ArrayList();
////        User user1 = new User(1L,"lisi","历史","123","123456@163.com","aaa",
////                2,LocalDateTime.now() ,"admin",LocalDateTime.now(),"admin2");
////        userList.add(user1);
////        List<UserExcel> userExcelList = new ArrayList();
////        for (User user : userList) {
////            UserExcel userExcel = UserExcel.builder()
////                    .username(user.getUsername())
////                    .password(user.getPassword())
////                    .nickname(user.getNickname())
////                    .email(user.getEmail())
////                    .avatar(user.getAvatar())
////                    .status(String.valueOf(user.getStatus()))
////                    .createdTime("2020/5/27 16:00").build();
////            userExcelList.add(userExcel);
////        }
//
//        try {
////            fileName = URLEncoder.encode(fileName, "UTF-8");
////            response.setContentType("application/vnd.ms-excel");
////            response.setCharacterEncoding("utf8");
////            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
////            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
////            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "客户信息").head(clazz).build();
////            WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "供应商信息").head(clazz).build();
////            excelWriter.write(data,writeSheet1);
////            excelWriter.write(data, writeSheet2);
////            excelWriter.finish();
//            // 获取数据
//
//
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setContentType("application/vnd.ms-excel");
//            response.setCharacterEncoding("utf8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
//            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
//            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "文科").head(StudentScore.class).build();
//            WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "理科").head(StudentScore.class).build();
////            excelWriter.write(artsData,writeSheet1);
////            excelWriter.write(scienceData, writeSheet2);
//            excelWriter.finish();
//
//
////            ExcelUtils.writeExcel(response, userExcelList, fileName, sheetName, UserExcel.class);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
////    private void checkImportData(List<UserExcel> userExcelList) throws Exception {
////        // 行号从第2行开始
////        int rowNo = 2;
////        // 遍历校验所有行和列
////        for (UserExcel userExcel : userExcelList) {
////            // 1.校验用户名
////            String username = userExcel.getUsername();
////            if (StringUtils.isEmpty(username)) {
////                throw new Exception("用户名");
////            }
////            if (username.length() > 20 || username.length() < 4) {
////                throw new Exception("用户名");
////            }
////            // 2.校验密码
////            String password = userExcel.getPassword();
////            if (StringUtils.isEmpty(password)) {
////                throw new Exception("密码");
////            }
////            if (password.length() > 100 || password.length() < 6) {
////                throw new Exception("密码");
////            }
////            // 3.校验昵称
////            String nickname = userExcel.getNickname();
////            if (StringUtils.isEmpty(nickname)) {
////                throw new Exception("昵称");
////            }
////            if (nickname.length() > 20 || nickname.length() < 2) {
////                throw new Exception("昵称");
////            }
////            // 4.校验Email
////            String email = userExcel.getEmail();
////            if (StringUtils.isEmpty(email)) {
////                throw new Exception("邮箱");
////            }
////            // 5.校验状态
////            String status = userExcel.getStatus();
////            if (StringUtils.isEmpty(status)) {
////                throw new Exception("状态");
////            }
////            if (!"0".equals(status) && !"1".equals(status)) {
////                throw new Exception("状态");
////            }
////            // 6.校验注册时间
////            String createdTime = userExcel.getCreatedTime();
////            if (StringUtils.isEmpty(createdTime)) {
////                throw new Exception("注册时间");
////            }
////
////        }
////    }
//    /**
//     * userExcelList转成userList
//     *
//     * @param userExcelList
//     * @return
//     */
//    private List<User> userExcelList2UserList(List<UserExcel> userExcelList) throws ParseException {
//        List<User> userList = new ArrayList();
//        User u = new User();
////        userList.stream().filter(x->x.getEmail()=="").forEach(m-> u.builder().email("ww").avatar("ww").build());
//
//        for (UserExcel userExcel : userExcelList) {
//            User user = User.builder()
//                    .username(userExcel.getUsername())
//                    .password(userExcel.getPassword())
//                    .nickname(userExcel.getNickname())
//                    .email(userExcel.getEmail())
//                    .avatar(userExcel.getAvatar())
//                    .status(Integer.valueOf(userExcel.getStatus()))
//                    .createdTime(LocalDateTimeUtils.formatDateTime(userExcel.getCreatedTime())).build();
//            user.setCreatedBy("import");
//            user.setUpdatedBy("import");
//            user.setUpdatedTime(LocalDateTime.now());
//            userList.add(user);
//        }
//        return userList;
//    }
//
//    // 定时任务
//
//    private void scheduler() throws SchedulerException, InterruptedException{
//
////        Date startDate = new Date();
////        startDate.setTime(startDate.getTime() + 5000);
////        Date endDate = new Date();
////        endDate.setTime(startDate.getTime() + 11000);
////        // 1、创建调度器Scheduler
////        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
////        Scheduler scheduler = schedulerFactory.getScheduler();
////        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
////        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class).usingJobData("jobDetail1","第一个测试任务")
////                .withIdentity("job1", "group1").build();
////        // 3、构建Trigger实例,每隔1s执行一次
////        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
////                .usingJobData("trigger1","第一个测试任务的触发器")
//////                .startNow()//立即生效
////                .startAt(startDate)
////                .endAt(endDate)
////                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
////                        .withIntervalInSeconds(2)
//////                        .withIntervalInSeconds(1)//每隔1s执行一次
////                        .repeatForever()).build();//一直执行
////
////        //4、执行
////        scheduler.scheduleJob(jobDetail, trigger);
////        System.out.println("--------scheduler start ! ------------");
////        scheduler.start();
//
//        //睡眠
////        TimeUnit.MINUTES.sleep(1);
////        scheduler.shutdown();
////        System.out.println("--------scheduler shutdown ! ------------");
//    }
//
//
//}
