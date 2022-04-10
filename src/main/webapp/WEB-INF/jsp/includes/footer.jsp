<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer>
	<h2>PROJET CDA 2022@ENI-ENCHERE</h2>
	<p>copyright © <span>RedDevs</span></p>
</footer>
<<<<<<< HEAD
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script  src="${context}/assets/js/main.js" /></script>
=======
<c:if test="${utilisateur != null}">
<script>
				<%@include file="../../../assets/js/filtresHome.js" %>
</script>
</c:if>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
