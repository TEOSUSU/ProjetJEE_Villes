<!DOCTYPE html>
<html>

<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="utf-8" />

<title>JEE TWIC 2023</title>
<body>
	<h1>Bienvenue sur le Projet JEE TWIC 2023</h1>

	<p>Consignes du projet :</p>
	<ul>
		<li>Premi�re page : Afficher deux liste d�roulantes avec la liste
			des villes et calculer la distance entre 2 villes s�lectionn�es</li>
		<li>Deuxi�me page : Afficher l'ensemble des villes, pouvoir supprimer une ville et �diter une ville</li>
		<li>Troisi�me page : Ajout d'une ville � la BDD</li>
	</ul>


	<div class="sectionButtonNav">
		<form action="/ProjetVilles_JEE/calculDistance">
			<button type="submit" class="buttonNav">Calculer la distance
				entre 2 villes</button>
		</form>
		
		<form action="/ProjetVilles_JEE/ajoutVille">
			<button type="submit" class="buttonNav">Ajouter une ville</button>
		</form>
		
		<form action="/ProjetVilles_JEE/ensembleVilles">
			<button type="submit" class="buttonNav">Voir l'ensemble des villes</button>
		</form>
	</div>
</body>
</html>