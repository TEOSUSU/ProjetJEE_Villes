<!DOCTYPE html>
<html>

<link rel="stylesheet" type="text/css" href="style.css">

<meta charset="utf-8" />


<title>JEE TWIC 2023</title>
<body>
	<h1>Bienvenue sur la page d'affichage de l'ensemble des villes de
		France</h1>

	<div class="sectionButtonNav">
		<form action="/ProjetVilles_JEE/accueil">
			<button type="submit" class="buttonNav">Aller sur la page
				Accueil</button>
		</form>

		<form action="/ProjetVilles_JEE/calculDistance">
			<button type="submit" class="buttonNav">Calculer la distance
				entre 2 villes</button>
		</form>

		<form action="/ProjetVilles_JEE/ajoutVille">
			<button type="submit" class="buttonNav">Ajouter une ville</button>
		</form>
	</div>

	<c:set var="page" value="${param.page eq null ? 1 : param.page}" />
	<c:set var="size" value="50" />
	<c:set var="startIndex" value="${(page - 1) * size}" />
	<c:set var="endIndex" value="${startIndex + size - 1}" />
	<c:set var="indexMeteo" value="0" />

	<div>
		<c:choose>
			<c:when test="${ empty ville }">
				<c:forEach items="${ villes }" var="current" begin="${startIndex}"
					end="${endIndex}">

					<br></br>
					<form method="post" class="showVille">
						<ul>
							<li><input type="hidden" name="codePostal"
								value="${current.codePostal}" /><input type="hidden"
								name="idCommune" value="${current.codeCommune}" /> <c:out
									value="${ current.codePostal }" /> <c:out
									value="${ current.nomCommune }" /> <input type="submit"
								name="function" value="Editer"> <input type="submit"
								name="function" value="Supprimer"></li>
							<li>Température : ${meteos[indexMeteo].temperature}°C , et
								le temps est ${meteos[indexMeteo].temps} !</li>
							<c:if
								test="${meteos[indexMeteo].temps == 'couvert' || meteos[indexMeteo].temps == 'nuageux' || meteos[indexMeteo].temps == 'partiellement nuageux'}">
								<li><img src="https://openweathermap.org/img/wn/04d@2x.png"
									width="50px" height="50px"></li>
							</c:if>
							<c:if
								test="${meteos[indexMeteo].temps == 'peu nuageux' || meteos[indexMeteo].temps == 'ciel dégagé'}">
								<li><img src="https://openweathermap.org/img/wn/01d@2x.png"
									width="50px" height="50px"></li>
							</c:if>
							<c:if
								test="${meteos[indexMeteo].temps == 'légère pluie' || meteos[indexMeteo].temps == 'pluie modérée'}">
								<li><img src="https://openweathermap.org/img/wn/10d@2x.png"
									width="50px" height="50px"></li>
							</c:if>

							<c:set var="indexMeteo" value="${indexMeteo + 1}" />
						</ul>
					</form>
				</c:forEach>

				<c:if test="${page > 1}">
					<form method="get">
						<input type="hidden" name="page" value="${page - 1}" /> <input
							type="submit" value="Page précédente" />
					</form>
				</c:if>
				<c:out value="Page : ${page}" />
				<c:if test="${endIndex < fn:length(villes) - 1}">
					<form method="get">
						<input type="hidden" name="page" value="${page + 1}" /> <input
							type="submit" value="Page suivante" />
					</form>
				</c:if>

			</c:when>

			<c:otherwise>
				<br></br>
				<form method="post" class="ajoutVille">
					<input type="hidden" name="idCommune"
						value="${current.codeCommune}" /> <label for="codePostal">Code
						Postal :</label> <input type="text" id="codePostal" name="Code_Postal"
						value="${ ville.codePostal }"> <label for="nomCommune">Nom
						Commune :</label> <input type="text" id="nomCommune" name="Nom_Commune"
						value="${ ville.nomCommune }"> <label for="codeCommune">Code
						Commune :</label> <input type="text" id="codeCommune" name="Code_Commune"
						value="${ ville.codeCommune }"> <label for="libelle">Libelle
						:</label> <input type="text" id="libelle" name="Libelle"
						value="${ ville.libelleAcheminement }"> <label for="ligne">Ligne
						:</label> <input type="text" id="ligne" name="Ligne"
						value="${ ville.ligne }"> <label for="longitude">Longitude
						:</label> <input type="text" id="longitude" name="Longitude"
						value="${ ville.longitude }"> <label for="latitude">Latitude
						:</label> <input type="text" id="latitude" name="Latitude"
						value="${ ville.latitude }"> <input type="submit"
						name="function" value="Valider">
				</form>
			</c:otherwise>

		</c:choose>
	</div>

</body>
</html>