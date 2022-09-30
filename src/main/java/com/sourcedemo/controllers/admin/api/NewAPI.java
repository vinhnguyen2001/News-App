package com.sourcedemo.controllers.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcedemo.models.NewModel;
import com.sourcedemo.models.UserModel;
import com.sourcedemo.services.INewService;
import com.sourcedemo.utils.HttpUtil;
import com.sourcedemo.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {

    @Inject
    private INewService newService;

    private static final long serialVersionUID = -915988021506484384L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel newModel =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
        newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        newModel = newService.save(newModel);
        mapper.writeValue(response.getOutputStream(), newModel);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel updateNew =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
        updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        updateNew = newService.update(updateNew);
        mapper.writeValue(response.getOutputStream(), updateNew);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel newModel =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
        newService.delete(newModel.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

}