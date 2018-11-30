
class Grid{
	
   static draw( maxH, maxW ){
	 
	 var gridContainerId = "grid_container";
	 var gridCellPrefix = "grid_cell";
	 var gridCellCoordSeparator = "x";
	 
	 
	 var table = document.createElement("table");
	 for (var i = 0; i < maxH; i++) {
	   
	   var tr = document.createElement('tr');
	   
	   for (var j = 0; j < maxW; j++) {
	     var td = document.createElement('td');
		 td.id = gridCellPrefix+i + gridCellCoordSeparator + j;			
		 tr.appendChild(td);
	   }
	   
	   table.appendChild(tr);
	 }

	 $("#"+gridContainerId).append(table);
   }
   
}

