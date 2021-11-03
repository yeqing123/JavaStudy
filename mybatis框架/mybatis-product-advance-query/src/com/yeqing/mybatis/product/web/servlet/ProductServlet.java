package com.yeqing.mybatis.product.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeqing.mybatis.product.dao.mapper.ProductMapper;
import com.yeqing.mybatis.product.dao.mapper.SubDirMapper;
import com.yeqing.mybatis.product.dao.util.MybatisUtil;
import com.yeqing.mybatis.product.domain.Product;
import com.yeqing.mybatis.product.domain.SubDir;
import com.yeqing.mybatis.product.domain.User;
import com.yeqing.mybatis.product.page.PageIndex;
import com.yeqing.mybatis.product.query.QueryObject;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private User u = null;
    private ProductMapper productMapper = MybatisUtil.getMapper(ProductMapper.class);
    private SubDirMapper subDirMapper = MybatisUtil.getMapper(SubDirMapper.class);
    
    //获取请求类型，并分发请求
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String cmd = req.getParameter("cmd");
		u = (User) req.getSession().getAttribute("u");  //从Session对象中获取当前登录的用户信息
		if(u == null) {  //如果Session对象中的没有用户信息，说明已经登录超时，需要重新登录
			req.setAttribute("msg", "登录超时，请重新登录！");
			req.getRequestDispatcher("/Login").forward(req, resp);
		}
		
		if("edit".equals(cmd)) {
			edit(req, resp);
		}else if("delete".equals(cmd)) {
			delete(req, resp);
		}else if("saveOrUpdate".equals(cmd)) {
			saveOrUpdate(req, resp);
		}else if("exit".equals(cmd)) {
			exit(req, resp);
		}else {
			list(req, resp);
		}
	}
	
	//处理显示商品列表的请求
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		QueryObject qo = parameter2QueryObject(req, resp);  //从页面中获取高级查询和分页查询的条件，并封装成一个QueryObject对象
		PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize()); //使用mybatis的PageHelper插件，实现分页查询
		List<Product> list = productMapper.query(qo);    //进行数据库的高级查询+分页查询
		List<SubDir> dirs = subDirMapper.listAll();      //查询出所有的分类信息
		PageInfo<Product> pageInfo = new PageInfo<>(list);  //包装成一个PageInfo，它包含了分页的信息
		PageIndex pageIndex = PageIndex.getPageIndex(3, pageInfo.getPageNum(), pageInfo.getPages());
		//设置页面所需的请求参数
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("pageIndex", pageIndex);
		req.setAttribute("dirs", dirs);
		req.setAttribute("qo", qo);
		if(u != null && u.getIsManager()) {  //如果登录的是管理员，就跳转到管理员页面
			req.getRequestDispatcher("/WEB-INF/views/product/list_manager.jsp").forward(req, resp);
		}
		if(u != null && !u.getIsManager()) {  //如果是普通用户，就跳转到用户页面
			req.getRequestDispatcher("/WEB-INF/views/product/list_user.jsp").forward(req, resp);
		}
	}
	//处理用户发出的添加或修改请求，并跳转到edit.jsp页面
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		if(hasLength(idStr)) {    //如果商品的id不为空，说明是修改指定商品的信息，否则就是添加新商品信息
			Product p = productMapper.get(Long.valueOf(idStr));
			req.setAttribute("p", p);
		}
		List<SubDir> dirs = subDirMapper.listAll();  //获得所有的分类信息的集合
		req.setAttribute("dirs", dirs);
		req.getRequestDispatcher("/WEB-INF/views/product/edit.jsp").forward(req, resp);
	}
	//添加或修改商品信息
	private void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Product p = parameter2Product(req, resp);
		if(p.getPid() == null) {  //判断edit.jsp页面获取的请求参数中，隐藏标签id是否为空
			productMapper.save(p);  //为空，添加新的商品信息
		}else {
			productMapper.update(p); //不为空，修改指定商品信息
		}
		resp.sendRedirect("/products");
	}
	//删除指定商品
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		if(hasLength(idStr)) {
			productMapper.delete(Long.valueOf(idStr));
		}
		resp.sendRedirect("/products");
	}
	//退出登录
	private void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("u");  //清除Session中的用户属性
		session.removeAttribute("pageInfo"); //清除Session中的结果集PageInfo
 		resp.sendRedirect("/Login");   //重定向到登录页面
	}
	
	//从请求中获取参数，将它们封装成一个QueryObject对象
	private QueryObject parameter2QueryObject(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");
		String note = req.getParameter("note");
		String currentPageStr = req.getParameter("currentPage");
		String pageSizeStr = req.getParameter("pageSize");
		String minpriceStr = req.getParameter("minprice");
		String maxpriceStr = req.getParameter("maxprice");
		String keyword = req.getParameter("keyword");
		String dirStr = req.getParameter("dir");
		
		QueryObject qo = new QueryObject();
		if(hasLength(currentPageStr)) {
			qo.setCurrentPage(Integer.valueOf(currentPageStr));
		}
		if(hasLength(pageSizeStr)) {
			qo.setPageSize(Integer.valueOf(pageSizeStr));
		}
		if(hasLength(name)) {
			qo.setName(name);
		}
		if(hasLength(note)) {
			qo.setNote(note);
		}
		if(hasLength(minpriceStr)) {
			qo.setMinprice(new BigDecimal(minpriceStr));
		}
		if(hasLength(maxpriceStr)) {
			qo.setMaxprice(new BigDecimal(maxpriceStr));
		}
		if(hasLength(keyword)) {
			qo.setKeyword(keyword);
		}
		if(hasLength(dirStr)) {
			qo.setDir(Long.valueOf(dirStr));
		}
		return qo;
	}
	
	//从请求中获取参数，将它们封装成一个Product对象
	private Product parameter2Product(HttpServletRequest req, HttpServletResponse resp) {
		String idStr = req.getParameter("id");
		String name = req.getParameter("name");
		String note = req.getParameter("note");
		String priceStr = req.getParameter("price");
		String countStr = req.getParameter("count");
		String dirStr = req.getParameter("dir");
		Product p  = new Product();

		if(hasLength(idStr)) {
			p.setPid(Long.valueOf(idStr));
		}
		if(hasLength(name)) {
			p.setName(name);
		}
		if(hasLength(note)) {
			p.setNote(note);
		}
		if(hasLength(priceStr)) {
			p.setPrice(new BigDecimal(priceStr));
		}
		if(hasLength(countStr)) {
			p.setCount(Integer.valueOf(countStr));
			
		}
		if(hasLength(dirStr)) {
			Long dir = Long.valueOf(dirStr);
			if(dir != -1) {
				p.setSubDir(dir);
			}else {
				p.setSubDir(null);
			}
		}
		return p;
	}
	private boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
}
