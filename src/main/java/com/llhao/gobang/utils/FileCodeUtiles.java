package com.llhao.gobang.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Everthing-- on 2017/5/16.
 */
public class FileCodeUtiles {
    public static void transCode(String base,String target,File file) throws IOException{
        if(file.isDirectory()){
            File[] lists = file.listFiles();
            if(lists!=null){
                for (File list : lists) {
                    transCode(base,target,list);
                }
            }
            return;
        }
        if(!file.getName().endsWith(".java")){
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),base));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line=reader.readLine())!=null){
            builder.append(line+"\n");
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),target));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
        System.out.println(file.getAbsolutePath());
    }
    public static void main(String[] args) throws IOException{
        File file = new File("E:\\毕业设计\\代码\\gobangai\\");
        FileCodeUtiles.transCode("utf-8","GBK",file);
    }
}
