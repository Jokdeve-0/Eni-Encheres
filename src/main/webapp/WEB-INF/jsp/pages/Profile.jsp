<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<<<<<<< HEAD
=======
<%@include file="../includes/userConnect.jsp"%>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
<main class="Inscription">
	<%@include file="../includes/erreurs.jsp"%>
	<section class="MonProfil">
		<h1>Profile</h1>
		<div class="MonProfil-card">
			<div>
				<div class="groupForm">
					<p>pseudo :</p>
					<p>${utilisateur.getPseudo()}</p>
				</div>
				<div class="groupForm">
					<p>nom :</p>
					<p>${utilisateur.getNom()}</p>
				</div>
				<div class="groupForm">
<<<<<<< HEAD
					<p>prénom :</p>
					<p>${utilisateur.getPrenom()}</p>
				</div>
				<div class="groupForm">
					<p>email :</p>
					<p>${utilisateur.getEmail()}</p>
				</div>
				
			</div>
			<div>
				<div class="groupForm">
=======
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
					<p>téléphone :</p>
					<p>${utilisateur.getTelephone()}</p>
				</div>
				<div class="groupForm">
					<p>rue :</p>
					<p>${utilisateur.getRue()}</p>
				</div>
				<div class="groupForm">
<<<<<<< HEAD
					<p>code Postal :</p>
					<p>${utilisateur.getCode_postal()}</p>
				</div>
				<div class="groupForm">
					<p>ville :</p>
					<p>${utilisateur.getVille()}</p>
				</div>
=======
					<p>ville :</p>
					<p>${utilisateur.getVille()}</p>
				</div>
			</div>
			<div>
			<div class="groupForm">
					<p>email :</p>
					<p>${utilisateur.getEmail()}</p>
				</div>
				<div class="groupForm">
					<p>prénom :</p>
					<p>${utilisateur.getPrenom()}</p>
				</div>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
				<div class="groupForm">
					<p>Crédit :</p>
					<p>${utilisateur.getCredit()} pts</p>
				</div>
<<<<<<< HEAD
=======
				<div class="groupForm">
					<p>code Postal :</p>
					<p>${utilisateur.getCode_postal()}</p>
				</div>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
			</div>
			<div class="btnsForm">
				<a href="ModifierProfil">Modifier</a>
			</div>
		</div>
		
	</section>
</main>