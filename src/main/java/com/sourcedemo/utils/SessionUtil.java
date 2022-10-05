package com.sourcedemo.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    private static SessionUtil sessionUtil = null;

    public static SessionUtil getInstance() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }

    public void putValue(HttpServletRequest request, String key, Object value) {
        // we use session object , which have a key, and value field.
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key) {

        return request.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest request, String key) {

        request.getSession().removeAttribute(key);
    }
}
