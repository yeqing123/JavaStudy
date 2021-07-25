package com.yeqing._05_beanutils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.LongConverter;

import com.yeqing._01_javabean.Person;

public class BeanUtilsDemo {

	public static void main(String[] args) throws Exception {
        Person p = new Person();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("id", "");
        map.put("age", 19);
        map.put("bornDate", "2017-10-20");
        
        System.out.println(p);
        // 重新注册一个LongConverter转换器，指定缺省的默认值为null
        ConvertUtils.register(new LongConverter(null), Long.class);
        // beanutils没有自带的从String到Date的转换器，因此要自定义一个
        DateConverter dateConverter = new DateConverter(null);
        // 设置日期的字符串模版，否则转换器无法识别
        dateConverter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dateConverter, Date.class);
        // 将源对象map中的数据拷贝到目标对象p中
        BeanUtils.copyProperties(p, map);
        System.out.println(p);
	}

}
