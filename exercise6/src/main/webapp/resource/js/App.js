var App = { // 負責控制CRUD執行
	modelObj : null,
	studentId : null,
	parentModelObj : null,
	childModelObj : null,
	list : function(parentModelObj, childModelObj) {
		App.parentModelObj = parentModelObj;
		App.childModelObj = childModelObj;
		App.modelObj = App.parentModelObj;
		var url = "/exercise6/student/loadStudents";
		App.doPost(url, App.listCallback, null);
	},
	createItem : function() {

		var model = App.modelObj;
		var modelName = model.name.toLowerCase();
		if (modelName == "student") {
			var url = "/exercise6/" + modelName + "/add";
			params = model.columns[0].name + "="
					+ $(modelName + BuildUIObj.title[0]).value;
		} else {
			console.log(studentId);
			var url = "/exercise6/" + modelName + "/add";
			params = model.columns[0].name + "="
					+ $(modelName + BuildUIObj.title[0]).value;
		}
		for (var i = 1; i < model.columns.length; i++) {
			params += "&" + (model.columns[i].name) + "="
					+ $(modelName + BuildUIObj.title[i]).value;
		}
		console.log(url);
		App.doPost(url, App.crudCallback, params);
	},
	updateItem : function() {
		var model = App.modelObj;
		var modelName = model.name.toLowerCase();
		var id = $(this).value;
		console.log("id=" + id);
		var url = "/exercise6/" + modelName + "/update/" + $(this).value;
		console.log("url=" + url);
		var modelName = model.name.toLowerCase();
		var params = "id=" + id;
		model.data.id = $(this).value;

		for (var i = 0; i < model.columns.length; i++) {
			var value = $(i + 'td' + id).firstChild.value;
			model.data[model.columns[i].name] = value; // 列出實際在DB裡面此筆資料ID
			console.log(model.data[model.columns[i].name]);
			params += "&" + model.columns[i].name + "=" + value; // 列出完整修改過後的資料
		}
		if ($$("button.save").length - 1 == 0) { // 按下按鈕後會呼叫doPost將修改過的資料傳出去
			console.log(params);
			App.doPost(url, App.crudCallback, params);
			return;
		}
		App.doPost(url, App.updateCallback(model.data.id), params);
	},
	showDetails : function() {
		App.modelObj = Course; // 指定model為course
		var id = event.target.value;
		studentId = (String($(this).value) != "undefined")
				? $(this).value
				: $("createStudent").value;
		var url = "/exercise6/student/detail/" + $(this).value;
		params = "id= " + studentId;

		App.doPost(url, App.listCallback, params);

		var div = document.getElementById("listCourseDiv");// 使用toggle方式來做listCourseDiv的show&hide
		if (div.style.display == 'block') {
			ChangeBtnStatus.enableStdBtn();
			div.style.display = 'none';
			return;
		} else {
			ChangeBtnStatus.disableStudentBtn();
			div.style.display = 'block';
		}
	},
	getModelName : function() {
		var model = App.modelObj;
		var modelName = model.name.toLowerCase();
	},
	deleteItem : function() {
		var model = App.modelObj;
		var modelName = model.name.toLowerCase();
		var url = "/exercise6/" + modelName + "/delete/" + $(this).value;
		if (confirm("sure to delete this?"))
			App.doPost(url, App.crudCallback, null);
	},
	doPost : function(url, func, params) {
		Ajax.makeAjaxRequest(url, func, params);
	},
	listCallback : function() {
		BuildUIObj.initTable(Ajax.responseData);
		BuildUIObj.initTitle();
		BuildUIObj.initContent(Ajax.responseData);
		BuildUIObj.addListener();
	},
	crudCallback : function() {
		if (App.modelObj.name == "Student") {
			App.list();
		} else {
			App.showDetails();
		}
	},
	updateCallback : function(id) {
		BuildUIObj.initUpdate(id);
	}
}