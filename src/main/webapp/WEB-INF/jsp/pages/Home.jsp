<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/userConnect.jsp" %>

<main class="home">
<%@include file="../includes/erreurs.jsp" %>
	<h1>Eni-Enchère</h1>
	<section>
		<div class="filtres">
			<h2>Filtres :</h2>
			<form action="Home" method="post" class="formHome">
				<div>
					<label for="categories">Catégorie</label>
					<select name="categorie" id="categories">
					    <option value="Toutes">Toutes les catégories</option>
					    <c:forEach var="cat" items="${categorie}">
					    	<option value="${cat.libelle}">${cat.libelle}</option>
					    </c:forEach>
					   
					</select>		
				</div>
				<div>
					<input type="search" id="site-search" name="search">
					<button type="submit">Rechercher</button>
				</div>
			</form>
		</div>
		<div>	
			<c:if test="${utilisateur != null }">
				<%@include file="../includes/filtresHome.jsp" %>
			</c:if>
		</div>	
	</section>
<c:forEach var="article" items="${catalogue}">
  <div class="projet">
<div class="photo"></div>
          <p>${article.nom_article}</p>
          <p>Prix:${article.prix_initial }€</p>
          <p>Fin de l'enchére: ${article.date_fin_encheres }</p>
          <p>Retrait : 5 rue des Pinsons<br>44000 Nantes</p>
          <p>Vendeur : NineJea</p>
</div>
</c:forEach>

</main>