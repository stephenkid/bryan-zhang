function initLevitationDiv(className, url, pars){
    var infoDiv = $("_levitationDiv");
	var infoIfm = $("_levitationIfm");
    if (!infoDiv) {
        infoDiv = document.createElement("div");	
        infoDiv.id = "_levitationDiv";
        infoDiv.style.display = "none";
        infoDiv.style.position = "absolute";
        infoDiv.style.zIndex = 1;
        var body = document.getElementsByTagName("body")[0];
		body.insertBefore(infoDiv);

		infoIfm = document.createElement("iframe");
		infoIfm.id = "_levitationIfm";
		infoIfm.frameBorder = 0;
		infoIfm.style.display = "none";
        infoIfm.style.position = "absolute";
		infoIfm.style.zIndex = infoDiv.style.zIndex-1;
		
		body.insertBefore(infoIfm);
/*
		Event.observe(infoDiv, 'mouseout', function(){
			if(!this.contains(event.toElement)){
				hiddenLevitationDiv();
			}
		}, false);
*/
    }

    var img = document.createElement("img");
    img.src = "js/levitation/waitLoading.gif";
    
    var tds = document.getElementsByTagName("td");
    for (var i = 0; i < tds.length; i++) {
        if (tds[i].className == className) {
            tds[i].style.cursor = "hand";
			tds[i].style.color = "blue";
            Event.observe(tds[i], 'click', function(){
                var mouseX = event.clientX;
                var mouseY = event.clientY;
                
                infoDiv.innerHTML = "";
                infoDiv.appendChild(img);
				fixedPosition(infoIfm,infoDiv,mouseX,mouseY);
                infoDiv.style.display = "block";
                infoIfm.style.display = "block";
				
				var param="";
				if(Event.element(event).pars){
					param=Event.element(event).pars;
				}
				param="&"+param;
				
                var template = new Template('#{pars}&page=#{page}#{param}');
                var allPars = template.evaluate({pars: pars,page: className,param: param});
				
                new Ajax.Request(url, {method: 'post',parameters: allPars,onComplete: function(originalRequest){
					infoDiv.innerHTML = originalRequest.responseText;
					fixedPosition(infoIfm,infoDiv,mouseX,mouseY);
					infoDiv.focus();
				}});
            }, false);
        }
    }
}

function hiddenLevitationDiv(){
	var infoDiv = $("_levitationDiv");
	if(infoDiv){
		infoDiv.style.display="none";
		infoDiv.innerHTML="";
	}
	var infoIfm = $("_levitationIfm");
	if(infoIfm){
		infoIfm.style.display="none";
	}
}

function fixedPosition(infoIfm,infoDiv,mouseX,mouseY){
    var popTopAdjust = 10;
    var popLeftAdjust = 10;
	var popHeight = infoDiv.clientHeight+popTopAdjust;
    var popWidth = infoDiv.clientWidth+popLeftAdjust;
    var bodyHeight=document.body.clientHeight;
	var bodyWidth=document.body.clientWidth;
	if (popHeight<=mouseY && mouseY + popHeight > bodyHeight) {
		popTopAdjust = -popHeight;
	}
	if (popWidth<=mouseX && mouseX + popWidth > bodyWidth) {
		popLeftAdjust = -popWidth;
	}
    infoDiv.style.left = document.body.scrollLeft + mouseX + popLeftAdjust;
    infoDiv.style.top = document.body.scrollTop + mouseY + popTopAdjust;
	
	infoIfm.style.width = infoDiv.offsetWidth;
	infoIfm.style.height = infoDiv.offsetHeight;
	infoIfm.style.top = infoDiv.style.top;
	infoIfm.style.left = infoDiv.style.left;
}
