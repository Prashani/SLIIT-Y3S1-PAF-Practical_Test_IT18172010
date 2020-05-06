<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"
	integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="Views/bootstrap-grid.min.css">

<script src="Components/main.js"></script>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-5"
				style="margin: 20px auto; border-style: dashed; border-width: 3px; padding: 28px; border-radius: 10px;border-color: gray;">
				<h3 class="m-3" style="text-align: center;font-weight: 600">Payment details</h3>
				<form id="formPayment" name="formPayment">


				<div class="input-group input-group-sm mb-3"
						style="width: 350px; margin: 1px auto;">
						<div class="input-group-prepend">
							<span class="input-group-text" style="width: 100px" id="lblName">Patient
								ID </span>
						</div>
						<input type="text" id="txtPatient" name="txtPatient"
							style="width: 250px; padding-left: 10px">
					</div>
					<div class="input-group input-group-sm mb-3"
						style="width: 350px; margin: 1px auto;">
						<div class="input-group-prepend">
							<span class="input-group-text" style="width: 100px"
								id="lblHospital">Hospital ID </span>
						</div>
						<input type="text" id="txtHospital" name="txtHospital"
							style="width: 250px;; padding-left: 10px">
					</div>
					<div class="input-group input-group-sm mb-3"
						style="width: 350px; margin: 1px auto;">
						<div class="input-group-prepend">
							<span class="input-group-text" style="width: 100px"
								id="lblDoctor">Doctor ID </span>
						</div>
						<input type="text" id="txtDoctor" name="txtDoctor"
							style="width: 250px; padding-left: 10px">
					</div>
					<div class="input-group input-group-sm mb-3"
						style="width: 350px; margin: 1px auto;">
						<div class="input-group-prepend">
							<span class="input-group-text" style="width: 100px" id="lblTotal">Total
							</span> <input type="number" id="txtTotal" name="txtTotal"
								style="width: 250px; padding-left: 10px" />
						</div>

					</div>

					<div id="alertSuccess" class="alert alert-success"
						style="width: 350px; margin-left: 30px; text-align: center;"></div>
					<div id="alertError" class="alert alert-danger"
						style="width: 350px; margin-left: 30px; ; text-align: center;"></div>
					<div style="width: 119px; margin-left: 145px;">
						<input style="width: 145px" id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary"> <input type="hidden"
							id="hidItemIDSave" name="hidItemIDSave" value="">
					</div>
			</div>
		</div>
		<br>
		
		<br>
		<div id="divItemsGrid">
			
		</div>
	</div>

</body>
</html>