
class Grid{

   static calcGridCellId( x,y ){
	   var gridCellPrefix = "grid_cell_";
	   var gridCellCoordSeparator = "x";
	   return gridCellPrefix+x+gridCellCoordSeparator+y;
   }	
	
   static draw( maxH, maxW ){
	 
	 var gridContainerId = "grid_container";
	 
	 var table = document.createElement("table");
	 table.id ="grid_table";
	 for (var i = 0; i < maxH; i++) {
	   
	   var tr = document.createElement('tr');
	   
	   for (var j = 0; j < maxW; j++) {
	     var td = document.createElement('td');
		 td.id = Grid.calcGridCellId(j,i);
		 
		 var div= document.createElement("div"); 
		 div.innerHTML='('+j+":"+i+")";
		 div.className="coordinate_subscript";
		 td.appendChild(div);
		 tr.appendChild(td);
	   }
	   
	   table.appendChild(tr);
	 }

	 $("#"+gridContainerId).append(table);
   }
   
}

