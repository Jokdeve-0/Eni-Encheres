<<<<<<< HEAD
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../tests/includes/Menu-Test.jsp" %>

<main class="home">
	<h1>
		ENI-Enchères <br /> <span>la plateforme web qui vous permet de
			céder ou d'acheter des objets sans transaction financière.</span>
	</h1>
	<section class="box-filter">
		<%@include file="../includes/filtreArticle.jsp"%>
		<div class="box-filterHome">
			<c:if test="${utilisateur != null }">
				<%@include file="../includes/filtresHome.jsp"%>
			</c:if>
		</div>
	</section>
	<%@include file="../includes/erreurs.jsp"%>
	<div class="box-articles">
		<c:forEach var="article" items="${catalogue}">
			<article class="container">
				<div class="poster">
					<div class="poster__img"></div>
					<div class="poster__info">
						<h3 class="poster__title">${article.nom_article}</h3>
						<hr /><br>
						<p class="poster__text">
							<span><strong>Prix:</strong><strong>${article.prix_initial }€</strong></span>
							<span><strong>Fin de l'enchére:</strong><strong><fmt:formatDate pattern="dd/MM/yy" value="${article.date_fin_encheres}" /></strong></span>
							<c:forEach var="user" items="${users}">
								<c:if test="${user.no_utilisateur == article.no_utilisateur }">								
									<span><strong>Retrait: </strong><strong class="rue">${user.rue}<br> ${user.code_postal} ${user.ville }</strong></span>
								</c:if>
							</c:forEach>
							  <span><strong>Vendeur:</strong><strong> ${article.vendeur}</strong></span>
						</p>
					</div>
				</div>
				<a class="btn-article" href="Article?id=${article.no_article}">Voir les détails</a>
			</article>
		</c:forEach>
	</div>

=======
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
          
          <p>Fin de l'enchére : <fmt:formatDate pattern = "dd/MM/yy" value="${article.date_fin_encheres}"/></p>
           <p>Vendeur : ${article.vendeur}</p>
</div>
</c:forEach>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958

</main>