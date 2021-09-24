package com.yeqin.pims.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.yeqin.pims.dao.IProductDAO;
import com.yeqin.pims.dao.IProductDirDAO;
import com.yeqin.pims.dao.impl.ProductDAOImpl;
import com.yeqin.pims.dao.impl.ProductDirDAOImpl;
import com.yeqin.pims.domain.ProductDir;
import com.yeqin.pims.page.PageResult;
import com.yeqin.pims.query.ProductQueryObject;

//处理高级查询和分页查询请求
@WebServlet("/product")
public class ProductcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IProductDAO productDAO;
    private IProductDirDAO dirDAO;

    public void init(ServletConfig config) throws ServletException {
    	productDAO = new ProductDAOImpl();
    	dirDAO = new ProductDirDAOImpl();
    }
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//老三样：
    	//1:获得请求参数，封装成对象
    	ProductQueryObject qo = request2QueryObject(req);
    	//2:调用业务方法处理请求
    	PageResult ps = productDAO.query(qo);
    	List<ProductDir> dirs = dirDAO.listAll();
    	req.setAttribute("qo", qo);
    	req.setAttribute("pageResult", ps);
    	req.setAttribute("dirs", dirs);
    	//3:控制页面跳转
    	req.getRequestDispatcher("/WEB-INF/product/product_list.jsp").forward(req, resp);
    }
    //获取请求参数，封装成ProductQueryObject对象
    private ProductQueryObject request2QueryObject(HttpServletRequest req) {
    	String name = req.getParameter("name");
    	String note = req.getParameter("note");
    	String sMinprice = req.getParameter("minprice");
    	String sMaxprice = req.getParameter("maxprice");
    	String sDir = req.getParameter("dir");
    	String keyword = req.getParameter("keyword");
    	String sCurrentPage = req.getParameter("currentPage");
    	String sPageSize = req.getParameter("pageSize");
    	ProductQueryObject qo = new ProductQueryObject();
    	if(StringUtils.isNotBlank(name)) {
    		qo.setName(name);
    	}
    	if(StringUtils.isNotBlank(note)) {
    		qo.setNote(note);
    	}
    	if(StringUtils.isNotBlank(sMinprice)) {
    		qo.setMinprice(new BigDecimal(sMinprice));
    	}
    	if(StringUtils.isNotBlank(sMaxprice)) {
    		qo.setMaxprice(new BigDecimal(sMaxprice));
    	}
    	if(StringUtils.isNotBlank(sDir)) {
    		qo.setDir(Long.valueOf(sDir));
    	}
    	if(StringUtils.isNotBlank(keyword)) {
    		qo.setKeyword(keyword);
    	}
    	if(StringUtils.isNotBlank(sCurrentPage)) {
    		qo.setCurrentPage(Integer.valueOf(sCurrentPage));
    	}
    	if(StringUtils.isNotBlank(sPageSize)) {
    		qo.setPageSize(Integer.valueOf(sPageSize));
    	}
    	return qo;
    }
}
