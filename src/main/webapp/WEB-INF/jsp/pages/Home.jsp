<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/userConnect.jsp" %>

<main class="home">
<%@include file="../includes/erreurs.jsp" %>
	<h1>Eni-Enchère</h1>
	<h2>Filtres :</h2><br>
	<label for="pet-select">Catégorie</label>

<select name="pets" id="pet-select">
    <option value="">Toutes</option>
    <option value="dog">Dog</option>
    <option value="cat">Cat</option>
    <option value="hamster">Hamster</option>
    <option value="parrot">Parrot</option>
    <option value="spider">Spider</option>
    <option value="goldfish">Goldfish</option>
</select><br>

<label for="site-search"></label>

<input type="search" id="site-search" name="q"><br>
<button>Search</button><br>

  <div class="projet">
<div class="photo"></div>
          <p>Rocket stove pour riz et pâtes</p>
          <p>Prix:9.99€</p>
          <p>Fin de l'enchére: 09/10/2018</p>
          <p>Retrait : 5 rue des Pinsons<br>44000 Nantes</p>
          <p>Vendeur : NineJea</p>

        </div>

</main>