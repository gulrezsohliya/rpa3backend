//Common Factories
app.factory('commonFactory', function($http) {
	return {
		listUsers : function(callback) {
			$http.get("./listUsers").success(
					function(response, status, headers, config) {
						callback(response);
					});
		},		
	}
});