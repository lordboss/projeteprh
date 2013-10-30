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
						 <h2><center>Modifiez les informations concernant le communiqué</center></h2>
                            <center><div><table width="65%" border=0> <form    method="post" action="communiquer">
                         
                                  <tr> <td align="left"><label ><font color="black">Identifiant du communiquer: </font></label></td><td align="left">
                                  <select name="id">
                                  <c:forEach items="${debut}" var="ens">
												<option value="<c:out value="${ens}"/>">
													<c:out value="${ens}" />
											</c:forEach>
                                  </select>
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>  
                                  <tr> <td align="left"><label ><font color="black">Etat du communiqué</font></label></td><td align="left">
                                  <select name="etatcom">                                
                                          <option value= "en cour">en cour 
                                          <option value="plus en cour">plu en cour                                                                                
                                      
                                  </select>
                                 
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>                                 
                                  <tr> <td align="left"><label ><font color="black">Objet du communique: </font></label></td><td align="left">
                                  <input type="text" value="" title="objet"  name="objetc"  required="true"/>
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                  <tr> <td align="left"><label ><font color="black">Contenu du Communiqué </font></label></td><td align="left">
                                  <input type="text" value="inserer l'information" title="contenu"  name="contenuc"  required="true" heigh="20" witdh="20"/>
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                  <tr> <td align="left"><label ><font color="black">Destinataire</font></label></td><td align="left">
                                  <select name="destinataire">                                
                                          <option value=Enseignant/>Enseignant  
                                          <option value=Etudiant/>Etudiant                                                                                 
                                      
                                  </select>
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>                                 
                                
                                  
                                  </td></tr><tr><td><font color="white">.</font></td><td><font color="white">.</font></td></tr>
                                  <tr> <td align="right">
                                  <input  type="submit"  value="modifier" name="connection">
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
						<div class="h3">menu Communiqué</div>
						<div id="ajaxCV">
							<div class="tx-sharecms-pi1"><a href="communiquer?action=0"> Créer un Communiqué</a></div>
							<div class="tx-sharecms-pi1"><a href="communiquer?action=1"> Modifier un Communiqué</a></div>
							<div class="tx-sharecms-pi1"><a href="communiquer?action=2"> Supprimer un Communiqué</a></div>
							<div class="tx-sharecms-pi1"><a href="communiquer?action=3">Consulter les Communiqués</a></div><br> <br> <br> <br> <br> <br>
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
>