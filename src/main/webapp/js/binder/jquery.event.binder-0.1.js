/**
 * jQuery Event Trigger Plug-in
 * 
 * Copyright (c) 2014 Jinhyoung Lee(polyam@diycad.com)

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

 * Plug-in version : 1.1
 * 
 * 
 * 
 */
var _ws = null;
var _webSkState = null;	// 0= 비연결, 1= 연결, 2=원격종료
var _bindMode = null;
var _binderId = null;
(function($){
	//initialization of WebSocket; 
	$.initBinder = function(url, mode){
		_ws = window.WebSocket ? new WebSocket(url) : null;
		if(_ws == null){
			alert("Your browser does not support Websocket~!!")
			return;
		}else{
			_bindMode = mode;
			_ws.onmessage = function(event){
				console.log("DATA : "+event.data);
				msg = JSON.parse(event.data);
				if(msg.command == "connection"){
					_webSkState = msg.state;
					if(_webSkState == 0){
						if(confirm("현재 연결된 원격사용자가 있습니다.\n이전 연결을 해제 하시겠습니까?")){
							var reqJson = {"command" : "connection", "state" : 1};
							_ws.send(JSON.stringify(reqJson));
						}else{
							_ws.close();
						}
					}else if(_webSkState == 2){
						alert("다른 원격 사용자에 의해 연결이 해제되었습니다.");
						_ws.close();
					}
				}else if(msg.command == "message"){
					console.log(JSON.stringify(msg));
					switch(msg.type){
						case "event":{
							$(msg.target).trigger(msg.params);
							break;
						}
						case "function" :{
							executeFunctionByName(msg.target, window, msg.params);
						}
					}
				}
			};
		}
	};
	
	$.initBinderById = function(binderId, url, mode){
		_ws = window.WebSocket ? new WebSocket(url) : null;
		_binderId = binderId;
		if(_ws == null){
			alert("Your browser does not support Websocket~!!")
			return;
		}else{
			_bindMode = mode;
			_ws.onmessage = function(event){
				console.log("DATA : "+event.data);
				msg = JSON.parse(event.data);
				if(msg.command == "connection"){
					console.log("Connected WebSocket");
					var reqJson = {"command" : "connectionCheck", "state" : 1, "binderId" : _binderId, "bindMode" : _bindMode};
					_ws.send(JSON.stringify(reqJson));
				}else if(msg.command == "connectionCheck"){
					_webSkState = msg.state;
					if(_webSkState == 0){
						if(confirm("현재 연결된 원격사용자가 있습니다.\n이전 연결을 해제 하시겠습니까?")){
							var reqJson = {"command" : "connectionCheck", "state" : 1, "binderId" : _binderId};
							_ws.send(JSON.stringify(reqJson));
						}else{
							_ws.close();
						}
					}else if(_webSkState == 2){
						alert("다른 원격 사용자에 의해 연결이 해제되었습니다.");
						_ws.close();
					}
				}else if(msg.command == "message"){
					console.log(JSON.stringify(msg));
					if(msg.binderId != _binderId){
						return;
					}
					switch(msg.type){
						case "event":{
							$(msg.target).trigger(msg.params);
							break;
						}
						case "function" :{
							var functionName = ""+msg.target;
							executeFunctionByName(functionName, window, msg.params);
							
							break;
						}
					}
				}
			};
		}
	};
	
	function executeFunctionByName(functionName, context /*, args */) {
		  var args = [].slice.call(arguments).splice(2);
		  var namespaces = functionName.split(".");
		  var func = namespaces.pop();
		  for(var i = 0; i < namespaces.length; i++) {
		    context = context[namespaces[i]];
		  }
		  return context[func].apply(this, args);
		}
	
	//When page unloaded, close socket;
	$(window).unload(function () { _ws.close(); _ws = null });
	
	$.fn.bindRegisterEvent = function(types, selector, data, fn){
		
		// Types can be a map of types/handlers
		if ( typeof types === "object" ) {
			// ( types-Object, selector, data )
			if ( typeof selector !== "string" ) {
				// ( types-Object, data )
				data = data || selector;
				selector = undefined;
			}
			for ( type in types ) {
				this.on( type, selector, data, types[ type ], one );
			}
			return this;
		}

		if ( data == null && fn == null ) {
			// ( types, fn )
			fn = selector;
			data = selector = undefined;
		} else if ( fn == null ) {
			if ( typeof selector === "string" ) {
				// ( types, selector, fn )
				fn = data;
				data = undefined;
			} else {
				// ( types, data, fn )
				fn = data;
				data = selector;
				selector = undefined;
			}
		}
		
		cloneFn = fn;
				
		preFn = addPreCall(cloneFn);
		postFn = addPostCall(preFn);
		
		//console.log(this.selector);
		
		if(_bindMode != null){
			fn = postFn;
		}		
		
		if ( fn === false ) {
			fn = returnFalse;
		} else if ( !fn ) {
			return this;
		}
		
		return this.each(function(){
			$(this).on(types, selector, data, fn);
		});
	}
	
	$.fn.bindRegisterEventOne = function(types, selector, data, fn){
		
		// Types can be a map of types/handlers
		if ( typeof types === "object" ) {
			// ( types-Object, selector, data )
			if ( typeof selector !== "string" ) {
				// ( types-Object, data )
				data = data || selector;
				selector = undefined;
			}
			for ( type in types ) {
				this.on( type, selector, data, types[ type ], one );
			}
			return this;
		}
		
		if ( data == null && fn == null ) {
			// ( types, fn )
			fn = selector;
			data = selector = undefined;
		} else if ( fn == null ) {
			if ( typeof selector === "string" ) {
				// ( types, selector, fn )
				fn = data;
				data = undefined;
			} else {
				// ( types, data, fn )
				fn = data;
				data = selector;
				selector = undefined;
			}
		}
		
		cloneFn = fn;
		
		preFn = addPreCall(cloneFn);
		postFn = addPostCall(preFn);
		
		//console.log(this.selector);
		
		if(_bindMode != null){
			fn = postFn;
		}
		
		if ( fn === false ) {
			fn = returnFalse;
		} else if ( !fn ) {
			return this;
		}
		
		return this.each(function(){
			$(this).on(types, selector, data, fn, 1);
		});
	}
	
	$.bindRegisterFunction = function(){

		callFn = arguments[0];
		preProc_();
		
		callFn.apply(this, Array.prototype.slice.call(arguments, 1));
		
		postProc_();
	}
	
	function preProc_(){
		//TODO : 함수 호출전에 할일
		console.log("Pre Function Call");
	}
	
	function postProc_(){
		//TODO : 함수 호출후에 할일
		console.log("Post Function Call");
	}
	
	function addPreCall(oldFn){
		return function(){
			//TODO:함수 호출전에 할일 추가
			console.log("Pre Call");
			oldFn.apply(this, arguments);
		};
	}
	
	function addPostCall(oldFn){
		return function(){
			oldFn.apply(this, arguments);
			//TODO:함후 호출후에 할일 추가
			console.log("Post Call");
			var curSelector = getSelector(this)
			
			if(_ws != null){
				// event :
				// - target : selector
				// - params  : action
				// function : 
				// - target : functinon name
				// - params  : parameters (Array)
				var syncCommand = {
						command : "message",
						type : "event",
						target : curSelector,
						binderId : _binderId,
						params : "click"
				};
				_ws.send(JSON.stringify(syncCommand));
			}
			
			console.log(curSelector);
		};
	}
	
	$.bindFunctionCall = function(){
		if(_bindMode == "master"){
			var target = arguments[0];
			console.log(arguments);
			var params = Array.prototype.slice.call(arguments, 1);
			if(_ws != null){
				var syncCommand = {
						command : "message",
						type : "function",
						target : target.toString().match(/^function\s*([^\s(]+)/)[1],
						binderId : _binderId,
						params : params[0]
				};
				commandStr = " "+JSON.stringify(syncCommand) + " ";
				console.log(commandStr );
				_ws.send(commandStr);
			}
		}
	}
	
	function getSelector(elem){
		var selector = "";
//		var selector = $(elem).parents()
//		.map(function() { return this.tagName; })
//		.get().reverse().join(" ");
//
//		if (selector) { 
//			selector += " "+ $(elem)[0].nodeName;
//		}
		
		var id = $(elem).attr("id");
		if (id) { 
			selector += "#"+ id;
		}
		
//		var classNames = $(elem).attr("class");
//		if (classNames) {
//			selector += " ." + $.trim(classNames).replace(/\s/gi, ".");
//		}
		return selector;
	}
}(jQuery));