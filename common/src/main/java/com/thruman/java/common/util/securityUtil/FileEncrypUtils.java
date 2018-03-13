package com.thruman.java.common.util.securityUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import junit.framework.TestCase;

public class FileEncrypUtils{
	private static final Logger logger = LoggerFactory.getLogger(FileEncrypUtils.class);

    @Test
    public void testEncrypt() throws Exception {
        String key = "AD67EA2F3FQ6E5GDE376DFG0";

        File in = new File("D:/test/CustomerInfo.txt");
        //加密
        encrypt(in, key);
    }

    @Test
    public void testDecrypt() throws Exception {
        String key = "AD67EA2F3FQ6E5GDE376DFG0";

        File in = new File("D:/test/encrypt/CustomerInfo.txt");
        //解密
        decrypt(in, key);
    }


    /**
     * @param fileIn
     * @param sKey
     * @return
     * @throws Exception
     */
    public static File encrypt(File fileIn, String sKey) throws Exception {

        String deskFilePath = fileIn.getParent() + "/encrypt/" + fileIn.getName();
        File folder = new File(fileIn.getParent() + "/encrypt");
        //如果文件夹不存在，则先创建
        if(!folder.exists()){
            folder.mkdir();
        }
        FileInputStream fis = new FileInputStream(fileIn);
        FileOutputStream fos = new FileOutputStream(deskFilePath);

        DesEncryptUtils.encrypt(sKey.getBytes("utf-8"), fis, fos);
        logger.info("加密成功");

        return new File(deskFilePath);
    }


    /**
     * @param fileIn
     * @param sKey
     * @throws Exception
     */
    public static File decrypt(File fileIn, String sKey) throws Exception {


        String deskFilePath = fileIn.getParent() + "/decrypt/" + fileIn.getName();
        File folder = new File(fileIn.getParent() + "/decrypt");
        //如果文件夹不存在，则先创建
        if(!folder.exists()){
            folder.mkdir();
        }

        FileInputStream fis = new FileInputStream(fileIn);
        FileOutputStream fos = new FileOutputStream(deskFilePath);

        DesEncryptUtils.decrypt(sKey.getBytes("utf-8"), fis, fos);
        
        logger.info("解密成功");

        return new File(deskFilePath);

    }



}
