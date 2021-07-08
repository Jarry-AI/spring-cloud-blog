package com.example.easyexcel.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * <p>
 * poi文档处理
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-03-08
 */
public class PoiUtils {

    public static void main(String[] args) throws Exception{
        expertWorld();
    }

    public static void expertWorld() throws Exception{

        XWPFDocument doc = new XWPFDocument(); // 创建对象
        XWPFParagraph para;
        XWPFRun run;
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);//样式居中
        XWPFRun titleFun = paragraph.createRun();    //创建文本对象
        titleFun.addTab();
        titleFun.setText("人生并不是一段具体的旅程，而是一个概念、一种经历。人们皆说人生实苦，但是人生的滋味又怎能是一种味道就可以概括的呢？食物的烹饪有着酸、甜、苦、辣、咸，而人生的滋味是一种混合的味道，很多滋味需要细细品味,《人生滋味》是冯骥才先生的经典散文合集，本书一共分为七章，讲述了冯骥才先生对于生活、万物、四季、人生、文雅、山川、文化的独到见解。"); //设置标题的名字
        titleFun.setBold(true); //加粗
        titleFun.setColor("000000");//设置颜色
        titleFun.setFontSize(18);    //字体大小
        titleFun.addBreak();    //换行
        //创建表格
        XWPFTable table  = doc.createTable(8,4);
        table.setCellMargins(3, 5, 3, 5);
        // 创建文本对象


        // 设置表头信息
        String[] title1 = new String[]{"编号"," ","项目类型"," "};
        String[] title2 = new String[]{"调查日期"," ","建设状态"," "};
        //
        XWPFTableRow row;
        // 表格的单元格对象
        XWPFTableCell cell;
        // 单元格属性
        CTTcPr cellPr;
        // 遍历表格
        for(int j=0;j<8;j++) {
            row = table.getRow(j);

            row.setHeight(600);
            //第一行
            for(int i=0;i<title1.length;i++) {
                //创建单元格
                cell = row.getCell(i);
                //单元格属性
                cellPr = cell.getCTTc().addNewTcPr();
                //设置宽度
                cellPr.addNewTcW().setW(BigInteger.valueOf(5000));
                //垂直居中
                cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
                //第一个段落
                para = cell.getParagraphs().get(0);
                //设置对齐方式
                para.setAlignment(ParagraphAlignment.CENTER);
                //段落的对齐方式 1左 2中 3右 4往上 左 不可写0和负数
                para.setFontAlignment(2);
                //一个XWPFRun代表具有相同属性的一个区域：一段文本
                run = para.createRun();
                //设置字体
                run.setFontFamily("仿宋");
                //设置字体大小
                run.setFontSize(13);
                if(j==0) {
                    //字体宽
	                    run.setBold(true);

                    if(i==1) {
                        title1[i]="9527";
                        run = para.createRun();
                        run.setFontSize(11);
                        run.setText(title1[i]);
                    }
                    if(i==3) {
                        title1[i]="影视项目";
                        run = para.createRun();
                        run.setFontSize(11);
                        run.setText(title1[i]);
                    }

                    if(i==2) {
                        run.setText(title1[i]);
                    }
                    if(i==0) {
                        run.setText(title1[i]);
                    }

                }
            }
            //第二行

            if(j==1){
                for(int y=0;y<4;y++){
                    if(y==0) {
                        cell = row.getCell(0);
                        cellPr = cell.getCTTc().addNewTcPr();
                        cellPr.addNewTcW().setW(BigInteger.valueOf(5000));
                        para = cell.getParagraphs().get(0);
                        para.setAlignment(ParagraphAlignment.CENTER);
                        para.setFontAlignment(2);
                        run = para.createRun();
                        run.setFontFamily("仿宋");
                        run.setFontSize(13);
                        run.setText("扰动类型");
                    }
                    if(y==1) {
                        cell = row.getCell(y);
                        cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                        cell.setText("学校");
                    }else {
                        cell = row.getCell(y);
                        cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                    }
                }

            }
            // 第三行
            if (j==3) {

            }

        }
        String fileName="解析标志基本信息.doc";
        String path="C:/doc/"+fileName;
        File storeDirectory = new File("C:/doc");
        if (!storeDirectory.exists() &&!storeDirectory.isDirectory()) {
            storeDirectory.mkdirs();
        }
        File file = new File(path);
        FileOutputStream os = new FileOutputStream(file);
        doc.write(os);
        if(os!=null){
            try{
                os.close();
                System.out.println("文件已输出！");
//                HttpHeaders headers = new HttpHeaders();
//                // 下载显示的文件名，解决中文名称乱码问题
//                String downloadFielName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
//                // 通知浏览器以attachment（下载方式）打开
//                headers.setContentDispositionFormData("attachment", downloadFielName);
//                // application/octet-stream ： 二进制流数据（最常见的文件下载）。
//                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//                return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    @Test
    // 使用jsoup读取字符串
    public void jsoup2() throws Exception{

        String string = FileUtils.readFileToString(new File("f:/oldpo.xml"),"utf-8");

        // 解析url,
        Document doc = Jsoup.parse(string);
        doc.getElementsByTag("p").attr("style","text-indent: 2em;");
        doc.getElementsByTag("span").removeAttr("style");

        // 使用标签选择器,获取title中的内容
        String trim = doc.toString().replaceAll("<html>|</html>|<head></head>|<body>|</body>|　　", "").trim();


        System.err.println(trim);

    }

}
