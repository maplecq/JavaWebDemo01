package com.chiansofit.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
	 private static DaoFactory factory = new DaoFactory();  
	    private static Properties properties;  
	  
	    private DaoFactory() {  
	        InputStream inputStream = DaoFactory.class.getClassLoader()  
	                .getResourceAsStream("daoFactory.properties");  
	        try {  
	            properties = new Properties();  
	            properties.load(inputStream);  
	        } catch (IOException e) {  
	            throw new ExceptionInInitializerError(e);  
	        }  
	    }  
	  
	    public static DaoFactory newInstance() {  
	        return factory;  
	    }  
	  
	    @SuppressWarnings("unchecked")  
	    public <T> T getDao(Class<T> clazz) {  
	        String simpleName = clazz.getSimpleName();  
	        String className = properties.getProperty(simpleName);  
	        try {  
	            return (T) Class.forName(className).newInstance();  
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
	    }  
}
