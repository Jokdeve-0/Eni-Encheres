<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${utilisateur != null }">
		<aside class="user-connected">
			<div class="card-connected">
		        <img src="https://www.nautec.com/wp-content/uploads/2018/04/placeholder-person.png" alt="">
<<<<<<< HEAD
		        <h2>Bienvenue ${utilisateur.getPseudo()}<br> ${utilisateur.credit} pts</h2>
		        
=======
		        <h2>Bienvenue ${utilisateur.getPseudo()}</h2>
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
		    </div>
		</aside>
</c:if>