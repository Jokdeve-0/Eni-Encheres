<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<<<<<<< HEAD
=======
<%@include file="../includes/userConnect.jsp" %>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
<main class="home">
    <%@include file="../includes/erreurs.jsp" %>
      
	<c:url var="VenteArticle" value="/venteArticle" />
<<<<<<< HEAD
	<form method="post" action="VendreUnArticle">

    <div>
        <label for="Article">Nom</label>
        <input type="text" id="name" name="name" required >
    </div>
    <div>
        <label for="story">Description</label>
=======
	<form method="POST" action="VendreUnArticle">

    <div>
        <label for="Article">Name (4 to 8 characters):</label>
        <input type="text" id="name" name="name" required >
    </div>
    <div>
        <label for="story">Description : </label>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
        <textarea id="story" name="story" ></textarea>
    </div>
    <div>
        <label for="categorie">Catégorie</label>
        <select name="categorie" id="categorie">
<<<<<<< HEAD
            <c:forEach var="cat" items="${categorie}">
            	<option value="${cat.libelle}">${cat.libelle}</option>
            </c:forEach>
=======
            <option value="informatique">informatique</option>
            <option value="vêtements">vêtements</option>
            <option value="test">test</option>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
            
        </select>
    </div>
    <div class="photo" style="width:50px;height:50px;background:#000;"></div>

    <div>
        <label for="prix">Prix initial : </label>
<<<<<<< HEAD
        <input type="number" id="prix" name="howmuch" >
=======
        <input type="number" id="prix" name="howmuch" value="${article.prix_initial}" >
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
    </div>


    <div>
        <label for="date_debut">Début de l'enchére :</label>
        <input type="date" id="date_debut" name="date_debut">

    </div>

    <div>
        <label for="date_fin">fin de l'enchére :</label>
        <input type="date"name="date_fin" id="date_fin">
    </div>
<<<<<<< HEAD
=======


<!--     <fieldset> -->
<!--         <legend>Retrait</legend> -->
<!--         <div> -->
<!--             <label for="rue">rue</label> -->
<!--             <input type="text" id="rue" name="rue"> -->
<!--         </div> -->

<!--         <div> -->
<!--             <label for="cpostal">code postal</label> -->
<!--             <input type="text" id="cpostal" name="cpostal" placeholder="75000" pattern="[0-9]{5}"> -->
<!--         </div> -->

<!--         <div> -->
<!--             <label for="ville">ville</label> -->
<!--             <input type="text" id="ville" name="ville"> -->
<!--         </div> -->
<!--     </fieldset> -->


>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
    <div>
        <button type="submit">Enregistrer</button>
        <button type="reset"> annuler</button>
    </div>
    </form>
</main>