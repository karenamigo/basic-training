var CreateElement = {
	initTable : function() {
		var model = App.modelObj;
		var table = document.createElement('table');
		table.setAttribute("id", model.name.toLowerCase + "table");
		table.setAttribute("class", "table table-bordered");
		return table;
	},
	buildTd : function(tr, i) {
		var td = document.createElement('td');
		td.setAttribute('id', i + 'td' + App.modelObj.data.id);
		return tr.appendChild(td);
	},
	buildElement : function(i) {
		var model = App.modelObj;
		// console.log(App.modelObj);
	//	if (model.type == "input") {
			var obj = CreateElement.buildTextField(model, i);
		//} else if (model.type == "select") {
		//	var obj = CreateElement.buildSelectObj(value);
		//}
		return obj;
	},
	buildTextField : function(model, i) {
		var obj = document.createElement('input');
		obj.setAttribute('id', model.name.toLowerCase()
						+ model.columns[i].displayName);
		obj.setAttribute('class', 'form-control');
		return obj;
	},
	buildSelectObj : function(value) {
		// var model = App.modelObj;
		var department = model.department;
		var selectObj = document.createElement('select');
		selectObj.setAttribute('id', model.name.toLowerCase() + 'Department');
		for (var i = 0; i < department.length; i++) {
			var optionObj = document.createElement('option');
			selectObj.appendChild(optionObj);
			var contentText = document.createTextNode(department[i])
			optionObj.appendChild(contentText);
			if (value != null && value == department[i]) {
				optionObj.selected = true;
			}
		}
		return selectObj;
	},

	buildButton : function(btnName) {
		var model = App.modelObj;
		var button = document.createElement('button');
		var name = (btnName == "open") ? model.data.name : btnName;
		var btnClass = (btnName == "open") ? btnName : btnName + model.name;
		var contentText = document.createTextNode(name);
		button.appendChild(contentText);
		button.setAttribute('class', btnClass);
		button.setAttribute('name', btnName);
		if (btnName == "create") {
			button.setAttribute('id', btnName + model.name);
			button.setAttribute('value', Course.data.studentId);
			return button;
		}
		button.setAttribute('id', btnName + '' + model.data.id);
		button.setAttribute('value', model.data.id);
		return button;
	}
}