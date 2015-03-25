// Daum Map API Call Function in MAP

function mapCommon(){
	// Map 객체
	
	/******************************************************
	내      용	: OpenLayers Base ImgPath
	참고사항	: Map Control Bar View Img
	*******************************************************/
	OpenLayers.ImgPath = "/ucity/images/openlayers/";
	var _mapBaseProjection = new Proj4js.Proj("EPSG:5181");
	
	var _map;
	
	// 지도 Layer
	// 1: 일반, 2: 위성
	var _mapLayer1;
	var _mapLayer2;
	
	/*******************************************************
		내      용	: 구글 지도 Layer Set
		파라미터	: 없음
		리 턴 값	: 없음
		참고사항	: GoogleMap의 ZoomLevel을 9~19로 제한
						  20 Level은 지원이 안됨
		*******************************************************/
	this.setMapLayer = function () {
		_mapLayer1 = new OpenLayers.Layer.DaumStreet("Daum Street Map", {
			resolutions : [128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25]
		});
		_mapLayer2 = new OpenLayers.Layer.DaumSatellite("Daum Satellite Map", {
			resolutions : [128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25]
		});
	};
	
	/*******************************************************
	내      용	: map 생성 (생성된 Layer를 통해)
	파라미터	: 없음
	리 턴 값	: 없음
	참고사항	: 없음
	*******************************************************/
	this.createMap = function(){
		//var cvtMinExtent = convertCoord(${minXExtent}, ${minYExtent}, true);
		//var cvtMaxExtent = convertCoord(${maxXExtent}, ${maxYExtent}, true);
		_map = new OpenLayers.Map({
			div: "map",
			//maxExtent: new OpenLayers.Bounds( cvtMinExtent[0], cvtMinExtent[1], cvtMaxExtent[0], cvtMaxExtent[1]),
			//restrictedExtent : new OpenLayers.Bounds( cvtMinExtent[0], cvtMinExtent[1], cvtMaxExtent[0], cvtMaxExtent[1]),
			projection : new OpenLayers.Projection("EPSG:5181"),
			displayProjection : new OpenLayers.Projection("WGS84"),
			//center : new OpenLayers.LonLat(lon, lat),
			layers: [_mapLayer1, _mapLayer2],
			controls: [
				/*new OpenLayers.Control.Zoom({
		            zoomOutId: "customZoomOut"
		        }) ,*/	//지도 축소
		        new OpenLayers.Control.Navigation({
		        	zoomWheelEnabled : true
				})
			],
			zoom : 7//defaultZoomLev
		});
		
		//_map.setBaseLayer(_mapLayer1);
		
		_map.addControl(new OpenLayers.Control.PanZoomBar(), new OpenLayers.Pixel(10, 10));	//10, 54
		_map.events.on({								//map Zoom event 발생 시 실행할 함수 지정
			//"zoomend" : mapEventControl,	//단순한 Map Zoom Level 표시
			"moveend" : mapEventControl			//Zoom 및 map 이동시 발생
		});
		
		_map.addControl(new OpenLayers.Control.MousePosition());
		_map.addControl(new OpenLayers.Control.ScaleLine());
		
		//var mapExtentArray = map.getExtent().toString().split(",");	//현재 map의 Extent 배열에 저장 
	};
	
	this.getMapLayer1 = function(){
		return _mapLayer1;
	};
	
	this.getMapLayer2 = function(){
		return _mapLayer2;
	};
	
	this.setBaseLayer = function(mapLayer){
		_map.setBaseLayer(mapLayer);
	};
	
	this.setLayer = function(layer){
		_map.addLayer(layer);
	};
	
	this.setLayers = function(layers){
		_map.addLayers(layers);
	};
	
	this.getMapProjection = function(){
		return _mapBaseProjection;
	};
	
	this.setMapCenter = function(lon, lat){
		_map.setCenter(new OpenLayers.LonLat(lon, lat));
	};
	
	this.setMoveZoomToExtent = function(BoundObj){
		_map.zoomToExtent(BoundObj);
	};
	
	this.setAddControll = function(control){
		_map.addControl(control);
	}
	
	this.updateSize = function(){
		_map.updateSize();
	}
	
	this.setControls = function(controls){
		_map.addControls(controls);
	}
	
	this.setControl = function(control){
		_map.addControl(control);
	}
};