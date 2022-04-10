<section class="Menu-App" style="display:none;">
	<nav>
		<a href="${context}/Home">Accueil</a>
		<c:choose>
			<c:when test="${utilisateur != null }">
<<<<<<< HEAD
				<a href="${context}/MonProfil">Profile</a>
				<a href="${context}/VendreUnArticle">Vendre article</a>
				<a href="${context}/Deconnexion">Déconnexion</a>
=======
				<a href="${context}/Deconnexion">DÃ©connexion</a>						
				<a href="${context}/MonProfil">Profile</a>						
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
			</c:when>
			<c:otherwise>
				<a href="${context}/Inscription">Inscription</a>
				<a href="${context}/Connexion">Connexion</a>
			</c:otherwise>
		</c:choose>
	</nav>
</section>