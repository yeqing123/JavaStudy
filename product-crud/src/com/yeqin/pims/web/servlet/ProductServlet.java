package com.yeqin.pims.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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
import com.yeqin.pims.domain.Product;
import com.yeqin.pims.domain.ProductDir;
import com.yeqin.pims.query.ProductQueryObject;

//处理高级查询请求
@WebServlet("/product")
public class ProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private IProductDAO productDAO;
    private IProductDirDAO dirDAO;
    public void init() throws ServletException {
    	productDAO = new ProductDAOImpl();
    	dirDAO = new ProductDirDAOImpl();
    }
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	//老三样:
    	//1:获取请求参数，封装成对象
    	ProductQueryObject qo = request2QueryObject(req);
    	//2:调用业务方法，处理请求
        List<Product> list = productDAO.query(qo);
        List<ProductDir> dirs = dirDAO.listAll();
        req.setAttribute("products", list);
        req.setAttribute("dirs", dirs);
        req.setAttribute("qo", qo);
    	//3:控制页面跳转
        req.getRequestDispatcher("/WEB-INF/product/product_list.jsp").forward(req, resp);
    }
    
    private ProductQueryObject request2QueryObject(HttpServletRequest req) {
    	String name = req.getParameter("name");
    	String note = req.getParameter("note");
    	String minprice = req.getParameter("minprice");
    	String maxprice = req.getParameter("maxprice");
    	String dir = req.getParameter("dir");
    	String keyword = req.getParameter("keyword");
    	ProductQueryObject qo = new ProductQueryObject();
    	if(StringUtils.isNotBlank(name)) {
    		qo.setName(name);
    	}
    	if(StringUtils.isNotBlank(note)) {
    		qo.setNote(note);
    	}
    	if(StringUtils.isNotBlank(minprice)) {
    		qo.setMinprice(new BigDecimal(minprice));
    	}
    	if(StringUtils.isNotBlank(maxprice)) {
    		qo.setMaxprice(new BigDecimal(maxprice));
    	}
    	if(StringUtils.isNotBlank(dir)) {
    		qo.setDir(Long.valueOf(dir));
    	}
    	if(StringUtils.isNotBlank(keyword)) {
    		qo.setKeyword(keyword);
    	}
    	return qo;
    }
}
