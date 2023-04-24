<!DOCTYPE html>
<html>

<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="utf-8" />

<title>JEE TWIC 2023</title>
<body>
	<h1>Bienvenue sur la page d'édition d'un ville de France</h1>

	<div class="sectionButtonNav">
		<form action="/ProjetVilles_JEE/accueil">
			<button type="submit" class="buttonNav">Aller sur la page
				Accueil</button>
		</form>

		<form action="/ProjetVilles_JEE/calculDistance">
			<button type="submit" class="buttonNav">Calculer la distance
				entre 2 villes</button>
		</form>

		<form action="/ProjetVilles_JEE/ensembleVilles">
			<button type="submit" class="buttonNav">Voir l'ensemble des
				villes</button>
		</form>
	</div>

	<div>
		<br></br>
		<form method="post" class="ajoutVille">
			<input type="hidden" name="idCommune" value="${current.codeCommune}" />
			<label for="codePostal">Code Postal :</label> <input type="text"
				id="codePostal" name="Code_Postal" value="${ ville.codePostal }">
			<label for="nomCommune">Nom Commune :</label> <input type="text"
				id="nomCommune" name="Nom_Commune" value="${ ville.nomCommune }">
			<label for="codeCommune">Code Commune :</label> <input type="text"
				id="codeCommune" name="Code_Commune" value="${ ville.codeCommune }">
			<label for="libelle">Libelle :</label> <input type="text"
				id="libelle" name="Libelle" value="${ ville.libelleAcheminement }">
			<label for="ligne">Ligne :</label> <input type="text" id="ligne"
				name="Ligne" value="${ ville.ligne }"> <label
				for="longitude">Longitude :</label> <input type="text"
				id="longitude" name="Longitude" value="${ ville.longitude }">
			<label for="latitude">Latitude :</label> <input type="text"
				id="latitude" name="Latitude" value="${ ville.latitude }">
			<c:out value="${valide}" />
			<input type="submit" name="function" value="Ajouter">
		</form>
		<br></br>
	</div>
</body>
</html>