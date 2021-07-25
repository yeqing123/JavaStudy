package com.yeqing._03_anno;

@MyAnno(name="张三", age=18, hobby= {"java", "movement", "girl"})
public class Employee {
    @Deprecated
    public void doWork() {
    	@SuppressWarnings("unused")
    	String sex = null;
    }
    /**
     * @deprecated
     */
    public void doWork11() {}
}


