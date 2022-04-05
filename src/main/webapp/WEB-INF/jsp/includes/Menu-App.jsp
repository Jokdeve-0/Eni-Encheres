	<%@ page pageEncoding="UTF-8" %>
	<section>
		<nav>
			<a href="${context}/Home">Accueil</a>
			<c:choose>
			<c:when test="${utilisateur != null }">
				<a href="${context}/Deconnexion">Déconnexion</a>						
			</c:when>
			<c:otherwise>
				<a href="${context}/Inscription">Inscription</a>
				<a href="${context}/Connexion">Connexion</a>			
			</c:otherwise>
			</c:choose>
		</nav>
	</section>