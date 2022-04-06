<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/userConnect.jsp" %>
<main class="Inscription">
<%@include file="../includes/erreurs.jsp" %>
		<h1>Profile</h1>
		<div>
			<div class="groupForm">
				<p>pseudo : ${utilisateur.getPseudo()}</p>
			</div>
			<div class="groupForm">
				<p>nom :${utilisateur.getNom()}</p>
			</div>
			<div class="groupForm">
				<p>prénom :${utilisateur.getPrenom()}</p>
			</div>
			<div class="groupForm">
				<p>email :${utilisateur.getEmail()}</p>	
			</div>
			<div class="groupForm">
				<p>téléphone :${utilisateur.getTelephone()}</p>
			</div>
		</div>
		<div>
			<div class="groupForm">
				<p>rue :${utilisateur.getRue()}</p>
			</div>
			<div class="groupForm">
				<p>code Postal :${utilisateur.getCode_postal()}</p>
			</div>
			<div class="groupForm">
				<p>ville :${utilisateur.getVille()}</p>	
			</div>
			<div class="groupForm">		
				<p>Crédit :${utilisateur.getCredit()}</p>
			</div>
		</div>
		<div class="btnsForm">
			<a href="ModifierProfil">Modifier</a>
		</div>
</main>