var Ajax = {
	name : "Ajax",
	responseData : null,
	getXmlhttp : function() {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	},
	makeAjaxRequest : function(url, func, params) {
		var xmlhttp = this.getXmlhttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				Ajax.responseData = xmlhttp.responseText;
				func.call();
			}
		};
		xmlhttp.open("POST", url, true);

		xmlhttp.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		// console.log(params);
		xmlhttp.send(params);
	}
}
