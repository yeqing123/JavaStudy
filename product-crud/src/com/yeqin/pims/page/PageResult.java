package com.yeqin.pims.page;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

//用于封装数据库查询结果和相关信息
@Getter
public class PageResult {
	private List<?> listData = new ArrayList<>(); //执行查询后得到的结果集
	private Integer currentPage;  //要跳转到的页面
	private Integer pageSize;     //每页显示的数据量
	private Integer totalCount;   //查询到的数据总数
	private Integer totalPage;    //末页
	private Integer prevPage;     //上页
	private Integer nextPage;     //下页
	private PageIndex pageIndex;  //操作页码条的对象
	public PageResult(List<?> listData, Integer currentPage, Integer pageSize, Integer totalCount) {
		this.listData = listData;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalCount % pageSize==0 ? totalCount/pageSize : totalCount/pageSize + 1;
		this.prevPage = currentPage - 1 <= 1 ? 1 : currentPage - 1;
		this.nextPage = currentPage + 1 >= totalPage ? totalPage : currentPage + 1;
		this.pageIndex = PageIndex.getPageIndex(5, currentPage, totalPage);
	}

	
}
