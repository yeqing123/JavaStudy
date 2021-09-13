package com.yeqin.shoppingcart.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqin.shoppingcart.domain.CartItem;
import com.yeqin.shoppingcart.domain.ShoppingCart;

//处理向购物车中添加商品或删除商品的请求
@WebServlet("/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//从Session中获取一个购物侧属性，如果没有就创建一个
    	ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("SHOPPINGCART_IN_SESSION");
    	if(cart == null) {
    		cart = new ShoppingCart();
    		req.getSession().setAttribute("SHOPPINGCART_IN_SESSION", cart);
    	}
    	//1:获取请求参数
    	String cmd = req.getParameter("cmd");
    	//2:调用业务方法，处理请求
    	if("save".equals(cmd)) {
    		String name = req.getParameter("name");
    		String num = req.getParameter("num");
    		if("iphone".equals(name)) {
    			cart.save(new CartItem(123L, name, new BigDecimal(5000), Integer.valueOf(num)));
    		}else if("ipad".equals(name)) {
    			cart.save(new CartItem(456L, name, new BigDecimal(3000), Integer.valueOf(num)));
    		}else {
    			cart.save(new CartItem(789L, name, new BigDecimal(4000), Integer.valueOf(num)));
    		}
    	}
    	if("delete".equals(cmd)) {
    		String id = req.getParameter("id");
    		cart.delete(Long.valueOf(id));
    	}
    	//3:控制页面跳转
    	resp.sendRedirect("/shoppingCart.jsp");
    }
}
