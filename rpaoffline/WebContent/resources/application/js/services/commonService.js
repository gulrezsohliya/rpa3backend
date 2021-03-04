/*@author Decent Khongstia*/

app.service("commonService", function($http) {
	console.info("commonService");
	
	this.success = (elementID, msg, ...arg) => {
		console.info("commonService.success");
		console.info(elementID, msg, ...arg);
	};

	this.danger = (elementID, msg, ...arg) => {
		console.info("commonService.danger");
		console.info(elementID, msg, ...arg);
	};
	
	this.save = (method, endpoint, data, successCallback, errorCallback)=>{
		console.info('method-', method, 'endpoint-',endpoint, 'data-',data );
		$.ajax({
            type: method,
            url: endpoint,
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            data: angular.toJson(data),
            success: function (res) {
            	console.info("save");
            	console.info("save: ",res);
            	if(res.response === CREATED){
//            		success
            		successCallback();
            	}else{
//            		failed
            		errorCallback();
//            		alert("failed");
            	}
                
            },
            error: function (xhr) {
                alert(xhr.status + " = " + xhr)
                console.log("saveERROR")
                alert("Sorry, there was an error while trying to process the request.");
                errorCallback();
            }
        }).then(() => {
        	
        });
	}
	
	/*this.updateUsers = (data, callback)=>{
		$.ajax({
            type: 'POST',
            url: "./updateuser",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: angular.toJson(data),
            success: function (res) {
            	if(res.response === CREATED){
//            		success
            		$scope.reset();
                    $scope.listUsers();
                    successCallback();
            	}else{
//            		failed
            		errorCallback();
            	}
                
            },
            error: function (xhr) {
                alert(xhr.status + " = " + xhr)
                alert("Sorry, there was an error while trying to process the request.");
                errorCallback();
            }
        });
	}*/
	
});