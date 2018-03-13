package com.thruman.java.common.util.poiUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author niexiang
 * @Description
 * @create 2017-12-13 16:18
 **/
public class PoiUtil {

    /**
     * Map生成excel表
     * @param head (头部的key必须对应map的key)
     * @param contents
     * @return 返回保存的地址
     */
    public static String mapToExcel(LinkedHashMap<String,String> head, List<Map> contents,String sheetName,String fileName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        mapToSheet(workbook,head,sheetName,contents);
        String folder=System.getProperty("java.io.tmpdir");
        String filePath = folder + "/" + fileName;
        return writeFile(workbook,filePath);
    }

    /**
     * bean生成excel表
     * @param head (头部的key必须对应bean的属性名)
     * @param contents
     * @return 返回保存的地址
     */
    public static <T> String beanToExcel(LinkedHashMap<String,String> head, List<T> contents,String sheetName,String fileName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        beanToSheet(workbook,head,contents,sheetName);
        String folder=System.getProperty("java.io.tmpdir");
        String filePath = folder + "/" + fileName;
        return writeFile(workbook,filePath);
    }

    /**
     *
     * @param list
     * @param fileName name.xls
     * @return
     * @throws Exception
     */
    public static String mapToSheetsExcel(List<ExcelSheet> list,String fileName){
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (ExcelSheet excelSheet : list) {
            mapToSheet(workbook,excelSheet.getHead(),excelSheet.getSheetName(),excelSheet.getContents());
        }
        String folder=System.getProperty("java.io.tmpdir");
        String filePath = folder + "/" + fileName;
        return writeFile(workbook,filePath);
    }
    /**
     *
     * @param list
     * @param fileName name.xls
     * @return
     * @throws Exception
     */
    public static String beanToSheetsExcel(List<ExcelSheet> list,String fileName){
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (ExcelSheet excelSheet : list) {
            beanToSheet(workbook,excelSheet.getHead(),excelSheet.getContents(),excelSheet.getSheetName());
        }
        String folder=System.getProperty("java.io.tmpdir");
        String filePath = folder + "/" + fileName;
        return writeFile(workbook,filePath);
    }

    private static <T> void beanToSheet(HSSFWorkbook workbook,LinkedHashMap<String,String> head, List<T> contents,String sheetName) {
        HSSFSheet sheet = workbook.createSheet(sheetName);
        Boolean flag = true;
        Class clazz = null;
        int num = 0;
        if (contents == null || contents.size() == 0) {
            flag = false;
        }else {
            clazz = contents.get(0).getClass();
            num = contents.size();
        }
        //生成内容
        for (int i = 0;i < num + 1;i++){//excel的行数
            HSSFRow row = sheet.createRow(i);
            int j = 0;//列
            for (Map.Entry<String,String> property : head.entrySet()) {
                HSSFCell cell = row.createCell(j++);
                if (i == 0) {//表头信息
                    cell.setCellValue(property.getValue());
                }else if(flag){
                    PropertyDescriptor pd = null;
                    Object result = null;
                    Type type = null;
                    try {
                        pd = new PropertyDescriptor(property.getKey(),clazz);
                        Method getMethod = pd.getReadMethod();
                        type = getMethod.getAnnotatedReturnType().getType();
                        result = getMethod.invoke(contents.get(i - 1));
                    } catch (IntrospectionException e) {
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    if (type != null ){
                        if ("class java.util.Date".equals(type.toString())) {
                            cell.setCellValue(formatDatetimePattern((Date)result,"yyyy-MM-dd"));
                        }else if ("class java.lang.Double".equals(type.toString())){
                            if (result == null) {
                                cell.setCellValue(0.00);
                            }else {
                                cell.setCellValue(getDoubleRoundHalfUpBy2((double)result));
                            }
                        }else{
                            cell.setCellValue(result == null ? null :result.toString());
                        }
                    }
                }
            }
        }
    }

    private static void mapToSheet(HSSFWorkbook workbook, LinkedHashMap<String, String> head, String sheetName, List<Map> contents) {
        HSSFSheet sheet = workbook.createSheet(sheetName);
        Boolean flag = true;
        int num = 0;
        if (contents == null || contents.size() == 0) {
            flag = false;
        }else {
            num = contents.size();
        }
        for (int i = 0;i< num+1;i++){//excel的行数
            HSSFRow row = sheet.createRow(i);
            int j = 0;//列
            for (Map.Entry<String,String> property : head.entrySet()) {
                HSSFCell cell = row.createCell(j++);
                if (i == 0) {//表头信息
                    cell.setCellValue(property.getValue());
                }else if(flag){
                    cell.setCellValue(contents.get(i - 1).get(property.getKey()) == null ? null :contents.get(i - 1).get(property.getKey()).toString());
                }
            }
        }
    }


    private static String writeFile(HSSFWorkbook workbook, String filePath) {
        FileOutputStream stream = null;
        BufferedOutputStream outputStream = null;
        try {
            stream = new FileOutputStream(filePath);
            outputStream = new BufferedOutputStream(stream);
            workbook.write(outputStream);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     *
     * @param date
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return
     */
    private static String formatDatetimePattern(Date date, String pattern) {
        if (date == null)
            return "";
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 四舍五入保存两个小数
     * @param result
     * @return
     */
    public static Double getDoubleRoundHalfUpBy2(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);

        return bigDecimal.doubleValue();
    }

    

    public static void main(String[] args) {

    }



}