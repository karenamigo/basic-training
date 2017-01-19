Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.state.*',
		'Ext.form.*']);
Ext.onReady(function() {
	// Creation of data model
	Ext.define('StudentDataModel', {
				extend : 'Ext.data.Model',
				idProperty : 'id',
				fields : [{
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
						}]
			});

	var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
				clicksToMoveEditor : 2,
				autoCancel : false
			});

	// Creation of first grid store
	var gridStore = Ext.create('Ext.data.Store', {
				model : 'StudentDataModel',
				fields : ['name', 'age', 'address', 'department'],
				proxy : {
					type : 'ajax',
					url : 'student/loadStudents',
					reader : {
						type : 'json'
					}
				},
				autoLoad : true,
				autoSave : true
			});

	// Creation of first grid
	var grid = Ext.create('Ext.grid.Panel', {
		id : 'gridId',
		store : gridStore,
		stripeRows : true,
		title : 'Students Grid',
		renderTo : 'gridDiv',
		width : '100%',
		height : 400,

		collapsible : true,
		enableColumnMove : true,

		enableColumnResize : true,

		columns : [{

					header : "Student Name",
					dataIndex : 'name',
					flex : .5,
					sortable : true,
					hideable : true,
					editor : {
						allowBlank : false
					}
				}, {
					header : "Age",
					dataIndex : 'age',
					flex : .5,
					sortable : true,
					hideable : false,
					editor : {
						allowBlank : false
					}

				}, {
					header : "Address",
					dataIndex : 'address',
					flex : 1,
					sortable : true,
					hideable : false,
					editor : {
						allowBlank : false
					}
				}, {
					header : "Department",
					dataIndex : 'department',
					flex : .5,
					sortable : true,
					hideable : false,
					editor : {
						allowBlank : false
					}
				}],

		tbar : [{
					text : 'Add Student',
					itemId : 'add Student',
					action : 'add',
					handler : function() {
						rowEditing.cancelEdit();
						// Create a model instance
						var r = Ext.create('StudentDataModel', {
									name : 'New Guy',
									age : 'New Guy',
									address : 'New Guy',
									department : 'New Guy'
								});

						gridStore.add(r);
						console.log(r);
						grid.editingPlugin.startEdit(r, 3);
						Ext.Ajax.request({
									url : 'student/add',
									method : 'POST',
									success : function(response) {
										gridStore.load();
									}
								});
					}
				}, {
					itemId : 'removeStudent',
					text : 'Remove Student',
					handler : function() {
						var sm = grid.getSelectionModel().getSelection();
						rowEditing.cancelEdit();

						Ext.Msg.confirm('DeleteStudent',
								'Sure delete this Student?', function(btn) {
									if (btn == 'yes') {

										gridStore.remove(sm);
										Ext.Ajax.request({
													url : 'student/delete',
													method : 'POST',
													success : function(response) {
														gridStore.load();
													}
												});
									}
								});

					},
					disabled : true
				}, {
					itemId : 'showStudentCourse',
					text : 'ShowStudentCourse',
					handler : function() {
						rowEditing.cancelEdit();

					},
					disabled : true
				}],
		plugins : [rowEditing],// enable row editing
		listeners : {
			'selectionchange' : function(view, records) {
				grid.down('#removeStudent').setDisabled(!records.length);
				grid.down('#showStudentCourse').setDisabled(!records.length);
			}

		}
	});

});
