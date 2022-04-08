<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/userConnect.jsp" %>
<main class="home">
    <%@include file="../includes/erreurs.jsp" %>
      
	<c:url var="VenteArticle" value="/venteArticle" />
	<form method="POST" action="VendreUnArticle">

    <div>
        <label for="Article">Name (4 to 8 characters):</label>
        <input type="text" id="name" name="name" required >
    </div>
    <div>
        <label for="story">Description : </label>
        <textarea id="story" name="story" ></textarea>
    </div>
    <div>
        <label for="categorie">Catégorie</label>
        <select name="categorie" id="categorie">
            <option value="informatique">informatique</option>
            <option value="vêtements">vêtements</option>
            <option value="test">test</option>
            
        </select>
    </div>
    <div class="photo" style="width:50px;height:50px;background:#000;"></div>

    <div>
        <label for="prix">Prix initial : </label>
        <input type="number" id="prix" name="howmuch" value="${article.prix_initial}" >
    </div>


    <div>
        <label for="date_debut">Début de l'enchére :</label>
        <input type="date" id="date_debut" name="date_debut">

    </div>

    <div>
        <label for="date_fin">fin de l'enchére :</label>
        <input type="date"name="date_fin" id="date_fin">
    </div>


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


    <div>
        <button type="submit">Enregistrer</button>
        <button type="reset"> annuler</button>
    </div>
    </form>
</main>