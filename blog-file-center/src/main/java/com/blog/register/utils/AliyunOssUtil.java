package com.blog.register.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p>
 * 阿里云Oss工具包
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-07-02
 */
@Component
@RefreshScope
public class AliyunOssUtil {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.flag}")
    private String flag;

    private OSS ossClient;

    private OSS getInstance() {
        if(ossClient==null){
            synchronized(AliyunOssUtil.class){
                if(ossClient==null){
                    ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    /**
     * 上传
     */
    public void upload(InputStream file, String fileName){

        getInstance().putObject(bucketName,fileName,file);
        ossClient.shutdown();
    }

    /**
     * 下载
     */
    public void download(String objectName){
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                while (true) {
                    String line = reader.readLine();
                    if (line == null){
                        break;
                    }
                    System.out.println("\n" + line);
                }
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            }catch (Exception e){

            }

        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
