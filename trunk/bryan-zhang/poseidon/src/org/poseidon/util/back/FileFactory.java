package org.poseidon.util.back;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.poseidon.util.LoggerUtil;

/**
 * FileUtil
 *
 * @author 赵卫东
 * @version 创建时间：2009-5-22 下午03:10:15
 */
public class FileFactory {

    private static final Logger LOGGER = Logger.getLogger(FileFactory.class);
    static{
        LoggerUtil.initLogging(LOGGER);
    }
    
    /** 单例,暂不使用
    private static FileFactory factoryInstance ;
    
    private FileFactory(){}
    
    public static FileFactory GetInstance(){
        if( factoryInstance == null ){
            synchronized (FileFactory.class) {
                if( factoryInstance == null ){
                    factoryInstance = new FileFactory();
                }
            }
        }
        return factoryInstance;
    }*/
    
    //生成文件夹
    public static void createDir(String path , String dirName){
        File filePath=new File(path+"//"+dirName);   
        if(!filePath.exists()){   
            LOGGER.info("[生成新文件夹][DIR不存在,正在生成新文件夹]["+path+"]");
            filePath.mkdirs();   
        }  else{
            LOGGER.debug("[生成新文件夹][DIR存在,无需生成新文件夹]["+path+"]");
        }
    }
    
    //生成文件
    public static void createFile(String path , String fileName) throws IOException {
        createDir(path, "");
        File fileDat=new File(path+"//"+fileName);  
        if( !fileDat.exists() ){   
            LOGGER.debug("[生成新文件][FILE不存在,正在生成新文件]["+path+"/"+fileName+"]");
            fileDat.createNewFile();
        }else{
            LOGGER.debug("[生成新文件][FILE存在,不用在生成新文件]["+path+"/"+fileName+"]");
        }
    }
    
    //写入文件
    public static boolean writeText(String path , String textName,String data){  
        createDir(path, "");
        boolean flag = false;  
        FileWriter fw = null;
        try {   
            fw = new FileWriter(path + File.separator + textName);   
            LOGGER.debug("[数据写入][正在进行中]["+path + File.separator + textName+"]");
            fw.write(data);   
            fw.flush(); // 先刷新到缓存,再写入文件,提高效率
            flag = true;   
            LOGGER.debug("[数据写入][成功]");
        } catch (IOException e) {   
            LOGGER.error(e.getMessage(), e);
        }  finally{
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }  
            }
        }
        return flag;           
    } 
    
    //读取文件
    public static String readText(String fileName ){  
        return readText( fileName , "\n" );
    }
    
    //读取文件
    public static String readText(String path , String textName,String line){  
        return readText(path + File.separator + textName,"\n");
    }
    
    //读取文件
    public static String readText(String fileName,String lineCode){  
        LOGGER.debug("[读取文件]["+ fileName +"]");
        BufferedReader br = null;
        String data = "";
 
        try {
            br = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(fileName)
                        )
                    );
            String tmpReadTxt = "";
            while( (tmpReadTxt = br.readLine()) != null) {
                data += tmpReadTxt + lineCode;
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally{
            if( br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        LOGGER.debug("[读取内容]["+data+"]");
        return data;
    }
    
    //读取文件最后一行
    public static String readLastLine(String fileName){  
        LOGGER.debug("[读取文件]["+ fileName +"]");
        BufferedReader br = null;
        String data = "";
 
        try {
            br = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(fileName)
                        )
                    );
            String tmpReadTxt = "";
            while( (tmpReadTxt = br.readLine()) != null) {
                data = tmpReadTxt;
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally{
            if( br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        LOGGER.debug("[读取内容]["+data+"]");
        return data;
    }
    
    /**
     * 文件追加内容
     * @throws IOException 
     */
    public static void appendFile(String path,String fileName,String data) 
    throws IOException{
        createFile(path, fileName);
        FileWriter fw;
        try {
            LOGGER.debug("[追加文件内容][正在执行]");
            fw = new FileWriter(path+"/"+fileName,true);
            java.io.PrintWriter pw = new PrintWriter(fw);  
            pw.println(data);   
            pw.close();  
            fw.close();
            LOGGER.debug("[追加文件内容][完成]");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }     
    }


}
