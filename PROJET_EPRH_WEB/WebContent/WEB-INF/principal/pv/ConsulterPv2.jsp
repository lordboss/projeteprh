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
						 <div ><h2><center>Semestre 1</center></h2>
                            <table border=1 width="90%">
                                <thead bgcolor="cyan"><td width="40%" align="center">Intitule du PV</td>
                                    <td width="24%" align="center">Nom du fichier:</td>
                                    <td width="18%" align="center">Date du Pv</td>
                                    <td width="18%" align="center">Heure</td></thead> 
                                  <c:forEach items="${semestre1 }" var="liste">
                                       <tr>
                                       <c:forTokens var="sous" items="${liste}" delims=";,+">
                                            <td align="center">${sous }</td>                          
                                        </c:forTokens>
                                       </tr>
                                  </c:forEach> 
                                  <%-- ${nb} --%>
                            </table>
                         </div>
                         
                         <div ><h2><center>Semestre 2</center></h2>
                            <table border=1 width="90%">
                                <thead bgcolor="cyan"><td width="40%" align="center">Intitule du PV</td>
                                    <td width="24%" align="center">Nom du fichier:</td>
                                    <td width="18%" align="center">Date du Pv</td>
                                    <td width="18%" align="center">Heure</td></thead> 
                                  <c:forEach items="${semestre2 }" var="liste">
                                       <tr>
                                       <c:forTokens var="sous" items="${liste}" delims=";,+">
                                            <td align="center">${sous }</td>                          
                                        </c:forTokens>
                                       </tr>
                                  </c:forEach> 
                                  <%-- ${nb} --%>
                            </table>
                         </div>		
                         
                         <div ><h2><center>Semestre 3</center></h2>
                            <table border=1 width="90%">
                                <thead bgcolor="cyan"><td width="40%" align="center">Intitule du PV</td>
                                    <td width="24%" align="center">Nom du fichier:</td>
                                    <td width="18%" align="center">Date du Pv</td>
                                    <td width="18%" align="center">Heure</td></thead> 
                                  <c:forEach items="${semestre3 }" var="liste">
                                       <tr>
                                       <c:forTokens var="sous" items="${liste}" delims=";,+">
                                            <td align="center">${sous }</td>                          
                                        </c:forTokens>
                                       </tr>
                                  </c:forEach> 
                                  <%-- ${nb} --%>
                            </table>
                         </div>	
                         <font color="green"><center> Bien vouloir cliquer sur le nom du fichier pour consulter le pv</center></font>
                         		
							</p>
						</section>
						<div id="taf_result"></div>
						<br class="clearfix" />
					</article>
				</div>
				<aside class="sideBar">

					<div class="bloc defaut">
						<div class="h3">menu PV</div>
						<div id="ajaxCV">
							<div class="tx-sharecms-pi1"><a href="pv?action=1"> Enregistrer un PV</a></div>
							<div class="tx-sharecms-pi1"><a href="pv?action=2"> Rechercher un PV par son Intitule</a></div>
							<div class="tx-sharecms-pi1"><a href="pv?action=3"> Rechercher un PV par année académique</a></div>
							<div class="tx-sharecms-pi1"><a href="pv?action=4"> Modifier un PV</a></div>
							<br> <br> <br> <br> <br> <br>
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
