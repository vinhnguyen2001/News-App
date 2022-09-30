package com.sourcedemo.controllers.web;

import com.sourcedemo.services.ICategoryService;
import com.sourcedemo.services.IUserService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home","/auth","/thoat"})
public class HomeController extends HttpServlet {
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private IUserService userService;
	
	private static final long serialVersionUID = 2686801510274002166L;

//	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if(action != null && action.equals("login")){
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}
		else if(action != null && action.equals("logout")){

		}
		else{
			//request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getParameter("action");
//		if (action != null && action.equals("login")) {
//			UserModel model = FormUtil.toModel(UserModel.class, request);
//			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
//			if (model != null) {
//				SessionUtil.getInstance().putValue(request, "USERMODEL", model);
//				if (model.getRole().getCode().equals("USER")) {
//					response.sendRedirect(request.getContextPath()+"/trang-chu");
//				} else if (model.getRole().getCode().equals("ADMIN")) {
//					response.sendRedirect(request.getContextPath()+"/admin-home");
//				}
//			} else {
//				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
//			}
//		}
	}
}
