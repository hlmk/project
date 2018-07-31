<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>spring boot demo - jsp</title>
	<link href="/static/css/index.css" rel="stylesheet"/>
</head>
<body>
	<img src="/static/images/logo.png" alt="logo"/>
	<h1 id="title"> ${title} </h1>
	
	<c:url value="http://www.roncoo.com" var="url"/>	
	<spring:url value="http://www.roncoo.com" htmlEscape="true" var="springUrl"/>
	
	Spring URL: ${springUrl}
	<br/>
	JSTL URL: ${url}
</body>
</html>