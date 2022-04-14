<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<main class="inscription">
	<form action="" method="post">
		<h1>Gestion des utilisateurs et des catégories</h1>
		<%@include file="../includes/erreurs.jsp"%>
		<div>
			<h2>Gestion des utilisateurs</h2>
			<div class="groupForm">
				<c:forEach var="user" items="${listUser}">
					<Strong>${user.no_utilisateur}</Strong>
					<Strong>${user.pseudo}</Strong>
					<a class="delete"
						href="AdminSuppressionCompte?id=${user.no_utilisateur}">Supprimer</a>
					<a class="desactivate" href="">Désactiver</a>
			</c:forEach>		
			</div>
		</div>
	</form>

<div class="adminCat">
	<h2>Gestion des catégories</h2>
	<c:forEach var="cat" items="${categorie}">
		<form action="" method="post">
			<strong>${cat.libelle}</strong> <a class="delete"
				href="AdminSuppressionCategorie?id=${cat.no_categorie}">Supprimer</a>
		</form>
		<form action="AdminModifierCategorie?id=${cat.no_categorie}"
			method="post">
			<input required type="text" id="categorie" name="categorie"
				placeholder="Nouveau nom" />
			<button type="submit">Modifier la catégorie</button>
		</form>
	</c:forEach>
	<form action="AdminAjoutCategorie" method="post">
		<h3>Ajouter une catégorie</h3>
		<input required type="text" id="categorie" name="categorie"
			placeholder="Jeux vidéo" />
		<button type="submit">Ajouter</button>
	</form>
</div>


</main>