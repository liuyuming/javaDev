package com.thruman.java.common.util.poiUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author niexiang
 * @Description
 * @create 2017-12-19 19:27
 **/
public class ExcelSheet {
    private LinkedHashMap<String,String> head;
    private List contents;
    private String sheetName;
    public ExcelSheet() {
    	
    }
    public ExcelSheet(LinkedHashMap<String, String> head, List contents, String sheetName) {
        this.head = head;
        this.contents = contents;
        this.sheetName = sheetName;
    }
    
    
    public ExcelSheet(LinkedHashMap<String, String> head,String sheetName) {
    	 this.head = head;
         this.sheetName = sheetName;
    }

    public LinkedHashMap<String, String> getHead() {
        return head;
    }

    public void setHead(LinkedHashMap<String, String> head) {
        this.head = head;
    }

    public List getContents() {
        return contents;
    }

    public void setContents(List contents) {
        this.contents = contents;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }


}