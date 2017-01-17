var Student = {
	name : "Student",
	columnCount : 5,
	displayCount : 4,
	data : {
		id : null,
		name : null,
		age : null,
		address : null,
		department : null

	},
	columns : [{
				name : "name",
				value : "",
				type : 'input',
				displayName : "Name"

			}, {
				name : "age",
				value : "",
				type : 'input',
				displayName : "Age"

			}, {
				name : "address",
				value : "",
				type : "input",
				displayName : "Address"

			}, {
				name : "department",
				value : "",
				type : "input",
				displayName : "Department"

			}],
	buttons : {
		showBtn : {
			name : "showDetails",
			displayName : ""
		},

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
		cancelBtn : {
			name : "cancel",
			displayName : ""

		},
		saveBtn : {
			name : "save",
			displayName : ""
		}
	}
}