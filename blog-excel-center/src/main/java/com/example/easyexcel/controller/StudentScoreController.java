package com.example.easyexcel.controller;


import com.example.easyexcel.entity.Result;
import com.example.easyexcel.mapper.StudentScoreMapper;
import com.example.easyexcel.service.UserLikeNumService;
import com.example.easyexcel.service.impl.StudentScoreServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhen huaxiang
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/score")
@Api(tags = "分数模块")
public class StudentScoreController {






    @Resource
    private StudentScoreServiceImpl scoreService;

    @Resource
    private UserLikeNumService numService;

    @Resource
    private StudentScoreMapper scoreMapper;

    @Autowired
    private com.example.easyexcel.service.Test test;

    @PostMapping("/toHome")
    @ApiOperation(value = "主页访问")
    public String toHome(){
        System.out.println("===========登录访问主页===========");
        return "登录访问主页";

    }


    @GetMapping("/get")
    @ApiOperation(value = "获取学生分页成绩")
    @Secured("ROLE1")
    public Result<Boolean> get(){

        System.out.println("-----");
        return new Result<>(true);

    }

    //psvm 补全Main方法快捷键
    //sout 打印
    private static final String filePath = "D:/听力.mp4";
    private static String url = "http://bw-bks.oss-cn-chengdu.aliyuncs.com/pdf/bks5b5dbbfbbe5c5499c40a36c16badbd92.mp3";
    //下载文件的线程数
    private static int mThreadCount = 8;


    public static void main(String[] args) {



//        try {
//
//            //获取要进行下载文件的长度
//            URL mUrl = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
//            connection.setDoInput(true);
//            connection.setRequestMethod("GET");
//            long contentLength = connection.getContentLengthLong();
//            System.out.println("要下载文件的长度:"+contentLength);
//
//            //根据文件的长度创建一个空的文件
//            RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
//            //提前设置长度进行占位
//            randomAccessFile.setLength(contentLength);
//
//
//            // 计算每个子线程要进行下载的文件块的数据的起始位置和结束位置
//            for(int i=0;i<mThreadCount;i++){
//
//
//                long startIndex = i*(contentLength/(mThreadCount-1));
//
//                Long endIndex = (i+1)*(contentLength/(mThreadCount-1))-1;
//
//                //最后一个线程下载最后剩下的文件块
//                if(mThreadCount-1 == i){
//
//                    endIndex = contentLength-1;
//
//                }
//
//
//                //创建线程进行分块下载
//                DownLoadThread downLoadThread = new DownLoadThread(startIndex, endIndex, url, "线程:" + i);
//                downLoadThread.setName("线程："+i);
//                downLoadThread.start();
//
//
//                System.out.println("第"+i+"线程:"+"起始位置:"+startIndex+" 结束位置:"+endIndex);
//
//            }
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//        }


    }


    static class DownLoadThread extends Thread{

        private long startIndex;
        private long endIndex;
        private String downLoadUrl;

        public DownLoadThread(long startIndex, long endIndex, String downLoadUrl, String threadName) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.downLoadUrl = downLoadUrl;
        }



        @Override
        public void run() {


            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(downLoadUrl).openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                //设置请求头,告知服务器从指定的位置返回数据
                connection.setRequestProperty("Range","bytes="+startIndex+"-"+endIndex);

                int responseCode = connection.getResponseCode();

                System.out.println("服务器返回的响应码："+responseCode);

                if(responseCode == 206){

                    //获取要下载文件的流
                    InputStream is = connection.getInputStream();

                    RandomAccessFile accessFile = new RandomAccessFile(filePath, "rw");
                    //指定文件写入的开始位置
                    accessFile.seek(startIndex);

                    byte[] bytes = new byte[1024];
                    int len = -1;
                    int count = 0;

                    while ((len = is.read(bytes))!=-1){

                        accessFile.write(bytes,0,len);

                        count+=bytes.length;


                        System.out.println(getName()+"的下载进度:"+count);
                    }

                    System.out.println(getName()+"下载完成！！！");

                    accessFile.close();

                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }

        }
    }

    // 下载文件
    public static String downLoadFile(HttpServletResponse response, String fileUrl, String fileName) throws IOException {
        ServletOutputStream out = null;
        BufferedInputStream ips = null;
        HttpURLConnection conn = null;
        try {

            URL url = new URL(fileUrl);
            conn = (HttpURLConnection)url.openConnection();
            ips =(BufferedInputStream)conn.getInputStream();
            response.setContentType("multipart/form-data");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" +
                    URLEncoder.encode(fileName, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 100];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(conn!=null){
                conn.disconnect();
            }
            if(ips != null) {
                ips.close();
            }
            if(out != null) {
                out.close();
            }

        }
        return "success";
    }


}

