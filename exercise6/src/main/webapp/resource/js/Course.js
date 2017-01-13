var Course = {
	name : "Course",
	columnCount : 3,
	displayCount : 1,
	data : {
		id : null,
		name : null,
		studentId : null
	},
	columns : [{
				name : "name",
				value : "",
				type : 'input',
				displayName : "Name"
			}],
	buttons : {
		updateBtn : {
			name : "update",
			displayName : "Update"

		},
		deleteBtn : {
			name : "delete",
			displayName : "Delete"

		},
		createBtn : {
			name : "create",
			displayName : ""

		},
		saveBtn : {
			name : "save",
			displayName : ""

		},
		cancelBtn : {
			name : "cancel",
			displayName : ""

		}
	}
}