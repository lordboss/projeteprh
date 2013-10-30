
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html >


<head>
<meta charset="utf-8" />
	<link href="inc/fs/template.css" rel="stylesheet" type="text/css" media="all" />
	<link href="inc/fs/joomla.css" rel="stylesheet" type="text/css" media="all" />
	<link href="inc/fs/colors.css" rel="stylesheet" type="text/css" media="all" />
	<link href="inc/fs/lvdropdown.css" rel="stylesheet" type="text/css" media="all" />
	<link href="inc/fs/typo.css" rel="stylesheet" type="text/css" media="all" />
	<link href="inc/fs/modules.css" rel="stylesheet" type="text/css" media="all" />
	
	<link href="inc/css/ie7.css" rel="stylesheet" type="text/css" media="all" />


	<link href="inc/css/ie5x6x.css" rel="stylesheet" type="text/css" media="all" />

	<script type="text/javascript" src="inc/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="inc/js/lv-dropdown.js"></script>
	<script type="text/javascript" src="inc/js/jq.easy-tooltip.min.js"></script>
	<script type="text/javascript" src="inc/js/jq.easy-caption.min.js"></script>
	<script type="text/javascript" src="inc/js/jq.corner.packed.js"></script>
	<script type="text/javascript" src="inc/js/reflection.js"></script>
	<script type="text/javascript" src="inc/js/effects.js"></script>
	<style type="text/css">
	#wrapper {
		margin-top:10px;
	}
	#foot_container {
		margin-bottom:0px;
	}
	#topmenu ul.menu, #topmenu ul.menu li a, #topmenu ul.menu li span.separator {
		background-image: url('image/images/dropdown-smooth.png');
	}
	#topmenu ul.menu li.parent a, #topmenu ul.menu li.parent span.separator {
		background-image: url('image/images/menu-parent-smooth.png');
	}
	#topmenu ul.menu li.parent a:hover, #topmenu ul.menu li.parent span.separator:hover, 
	#topmenu ul.menu li#current.parent a, #topmenu ul.menu li#current.parent span.separator {
		background-image: url('image/images/menu-parent-hover-smooth.png');
	}
			
</style>
	<style type="text/css">
	body {
		font-size:14px;
		font-family:Arial, Helvetica, Sans-Serif;
		line-height:1.3em;
	}

</style>
	<style type="text/css">
	body {
		background-color: #1CFD14;
		color: #555555;
	}
	span#copy a {
		color: #555555;
	}
	a, a:link, a:visited, a:active, a:focus {
		color: #A52A2A;
	}

	#wrapper, #foot_container {
		background-color: #EFEFEF;
		border: 1px solid #FFFFFF;
	}
	#header {
		background-color: #383838;
		border-top:1px solid #000000;
		border-left:1px solid #000000;	
		border-right:1px solid #000000;	
	}
	.heckl, .heckr {  
		border-top: 10px solid #222222;
	}

	#footer {
		background-color: #222222;
		border:1px solid #444444;
		border-bottom:2px solid #444444;
	}
	.feckl, .feckr {  
		border-bottom: 10px solid #222222;
	}
	#footer a#gotop {
		color: #FFFFFF;
	}
	#footermodule1234, #footermodule1234 a, #footermodule1234 ul.menu, #footermodule5, #footermodule5 a {
		color: #5B6C71;
	}
	#subfoot {
		background-color: #313131;
		border-top:1px solid #FFFFFF;
		border-bottom:1px solid #222222;
	}
	.copytext {
		color: #5B6C71;
	}

	#maincontent {
		background-color: #F9F9F9;
		border: 3px double #E9E9E9;
	}
	
	.item-page, .item, .leading-0, .leading-1, .leading-2, .leading-3, .leading-4, .leading-5 {
		background-color:#FFFFFF;
		border: 1px solid #E9E9E9;
	}

	.contentheading, .contentheading a {
		color: #484848 !important;
	}
	div.item-separator {
		border-bottom:3px double #EEEEEE !important;
	}

	#wrapper #leftcol h3.moduleh3, #wrapper #rightcol h3.moduleh3	 {
		background-color: #383838;
		border-top:	1px solid #555555;
		color: #DDDDDD;
	}
	#wrapper #leftcol h3.moduleh3	 {
		border-left: 1px solid #999999;
		border-right: 1px solid #555555;
	}
	#wrapper #rightcol h3.moduleh3	 {
		border-right: 1px solid #999999;
		border-left: 1px solid #555555;
	}
	.h3eckl, .h3eckr {  
		border-top: 10px solid #222222;
	}

	#leftcol .module div.lvround-inner, #leftcol .module_menu div.lvround-inner, #leftcol .module_text div.lvround-inner, 
	#rightcol .module div.lvround-inner, #rightcol .module_menu div.lvround-inner, #rightcol .module_text div.lvround-inner {
		background-color: #F9F9F9;
		border: 1px solid #DDDDDD;
		color: #505050;
	}
	#subhead {
		background-color: #F9F9F9;
		border: 1px solid #FFFFFF;
	}
	.breadcrumbs, .breadcrumbs span {
		color: #555555;
	}
	#leftcol .module div div, #leftcol .module_menu div div, #leftcol .module_text div div, 
	#rightcol .module div div, #rightcol .module_menu div div, #rightcol .module_text div div {
		border: 1px solid #FFFFFF;
	}
	div.module {
		color:#505050;
		background-color:#FFFFFF;
		border:1px solid #DDDDDD;
	}
	div.module h3.moduleh3 {
		background-color:#EFEFEF;
		border:1px solid #DDDDDD;
	}
	.input, .inputbox {
		color: #555555;
		background-color: #EFEFEF;
		border-top: 1px solid #CCCCCC;
		border-left: 1px solid #CCCCCC;
		border-right: 1px solid #FFFFFF;
		border-bottom: 1px solid #FFFFFF;
	}
	.input:hover, .inputbox:hover {
		color: #555555;
		background-color: #F9F9F9;
	}

	input.button, button.button, button.validate, .pagenav, ul.pagenav li a {
		color: #555;
		background-color: #efefef;
		color: #555555;
		background-color: #EFEFEF;
		border-top: 1px solid #CCCCCC;
		border-left: 1px solid #CCCCCC;
		border-right: 1px solid #FFFFFF;
		border-bottom: 1px solid #FFFFFF;
	}
	input.button:hover, button.button:hover, button.validate:hover, .pagenav, ul.pagenav li a:hover {
		color: #000000;
		background-color: #FFFFFF;
	}

	/**** Mainmenu with suffix: _menu ****/
	
	.module_menu ul.menu li a, .module_menu ul.menu li span.separator {
		color: #333333;
		border-bottom:1px dotted #CCCCCC;
	}
	.module_menu ul.menu li a:hover, .module_menu ul.menu li a:active, .module_menu ul.menu li a:focus {
		color: #000000!important;
	}
	.module_menu ul.menu li.current a {
		color: #000000;
	}
	.module_menu ul.menu li.current ul li a {
		color: #000000;
	}
	.easy-tooltip-default {
		border: 1px solid #A6A7AB; 
		background-color: #F2F3F5; 
		color: #800000;
	}
</style>
<style type="text/css">
	#leftcol {width: 20%;}
	#rightcol {width: 20%;}
	#content_outmiddle {width: 58%;}
	#content_outright {width: 79%;}
	#content_outleft {width: 79%;}
</style>
<base  />
  
  <title>ACCEUIL</title>
   
</head>
<body>
Administrateur: ${nom }
<br><br><br>
	<div id="wrapper" style="max-width:1180px;">
			<div id="header_container">
				<div id="header">
					<div id="logo">
						 <center><a class="imglogo" href="#"><font color="blue">${struc }<br></font></a></center>
						<div style="color:blue;" class="slogan"></div>
					</div>		
					<span class="heckl">&nbsp;</span>
					<span class="heckr">&nbsp;</span>
				</div>
				
   				
				 <div id="content_outleft">
				     <div id="maincontent">						
					     <div class="foxcontainer" style="width:100% !important;"><h2><center>Faite le choix sur l'action à exécuter</center></h2>
                            <table border=0>                
                                                     
                                 <tr><td> <div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=1&nom=${nom }&idadmin=${idadmin}">Enregistrer un personnel</a> </label> </div></td><td><font color="red">${resultat1 }</font></td></tr>
                                 <tr><td> <div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=2&nom=${nom }&idadmin=${idadmin}">Mettre à jour un personnel</a> </label> </div></td><td><font color="red">${resultat2 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=3&nom=${nom }&idadmin=${idadmin}">Creer un cours</a> </label> </div></td><td><font color="red">${resultat3 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=4&nom=${nom }&idadmin=${idadmin}">Mettre à jour un cours</a> </label> </div></td><td><font color="red">${resultat4 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=5&nom=${nom }&idadmin=${idadmin}">Décharger un matériel</a> </label> </div></td><td><font color="red">${resultat5 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=6&nom=${nom }&idadmin=${idadmin}">Afficher les commandes déjà passée</a> </label> </div></td><td><font color="red">${resultat6 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=7&nom=${nom }&idadmin=${idadmin}">Passer une Commande</a> </label> </div></td><td><font color="red">${resultat7 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=8&nom=${nom }&idadmin=${idadmin}">Lister le matériel en stock chez le comptable matière</a> </label> </div></td><td><font color="red">${resultat8 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=9&nom=${nom }&idadmin=${idadmin}">Enregistrer le procès verbal d'une réunion</a> </label> </div></td><td><font color="red">${resultat9 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=10&nom=${nom }&idadmin=${idadmin}">Modifier la grille des UV</a> </label> </div></td><td><font color="red">${resultat10 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=11&nom=${nom }&idadmin=${idadmin}">Emettre un communiqué</a> </label> </div></td><td><font color="red">${resultat11 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=12&nom=${nom }&idadmin=${idadmin}">Consulter l'emploi du temps d'un enseignant</a> </label> </div></td><td><font color="red">${resultat12 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=13&nom=${nom }&idadmin=${idadmin}">Acceder à la grille des UV</a> </label> </div></td><td><font color="red">${resultat13 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=14&nom=${nom }&idadmin=${idadmin}">Consulter les communiqués</a> </label> </div></td><td><font color="red">${resultat14 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=15&nom=${nom }&idadmin=${idadmin}">Créer une horaire</a> </label> </div></td><td><font color="red">${resultat15 }</font></td></tr>
                                  <tr><td><div style="clear:both;"><label style="float:left;width:220px !important;"><a href="eprh?action=16&nom=${nom }&idadmin=${idadmin}">Dossier du personnel</a> </label> </div></td><td><font color="red">${resultat16 }</font></td></tr>
                                                                                               
                               
                             </table></center>
                         </div>
                         
				</div>				
					
			</div>
			<div class="clr"></div>
		</div>	
		<div class="clr"></div>
	</div>
			<div id="foot_container" style="max-width:1180px;">
			<div id="subfoot">
			<div class="copytext">&copy; ${struc }</div>
			</div>
		<div class="clr"></div>
			<div id="footer">
				<div class="footer-inner">
					<span class="feckl">&nbsp;</span>
					<span class="feckr">&nbsp;</span>
					<div id="scroll_up"> <a href="#" class="lv-tooltip" id="gotop" title="Scroll to top">&uarr;&uarr;&uarr;</a></div>
		</div>
				<div class="footer-bottom">
				  		<span id="date">${date}</span>
								  		<span id="copy"><a href="#" title="Joomla Templates" target="_blank">Template designed by miguel foko</a></span>
				</div>
			</div>		
		<div class="clr"></div>
	</div>		
</body>
</html>