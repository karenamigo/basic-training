Ext.require(['Ext.grid.*', 'Ext.data.*']); // 預先載入grid和data
Ext.onReady(function() {
	// Creation of course model
	Ext.define('StudentCourseModel', {
				extend : 'Ext.data.Model',
				idProperty : 'Id',
				fields : [{
							name : 'Id',
							type : 'int',
							defaultValue : 0
						}, {
							name : 'name',
							type : 'string'
						}],
				proxy : {
					type : 'ajax',
					reader : {
						type : 'json'
					},
					api : {
						read : 'student/detail',
						create : 'course/add',
						update : 'course/update',
						destroy : 'course/delete'
					},
					actionMethods : {
						destroy : 'POST',
						read : 'GET',
						create : 'POST',
						update : 'POST'
					}
				}
			});
	// Creation of student model
	Ext.define('StudentDataModel', {
				extend : 'Ext.data.Model',
				idProperty : 'Id',
				fields : [{
							name : 'Id',
							type : 'int',
							defaultValue : 0
						}, {
							name : 'name',
							type : 'string'
						}, {
							name : 'age',
							type : 'string'
						}, {
							name : 'address',
							type : 'string'
						}, {
							name : 'department',
							type : 'string'
						}],

				proxy : {
					type : 'ajax',
					reader : {
						type : 'json'
					},
					api : {
						read : 'student/loadStudents',
						create : 'student/add',
						update : 'student/update',
						destroy : 'student/delete'
					},
					actionMethods : {
						destroy : 'POST',
						read : 'GET',
						create : 'POST',
						update : 'POST'
					}
				}
			});
	var courseStore = Ext.create('Ext.data.Store', {
				id : 'course',
				model : 'StudentCourseModel',
				writer : writer,
				reader : reader,
				autoSave : false
			});

	// Creation of student store
	var sote = Ext.create('Ext.data.Store', {
				id : 'student',
				model : 'StudentDataModel',
				writer : writer,
				reader : reader,
				autoSave : false,
				autoLoad : true
			});

	var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
				clicksToMoveEditor : 2,
				autoCancel : false,
				listeners : {
					'canceledit' : function(rowEditing, context) {
						if (context.record.phantom) {
							sote.remove(context.record);
						}
					},
					'edit' : function(rowEditing, context) {
						var student = Ext.create('StudentDataModel');
						var data = grid.getSelectionModel().getSelection()[0].data;
						proxy = StudentDataModel.getProxy();
						Ext.Ajax.request({
									headers : {
										'Content-Type' : 'application/json'
									},
									url : 'student/update/' + data.id,
									method : 'POST',
									jsonData : data
								});
						sote.save();
					}
				}
			});

	var rowEditingCourse = Ext.create('Ext.grid.plugin.RowEditing', {
		clicksToMoveEditor : 2,
		autoCancel : false,
		listeners : {
			'canceledit' : function(rowEditingCourse, context) {
				if (context.record.phantom) {
					courseStore.remove(context.record);
				}
			},
			'edit' : function(rowEditingCourse, context) {
				var Course = Ext.create('StudentCourseModel');
				var data = gridCourse.getSelectionModel().getSelection()[0].data;
				var stdId = grid.getSelectionModel().getSelection()[0].data.id;
				proxy = StudentCourseModel.getProxy();
				Ext.Ajax.request({
							headers : {
								'Content-Type' : 'application/json'
							},
							url : 'course/update/' + stdId + "/" + data.id,
							method : 'POST',
							jsonData : data
						});
				courseStore.save();
			}
		}

	});

	var writer = new Ext.data.JsonWriter({
				encode : true,
				writeAllFields : true
			});
	var reader = new Ext.data.JsonReader({
				totalProperty : 'total',
				successProperty : 'success',
				idProperty : 'Id',
				messageProperty : 'message'
			});

	// Creation of student grid
	var grid = Ext.create('Ext.grid.Panel', {
		store : sote,
		stripeRows : true,
		title : 'Students Grid',
		renderTo : 'gridDiv',
		width : '100%',
		height : 600,
		frame : true,
		collapsible : true,
		enableColumnMove : false,
		enableColumnResize : false,
		columns : [{
					header : "Id",
					dataIndex : 'id',
					flex : .5,
					readOnly : true
				}, {

					text : "Student Name",
					dataIndex : 'name',
					flex : .5,
					sortable : true,
					hideable : true,
					editor : {
						xtype : 'textfield',
						allowBlank : false
					}
				}, {
					text : "Age",
					dataIndex : 'age',
					flex : .5,
					sortable : true,
					hideable : false,
					editor : {
						xtype : 'numberfield',
						allowBlank : false
					}

				}, {
					text : "Address",
					dataIndex : 'address',
					flex : 1,
					sortable : true,
					hideable : false,
					editor : {
						xtype : 'textfield',
						allowBlank : false
					}
				}, {
					text : "Department",
					dataIndex : 'department',
					flex : .5,
					sortable : true,
					hideable : false,
					editor : {
						xtype : 'textfield',
						allowBlank : false
					}
				}],
		tbar : [{
					text : 'Save All Modifications',
					handler : function() {
						sote.save();
						sote.load();
					}
				}, {
					text : 'Add Student',
					itemId : 'add Student',
					handler : function() {

						// Create a model instance
						var r = Ext.create('StudentDataModel', {
									name : 'New Student',
									age : '',
									address : '',
									department : ''
								});
						rowEditing.cancelEdit();
						sote.insert(0, r);
						grid.getView().refresh();
						rowEditing.startEdit(0);
					}
				}, {
					itemId : 'removeStudent',
					text : 'Remove Student',
					handler : function() {
						rowEditing.cancelEdit();
						var sm = grid.getSelectionModel().getSelection();
						var id = grid.getSelectionModel().getSelection()[0].data.id;
						proxy = StudentDataModel.getProxy();
						Ext.apply(proxy.api, {
									destroy : 'student/delete/' + id
								});
						Ext.Msg.confirm('DeleteStudent',
								'Sure delete this Student?', function(btn) {
									if (btn == 'yes') {
										for (var i = 0, r; r = sm[i]; i++) {
											sote.remove(sm);
										}
									}
									sote.save();
								});

					},
					disabled : true
				}, {
					itemId : 'showStudentCourse',
					text : 'ShowStudentCourse',
					handler : function() {
						rowEditing.cancelEdit();
						var id = grid.getSelectionModel().getSelection()[0].data.id;
						proxy = StudentCourseModel.getProxy();
						Ext.apply(proxy.api, {
									read : 'student/detail/' + id
								});
						courseStore.load();
					},
					disabled : true

				}],
		viewConfig : {
			forcefit : true
		},
		plugins : [rowEditing],// enable row editing
		listeners : {
			'selectionchange' : function(view, records) {
				grid.down('#removeStudent').setDisabled(!records.length);
				grid.down('#showStudentCourse').setDisabled(!records.length);
			}
		}
	});

	var gridCourse = Ext.create('Ext.grid.Panel', {
		title : 'Course Grid',
		renderTo : 'gridDiv',
		store : courseStore,
		stripeRows : true,
		width : '100%',
		height : 300,
		frame : true,
		collapsible : true,
		enableColumnMove : false,
		enableColumnResize : false,
		columns : [{
					header : "Id",
					dataIndex : 'id',
					flex : .5,
					hidden : true
				}, {

					text : "Course Name",
					dataIndex : 'name',
					flex : .5,
					sortable : true,
					hideable : true,
					editor : {
						xtype : 'textfield',
						allowBlank : false
					}
				}],
		tbar : [{
					text : 'Save All Modifications',
					handler : function() {
						courseStore.save();
						courseStore.load();
					}
				}, {
					text : 'Add Student Course',
					itemId : 'add Student Course',
					handler : function() {
						// Create a model instance
						var r = Ext.create('StudentCourseModel', {
									name : 'New Course'
								});
						var stdId = grid.getSelectionModel().getSelection()[0].data.id;
						console.log("stdid = " + stdId);
						Ext.apply(proxy.api, {
									create : 'course/add/' + stdId
								});
						rowEditingCourse.cancelEdit();
						courseStore.insert(0, r);
						gridCourse.getView().refresh();
						rowEditingCourse.startEdit(0);
					}
				}, {
					itemId : 'Remove Course',
					text : 'Remove Course',
					handler : function() {
						rowEditingCourse.cancelEdit();
						var sm = gridCourse.getSelectionModel().getSelection();
						var courseId = gridCourse.getSelectionModel()
								.getSelection()[0].data.id;
						proxy = StudentCourseModel.getProxy();
						Ext.apply(proxy.api, {
									destroy : 'course/delete/' + courseId
								});
						Ext.Msg.confirm('DeleteCourse',
								'Sure delete this Course?', function(btn) {
									if (btn == 'yes') {
										for (var i = 0, r; r = sm[i]; i++) {
											courseStore.remove(sm);
										}
									}
									courseStore.save();
								});
					}
				}],
		viewConfig : {
			forcefit : true
		},
		plugins : [rowEditingCourse]
	});
});
