//Common Factories
app.factory('commonInitFactory', function($http) {
	return {
		listExamCenters:  function(callback) {
			$http.get("./listExamCenters").success(
			function(response, status, headers, config) {
				callback(response);
			});
		},
		listOffices:  function(callback) {
			$http.get("./listOffices").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listCells: function(callback) {
			$http.get("./listCells/").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listOfficeCells: function(callback, officecode) {
			$http.get("./listCells/"+officecode).success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listUser : function(callback, usercode) {
			console.info("listUsers: ", usercode);
			$http.get("./listUsers/usercode/"+usercode).success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listUsers : function(callback) {
			console.info("listUsers");
			$http.get("./listUsers").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},		
			
	}
});