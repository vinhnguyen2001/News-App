<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập</title>

</head>
<body id="LoginForm">
<div class="container">
    <div class="login-form">
        <h1 class="heading-title mt-5">Login Form</h1>
        <div class="main-div">
            <c:if test="${not empty message}">
                <div class="alert alert-${alert}">
                        ${message}
                </div>
            </c:if>
            <form action="<c:url value='/auth'/>" id="formLogin" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="userName" name="userName"
                           placeholder="Enter your username">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Enter your password">
                </div>
                <input type="hidden" value="login" name="action"/>
                <button type="submit" class="btn btn-primary" >Login</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>