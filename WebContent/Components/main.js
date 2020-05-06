/**
 * 
 */

$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
	$.ajax({
		url : "PaymentAPI",
		type : "GET",
		dataType : "text",
		complete : function(response, status) {
			var resultSet = JSON.parse(response.responseText);
			$("#divItemsGrid").html(resultSet.data);
		}
	});
});



function validateItemForm() {

	if ($("#txtPatient").val().trim() == "") {
		return "Insert patient ID.";
	}

	if ($("#txtHospital").val().trim() == "") {
		return "Insert  hospital ID.";
	}
	if ($("#txtDoctor").val().trim() == "") {
		return "Insert  doctor ID.";
	}
	if ($("#txtTotal").val().trim() == "") {
		return "Insert total.";
	}

	return true;
}


