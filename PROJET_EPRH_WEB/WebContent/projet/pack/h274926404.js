function xtProfilStatsalaire(a){return xt_click(a,'C','2','CE_connect::statistiques::salaire_actuel','A');}
function xtProfilStatnivexperience(a){return xt_click(a,'C','2','CE_connect::statistiques::niveau_experience','A');}
function xtProfilStatrepartsexe(a){return xt_click(a,'C','2','CE_connect::statistiques::repart_sexe','A');}
function xtProfilStatnivetudes(a){return xt_click(a,'C','2','CE_connect::statistiques::niveau_etudes','A');}
function xtProfilStatformation(a){return xt_click(a,'C','2','CE_connect::statistiques::types_formation','A');}
function xtProfilStatregions(a){return xt_click(a,'C','2','CE_connect::statistiques::repart_region','A');}
function xtProfilStateffectifsentreprise(a){return xt_click(a,'C','2','CE_connect::statistiques::effectif_entreprise','A');}
function xtProfilStateffectifsresp(a){return xt_click(a,'C','2','CE_connect::statistiques::pers_ss_responsablite','A');}
function xtProfilStattypecontrat(a){return xt_click(a,'C','2','CE_connect::statistiques::type_contrat','A');}
function xtProfilStattypecible(a){return xt_click(a,'C','2','CE_connect::statistiques::offre_par_type_cible','A');}
function xtProfilStatlangues(a){return xt_click(a,'C','2','CE_connect::statistiques::langues','A');}
function xtProfilConsultationsProfil(a){return xt_click(a,'C','2','CE_connect::consultations_profil','A');}
function xtTableauBordCreerCV(a){return xt_click(a,'C','2','CE_connect::tableau_de_bord::creer_cv','N');}
function xtTableauBordCompleterCV(a){return xt_click(a,'C','2','CE_connect::tableau_de_bord::completer_cv','N');}
function xtTableauBordActiver(a){return xt_click(a,'C','2','CE_connect::tableau_de_bord::activer','N');}
var _FRAMEWORK={jquery:{name:"jQuery"},prototype:{name:"prototype"}};if(typeof(jQuery)!="undefined"){jQuery(document).ready(function($){$('.xtProfilStatsalaire').click(function(){xtProfilStatsalaire(this);});$('.xtProfilStatnivexperience').click(function(){xtProfilStatnivexperience(this);});$('.xtProfilStatrepartsexe').click(function(){xtProfilStatrepartsexe(this);});$('.xtProfilStatnivetudes').click(function(){xtProfilStatnivetudes(this);});$('.xtProfilStatformation').click(function(){xtProfilStatformation(this);});$('.xtProfilStatregions').click(function(){xtProfilStatregions(this);});$('.xtProfilStateffectifsentreprise').click(function(){xtProfilStateffectifsentreprise(this);});$('.xtProfilStateffectifsresp').click(function(){xtProfilStateffectifsresp(this);});$('.xtProfilStattypecontrat').click(function(){xtProfilStattypecontrat(this);});$('.xtProfilStattypecible').click(function(){xtProfilStattypecible(this);});$('.xtProfilStatlangues').click(function(){xtProfilStatlangues(this);});$('.xtProfilConsultationsProfil').click(function(){xtProfilConsultationsProfil(this);});});}else if(typeof(Prototype)!="undefined"){$$('.xtTableauBordCreerCV').each(function(s)
{s.observe('click',function()
{lien=s;xtTableauBordCreerCV(this);});});$$('.xtTableauBordCompleterCV').each(function(s)
{s.observe('click',function()
{lien=s;xtTableauBordCompleterCV(this);});});$$('.xtTableauBordActiver').each(function(s)
{s.observe('click',function()
{lien=s;xtTableauBordActiver(this);});});}