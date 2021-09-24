package com.yeqin.pims.page;

import lombok.Getter;
import lombok.Setter;

//在分页条中动态显示页码超链接的工具类
@Getter@Setter
public class PageIndex {
	private Integer startIndex; //页码条开始值
    private Integer endIndex;   //页码条结束值
    private PageIndex(Integer startIndex, Integer endIndex) {
    	this.startIndex = startIndex;
    	this.endIndex = endIndex;
    }
    /**
     * 根据传入的当前页码和页码总数，计算startIndex和endIndex的值，并封装到一个PageIndex对象中
     * @param viewSize     显示在分页条上的页码个数
     * @param currentPage  用户输入的要跳转到的页码
     * @param totalPage    最大页码数
     * @return             返回一个封装了startIndex和endIndex值的PageIndex对象
     */
    public static PageIndex getPageIndex(Integer viewSize, Integer currentPage, Integer totalPage) {
    	Integer start = 1;
    	Integer end = viewSize <= totalPage ? viewSize : totalPage;  //默认最大页码值
    	Integer flagIndex = viewSize % 2 == 0 ? viewSize / 2 : (viewSize / 2) + 1; //用于标记是否发生变动的页码值（大于该值则页码条向前移动，小于该值页码条则向后移动）
    	//向前移动
    	if(currentPage > flagIndex) {   
    		Integer forward = currentPage - flagIndex;  //计算向前移动的距离
    		if((end + forward) <= totalPage) {  //判断是否还有足够的空间向前移动
    			//如果足够，就向前移动forward距离
    			start += forward;
    			end += forward;
    		}else { //如果不够，能向前移动多少就移动多少
    			start += totalPage - end;
    			end = totalPage;
    		}
    	}
    	//向后移动
    	if(currentPage < flagIndex) {
    		Integer retreat = flagIndex - currentPage;  //计算向后移动的距离
    		if((start - retreat) >= 1) {  //判断是否有足够的空间向后移动
    			//如果足够，就向后移动retreat距离
    			start = start - retreat;
    			end = end - retreat;
    		}else {  //如果不够，能向后移动多少就移动多少
    			start = 1;
    			end = end - (start - 1);
    		}
    	}
    	return new PageIndex(start, end);
    }
}
