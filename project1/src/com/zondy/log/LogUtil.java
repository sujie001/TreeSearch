package com.zondy.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;


public class LogUtil {

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void throwTest(JoinPoint joinPoint, Throwable ex){
		
		// ===================== 获取作者 =========================//
		// 获取抛出异常的对象
		Object oo = joinPoint.getTarget();
		Class cc = oo.getClass();
		String author = null;
		try {
			// 获取getAuthor方法
			Method mm = cc.getMethod("getAuthor");
			// 调用getAuthor方法
			author = (String)mm.invoke(oo, new Object[]{});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//====================== log =========================================//
		//获得当前目录路径
        String filePath=this.getClass().getResource("/").getPath();
        filePath = filePath.replaceAll("%20", " ");
        //找到log4j.properties配置文件所在的目录(已经创建好)
        filePath=filePath.substring(1);  
        //获得日志类loger的实例
        Logger loger=Logger.getLogger(this.getClass());
        //loger所需的配置文件路径 
        PropertyConfigurator.configure(filePath+"log4j.properties");
        
        String name = joinPoint.getSignature().toString();
        
        StringBuilder errorStr = new StringBuilder("异常:\r\n报异常的方法是"+name.substring(name.indexOf(" "))+"(作者："+author+")"+"\r\n");
        errorStr.append("返回值类型是"+name.substring(0,name.indexOf(" "))+"\r\n");
		Object[] obj=joinPoint.getArgs();
		for(int i=0;i<obj.length;i++){
			errorStr.append("参数"+i+":"+obj[i]+"\r\n");
		}
		errorStr.append("异常信息：\r\n");
		
		
		StringWriter sw = new StringWriter();  
        ex.printStackTrace(new PrintWriter(sw, true));  
        String str = sw.toString();  
        errorStr.append(str);
		errorStr.append("\r\n");
		loger.error(errorStr);	// 方法
		
	}
}
