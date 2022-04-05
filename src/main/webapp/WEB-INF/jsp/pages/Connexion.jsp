<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ page import=" java.util.List" %>
<%@ page import="fr.reddev.encheres.Exception.CodesMessages.LecteurMessage" %>
<% List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");  %>
<c:if test="${listeCodesErreur != null}">
	<p style="color: red;">Erreur :</p>
	<% for(int erreur :  listeCodesErreur){%>	
		<p style="color: red;"><%=LecteurMessage.getMessageErreur(erreur)%></p>
	<%}%>
</c:if>
<main class="inscription">
	<form action="Connexion" method="post">
		<h1>Connexion</h1>
		<div class="groupForm">
			<label for="Pseudo">Pseudo</label> <input type="text" id="Pseudo"
				name="Pseudo" />
		</div>
		<div class="groupForm">
			<label for="MDP">Mot de passe</label> <input type="password" id="MDP"
				name="MDP" />
		</div>
		<div class="btnsForm">
			<a href="${context}/Inscription" >S'inscrire</a>
			<button type="submit">Se connecter</button>
		</div>
	</form>
</main>