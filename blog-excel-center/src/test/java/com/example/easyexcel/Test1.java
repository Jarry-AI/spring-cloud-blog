package com.example.easyexcel;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSON;
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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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
    // 测试jsoup
    public void jsoup1() throws Exception{
        // 解析url,
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
        System.out.println(doc);
        // 使用标签选择器,获取title中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);

    }

    @Test
    // 使用jsoup读取字符串
    public void jsoup2() throws Exception{

        String s = "<p style=\"text-indent: 2em;\"><span>学校简况：</span><span>四川文理学院，位于四川东出北上综合交通枢纽城市、川渝陕结合部区域中心城市达州市，目前是川东地区唯一省管普通本科高校，学校办学文脉可溯及清末龙山书院，1976年开始举办高等教育，时称达县师范学院，1978年经国务院批准为全日制普通高校，相继更名为达县师范专科学校、达县师范高等专科学校。2006年2月经教育部批准成为本科高校，2016年被确定为“四川省高校整体转型发展试点单位”，2018年被增列为“四川省硕士学位授权立项建设单位”。</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>学校拥有莲湖、南坝两个校区，占地面积710095.7平方米（莲湖校区666666.7平方米，南坝校区43429平方米），校舍建筑面积255000平方米。馆藏纸质图书144.6万册，电子图书49万余册，中外文纸质报刊400余种，电子期刊8000余种，教学科研仪器设备值10538.5万元。现有各类教职工1252人，外籍教师16人，其中专任教师765人，（高级职称教师占专任教师总数的30.7%，具有博士、硕士学位的占83.7%），享受国务院特殊津贴1人，四川省学术技术带头人及后备人选6人，突出贡献专家3人。全日制在校普通学生14031人，留学生65人，面向全国22个省市招生。开设有53个本科专业，涵盖文学、理学、工学、教育学、艺术学、管理学、历史学、法学、经济学九大学科门类。</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>人才培养：</span><span>学校坚持“学生主体、教师主导、环境育人、社会合作”的“四圆同心”办学思路，积极探索知识结构模块化、理论实践融合化、教学服务信息化、考核评价常态化的“四化一体”人才培养模式改革，实施四年递进创新创业教育计划，努力通过通识核心课程、专业基础课程、专业核心课程、应用实践课程、复合素质课程五大课程体系，培养学生成长为“三心四能五复合”的高素质应用型、复合型人才。</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>学校与国内100多家地方政府、大中型企业和事业单位建立共建合作关系。建校以来为社会培养了7万余名大学毕业生，毕业生专业基础知识扎实，富有实践能力和创新意识，深受用人单位欢迎，就业率始终居于全省同类高校前列。</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>学术研究：</span><span>学校实施科研强校战略，注重科研平台和团队建设。建有省级重点研究基地四川革命老区发展研究中心、青少年文学艺术社科普及基地，省高校重点实验室特色植物开发研究实验室，参建“工业有机固体废物资源化处理”2011协同创新中心、四川省磷资源综合利用工程技术中心，建有巴文化研究院、川陕革命老区振兴发展研究院、高等教育研究所、秦巴文化产业研究院、智能制造产业技术研究院、国家城市污水处理及资源化工程技术研究中心川东分中心、油气田废水处理技术研发中心、巴山作家群研究院、书法艺术研究院等近30个科技创新平台；建有四川省社会科学高水平研究团队“四川革命老区生态文明建设研究团队”和四川省教育厅“秦巴文化研究传播创新团队”等22个科研团队。近年来，教职工承担国家级科研课题14项，省部级科研课题117项，市厅级课题1394项，获省市社科优秀成果奖300余项、科技成果奖近10项，获准国家专利近50项，30多项科研成果被地方各级政府领导批示或采纳，出版专著50余部，公开发表学术论文近4000余篇，400余篇文章被权威检索工具（期刊）转载或收录。</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>校园文化：</span><span>学校注重环境与校园文化对师生的潜移默化影响，充分发挥校园自然和人文景观及文化艺术科技活动的育人功能，打造生态、科技、文化校园。近五年参加全国和全省大学生艺术节、数学建模、电子设计、啦啦操、曲棍球等赛事，获得国家级奖励近百项、省级奖励400多项。舞蹈《致青春》《魂铸巴山》获第九届中国舞蹈“荷花奖”银奖；舞蹈《雨润巴山》《背山背水背太阳》《赤雪同行》、绘画《秋水游》等数十件作品荣获全国大学生艺术节展演一等奖；代表四川省参加全国第二届军事训练营，囊括理论知识竞赛、无线电测向、识图用图训练、实弹射击四个训练科目一等奖；2017年获教育部“国防教育特色学校”称号；中国特色社会主义理论研究会获“全国优秀理论社团”，蒹葭汉服社获“全国优秀大学生国学社团”，电脑学会获“全国学生最具影响力科技社团”，学生社团联合会获“全国学生最具影响力社团联合会”，37名学生登上2017年央视春晚舞台，3名学生代表中国高校参加第七届亚洲杯室内女子曲棍球赛荣获亚洲第6名，参加2018年美国旧金山世界排舞冠军赛荣获4项第1名。 </span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>国际教育：</span><span>学校积极开拓教育国际视野，与美国、英国、法国、澳大利亚、捷克、韩国、马来西亚、泰国、老挝等国家10余所高校建立合作与交流关系，常年选派学生和骨干教师赴草堂大学、帕拉茨基大学、北方大学、博特拉大学、斯旺西大学、高地与群岛大学等交流和访学，推荐品学兼优的学生赴美国带薪实习和攻读硕士学位。</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>学校坚持以习近平新时代中国特色社会主义思想为指导，全面贯彻党和国家教育方针，遵循高等教育规律，秉承“博文大理，厚德笃行”的校训，弘扬“艰难困苦，玉汝于成”的办学精神，吸取古今优秀教育理念和中外教育改革最新成果，创新人才培养模式，立足川东，服务四川，面向全国，按照“三步走”发展战略和“1234”总体部署，全面落实立德树人根本任务，坚持内涵发展、转型发展、创新发展、特色发展，培养德智体美劳全面发展的高素质人才，努力把学校建成特色鲜明、优势突出的高水平应用型大学。</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>学校地址：四川省达州市通川区塔石路中段519号</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>联系电话：0818-2790101 0818-2790027</span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span><img height=\"35\" src=\"/app/static/ueditor/themes/default/images/spacer.gif\" alt=\"文本框: 四川文理学院官方微信 \" width=\"100%\"></span></p>\n" +
                "  <p style=\"text-indent: 2em;\"><span>学校网址：</span><span><span>http://www.sasu.edu.cn/</span></span><span> </span><span>邮编：</span><span>635000</span></p>";

        Document doc = Jsoup.parse(s);
        Elements allElements = doc.getAllElements();
        for (Element e: allElements
             ) {
            e.removeAttr("style");
            e.removeAttr("width");
            e.removeAttr("valign");
            e.removeAttr("border");
            e.removeAttr("align");
            e.removeAttr("height");
            if (e.tagName().matches("h1|h2|h3|h4")) {
                e.tagName("p");
            }

            if (e.tagName().matches("td")) {
                e.attr("style","border-left: 1px solid black;border-top: 1px solid black;");
            }

            if (e.tagName().matches("table")) {
                e.attr("style","border-right: 1px solid black;border-bottom: 1px solid black;" +
                        "border-collapse:collapse;" + "text-align: center;margin:0 auto;");
            }

            if (e.tagName().equals("img")) {
                e.attr("width","100%");
            }

            if (e.tagName().matches("p")) {
                e.attr("style","text-indent: 2em;");
            }
        }
        // 使用标签选择器,获取title中的内容
        String content = doc.toString().replaceAll("<html>|</html>|<head></head>|<body>|</body>|　　|　 |<tbody>|</tbody>|<strong>|</strong>", "").trim();
        content = content.replace("<p style=\"text-indent: 2em;\"><img", "<p><img");
        content = content.replace("border-top: 1px solid black;\"><p style=\"text-indent: 2em;\"",
                "border-top: 1px solid black;\"><p");
        content = content.replace("src=\"/","style=\"display: none;\" src=\"/");

        System.err.println(content);

    }



    @Test
    // 创建httpclient连接池
    public void get5() throws Exception{

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        // =======后面一样===== 注意最后不关闭httpClient,由连接池管理
        // 设置最大连接数
        cm.setMaxTotal(100);
        // 设置每个主机最大连接数
        cm.setDefaultMaxPerRoute(10);
        // 使用连接池管理发起请求
        get4(httpClient);

    }

    // httpClient的POST请求
    public void get4(CloseableHttpClient client) throws Exception{
        // 1.打开浏览器,创建httpClient对象
//        CloseableHttpClient client = HttpClients.createDefault();

        // 2.输入网址,发起http get请求,创建HttpGet对象
        HttpPost httpPost = new HttpPost("https://m.cctv.com/quanzhannav2019/index.shtml");

        // 配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间
                .setConnectionRequestTimeout(500) // 设置获取连接的最长时间
                .setSocketTimeout(10 * 1000) // 设置数据传输的最长时间
                .build();
        // 给请求设置请求信息
        httpPost.setConfig(config);

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
                // 使用连接池时不关闭,由连接池管理
//                client.close();
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
    // 解析读取xml文件
    public void get1(){
        try {
            // open a file and read the content into a byte array
            VTDGen vg = new VTDGen();
            String path = Test1.class.getResource("").getPath();
            System.out.println(path);

            if (vg.parseFile("f:/oldpo.xml", true)){
                VTDNav vn = vg.getNav();
                File fo = new File("f:/newpo.xml");
                FileOutputStream fos = new FileOutputStream(fo);
                AutoPilot ap = new AutoPilot(vn);
                System.err.println(JSON.toJSONString(ap));
                XMLModifier xm = new XMLModifier(vn);
//                ap.selectXPath("/span");
//                int i = -1;
//                while((i=ap.evalXPath())!=-1){
//                    xm.remove();
//                }
//                ap.selectXPath("/p");
//                while((i=ap.evalXPath())!=-1){
//                    xm.updateToken(i,"<p style=\"text-indent: 2em;\">");
//                }
                xm.output(fos);
                fos.close();
            }
        }
//        catch (NavException e){
//            System.out.println(" Exception during navigation "+e);
//        }
        catch (ModifyException e){
            System.out.println(" Modify exception occurred "+e);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
