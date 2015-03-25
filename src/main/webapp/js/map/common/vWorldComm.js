// V-world Map API Call Function in MAP

function mapCommon(lon, lat, alt){
	var _map = null;
	var _SOPPlugin;
	var _lon = lon;
	var _lat = lat;
	var _alt = alt;
	var _defaultZoom = 8;
	
	var _workMode = 0;
	var _distanceMode = 1;
	var _areaMode = 2;
	var _inputPointCnt = 0;
	
	var _gisMode = 2;
	var _streetMode = 0;
	var _satelliteMode = 1;
	var _v3DMode = 2;
	
	this.createMap = function(){
		vworld.showMode = false;
		vworld.init(
				"map"	// rootDiv
				, "map-first" // mapType
				,function() {
					_map = this.vmap;
					_map.setBaseLayer(_map.vworldBaseMap);
					_map.setControlsType({"simpleMap":true});
					_map.setCenterAndZoom(_lon, _lat, _defaultZoom);
				},
				this.initCallback
		);
	};
	this.initCallback = function(obj){
		    _SOPPlugin = obj;
//		    _SOPPlugin.getViewCamera().moveLonLat(_lon, _lat);
//			_SOPPlugin.getViewCamera().setAltitude(_alt);
//			window.sop.earth.addEventListener(_SOPPlugin, "click" , eventListenerCallbackClick);
//			window.sop.earth.addEventListener(_SOPPlugin, "dblclick" , eventListenerCallbackDblclick);
	};
	
	eventListenerCallbackClick = function(){
		_inputPointCnt++;
		if(_inputPointCnt == 1){
			if(_workMode == 1){
				_SOPPlugin.getAnalysis().clearDist();
			}else if(_workMode == 2){
				_SOPPlugin.getAnalysis().clearArea();
			}
		}
//		_SOPPlugin.getView().clearInputPoint();
//		_SOPPlugin.getAnalysis().addDistObject();
		//_SOPPlugin.getAnalysis().clearDist();
		//_SOPPlugin.getAnalysis().clearArea();
		//_SOPPlugin.getView().setWorkMode(sop.cons.mouseState.SOPMOUSE_ANAAREA);
	};
	
	eventListenerCallbackDblclick = function(){
		_SOPPlugin.getView().clearInputPoint();
		if(_workMode == _distanceMode){
			_SOPPlugin.getAnalysis().addDistObject();
		}else if(_workMode == _areaMode){
			_SOPPlugin.getAnalysis().addAreaObject();
		}
		_inputPointCnt = 0;
		_SOPPlugin.getView().setWorkMode(1);
	};
	
	this.changeGisMode = function(type){
		switch(type){
			case "street" 	: vworld.setMode(_streetMode);
									_gisMode = _streetMode;
								break;
			case "satellite" : vworld.setMode(_satelliteMode);
									_gisMode = _satelliteMode;
								break;
			case "3D"			: vworld.setMode(_v3DMode);
									_gisMode = _v3DMode;
									_SOPPlugin.getViewCamera().setAltitude(_alt);
									window.sop.earth.addEventListener(_SOPPlugin, "click" , eventListenerCallbackClick);
									window.sop.earth.addEventListener(_SOPPlugin, "dblclick" , eventListenerCallbackDblclick);
								break;
		}
	};
	
	this.gisControll = function(mode){
		_SOPPlugin.getAnalysis().clearDist();
		_SOPPlugin.getAnalysis().clearArea();
		if(_distanceMode == mode){
			_workMode = _distanceMode;
			_SOPPlugin.getView().setWorkMode(sop.cons.mouseState.SOPMOUSE_ANADISTANCE);
		}else if(_areaMode == mode){
			_workMode = _areaMode;
			_SOPPlugin.getView().setWorkMode(sop.cons.mouseState.SOPMOUSE_ANAAREA);
		}else{
			_workMode = 0;
			_SOPPlugin.getView().setWorkMode(1);
		}
	};
	
	this.getDistWorkMode = function(){
		return _distanceMode;
	};
	
	this.getAreaWorkMode = function(){
		return _areaMode;
	};
	
};