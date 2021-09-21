package com.yeqin.pims.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqin.pims.dao.IProductDAO;
import com.yeqin.pims.dao.IProductDirDAO;
import com.yeqin.pims.dao.IProductSubDirDAO;
import com.yeqin.pims.dao.impl.ProductDAOImpl;
import com.yeqin.pims.dao.impl.ProductDirDAOImpl;
import com.yeqin.pims.dao.impl.ProductSubDirDAOImpl;
import com.yeqin.pims.domain.Product;

//处理业务各项请求
@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IProductDAO dao = null;
	private IProductDirDAO dirDao = null;
	private IProductSubDirDAO subDirDao = null;
	
	public void init() throws ServletException {
		dao = new ProductDAOImpl();
		dirDao = new ProductDirDAOImpl();
		subDirDao = new ProductSubDirDAOImpl();
	}
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	String cmd = req.getParameter("cmd"); 
    	//分发请求
    	if("edit".equals(cmd)) {
    		this.edit(req, resp);
    	}else if("save".equals(cmd)) {
    		this.saveOrUpdate(req, resp);
    	}else if("delete".equals(cmd)) {
    		this.delete(req, resp);
    	}else {
    		this.list(req, resp);
    	}
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//1:获取请求参数，封装成对象
    	String id = req.getParameter("id");
    	//2:调用业务方法，处理请求
    	if(hasLength(id)) {  //如果请求中有id，则获取被修改的商品信息
    	    req.setAttribute("p", dao.get(Long.valueOf(id)));
    	}
    	req.setAttribute("dirList", dirDao.listAll());  //获取所有的目录对象，用于显示到编辑页面中
    	req.setAttribute("subDirList", subDirDao.listAll());//获取所有子目录对象，用于显示到编辑页面中
    	//3:控制页面跳转
    	req.getRequestDispatcher("/WEB-INF/product/edit.jsp").forward(req, resp);
    }
    protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//1:获取请求参数，封装成对象
    	Product p = this.request2bean(req, resp);
    	//2:调用业务方法，处理请求
    	if(p.getId()!=null) { //有id，修改
    		dao.update(p);
    	}else {  //无id，添加
    		dao.save(p);
    	}
    	//3:控制页面跳转
    	resp.sendRedirect("/product");
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//1:获取请求参数，封装成对象
    	String id = req.getParameter("id");
    	//2:调用业务方法，处理请求
    	dao.delete(Long.valueOf(id));
    	//3:控制页面跳转
    	resp.sendRedirect("/product");
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//1:获取请求参数，封装成对象
    	//2:调用业务方法，处理请求
    	req.setAttribute("products", dao.listAll());
    	req.setAttribute("dirs", dirDao.listAll());
    	req.setAttribute("subDirs", subDirDao.listAll());
    	//3:控制页面跳转
    	req.getRequestDispatcher("/WEB-INF/product/product_list.jsp").forward(req, resp);
    }
    //从请求中获取参数，并封装成Product对象
    private Product request2bean(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	String name = req.getParameter("name");
    	String note = req.getParameter("note");
    	String price = req.getParameter("price");
    	String count = req.getParameter("count");
    	String dir = req.getParameter("dir");
    	String subDir = req.getParameter("subDir");
    	Product p = new Product();
    	//严格来讲，应该先逐个进行字符串为空的判断，然后在设置的对象中
    	p.setName(name);
    	p.setNote(note);
    	p.setPrice(new BigDecimal(price));
    	p.setCount(Integer.valueOf(count));
    	p.setDir(Long.valueOf(dir));
    	p.setSubDir(Long.valueOf(subDir));
    	String id = req.getParameter("id");
    	if(hasLength(id)) {
    		p.setId(Long.valueOf(id));
    	}
    	return p;
    }
    
    private boolean hasLength(String str) {
    	return str != null && !"".equals(str.trim());
    }
}
