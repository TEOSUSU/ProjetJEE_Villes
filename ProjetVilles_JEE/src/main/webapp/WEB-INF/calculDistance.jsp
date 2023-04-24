<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="utf-8" />

<title>JEE TWIC 2023</title>
<body>

	<h1>Bienvenue sur la page de calcul de distance entre 2 villes de
		France</h1>



	<div class="sectionButtonNav">
		<form action="/ProjetVilles_JEE/accueil">
			<button type="submit" class="buttonNav">Aller sur la page
				Accueil</button>
		</form>

		<form action="/ProjetVilles_JEE/ajoutVille">
			<button type="submit" class="buttonNav">Ajouter une ville</button>
		</form>

		<form action="/ProjetVilles_JEE/ensembleVilles">
			<button type="submit" class="buttonNav">Voir l'ensemble des
				villes</button>
		</form>
	</div>

	<br></br>
	<form method="post" action="calculDistance" class="calcDist">
		<br> <select name="villeDepart" id="villeDepart">
			<option value="">Ville de départ</option>
			<c:forEach items="${ villes }" var="current">
				<option value="${current.nomCommune}">${current.nomCommune}</option>
			</c:forEach>
		</select> <br> <select name="villeArrivee" id="villeArrivee">
			<option value="">Ville d'arrivée</option>
			<c:forEach items="${ villes }" var="current">
				<option value="${current.nomCommune}">${current.nomCommune}</option>
			</c:forEach>
		</select> <br> <input value="Calculer la distance" type="submit" />
	</form>

	<c:if test="${ !empty villeDepart }">
		<br></br>
		<form class="calcDist">
			<c:out
				value="La distance entre ${ villeDepart } et ${ villeArrivee } est de ${ distance } km (vol d'oiseau)."></c:out>
		</form>
	</c:if>

</body>
</html>