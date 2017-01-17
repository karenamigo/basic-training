var TableBuilder = {
	name : "TableBuilder",
	createRow : function() {
		var model = App.modelObj;
		var tr = document.createElement('tr');
		for (var i = 0; i < model.columns.length; i++) {
			var td = CreateElement.buildTd(tr);
			var domObj = CreateElement.buildElement(i);
			td.appendChild(domObj);
		}
		TableBuilder.createRowBtn(tr);
		BuildUIObj.table.appendChild(tr);
	},
	createRowBtn : function(tr) {
		var td = CreateElement.buildTd(tr);
		var createBtn = CreateElement.buildButton("create");
		td.appendChild(createBtn);
		if (App.modelObj.name == "Student") {
			td.setAttribute('colspan', '2');
		}
	},
	insertRow : function(json) {
		App.modelObj = TableBuilder.setModelData(json);
		var tr = document.createElement('tr');
		tr.setAttribute('id', App.modelObj.data.id);
		TableBuilder.buildDataContent(tr);
		BuildUIObj.table.appendChild(tr);
	},
	buildDataContent : function(tr) {
		var model = App.modelObj;
		var content = BuildUIObj.content;
		if (tr.firstChild) {
			for (var i = 0; i < BuildUIObj.title.length; i++) {
				tr.removeChild(tr.firstChild);
			}
		}
		for (var i = 0; i < BuildUIObj.title.length; i++) {
			var td = CreateElement.buildTd(tr, i);
			var contentText = document.createTextNode(model.data[content[i]]);
			if (model.name == "Student" && BuildUIObj.title[i] == 'Name') {
				var open = CreateElement.buildButton("open");
				td.appendChild(open);
				continue;
			}
			if (i > model.columns.length - 1) {
				var button = CreateElement.buildButton(BuildUIObj.content[i]);
				td.appendChild(button);
				if (BuildUIObj.content[i] == "update") {
					var button = CreateElement.buildButton("cancel");
					td.appendChild(button);
				}
			} else {
				td.setAttribute('class', 'dataContentText');
				td.appendChild(contentText);
			}
		}
	},
	setModelData : function(json) {
		for (var i in App.modelObj.data) {
			if (json.hasOwnProperty(i)) {
				App.modelObj.data[i] = json[i];
			}
		}
		return App.modelObj;
	}
}