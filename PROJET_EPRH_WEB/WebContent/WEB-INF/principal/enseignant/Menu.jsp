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
						
						    <h2><center>Entrez les informations de l'enseignant à enregistrer</center></h2>
                            <center><div><table width="65%" border=0><form    method="post" action="enseignant">
                              <tr> <td align="left"><label ><font color="black">Nom: </font></label></td><td align="left">
                                  <input type="text" value="${nome }" title="Nom et Prénom" name="nome"  required="true"/>
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                <tr> <td align="left"><label ><font color="black">Login: </font></label></td><td align="left">
                                
                                  <input type="text" value="${login }" title="Login" name="login"  required="true"/><br><br>
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                  <tr> <td align="left"><label><font color="black">Grade:</font> </label></td><td align="left">
                                  <select name="grade">
                                      <option value="ATTACHE DE LA RECHERCHE">Attaché de la recherche
                                      <option value="ASSISTANT">Assistant
                                      <option value="CHARGE DE COURS">Chargé de cours
                                      <option value="MAITRE DE CONFERENCE">Maître de conférence
                                      <option value="PROFESSEUR">Professeur
                                  </select> </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                  <tr> <td align="left"><label ><font color="black">Diplôme le plus élevé: </font></label></td><td align="left">
                                  <select name="diplome">
                                      <option value="MAITRISE">Maîtrise
                                      <option value="MASTER 1">Master 1
                                      <option value="DEA">DEA
                                      <option value="MASTER II">Master II
                                      <option value="DOCTORAT">Doctorat
                                      <option value="DOCTORAT D'ETAT">Doctorat D'état
                                  </select></td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                <tr> <td align="left"><label><font color="black">Date de recrutement: </font></label></td><td align="left">
                                 <select name="jour">
                                  <option value=0>Jour
                                       <c:forEach var="i" begin="1" end="31" step="1">
                                           <option value="<c:out value="${i}"/>"> <c:out value="${i}"/>
                                       </c:forEach>
                                  </select>
                                  <select name="mois">
                                       <option value=0>Mois
                                       <c:forEach var="i" begin="1" end="12" step="1">
                                           <option value="<c:out value="${i}"/>"> <c:out value="${i}"/>
                                       </c:forEach>
                                  </select>
                                  
                                  <select name="annee">
                                        <option value=0>Annéé
                                       <c:forEach var="i" begin="1980" end="2100" step="1">
                                           <option value="<c:out value="${i}"/>"> <c:out value="${i}"/>
                                       </c:forEach>
                                  </select></td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                   <tr> <td align="left"><label ><font color="black">Password: </font></label></td><td align="left">
                                  <input type="password" value="" title="Password"  name="pass1" required="true"/>
                                  </select></td></tr><tr><td><font color="white"></font></td><td><font color="white">.</font></td></tr>
                                  <tr> <td align="left"><label ><font color="black">Password: </font></label></td><td align="left">
                                  <input type="password" value="" title="Password" name="pass" required="true"/>
                                 </td></tr><tr><td colspan=2><font color="red">${pass }</font></td></tr>                      
                                  <tr> <td align="right"><input  type="submit"  value="Enregistrer" name="connection">
                                  </td><td align="left">                                                              
                                  <input  type="reset"  value="Reprendre"></td></tr><tr><td>.</td><td>.</td></tr>
                             </form></table></div><br>
                             ${resultat1 }
                             </center>
						


							</p>
						</section>
						<div id="taf_result"></div>
						<br class="clearfix" />
					</article>
				</div>
				<aside class="sideBar">

					<div class="bloc defaut">
						<div class="h3">menu enseignants</div>
						<div id="ajaxCV">
							<div class="tx-sharecms-pi1"><a href="enseignant?action=1"> Enregistrer un enseignant</a></div>
							<div class="tx-sharecms-pi1"><a href="enseignant?action=2"> Modifier un enseignant</a></div>
							<div class="tx-sharecms-pi1"><a href="enseignant?action=3"> Consulter l'emploi du temps d'un enseignant</a></div>
							<div class="tx-sharecms-pi1"><a href="enseignant?action=4"> Consulter la fiche des enseignants</a></div>
							<div class="tx-sharecms-pi1"><a href="enseignant?action=5"> Consulter la fiche d'un enseignant</a></div><br> <br> <br> <br> <br> <br>
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
