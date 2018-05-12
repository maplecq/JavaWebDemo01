package com.chiansofit.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InitialService {
	 // 系统初始化  
    public String initial() throws Exception {  
        String filePath = InitialService.class.getClassLoader().getResource(  
                "init.sql").getPath();  
        filePath = filePath.substring(1);  
        String command = "cmd /c mysql -uroot -proot<" + filePath;  
        Process process = Runtime.getRuntime().exec(command);  
        InputStream errorStream = process.getErrorStream();  
        BufferedReader br = new BufferedReader(new InputStreamReader(  
                errorStream));  
        char[] chars = new char[1024];  
        int len = 0;  
        StringBuffer sb = new StringBuffer();  
        while ((len = br.read(chars)) != -1) {  
            sb.append(chars, 0, len);  
        }  
        if (sb.length() > 0)  
            return sb.insert(0, "初始化失败，原因：").toString();  
        else  
            return "初始化成功";  
    }  
}
