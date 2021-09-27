package com.yeqing.pms.page;

import lombok.Getter;

//使用页码条的工具类
@Getter
public class PageIndex {
	private Integer startIndex;
	private Integer endIndex;
	//私有构造函数，只能由getPageIndex方法调用，不可以被外界直接使用
	private PageIndex(Integer startInteger, Integer endInteger) {
		this.startIndex = startInteger;
		this.endIndex = endInteger;
	}
	//根据页码数量、当前页和末页生成一个PageIndex对象
	public static PageIndex getPageIndex(Integer viewCount, Integer currentPage, Integer totalPage) {
		if(viewCount > totalPage) {
			viewCount = totalPage;
		}
		Integer start = 1;
		Integer end = viewCount;
		Integer flagIndex = viewCount % 2 == 0 ? viewCount / 2 : viewCount / 2 + 1;
		//向前移动
		if((currentPage - start + 1) > flagIndex) {
			Integer moveDistance = (currentPage - start + 1) - flagIndex;  //计算移动距离
			//判断是否越界
			if(end + moveDistance < totalPage) {  //没有越界
				start += moveDistance;
				end += moveDistance;
			} else {  //越界
				start += totalPage - end;
				end = totalPage;
			}
		}
		//向后移动
		if((currentPage - start + 1) < flagIndex) {
			Integer moveDistance = flagIndex - (currentPage - start + 1);  //计算移动距离
			//判断是否越界
			if(start - moveDistance > 1) {  //没有越界
				start -= moveDistance;
				end -= moveDistance;
			} else {  //越界
				end -= start - 1;
				start = 1;
			}
		}
		return new PageIndex(start, end);
	}
}
