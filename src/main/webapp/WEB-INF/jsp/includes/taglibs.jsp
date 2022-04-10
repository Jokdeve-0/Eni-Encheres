<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="FR-fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${titlePage} - ENI-enchère</title>
<link rel="stylesheet" href="${context}/assets/css/styles.css" />
<link  type="image/png" rel="icon" href="${context}/assets/img/eni.png" >
</head>
<body>
<%@ include file="header.jsp"%>
<%@include file="../includes/userConnect.jsp"%>