/**
 * Gestion de l'affichage et du masquage du texte dans le champ mots-cles
 * Dépend du script jquery.trim.js
 */
(function($){
	$.fn.searchMotsCles = function(ifHtml5){
		return $(this).each(function(){
			var initVal = (ifHtml5) ? $(this).attr('placeholder'): $(this).val();
			$(this).val(initVal);
			$(this).bind('focus',function(){
				if ($(this).val() === initVal ) {
					$(this).val('');
				} else {
					$(this).val($(this).val());
				}
			}).bind('blur',function(){ if ($(this).val() == '' ) $(this).val(initVal);
			}).bind('change',function(){ $(this).val($(this).val());
			}).bind('keypress',function(event){
				if(event.which == 13) $(this).parents('form[name=recherche]').submit(); 
			});
		});
	};
})(jQuery);
