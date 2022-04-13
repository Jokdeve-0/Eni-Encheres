<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<main class="home">
	<article class="Article-Page">
		<div class="box-title">
			<div class="img"></div>
			<div class="title">
				<div class="groupForm">
					<p>
						<strong>${article.nom_article}</strong>
					</p>
				</div>
				<div class="groupForm">
					<p>
						<strong>${article.description }</strong>
					</p>
				</div>
				<div class="groupForm">
					<c:forEach var="cat" items="${categorie}">
						<c:if test="${cat.no_categorie == article.no_categorie}">
							<p>
								<strong>Catégorie: </strong><strong>${cat.libelle}</strong>
							</p>
						</c:if>
					</c:forEach>
				</div>
				<div class="groupForm">
					<p>
						<strong>Prix de départ: </strong><strong>${article.prix_initial}
							pts</strong>
					</p>
				</div>
			</div>
		</div>
		<hr />
		<div class="box-details">
			<div class="groupForm">
				<p>
					<strong>Début de l'enchère: </strong><strong><fmt:formatDate
							pattern="dd/MM/yy" value="${article.date_debut_encheres}" /></strong>
				</p>
			</div>
			<hr>
			<div class="groupForm">
				<p>
					<strong>Fin de l'enchère: </strong> <strong><fmt:formatDate
							pattern="dd/MM/yy" value="${article.date_fin_encheres}" /></strong>
				</p>
			</div>
			<hr>
			<div class="groupForm">
				<p>
					<strong>Vendeur: </strong><a
						href="${context}/MonProfil?idVendeur=${vendeur.no_utilisateur }">${vendeur.pseudo}</a>
				</p>
			</div>
			<hr>
			<div class="groupForm">
				<p>
					<strong>Retrait: </strong><strong class="rue">${vendeur.rue }&ensp;&ensp;${vendeur.code_postal }&ensp;${vendeur.ville }</strong>
				</p>
			</div>
		</div>
	</article>
	<section class="Article-Page">
		<%@include file="../includes/erreursSession.jsp"%>
		<%@include file="../includes/messageConfirmation.jsp"%>
		<div class="bestAuction">
			<c:if test="${enchere.no_utilisateur > 0}">
				<p>Meilleure enchère actuelle</p>
				<p>
					<strong>${utilisateur.pseudo == bestEnchere.pseudo ? "Vous" : bestEnchere.pseudo}
						avec </strong> <strong>${enchere.montant_enchere} pts</strong>
				</p>
			</c:if>
			<c:if test="${enchere.no_utilisateur < 0}">
				<p>
					<strong>Il n'y a pas encore d'enchère pour cette article.</strong><strong>Soyez
						le premier a enchérir !</strong>
				</p>
			</c:if>
			<c:if
				test="${utilisateur != null && utilisateur.pseudo != vendeur.pseudo && etatEnchere == 'EC'}">
				<form action="Encherir?id=${article.no_article}" method="post"
					class="formEncherir">
					<label for="prix">Votre proposition:</label> <input type="number"
						id="prix" name="howmuch">
					<button type="submit">Encherir</button>
				</form>
			</c:if>
			<c:if test="${utilisateur == null}">
				<div class="formEncherir">
					<p>
						Si vous souhaitez encherir, veuillez <br> <a
							href="${context}/Connexion">vous connecter</a> ou <a
							href="${context}/Inscription">créer un compte</a>
					</p>
				</div>
			</c:if>
		</div>
			<div class="ListEnchere">
			<h2>Historique des enchères</h2>
			<hr />
				<c:forEach var="ench" items="${encheresArticle}">
					<p>
						<strong>${ench.pseudo}</strong><strong>${ench.montant_enchere} pts</strong>
					</p>
					<hr />
				</c:forEach>
			</div>
	</section>
</main>
<!-- test="${etatEnchere == 'EC'}"-->