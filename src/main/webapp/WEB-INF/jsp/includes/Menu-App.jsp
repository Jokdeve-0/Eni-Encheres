<section class="Menu-App" style="display:none;">
	<nav>
		<a href="${context}/Home">Accueil</a>
		<c:choose>
			<c:when test="${utilisateur != null }">
				<a href="${context}/MonProfil">Profile</a>
				<a href="${context}/VendreUnArticle">Vendre article</a>
				<a href="${context}/Deconnexion">Déconnexion</a>
			</c:when>
			<c:otherwise>
				<a href="${context}/Inscription">Inscription</a>
				<a href="${context}/Connexion">Connexion</a>
			</c:otherwise>
		</c:choose>
	</nav>
</section>