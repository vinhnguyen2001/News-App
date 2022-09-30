package com.sourcedemo.controllers.admin;

import com.sourcedemo.constant.SystemConstant;
import com.sourcedemo.models.NewModel;
import com.sourcedemo.paging.PageRequest;
import com.sourcedemo.paging.Pageable;
import com.sourcedemo.services.INewService;
import com.sourcedemo.sort.Sorter;
import com.sourcedemo.utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {



    private static final long serialVersionUID = 2686801510274002166L;

    @Inject
    private INewService newService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get parameters from client and switch it to map with model class equivalent to them.
        NewModel model = FormUtil.toModel(NewModel.class,request);

        // encapsulates the parameters into  a particular object.
        Pageable pageable  = new PageRequest(model.getPage(),model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));

        model.setListResult(newService.findAll(pageable));
        model.setTotalItem(newService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
        request.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/new/list.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
