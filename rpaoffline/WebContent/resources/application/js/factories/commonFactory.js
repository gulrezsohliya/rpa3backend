//Common Factories
app.factory('commonFactory', function($http) {
	return {
		listOffices:  function(callback) {
			$http.get("./listOffices").success(
			function(response, status, headers, config) {
				callback(response);
			});
//			$.ajax({
//		        type: "GET",
//		        url: "./listOffices",
//		        async: false,
//		        contentType: "application/json; charset=utf-8",
//		        success: function (response) {
//		          console.info("Success->response: ",response);
//		          res = response;
//		        },
//		        error: function (xhr) {
//		          alert(xhr.status + " = " + xhr);
//		          alert(
//		            "Sorry, there was an error while trying to process the request."
//		          );
//		          res = xhr;
//		    
//		        }
//			})
//			
//			callback(res);
		},
		listOfficeCells: function(callback, officecode) {
			$http.get("./listCells/"+officecode).success(
					function(response, status, headers, config) {
						callback(response);
					});
//			jQuery.ajax({
//	    		type: 'GET',
//	    		url: "./listCells/"+officecode,
//	    		async: false,
//	    		success: function (response) {
//	    			res = response;
//	    		},
//	    		error: function (xhr) {
//	    			alert(xhr.status + " = " + xhr)
//	    			alert("Sorry, there was an error while trying to process the request.");
//	    			res = xhr;
//	    		}
//	    	});
			
//			callback(res);
		},
		listUsers : function(callback) {
			$http.get("./listUsers").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},		
	}
});