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

$(document).on("click", "#btnSave", function(event) {

	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	var status = validateItemForm();

	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "PaymentAPI",
		type : type,
		data : $("#formPayment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

	function onItemSaveComplete(response, status) {
		if (status == "success") {
			
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success") {
				if ($("#hidItemIDSave").val() == "") {
					$("#alertSuccess").text("Successfully saved.");
					$("#alertSuccess").show();
				}else{
					$("#alertSuccess").text("Successfully updated.");
					$("#alertSuccess").show();
				}
				
				$("#divItemsGrid").html(resultSet.data);
			} else if (resultSet.status.trim() == "error") {
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
		} else if (status == "error") {
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else {
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
		}
		$("#hidItemIDSave").val("");
		
	}
	
	$("#txtPatient").val("");
	$("#txtHospital").val("");
	$("#txtDoctor").val("");
	$("#txtTotal").val("");

	console.log($("#formPayment").serialize());

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

$(document).on(
		"click",
		"#btnUpdate",
		function(event) {
			$("#hidItemIDSave").val(
					$(this).data("itemid"));
			$("#txtPatient").val($(this).closest("tr").find('td:eq(2)').text());
			$("#txtHospital").val($(this).closest("tr").find('td:eq(1)').text());
			$("#txtDoctor").val($(this).closest("tr").find('td:eq(3)').text());
			$("#txtTotal").val($(this).closest("tr").find('td:eq(4)').text());
			
			console.log($(this).data("itemid"));
		});


