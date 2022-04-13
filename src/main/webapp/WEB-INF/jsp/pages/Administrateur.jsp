<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<main>
	<h1>Gestion des utilisateurs et des catégories</h1>
	
	<h2>Gestion des utilisateurs</h2>

			<c:forEach var="user" items="${listUser}">
				<form action = "" method = "post">
					<Strong>${user.no_utilisateur}</Strong>
					<Strong>${user.pseudo}</Strong><br>
					<a  class="delete" href="AdminSuppressionCompte?id=${user.no_utilisateur}">Supprimer</a><br>	
					<a  class="desactivate" href="">Désactiver</a><br>	
				</form>
			</c:forEach>


	<h2>Gestion des catégories</h2>
			<c:forEach var="cat" items="${categorie}">
				<form action="" method = "post">
					<strong>${cat.libelle}</strong><br>
					<a  class="delete" href="AdminSuppressionCategorie?id=${cat.no_categorie}">Supprimer</a>
				</form>
				<form action="AdminModifierCategorie?id=${cat.no_categorie}" method ="post">
					<input required type="text" id="categorie" name="categorie" placeholder="Nouveau nom"/>
					<button type="submit">Modifier la catégorie</button>
				</form>
			</c:forEach>
			<form action= "AdminAjoutCategorie" method = "post">
				<h3>Ajouter une catégorie</h3>
				<input required type="text" id="categorie" name="categorie" placeholder="Jeux vidéo"/>
				<button type="submit">Ajouter</button>
			</form>

			
</main>