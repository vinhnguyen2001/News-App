package com.sourcedemo.filter;


import com.sourcedemo.constant.SystemConstant;
import com.sourcedemo.models.UserModel;
import com.sourcedemo.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        this.context  = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI(); // get specific url.

        if(url.startsWith("/admin")){

            UserModel model =(UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if(model!=null){
                if(model.getRole().getCode().equals(SystemConstant.ADMIN)){
                    filterChain.doFilter(request,response);

                }else if(model.getRole().getCode().equals(SystemConstant.USER)){
                    response.sendRedirect(request.getContextPath()+"/auth?action=login&message=not_permission&alert=danger");
                }
            }
            else{
            response.sendRedirect(request.getContextPath()+"/auth?action=login&message=not_login&alert=danger");
            }
        }else{
            // other URLs which aren't needed to filter.
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
