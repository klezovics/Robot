function drawGrid(maxH, maxW) {

	var table = document.createElement("table");
	for (var i = 0; i < maxH; i++) {
		var tr = document.createElement('tr');
		for (var j = 0; j < maxW; j++) {
			var td = document.createElement('td');
			td.id = i + ":" + j;
			
			tr.appendChild(td);
		}
		table.appendChild(tr);
	}

	document.getElementById("grid_container").appendChild(table);
}