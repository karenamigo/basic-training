var CreateElement = {
	initTable : function() {
		var model = App.modelObj;
		var table = document.createElement('table');
		table.setAttribute("id", App.modelObj.name.toLowerCase + "table");
		table.setAttribute("class", "table table-bordered");
		return table; // 建立表格並設定表格屬性
	},
	buildTd : function(tr, i) {
		var td = document.createElement('td');
		td.setAttribute('id', i + 'td' + App.modelObj.data.id);
		return tr.appendChild(td); // 建立<td>
	},
	buildElement : function(i) {
		var model = App.modelObj;
		var obj = CreateElement.buildTextField(model, i);
		return obj;
	},
	buildTextField : function(model, i) { // 建立輸入文字框
		var obj = document.createElement('input');
		obj.setAttribute('id', model.name.toLowerCase()
						+ model.columns[i].displayName);
		obj.setAttribute('class', 'form-control');
		obj.setAttribute('required', 'required');
		return obj;
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