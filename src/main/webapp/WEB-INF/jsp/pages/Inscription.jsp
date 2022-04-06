<%@ page pageEncoding="UTF-8" %>
<%@include file="../includes/erreurs.jsp" %>
<main class="inscription">
	<form action="Inscription" method="post">
		<h1>Formulaire d'inscription</h1>
		<div>
			<div class="groupForm">
				<label for="pseudo">pseudo</label>
				<input required type="text" id="pseudo" name="pseudo"/>
			</div>
			<div class="groupForm">
				<label for="nom">nom</label>
				<input required type="text" id="nom" name="nom" />
			</div>
			<div class="groupForm">
				<label for="prenom">prénom</label>
				<input required type="text" id="prenom" name="prenom" />
			</div>
			<div class="groupForm">
				<label for="email">email</label>
				<input required type="email" id="email" name="email"/>
			</div>
			<div class="groupForm">
				<label for="telephone">téléphone</label>
				<input required type="tel" id="telephone" name="telephone" placeholder="FR 0605040302"  pattern="[0-9]{10}" />
			</div>
		</div>
		<div>
			<div class="groupForm">
				<label for="rue">rue</label>
				<input required type="text" id="rue" name="rue" />
			</div>
			<div class="groupForm">
				<label for="codePostal">code Postal</label>
				<input required type="text" id="codePostal" name="codePostal" placeholder="75000" pattern="[0-9]{5}"/>
			</div>
			<div class="groupForm">
				<label for="ville">ville</label>
				<input required type="text" id="ville" name="ville" />
			</div>
			<div class="groupForm">
				<label for="mdp">mot de passe</label>
				<input required type="password" id="mdp" name="mdp" />
			</div>
			<div class="groupForm">
				<label for="mdpConfirme">confirmation du mot de passe</label>
				<input required type="password" id="mdpConfirme" name="mdpConfirme"/>
			</div>
		</div>
		<div class="btnsForm">
			<button type ="reset">Réinitialiser</button>
			<button type="submit">S'inscrire</button>
		</div>
	</form>
</main>