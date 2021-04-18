//Common Factories
app.factory('commonInitFactory', function($http) {
	return {		
		listAdvertisements:  function(callback) {
			$http.get("./listAdvertisements").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listOptionalSubjects:  function(callback) {
			$http.get("./listOptionalSubjects").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listExamSubjects:  function(callback) {
			$http.get("./listExamSubjects").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listCategories:  function(callback) {
			$http.get("./listCategories").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listOtherCategories:  function(callback) {
			$http.get("./listOtherCategories").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listQualifications:  function(callback) {
			$http.get("./listQualifications").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listQualificationsCategories:  function(callback) {
			$http.get("./listQualificationsCategories").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listExamCenters:  function(callback) {
			$http.get("./listExamCenters").success(
			function(response, status, headers, config) {
				callback(response);
			});
		},
		listVenues:  function(callback) {
			$http.get("./listVenues").success(
					function(response, status, headers, config) {
						callback(response);
					});
				},
		listOffice:  function(officecode,callback) {
			$http.get("./listOffices/"+officecode).success(
					function(response, status, headers, config) {
						callback(response);
					});
		},
		listOffices:  function(callback) {
			$http.get("./listOffices/").success(
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
		listUser : function(user, callback) {
			if(typeof(user)=="number"){
				$http.get("./listUsers/usercode/"+user).success(
					function(response, status, headers, config) {
						callback(response);
				});
			}else{
				$http.get("./listUsers/username/"+user).success(
					function(response, status, headers, config) {
						callback(response);
				});
			}
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