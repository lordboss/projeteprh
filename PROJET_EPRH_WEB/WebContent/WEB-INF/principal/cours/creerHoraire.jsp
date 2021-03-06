<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base>
<link rel="stylesheet" type="text/css"
	href="projet/typo3temp/stylesheet_bbd0ff5061.css" media="all">
<link rel="image_src" href="projet/uploads/pics/idee.jpg" />
<title>ACCEUIL</title>
<link rel="stylesheet" type="text/css"
	href="projet/pack/h-1769123047.css" media="screen" charset="utf-8" />
<link rel="stylesheet" type="text/css"
	href="projet/pack/h1660365076.css" media="print" charset="utf-8" />
<link rel="shortcut icon" href="http://www.cadremploi.fr/favicon.ico">
<link rel="apple-touch-icon" type="image/png" href="projet/iphone.png" />
<link rel="stylesheet" type="text/css"
	href="projet/fileadmin/templates/css/edito.css" media="screen" />
<body>
	<div id="toolbarContainer"></div>
	<div id="wrapTop">

		<div id="main">
			<header>
				<section class="header" role="banner">
					<div id="tagPubBanniere"></div>
					<div id="logo">
						<a href="#"
							title="Département d'économie Publiques  et des ressources Humaines"><img
							src="projet/logo.jpg"
							 width="200" height="90" /></a>
					</div>
				</section>
				<nav role="navigation" id="navBar">
					<ul class="menuCe" id="navmenu">
						<li><a title="Enseignants" href="conect" class="offres"><span>Enseignants</span></a></li>

						<li><a title="Cours" href="cours?action=0" class="recrute"><span>Cours</span></a></li>

						<li><a href="materiel?action=0" title="Matériel" class="actus"><span>Mat&eacute;riel</span></a></li>

						<li><a href="pv?action=0" class="conseil"title="Proc&egrave;s verbaux "><span>PV</span></a></li>

						<li><a title="Communiquer" href="communiquer?action=0" class="formation"><span>Communiquer</span></a></li>

						
						<li><a title="Déconnection" href="deconnection" class="espro"><span>Déconnection</span></a></li>
					</ul>
				</nav>

			</header>
			<div id="ventre" class="clearfix">
				<div id="ce_contenu" class="col1">
					<article class="article">
						<div class="tx-sharecms-pi1"><font color="blue">Administrateur:</font>${nom }</div>
						<br class="clearfix" />

						<p class="news-single-imgcaption" style="width: 105px;"></p>
						<section class="intro">	<p>
						 <h2><center>Entrez l'horaire à enregistrer</center></h2>
                            <center><div> <table width="65%" border=0><form    method="post" action="cours" name="form">
                                                                                           
                                  <tr> <td align="left"><label >Jour: </label></td><td align="left">
                                  <select name="jour">
                                       <option value="Lundi">Lundi
                                       <option value="Mardi">Mardi
                                       <option value="Mercredi">Mercredi
                                       <option value="Jeudi">Jeudi
                                       <option value="Vendredi">Vendredi
                                       <option value="Samedi">Samedi
                                  </select>
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                  <tr> <td align="left"><label >Heure de début: </label></td><td align="left">
                                  <select name="debut" id="debut">
                                      <c:forEach var="i" begin="8" end="21" step="1">
                                          <option value="<c:out value="${i}"/>"> <c:out value="${i} heures"/>
                                      </c:forEach>         
                                  </select>
                                   </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>   
                                  <tr> <td align="left"><label>Heure de fin: </label></td><td align="left">
                                  <select name="fin" id="fin">
                                      <c:forEach var="i" begin="9" end="22" step="1">
                                          <option value="<c:out value="${i}"/>"> <c:out value="${i} heures"/>
                                      </c:forEach> 
                                  </select>
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                  <tr> <td align="right">                                                                                      
                                  <input  type="submit"  value="OK" name="connection" onclick="valDeb(form);">
                                   </td><td align="left">                                                               
                                  <input  type="reset"  value="Reprendre"></td></tr><tr><td>.</td><td>.</td></tr>
                             </form><</table></div><br>
                            <font color="red"> ${resultat3}</font>
                             </center>
                         
							</p>
						</section>
						<div id="taf_result"></div>
						<br class="clearfix" />
					</article>
				</div>
				<aside class="sideBar">

					<div class="bloc defaut">
						<div class="h3">menu Cours</div>
						<div id="ajaxCV">
							<div class="tx-sharecms-pi1"><a href="cours?action=1"> Créer un cours</a></div>
							<div class="tx-sharecms-pi1"><a href="cours?action=2"> Mettre à jour un cours</a></div>
							<div class="tx-sharecms-pi1"><a href="cours?action=3"> Créer une Horaire</a></div>
							<div class="tx-sharecms-pi1"><a href="cours?action=4"> Créer une nouvelle Salle de Cours</a></div>
							<div class="tx-sharecms-pi1"><a href="cours?action=5"> Consulter la grille des UV</a></div><br> <br> <br> <br> <br> <br>
							<br> <br> <br> <br> 
						</div>
					</div>








				</aside>
				<br class="clearfix" />

			</div>
			<div id="wrapBtm">
				<div id="barreRSFoot">
					<div class="innerBarreRSFoot">
						<ul>
							<li><div role="heading" aria-role="heading" aria-level="3">
									<span class="icon"></span><br /> <strong>${date }</strong>
								</div></li>
						</ul>
					</div>
				</div>
				<div id="footer">
					<p class="out">
						<ahref="#">retour haut de page</a>
					</p>
					<br class="clearfix" />
				</div>
			</div>
		</div>
	</div>

	<div class="OUTBRAIN"></div>
</body>

</html>
