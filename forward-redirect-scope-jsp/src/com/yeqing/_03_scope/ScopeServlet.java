package com.yeqing._03_scope;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	    
	    //===============request对象=============================
	    Integer numInRequest = (Integer)req.getAttribute("NUM_IN_REQUEST");
	    if(numInRequest == null) {
	    	req.setAttribute("NUM_IN_REQUEST", 1);
	    } else {
	    	req.setAttribute("NUM_IN_REQUEST", numInRequest +1);
	    }
	    
	    //===============session对象================================
	    Integer numInSession = (Integer) req.getSession().getAttribute("NUM_IN_SESSION");
	    if(numInSession==null) {
	    	req.getSession().setAttribute("NUM_IN_SESSION", 1);
	    } else {
	    	req.getSession().setAttribute("NUM_IN_SESSION", numInSession + 1);
	    }
		
	    //================application对象===========================
		ServletContext app = super.getServletContext();
		Integer numInApp = (Integer) app.getAttribute("NUM_IN_APP");
		if(numInApp==null) {
			app.setAttribute("NUM_IN_APP", 1);
		} else {
			app.setAttribute("NUM_IN_APP", numInApp+1);
		}
		//=================跳转界面===================================
		req.getRequestDispatcher("/result").forward(req, resp);
		
	}
}
