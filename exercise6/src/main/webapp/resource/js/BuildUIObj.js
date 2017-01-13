var BuildUIObj = {
	name : "BuildUIObj",
	title : null,
	content : null,
	table : null,
	initTable : function(responseData) { // 初始化表格
		var model = App.modelObj;
		model.hashData = $H();
		// console.log("model = " + model);
		BuildUIObj.showStudentTbTitle(responseData);
		BuildUIObj.table = CreateElement.initTable();

		var listDiv = document.getElementById("list" + model.name + "Div");
		if (listDiv.firstChild) {
			// console.log("listDiv.firstChild = " + listDiv.firstChild);
			listDiv.removeChild(listDiv.firstChild);
		}
		listDiv.appendChild(BuildUIObj.table);
		// console.log("table =" + BuildUIObj.table);
		BuildUIObj.setTitle(model);
	},
	initTitle : function() {
		TableBuilder.createRow();
		var tr = document.createElement('tr');
		for (var i = 0; i < BuildUIObj.title.length; i++) {
			var th = document.createElement('th');
			var titleText = document.createTextNode(BuildUIObj.title[i]);
			th.appendChild(titleText);
			tr.appendChild(th);
		}
		this.table.appendChild(tr);
	},
	intitCreate : function() {
		TableBuilder.createRow();
	},
	initContent : function(responseData) {
		// console.log("initContent responseData =" + responseData);
		App.modelObj.dataCount = 0;
		var json = JSON.parse(responseData);
		// console.log("BuildUIObj initContent json = " + json);
		if (String(json) != "undefined") {
			for (var i = 0; i < json.length; i++) {
				App.modelObj.dataCount++;
				TableBuilder.insertRow(json[i]);
			}
		}
		// console.log(App.modelObj.dataCount);
	},

	addListener : function() {
		var model = App.modelObj;
		$('create' + model.name)
				.addEventListener('click', App.createItem, true);
		for (var i = 0; i < model.dataCount; i++) {

			$$('button.update' + model.name)[i].addEventListener('click',
					ChangeModeObj.setEditView, true);

			$$('button.delete' + model.name)[i].addEventListener('click',
					App.deleteItem, true);

			$$('button.cancel' + model.name)[i].hide();

		}
		BuildUIObj.addOtherListener(model);
	},
	addOtherListener : function(model) {
		if (model.name == "Student") {
			for (var i = 0; i < model.dataCount; i++) {
				$$('button.open')[i].addEventListener('click', App.showDetails,
						true);
			}
		}
	},
	initUpdate : function(id) {
		ChangeModeObj.setDataView(id);
	},

	setTitle : function(model) {
		BuildUIObj.title = new Array();
		BuildUIObj.content = new Array();

		for (var i = 0; i < model.columns.length; i++) {
			BuildUIObj.title.push(model.columns[i].displayName);
			BuildUIObj.content.push(model.columns[i].name);
		}
		for (var i in model.buttons) {
			if (model.buttons[i].displayName != "") {
				BuildUIObj.title.push(model.buttons[i].displayName);
				BuildUIObj.content.push(model.buttons[i].name);
			}
		}
	},
	showStudentTbTitle : function(responseData) {
		if (App.modelObj.name != "Course") {
			return;
		}
		var json = JSON.parse(responseData);
		BuildUIObj.setStudentInitData(json);

		var h1 = document.createElement('h1');
		h1.setAttribute("id", Course.data.StudentId);

		var contentText = document.createTextNode(Course.data.id);
		h1.appendChild(contentText);

		var titleDiv = document.getElementById("stdcourseTableTitle");
		if (titleDiv.firstChild) {
			titleDiv.removeAttribute(titleDiv.firstChild);
		}
		titleDiv.appendChild(h1);
	},
	setStudentInitData : function(json) {

		Course.data.name = json.name;
		Course.data.id = json.id;
	}
}