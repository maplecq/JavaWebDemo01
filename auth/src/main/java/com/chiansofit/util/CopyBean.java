package com.chiansofit.util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class CopyBean {
	public static void Copy(Object bean, Map<String, String[]> properties){  
        try {  
            BeanUtils.populate(bean, properties);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}
