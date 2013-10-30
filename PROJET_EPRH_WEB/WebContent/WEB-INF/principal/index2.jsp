<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script language="javascript">
function controle(f){
	if(document.f.pass1.value!=document.f.pass2.value || document.f.pass1.value=="" || document.f.pass2.value=="")
		{alert("Les deux mots de passe doivent etre identiques et non vides");}
}
</script>
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
				

			</header>
			<div id="ventre" class="clearfix">
				<div id="ce_contenu" class="col1">
					
					<h2><center>Vous effectuez votre premiere connection: Veuillez changer votre mot de passe pour plus de securite</center></h2><br>
                            <center><form    method="post" action="conect" name="f">
                            <div >
                               <table border=0>
                                  <tr>
                                  <td align="left"><label >Nouveau mot de passe <b>*</b></label></td>
                                  <td align="right"><input type="password" value="" title="password1" name="pass1" required="true" /></td>
                                  </tr>
                                  <tr>
                                   <td align="left"><label >Resaisissez votre mot de passe <b>*</b></label></td>
                                  <td align="right"><input type="password" value="" title="password2" name="pass2" required="true"/></td>
                                  </tr>    
                                  
                                  <tr><td align="left"><pree> </pree> </td> <td align="right"><pree> </pree> </td> </tr>      
                                    <tr><td align="left"><pree> </pree></td> <td align="right"><pree> </pree></td> </tr>    <tr><td align="left"><pree> </pree></td> <td align="right"><pree> </pree></td> </tr>
                                                                                           
                                  <tr><td><input  type="submit"  value="modifier" name="connection" OnMouseOver="controle()"></td>                                                               
                                  <td><input  type="reset"  value="Reprendre" ><input  type="hidden"  value=${idens} name="idens"></td></tr></table>
                                  </div>
                            </form>
                            <font color="red">${erreur }</font></center>
                         
				</div>
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
