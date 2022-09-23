package com.sourcedemo.controllers;

import com.sourcedemo.models.UserModel;
import com.sourcedemo.services.IUserService;
import com.sourcedemo.services.impl.UserService;


import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = { "/homepage"})
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 2686801510274002166L;

    @Inject
    private  IUserService userService;

    private UserService user = new UserService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        UserModel  x =  userService.findByUserId(1L);
//        System.out.println("gia tri" + x.get(0).getFullName());
        request.setAttribute("value",x);
        RequestDispatcher rd = request.getRequestDispatcher("/views/home.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
