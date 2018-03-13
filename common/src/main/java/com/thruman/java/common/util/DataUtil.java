package com.thruman.java.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author niexiang
 * @Description
 * @create 2018-01-13 16:35
 **/
public class DataUtil {
    /**
     * 将double型(单位为元)转成int型(单位为分)
     *
     * @param fee
     * @return
     */
    public static  Integer double2IntegerByPoint(Double fee) {
        BigDecimal d = new BigDecimal(String.valueOf(100));
        BigDecimal r = new BigDecimal(Double.toString(fee));
        return d.multiply(r).intValue();
    }
    //乘法
    public static double mul(Double d1,Double... d2){
        BigDecimal b1=new BigDecimal(d1 == null ? "0" : Double.toString(d1));
        for (Double d:d2) {
            BigDecimal b2=new BigDecimal(d == null ? "0" : Double.toString(d));
            b1 = b1.multiply(b2);
        }
        return b1.doubleValue();

    }

    //加法
    public static double add(Double d1,Double... d2){
        BigDecimal b1=new BigDecimal(d1 == null ? "0" : Double.toString(d1));
        for (Double d:d2) {
            BigDecimal b2=new BigDecimal(d == null ? "0" : Double.toString(d));
            b1 = b1.add(b2);
        }

        return b1.doubleValue();

    }

    //加法
    public static double add(String d1,String... d2){
        BigDecimal b1=new BigDecimal(d1);
        for (String d:d2) {
            BigDecimal b2=new BigDecimal(d);
            b1 = b1.add(b2);
        }

        return b1.doubleValue();

    }
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(Double v1,Double v2, int divScale ){
        if(divScale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if(v2 == null || v2==0){
            throw new IllegalArgumentException(
                    "Parameter can not be 0");
        }

        BigDecimal b1 = new BigDecimal(v1 == null ? "0" :  Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,divScale,BigDecimal.ROUND_CEILING).doubleValue();
    }
    //减法
    public static double sub(Double d1,Double... d2){
        BigDecimal b1=new BigDecimal(d1 == null ? "0" :  Double.toString(d1));
        for (Double d:d2) {
            BigDecimal b2=new BigDecimal(d == null ? "0" : Double.toString(d));
            b1 = b1.subtract(b2);
        }

        return b1.doubleValue();

    }

    //减法
    public static String sub(String d1,String... d2){
        BigDecimal b1=new BigDecimal(d1 == null ? "0" :  d1);
        for (String d:d2) {
            BigDecimal b2=new BigDecimal(d == null ? "0" : d);
            b1 = b1.subtract(b2);
        }

        return b1.toString();

    }

    //保留5为小数
    public static Double getDouble(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);

        return bigDecimal.doubleValue();
    }
    //保留3为小数
    public static Double getDoubleBy3(Double result){
        if (result == null)result = 0d;
        //保留3位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(3, BigDecimal.ROUND_HALF_UP);

        return bigDecimal.doubleValue();
    }

    //向上取整保留2为小数
    public static Double getDoubleRoundHalfUpBy2(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);

        return bigDecimal.doubleValue();
    }

    //向上取整保留2为小数
    public static Double getDoubleRoundHalfUp(Double result, int scale){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(scale, BigDecimal.ROUND_HALF_UP);

        return bigDecimal.doubleValue();
    }

    //向上取整保留2为小数
    public static String getStringRoundHalfUpBy2(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);

        return new DecimalFormat("0.00").format(bigDecimal.doubleValue());
    }

    //向上取整保留2为小数,带逗号的
    public static String getWithCommaRoundHalfUpBy2(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);

        return new DecimalFormat("#,##0.00").format(bigDecimal.doubleValue());
    }

    //向下取整保留2为小数
    public static Double getDoubleRoundDownBy2(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(2, BigDecimal.ROUND_DOWN);

        return bigDecimal.doubleValue();
    }
    //向下取整保留2为小数
    public static String getStringRoundDownBy2(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(2, BigDecimal.ROUND_DOWN);

        return new DecimalFormat("0.00").format(bigDecimal.doubleValue());
    }
    //向下取整保留2为小数,带逗号的
    public static String getWithCommaRoundDownBy2(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(2, BigDecimal.ROUND_DOWN);

        return new DecimalFormat("#,##0.00").format(bigDecimal.doubleValue());
    }

    //补零，不带逗号的
    public static String getStringFillZero(Double result){

        return new DecimalFormat("0.00").format(result);
    }
    //补零，带逗号的
    public static String getWithCommaFillZero(Double result){

        return new DecimalFormat("#,##0.00").format(result);
    }

    //向下取整保留8为小数
    public static Double getDoubleRoundDownBy9(Double result){
        if (result == null)result = 0d;
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(8, BigDecimal.ROUND_DOWN);

        return bigDecimal.doubleValue();
    }


    //保留4为小数
    public static Double getDoubleBy4(Double result){
        if (result == null)result = 0d;
        //保留4位小数
        BigDecimal bigDecimal = new BigDecimal(result.toString()).setScale(4, BigDecimal.ROUND_HALF_UP);

        return bigDecimal.doubleValue();
    }

    public static Long getLongRandom(){
        Random random = new Random();
        return random.nextLong();
    }


    public static void main(String[] args) {
        double i = 3088.00;
        double j = 0.14;
        double div = (i * j)/360;
        System.out.println(getStringFillZero(div));



    }


}
