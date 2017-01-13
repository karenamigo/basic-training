var ChangeModeObj = {
	name : "ChangeModeObj",
	hashData : $H(),
	setEditView : function() {
		var id = event.target.value;
		ChangeModeObj.setEditViewBtn(id);
		var model = App.modelObj;
		var data = new Array();
		var open = document.getElementById('open' + id);

		for (var i = 0; i < model.columns.length; i++) {
			var objTd = document.getElementById(i + 'td' + id);
			var objValue = (objTd.firstChild.value == id) ? open : objTd;
			objValue = ChangeModeObj.getElementValue(objValue);
			data.push(objValue);

			ChangeModeObj.setTbEditable(objTd, model.columns[i].name, objValue);
		}
		ChangeModeObj.hashData.set(id, data);
		ChangeModeObj.setButtonDisable(id);

	},
	setDataView : function(key) {
		var isCancel = ((String($(this).value) != "undefined")) ? 1 : 0;
		var id = (isCancel == 1) ? $(this).value : key;

		ChangeModeObj.setDataViewBtn(id);

		if ($$("button.save").length == 0) {
			App.crudCallback();
			return;
		}

		var model = App.modelObj;
		var open = document.getElementById('open' + id);
		var data = ChangeModeObj.hashData.get(id);

		for (var i = 0; i < model.columns.length; i++) {
			var objTd = document.getElementById(i + 'td' + id);
			var objValue = (isCancel == 1)
					? data[i]
					: ((objTd.firstChild.value == id)
							? open.firstChild.value
							: objTd.firstChild.value);
			objTd.removeChild(objTd.firstChild);

			if (model.name == "Student" && BuildUIObj.title[i] == 'Name') {
				model.data.id = id;
				model.data.name = objValue;
				var open = CreateElement.buildButton("open");
				open.addEventListener('click', App.showDetails, true);
				if ($$("button.save").length > 0) {
					open.disabled = true;
				}
				objTd.appendChild(open);
				continue;
			}
			var contentText = document.createTextNode(objValue);
			objTd.appendChild(contentText);
		}
	},
	setEditViewBtn : function(id) {
		$('update' + id).firstChild.nodeValue = (event.target.firstChild.nodeValue == "update")
				? "save"
				: "update";
		$('update' + id).addClassName("save");
		$('update' + id).removeEventListener('click',
				ChangeModeObj.setEditView, true);
		$('update' + id).addEventListener('click', App.updateItem, true);

		$('cancel' + id).show();
		$('cancel' + id).addEventListener('click', ChangeModeObj.setDataView,
				true);
	},
	setDataViewBtn : function(id) {
		$('update' + id).firstChild.nodeValue = ($('update' + id).firstChild.nodeValue == "update")
				? "save"
				: "update";
		$('update' + id).removeClassName("save");
		$('update' + id).removeEventListener('click', App.updateItem, true);
		$('update' + id).addEventListener('click', ChangeModeObj.setEditView,
				true);

		$('cancel' + id).hide();
		$('cancel' + id).removeEventListener('click',
				ChangeModeObj.cancelUpdate, true);
	},
	setTbEditable : function(td, colName, value) {
		td.removeChild(td.firstChild);
		var inputTextNode = document.createElement('input');
		td.appendChild(inputTextNode);
		inputTextNode.setAttribute('id', colName);
		inputTextNode.setAttribute('value', value);

	},
	setButtonDisable : function(id) {
		var model = App.modelObj;
		for (var i = 0; i < model.dataCount; i++) {
			$$('button.delete' + model.name)[i].disabled = true;
		}
		for (var i = 0; i < $$('button.open').length; i++) {
			$$('button.open')[i].disabled = true;
		}
	},

	setTdSelectable : function(td, colName, values) {
		td.removeChild(td.firstChild);
		var selectObj = CreateElement.buildSelectObj(values);
		td.appendChild(selectObj);
	},
	getElementValue : function(td) {
		return (event.target.firstChild.nodeValue == "save")
				? td.firstChild.nodeValue
				: td.firstChild.value;
	},
	disableStudentBtn : function() {
		$('createStudent').disabled = true;
		for (var i = 0; i < Student.dataCount; i++) {
			$$('button.updateStudent')[i].disabled = true;
			$$('button.deleteStudent')[i].disabled = true;
			$$('button.open')[i].disabled = false;
		}
	},
	enableStdBtn : function() {
		$('createStudent').disabled = false;
		for (var i = 0; i < Student.dataCount; i++) {
			$$('button.updateStudent')[i].disabled = false;
			$$('button.deleteStudent')[i].disabled = false;
			$$('button.open')[i].disabled = false;
		}
	}

}