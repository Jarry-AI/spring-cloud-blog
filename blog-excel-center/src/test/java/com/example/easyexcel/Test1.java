package com.example.easyexcel;

import cn.hutool.core.thread.ThreadUtil;
import com.ximpleware.*;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-04
 */
@SpringBootTest
public class Test1 {

    @Test
    // 创建httpclient连接池
    public void get5(){


        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        // =======后面一样===== 注意最后不关闭httpClient,由连接池管理


    }


    @Test
    // httpClient的POST请求
    public void get4() throws Exception{
        // 1.打开浏览器,创建httpClient对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 2.输入网址,发起http get请求,创建HttpGet对象
        HttpPost httpPost = new HttpPost("https://m.cctv.com/quanzhannav2019/index.shtml");
        // 声明List集合,封装表单中的参数
        List<NameValuePair> params = new ArrayList<>();
        // 创建表单的Entity对象
        params.add(new BasicNameValuePair("spm","C73544894212.PrKk0lxEAExm.EEVSt6b0zRs2.3"));
        // 设置表单的Entity对象到post对象中
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(formEntity);
        System.err.println("访问的地址是:"+httpPost);
        CloseableHttpResponse execute = null;
        // 3.使用httpClient对象发起请求
        try {
            execute = client.execute(httpPost);
            // 4.解析响应,获取数据
            // 判断状态码是否是200
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                String s = EntityUtils.toString(entity,"utf8");
                System.out.println(s);

            } else {
                System.out.println("请求失败!");
            }
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                execute.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Test
    // httpClient的get请求
    public void get3() throws Exception{

        // 1.打开浏览器,创建httpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建URI,让get请求携带参数 https://m.cctv.com/quanzhannav2019/index.shtml?spm=C73544894212.PrKk0lxEAExm.EEVSt6b0zRs2.3
        URIBuilder builder = new URIBuilder("https://m.cctv.com/quanzhannav2019/index.shtml");
        builder.addParameter("spm","C73544894212.PrKk0lxEAExm.EEVSt6b0zRs2.3");

        // 2.输入网址,发起http get请求,创建HttpGet对象
        HttpGet httpGet = new HttpGet(builder.build());
        // 配置请求信息
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000) //创建连接的最长时间
                .setConnectionRequestTimeout(500).build();// 设置获取连接的最长时间
        httpGet.setConfig(requestConfig);

        System.err.println("访问的地址是:"+httpGet);
        CloseableHttpResponse execute = null;
        // 3.使用httpClient对象发起请求
        try {
           execute = client.execute(httpGet);
            // 4.解析响应,获取数据
            // 判断状态码是否是200
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                String s = EntityUtils.toString(entity,"utf8");
                System.out.println(s);

            } else {
                System.out.println("请求失败!");
            }
        }
            catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                execute.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



    @Test
    public void get2() throws Exception{
        System.out.println("----1----");
        Thread.sleep(1000);
        ThreadUtil.execAsync(()->{
            ThreadUtil.sleep(1000);
            System.out.println("----3----");
        });

        System.out.println(String.format("bks:valid:time:sms:%s:%s:%s", "LOGIN", "18859632148", LocalDateTime.now()));

    }
    @Test
    public void get1(){
        try {
            // open a file and read the content into a byte array
            VTDGen vg = new VTDGen();
            String path = Test1.class.getResource("").getPath();
            System.out.println(path);

            if (vg.parseFile(path + "oldpo.xml", true)){
                VTDNav vn = vg.getNav();
                File fo = new File("f:/newpo.xml");
                FileOutputStream fos = new FileOutputStream(fo);
                AutoPilot ap = new AutoPilot(vn);
                XMLModifier xm = new XMLModifier(vn);
                ap.selectXPath("/purchaseOrder/items/item[@partNum='872-AA']");
                int i = -1;
                while((i=ap.evalXPath())!=-1){
                    xm.remove();
                    xm.insertBeforeElement("<something/>\n");
                }
                ap.selectXPath("/purchaseOrder/items/item/USPrice[.<40]/text()");
                while((i=ap.evalXPath())!=-1){
                    xm.updateToken(i,"200");
                }
                xm.output(fos);
                fos.close();
            }
        }
        catch (NavException e){
            System.out.println(" Exception during navigation "+e);
        }
        catch (ModifyException e){
            System.out.println(" Modify exception occurred "+e);
        }
        catch (Exception e){
        }
    }

}
