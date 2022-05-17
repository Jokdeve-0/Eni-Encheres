<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="green" value="color:green;" />
<c:set var="red" value="color:red;" />
<main class="test">
	<h1>TESTS USER</h1>
	<div class="group-test">
		<h2>INSERT</h2>
		<p>
			Resultat du test : <span style="${testInsert ? green : red}">${testInsert}</span>
		</p>
	</div>

	<div class="group-test">
		<h2>UPDATE</h2>
		<p>
			Resultat du test : <span style="${testUpdate ? green  :  red}">${testUpdate}</span>
		</p>
	</div>

	<div class="group-test">
		<h2>SELECT BY ID</h2>
		<p>
			Resultat du test : <span style="${testSelectById ? green  :  red}">${testSelectById}</span>
		</p>
<%-- 		<p>${resultSelectId}</p> --%>
	</div>
	<div class="group-test">
		<h2>SELECT ALL</h2>
		<p>
			Resultat du test : <span style="${testSelectAll ? green  :  red}">${testSelectAll}</span>
		</p>
<%-- 		<p>${resultSelectAll}</p> --%>
	</div>
	<div class="group-test">
		<h2>DELETE</h2>
		<p>
			Resultat du test : <span style="${testDelete ? green  :  red}">${testDelete}</span>
		</p>
	</div>
</main>