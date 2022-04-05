<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import=" java.util.List" %>
<%@ page import="fr.reddev.encheres.Exception.CodesMessages.LecteurMessage" %>
<% List<Integer> listeErreurs = (List<Integer>) request.getAttribute("listeErreur");  %>
<c:if test="${listeErreur != null}">
	<p style="color: red;">Erreur :</p>
	<% for(int erreur :  listeErreurs){%>	
		<p style="color: red;"><%=LecteurMessage.getMessageErreur(erreur)%></p>
	<%}%>
</c:if>