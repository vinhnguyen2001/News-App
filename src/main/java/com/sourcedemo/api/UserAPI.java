package com.sourcedemo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcedemo.daos.impl.UserDAO;
import com.sourcedemo.models.UserModel;
import com.sourcedemo.services.impl.UserService;
import com.sourcedemo.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "user-api", value = "/user-api")
public class UserAPI extends HttpServlet {

    public UserAPI() {
        super();
    }

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);

        userModel = userService.findByUserId(userModel.getId());
        userModel.setMessage("Xin chào FE");
        mapper.writeValue(response.getOutputStream(), userModel);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);

        userModel = userService.save(userModel);

        mapper.writeValue(response.getOutputStream(), userModel);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        UserModel userModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
        userModel = userService.update(userModel);
        mapper.writeValue(resp.getOutputStream(), userModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setBufferSize(8192);
        PrintWriter out = response.getWriter();
        out.println("null");
        out.close();
    }
}
