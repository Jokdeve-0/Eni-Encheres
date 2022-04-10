<nav class="menu-test">
	<c:if test="${utilisateur.administrateur}">Test Menu 
			<a href="${context}/test/AddArticle">Ajout d'un Article</a>
			<a href="${context}/test/TestAfficherTousUtilisateurs">Affichertous les utilisateurs</a>
	</c:if>
</nav>