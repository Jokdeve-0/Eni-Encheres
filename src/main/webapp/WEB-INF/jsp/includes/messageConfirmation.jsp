<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import=" java.util.List" %>
<%@ page import="fr.reddev.encheres.Exception.CodesMessages.LecteurMessage" %>
<% List<Integer> listeMessages= (List<Integer>) request.getSession().getAttribute("listeMessage");  %>

<section class="messagesConfirmation">
<%if(listeMessages != null){ %>
	<h2>Message de confirmation!</h2>
	<%for(int erreur : listeMessages) {%>
		<p>_ <%= LecteurMessage.getMessageErreur(erreur)%></p>
<%}} %>
</section>
