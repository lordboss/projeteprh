/* masque les tags adtech vide */
function hideIfEmpty(divName, nbMea, idShowAll){
	var show = false;
	var i = 0;
	var nbVisible = 0;
	$('#'+divName+' img').each(function(index, value) { 
		if( $.browser.msie && ($(this).attr('alt') == 'AdTech Ad') ){
			$(this).parent().hide();
//			$('#'+divName+':nth-child('+i+')').remove();
		}else if( !$.browser.msie && $(this).attr('width') < 2 ){
			$(this).parent().parent().remove();
//		$('#'+divName+':nth-child('+i+')').remove();			
		}else{
			nbVisible++;
			show = true;
		}
		if(nbVisible > nbMea){
		   if( $.browser.msie ){
			   $(this).parent().hide();
		   }else{
			    $(this).parent().parent().remove();
		   }
		}
		
		i++;
	});
	
	
	if((typeof nbMea !== 'undefined') && nbMea > 0 && (nbVisible <= nbMea) && (typeof idShowAll !== 'undefined') ){
		// Hide "voir tous" :		
		$(idShowAll).hide();
	}	
	if(show){
		$('#'+divName).show();
		$('#pubzoom').show();
	}
	
}

function adtechAlea(idArray,kvniveau,group){	
	idArray = shuffle(idArray);
	if(typeof kvniveau === 'undefined' ){
		kvniveau = '';
	}
	if(typeof group === 'undefined' ){
		group = '';
	}
	
	for (var i in idArray){
		document.write('<figure class="gauche"><scr'+'ipt language="javascript1.1" src="http://adserver.adtech.de/addyn|3.0|296|'+idArray[i]+'|0|16|ADTECH;loc=100;target=_blank;grp='+group+';misc='+new Date().getTime()+kvniveau+'"></scri'+'pt></figure>');
	}
//	$('#'+divName).show();
}

function shuffle(a)
{
   var j = 0;
   var valI = '';
   var valJ = valI;
   var l = a.length - 1;
   while(l > -1)
   {
		j = Math.floor(Math.random() * l);
		valI = a[l];
		valJ = a[j];
		a[l] = valJ;
		a[j] = valI;
		l = l - 1;
	}
	return a;
 }