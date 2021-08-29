package com.yeqing._02_el;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/el")
public class ELServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resq) 
    		throws ServletException, IOException {
    	Employee emp = new Employee();
    	req.setAttribute("emp", emp);
    	req.getRequestDispatcher("/el/el.jsp").forward(req, resq);
    }
}
