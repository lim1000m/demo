
/**
 * WebSocket InitBinder
 */
var _ws = null;
(function($){
	$.initBinder = function(url){
		_ws = window.WebSocket ? new WebSocket(url) : null;
		if(_ws == null){
			alert("Your browser does not support Websocket~!!")
			return;
		}else{
			_ws.onmessage = function(event){
				msg = JSON.parse(event.data);
				if(msg.command == "message"){
					switch(msg.type){
						case "event":{
							makeSomeNoise(msg.message);
							break;
						}
					}
				}
			};
		}
	};
}(jQuery));