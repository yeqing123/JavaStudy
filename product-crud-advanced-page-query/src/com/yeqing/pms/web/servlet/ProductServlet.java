package com.yeqing.pms.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.yeqing.pms.dao.ICommonDAO;
import com.yeqing.pms.dao.impl.ProductDAOImpl;
import com.yeqing.pms.dao.impl.ProductDirDAOImpl;
import com.yeqing.pms.domain.Product;
import com.yeqing.pms.domain.ProductDir;
import com.yeqing.pms.page.PageResult;
import com.yeqing.pms.query.ProductDirQueryObject;
import com.yeqing.pms.query.ProductQueryObject;

//处理查询商品信息的请求（包括：高级查询+分页查询）
@WebServlet("/product")
//@WebFilter("/encoding")
@SuppressWarnings("unchecked")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ICommonDAO productDAO;
	private ICommonDAO dirDAO;

	public void init() throws ServletException {
		productDAO = new ProductDAOImpl();
		dirDAO = new ProductDirDAOImpl();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取cmd参数，分发请求到不同的方法，进行分别处理
		String cmd = req.getParameter("cmd");
		if ("save".equals(cmd)) {
			this.save(req, resp);
		} else if ("edit".equals(cmd)) {
			this.edit(req, resp);
		} else if ("delete".equals(cmd)) {
			this.delete(req, resp);
		} else {
			this.list(req, resp);
		}
	}
    
	//处理新增或修改后的保存请求
	private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1:获取请求参数，封装成一个Product对象
		Product p = request2Product(req);
		// 2:调用业务方法，处理请求
		if(p.getId() == null) { //如果id为空，就说明是新增操作  
			productDAO.save(p);
		}else {
			productDAO.update(p);  //如果不为空，就说明是修改操作
		}
		resp.sendRedirect("/product");
	}

	// 处理查询请求
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 老三样
		// 1:获取请求参数，封装成对象
		ProductQueryObject qo = request2QueryObject(req);
		// 2:调用业务方法，处理请求
		PageResult ps = productDAO.query(qo);
		List<ProductDir> dirs = (List<ProductDir>) dirDAO.query(new ProductDirQueryObject()).getListData();
		req.setAttribute("qo", qo);
		req.setAttribute("pageResult", ps);
		req.setAttribute("dirs", dirs);
		// 3:控制页面跳转
		req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
	}

	// 处理删除请求
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if(hasLength(id)) {
			productDAO.delete(Long.valueOf(id));
		}
		resp.sendRedirect("/product");
	}

	// 处理编辑请求（它的主要功能是将页面跳转到edit.jsp）
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//如果是修改操作，那么浏览器会传入被修改商品的id
        String id = req.getParameter("id");
        //判断id是否为空，调用业务方法处理请求
        if(hasLength(id)) {
        	Product p = (Product) productDAO.get(Long.valueOf(id));
        	req.setAttribute("p", p);
        }
        //查询出所有的商品分类，并设置到request中
        List<ProductDir> dirs = (List<ProductDir>) dirDAO.query(new ProductDirQueryObject()).getListData();
        req.setAttribute("dirs", dirs);
        req.getRequestDispatcher("/WEB-INF/views/product/edit.jsp").forward(req, resp);
	}

	// 从请求中获取参数，封装成一个ProductQueryObject对象
	private ProductQueryObject request2QueryObject(HttpServletRequest req) {
		String name = req.getParameter("name");
		String note = req.getParameter("note");
		String sMinprice = req.getParameter("minprice");
		String sMaxprice = req.getParameter("maxprice");
		String sDir = req.getParameter("dir");
		String keyword = req.getParameter("keyword");
		String currentPage = req.getParameter("currentPage");
		String pageSize = req.getParameter("pageSize");

		ProductQueryObject qo = new ProductQueryObject();
		if (StringUtils.isNotBlank(name)) {
			qo.setName(name);
		}
		if (StringUtils.isNotBlank(note)) {
			qo.setNote(note);
		}
		if (StringUtils.isNotBlank(sMinprice)) {
			qo.setMinprice(new BigDecimal(sMinprice));
		}
		if (StringUtils.isNotBlank(sMaxprice)) {
			qo.setMaxprice(new BigDecimal(sMaxprice));
		}
		if (StringUtils.isNotBlank(sDir)) {
			qo.setDir(Long.valueOf(sDir));
		}
		if (StringUtils.isNotBlank(keyword)) {
			qo.setKeyword(keyword);
		}
		if(StringUtils.isNotBlank(currentPage)) {
			qo.setCurrentPage(Integer.valueOf(currentPage));
		}
		if(StringUtils.isNotBlank(pageSize)) {
			qo.setPageSize(Integer.valueOf(pageSize));
		}
		return qo;
	}
	
	// 从请求中获取参数，封装成一个Product对象
	private Product request2Product(HttpServletRequest req) {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String note = req.getParameter("note");
		String price = req.getParameter("price");
		String count = req.getParameter("count");
		String dir = req.getParameter("dir");
		Product p = new Product();
		if(hasLength(id)) {
			p.setId(Long.valueOf(id));
		}
		p.setName(name);
		p.setNote(note);
		p.setPrice(new BigDecimal(price));
		p.setCount(Integer.valueOf(count));
		p.setDir(Long.valueOf(dir));
		return p;
	}
	
	//判断字符串是否为空
	private boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
}
