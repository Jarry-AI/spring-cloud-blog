package com.example.easyexcel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author ZhenHX
 * @description 导出工具类
 * @date 2020-04-03 16:38
 */
public class ExcelUtils {
    /**
     * 导出
     * @param response
     * @param data
     * @param fileName
     * @param sheetName
     * @param clazz
     * @throws Exception
     */
    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz) throws Exception {
//        //表头样式
//        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//        //设置表头居中对齐
//        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        //内容样式
//        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//        //设置内容靠左对齐
//        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
//        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
//        EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy).doWrite(data);
        // 导出多个sheet
        ExcelWriter excelWriter = EasyExcel.write(getOutputStream(fileName, response)).build();
        WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "客户信息").head(clazz).build();
        WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "供应商信息").head(clazz).build();
        excelWriter.write(data,writeSheet1);
        excelWriter.write(data, writeSheet2);
        excelWriter.finish();



    }
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }
}
