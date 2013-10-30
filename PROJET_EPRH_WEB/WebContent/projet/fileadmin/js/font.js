var min=8;
var max=18;
function increaseFontSize() {
	fontSize('p',1);
	fontSize('li',1);
	fontSize('h1',1);
	fontSize('h2',1);
	fontSize('h3',1);
	fontSize('tr',1);
	fontSize('blockquote',1);
	fontSize('div',1);
}
function decreaseFontSize() {
	fontSize('p',-1);
	fontSize('li',-1);
	fontSize('h1',-1);
	fontSize('h2',-1);
	fontSize('h3',-1);
	fontSize('tr',-1);
	fontSize('blockquote',-1);
	fontSize('div',-1);
}

function fontSize(balise,step){
	   var p = document.getElementById("ce_contenu").getElementsByTagName(balise);
	   for(i=0;i<p.length;i++) {
	      if(p[i].style.fontSize) {
	         var s = parseInt(p[i].style.fontSize.replace("px",""));
	      } else {
	         var s = 12;
	      }
	      if( (s!=max && step > 0) || (s!=min && step < 0) ) {
	         s += step;
	      }
	      p[i].style.fontSize = s+"px"
	   }
}