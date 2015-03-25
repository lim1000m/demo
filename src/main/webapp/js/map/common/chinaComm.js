// Google Map API Call Function in MAP

function mapCommon(){
	// Map 객체
	
	/******************************************************
	내      용	: OpenLayers Base ImgPath
	참고사항	: Map Control Bar View Img
	*******************************************************/
	OpenLayers.ImgPath = "/ucity/images/openlayers/";
	//OpenLayers.Util.onImageLoadErrorColor = 1;
    OpenLayers.Util.onImageLoadErrorColor = "transparent";
	
	var _mapBaseProjection = new Proj4js.Proj("EPSG:4326");
	
	var _map;
	
	// 지도 Layer
	// 1: 일반, 2: 위성, 3: annotation, 4: annotaion (위성)
	var _mapLayer1;
	var _mapLayer2;
	var _mapLayer3;	
	var _mapLayer4;
	
	this.addPopup = function(popup){
		_map.addPopup(popup, true);
	};
	
	this.removePopup = function(popup){
		_map.removePopup(popup);
	};
	
	/*******************************************************
		내      용	: 중국 지도 Layer Set
		파라미터	: 없음
		리 턴 값	: 없음
		참고사항	: 
		*******************************************************/
	this.setMapLayer = function () {
		_mapLayer1 = new OpenLayers.Layer.WMTS({
			name: "天地图-普通",
			url: "http://t0.tianditu.com/vec_c/wmts",
			format: "tiles",
			layer: "vec",
			style: "default",
			matrixSet: "c",
			isBaseLayer: true
		});

		_mapLayer2 = new OpenLayers.Layer.WMTS({
			name: "天地图-影像",
			//url: "http://t7.tianditu.com/img_c/wmts",
			url: "http://t0.tianditu.com/img_c/wmts",
			format: "tiles",
			layer: "img",
			style: "default",
			matrixSet: "c",
			isBaseLayer: true
		});
		
		_mapLayer3 = new OpenLayers.Layer.WMTS({
			name: "天地图-普通注记",
//			//url: "http://t1.tianditu.com/cva_c/wmts",
			url: "http://t0.tianditu.com/cva_c/wmts",
			format: "tiles",
			layer: "cva",
			style: "default",
			transparent: true,
			matrixSet: "c",
			isBaseLayer: false
		});
		
		_mapLayer4 = new OpenLayers.Layer.WMTS({
			name: "天地图-影像注记",
			url: "http://t0.tianditu.com/cia_c/wmts",
			format: "tiles",
			layer: "cia",
			style: "default",
			matrixSet: "c",
			isBaseLayer: false
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
			projection : new OpenLayers.Projection("EPSG:4326"),
			displayProjection : new OpenLayers.Projection("WGS84"),
			numZoomLevels:19,
			//center : new OpenLayers.LonLat(lon, lat),
			layers: [_mapLayer1, _mapLayer2, _mapLayer3, _mapLayer4],
//			layers: [_mapLayer1, _mapLayer2],
			controls: [
				/*new OpenLayers.Control.Zoom({
		            zoomOutId: "customZoomOut"
		        }) ,*/	//지도 축소
		        new OpenLayers.Control.Navigation({
		        	zoomWheelEnabled : true
				})
			]
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
		_map.removeLayer(_mapLayer4);
		_map.addLayer(_mapLayer3);
		return _mapLayer1;
	};
	
	this.getMapLayer2 = function(){
		_map.removeLayer(_mapLayer3);
		_map.addLayer(_mapLayer4);
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
	
	this.setMapCenter = function(lon, lat, zoom){
		_map.setCenter(new OpenLayers.LonLat(lon, lat), zoom);
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
