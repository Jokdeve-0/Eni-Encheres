<%@ page pageEncoding="UTF-8" %>
<main class="inscription">
	<form action="" method="post">
		<h1>Modifier Profile</h1>
		<div>
			<div class="groupForm">
				<p>pseudo : ${utilisateur.getPrenom()} }</p>
			
			</div>
			<div class="groupForm">
				<label for="nom">nom</label>
				<input required type="text" id="nom" />
			</div>
			<div class="groupForm">
				<label for="prenom">prénom</label>
				<input required type="text" id="prenom" />
			</div>
		
			<div class="groupForm">
				<label for="telephone">téléphone</label>
				<input required type="tel" id="telephone" placeholder="FR 0605040302"  pattern="[0-9]{10}" />
			</div>
		</div>
		<div>
			<div class="groupForm">
				<label for="rue">rue</label>
				<input required type="text" id="rue" />
			</div>
			<div class="groupForm">
				<label for="codePostal">code Postal</label>
				<input required type="text" id="codePostal" placeholder="75000" pattern="[0-9]{5}"/>
			</div>
			<div class="groupForm">
				<label for="ville">ville</label>
				<input required type="text" id="ville" />
			</div>
		</div>
		<div class="btnsForm">
			<button type ="submit">Modifier</button>
			
		</div>
	</form>
</main>