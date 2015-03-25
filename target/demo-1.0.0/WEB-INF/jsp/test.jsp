<!DOCTYPE HTML>
<html>
	<head>
		<title>	Drag and Drop Example</title>
		<style type="text/css">
			#target1, #target2, #target3
			{
				float : left; 
				width : 250px; 
				height : 250px;
				padding : 10px;
				margin : 10px;
				background-color : cyan;
			}

			#draggable1, #draggable2, #draggable3
			{
				width : 75px;
				height : 70px;
				padding : 5px;
				margin : 5px;
				background-color : orange;
			}
		</style>
		<script type="text/javascript">
			function start( e )
			{
				e.dataTransfer.effectAllowed="move";
				e.dataTransfer.setData( "Data", e.target.getAttribute( "id" ) );
				e.dataTransfer.setDragImage( e.target, 0, 0 );
				return true;
			}

			function enter( e )
			{
				return true;
			}

			function over( e )
			{
				var iddraggable = e.dataTransfer.getData( "Data" );
				var id = e.target.getAttribute( "id" );

				if ( id == "target1" )
					return false; // false -> drop ok, true -> drop no.

				if ( id == "target2" && iddraggable == "draggable3" )
					return false;
				else if ( id == "target3" &&  ( iddraggable == "draggable1" || iddraggable == "draggable2" ) )
					return false;
				else
					return true;
			}

			function drop( e )
			{
				var iddraggable = e.dataTransfer.getData( "Data" );
				e.target.appendChild( document.getElementById( iddraggable ) );
				e.stopPropagation();
				return false;
			}

			function end( e )
			{
				e.dataTransfer.clearData( "Data" );
				return true;
			}
		</script>
	</head>
	<body>
		<h1>Drag and Drop Example</h1>
		<div id="target1"ondragover="return over(event)"ondragenter="return enter(event)"ondrop="return drop(event)">
			<div id="draggable1" draggable="true"ondragstart="return start(event)"	ondragend="return end(event)">1</div>
			<div id="draggable2" draggable="true"ondragstart="return start(event)"	ondragend="return end(event)">2</div>
			<div id="draggable3" draggable="true"ondragstart="return start(event)"  ondragend="return end(event)">3</div>
		</div>
		<div id="target2"ondragover="return over(event)"ondragenter="return enter(event)"ondrop="return drop(event)"></div>
		<div id="target3"ondragover="return over(event)"ondragenter="return enter(event)"ondrop="return drop(event)"></div>
	</body>
</html>
