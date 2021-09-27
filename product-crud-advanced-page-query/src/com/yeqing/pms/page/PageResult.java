package com.yeqing.pms.page;

import java.util.List;

import lombok.Getter;

//封装了分页查询的结果集和分页的相关信息
@Getter
public class PageResult {
    private List<?> listData;    //查询所得结果集
    private Integer currentPage; //用户输入的需要跳转的页面
    private Integer pageSize;    //用户输入的每页数量量
    private Integer totalCount;  //所有数据量的总数
	private Integer totalPage;   //末页
	private Integer prevPage;    //上页，计算得到
	private Integer nextPage;    //下页，计算得到
	private PageIndex pageIndex; //封装了页码条信息的对象
	
    public PageResult(List<?> listData, Integer currentPage, Integer pageSize, Integer totalCount) {
		this.listData = listData;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
		this.prevPage = currentPage - 1 <= 1 ? 1 : currentPage - 1;
		this.nextPage = currentPage + 1 >= totalPage ? totalPage : currentPage + 1;
		//创建一个页码条信息对象
		this.pageIndex = PageIndex.getPageIndex(5, currentPage, totalPage);
	}
}
