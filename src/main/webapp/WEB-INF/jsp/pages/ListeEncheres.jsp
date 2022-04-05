<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="home">
<%@include file="../includes/erreurs.jsp" %>
<div>
  <input type="radio" id="huey" name="drone" value="huey"
         checked>
  <label for="huey">Achats</label>
</div>

<div>
  <input type="checkbox" id="scales" name="scales"
         checked>
  <label for="scales">enchéres ouvertes</label>
</div>

<div>
  <input type="checkbox" id="horns" name="horns">
  <label for="horns">mes enchéres encours</label>
</div>
<div>
  <input type="checkbox" id="horns" name="horns">
  <label for="horns">mes enchéres remportées</label>
</div>

<div>
  <input type="radio" id="huey" name="drone" value="huey"
         checked>
  <label for="huey">Huey</label>
</div>
<div>
  <input type="checkbox" id="scales" name="scales"
         checked>
  <label for="scales">mes ventes en cours</label>
</div>

<div>
  <input type="checkbox" id="horns" name="horns">
  <label for="horns">ventes non débutées</label>
</div>
<div>
  <input type="checkbox" id="horns" name="horns">
  <label for="horns">ventes terminées</label>
</div>


