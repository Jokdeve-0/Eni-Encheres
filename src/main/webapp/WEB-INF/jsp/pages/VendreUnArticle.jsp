<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="home">
    <%@include file="../includes/erreurs.jsp" %>
      
	<c:url var="VenteArticle" value="/venteArticle" />
	<form method="post" action="VendreUnArticle">

    <div>
        <label for="Article">Nom</label>
        <input type="text" id="name" name="name" required >
    </div>
    <div>
        <label for="story">Description</label>
        <textarea id="story" name="story" ></textarea>
    </div>
    <div>
        <label for="categorie">Catégorie</label>
        <select name="categorie" id="categorie">
            <c:forEach var="cat" items="${categorie}">
            	<option value="${cat.libelle}">${cat.libelle}</option>
            </c:forEach>
            
        </select>
    </div>
    <div class="photo" style="width:50px;height:50px;background:#000;"></div>

    <div>
        <label for="prix">Prix initial : </label>
        <input type="number" id="prix" name="howmuch" >
    </div>


    <div>
        <label for="date_debut">Début de l'enchére :</label>
        <input type="date" id="date_debut" name="date_debut">

    </div>

    <div>
        <label for="date_fin">fin de l'enchére :</label>
        <input type="date"name="date_fin" id="date_fin">
    </div>
    <div>
        <button type="submit">Enregistrer</button>
        <button type="reset"> annuler</button>
    </div>
    </form>
</main>