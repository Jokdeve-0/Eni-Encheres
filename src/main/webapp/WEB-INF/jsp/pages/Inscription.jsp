<%@ page pageEncoding="UTF-8" %>
<<<<<<< HEAD
=======
<%@include file="../includes/erreurs.jsp" %>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
<main class="inscription">
	<form action="Inscription" method="post">
		<h1>Formulaire d'inscription</h1>
<%@include file="../includes/erreurs.jsp" %>
		<div>
			<div class="groupForm">
				<label for="pseudo">pseudo</label>
<<<<<<< HEAD
				<input required type="text" id="pseudo" name="pseudo" placeholder="Pseudo (max 30 caratères)" value="jok"/>
			</div>
			<div class="groupForm">
				<label for="nom">nom</label>
				<input required type="text" id="nom" name="nom" placeholder="Nom (max 30 caratères)" value="tt" />
			</div>
			<div class="groupForm">
				<label for="prenom">prénom</label>
				<input required type="text" id="prenom" name="prenom" placeholder="Prénom (max 30 caratères)" value="tt" />
			</div>
			<div class="groupForm">
				<label for="email">email</label>
				<input required type="email" id="email" name="email" placeholder="encheres@eni.fr (max 40 caratères)" value="tt@tt.fr"/>
			</div>
			<div class="groupForm">
				<label for="telephone">téléphone</label>
				<input required type="tel" id="telephone" name="telephone" placeholder="(*FR) 06...(10 chiffres)"  pattern="[0-9]{10}" value="0123456789" />
=======
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
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
			</div>
		</div>
		<div>
			<div class="groupForm">
				<label for="rue">rue</label>
<<<<<<< HEAD
				<input required type="text" id="rue" name="rue" placeholder="* (max 60 caratères) " value="tt" />
			</div>
			<div class="groupForm">
				<label for="codePostal">code Postal</label>
				<input required type="text" id="codePostal" name="codePostal" placeholder="(*FR)  75000" pattern="[0-9]{5}" value="12345"/>
			</div>
			<div class="groupForm">
				<label for="ville">ville</label>
				<input required type="text" id="ville" name="ville" placeholder="Paris (max 30 caratères)" value="tt" />
			</div>
			<div class="groupForm">
				<label for="mdp">Password</label>
				<input required type="password" id="mdp" name="mdp" placeholder="********" value="$" />
			</div>
			<div class="groupForm">
				<label for="mdpConfirme">confirmer Password</label>
				<input required type="password" id="mdpConfirme" name="mdpConfirme" placeholder="********" value="$" />
=======
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
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
			</div>
		</div>
		<div class="btnsForm">
			<button type ="reset">Réinitialiser</button>
			<button type="submit">S'inscrire</button>
		</div>
	</form>
</main>