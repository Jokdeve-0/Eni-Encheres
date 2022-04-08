<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer>
	<h2>PROJET CDA 2022@ENI-ENCHERE</h2>
	<p>copyright © <span>RedDevs</span></p>
</footer>
<c:if test="${utilisateur != null}">
<script>
				<%@include file="../../../assets/js/filtresHome.js" %>
</script>
</c:if>