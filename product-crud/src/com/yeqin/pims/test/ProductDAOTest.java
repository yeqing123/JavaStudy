package com.yeqin.pims.test;




import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.yeqin.pims.dao.IProductDAO;
import com.yeqin.pims.dao.impl.ProductDAOImpl;
import com.yeqin.pims.domain.Product;
import com.yeqin.pims.page.PageResult;
import com.yeqin.pims.query.ProductQueryObject;

public class ProductDAOTest {
	private IProductDAO dao = new ProductDAOImpl();
	@Test
	public void testListAll() {
		List<Product> list = dao.listAll();
		System.out.println("查询到"+list.size()+"条信息：");
		for (Product product : list) {
			System.out.println(product);
		}
	}
    
    @Test
	public void testQuery() throws Exception {
    	ProductQueryObject qo = new ProductQueryObject();
		qo.setMinprice(new BigDecimal(50));
		//qo.setName("java");
		qo.setCurrentPage(2);
		qo.setPageSize(5);
		PageResult ps = dao.query(qo);
		for(Object p : ps.getListData()) {
    		System.out.println(p);
    	}
    	System.out.println("上页：" + ps.getPrevPage() + ", 下页：" + ps.getNextPage() +
    			", 当前页：" + ps.getCurrentPage() + "/" + ps.getTotalPage() + ", 总信息数：" + ps.getTotalCount());
	}
}
