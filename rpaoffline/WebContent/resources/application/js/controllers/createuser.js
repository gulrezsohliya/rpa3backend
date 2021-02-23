/**
 * @Author Decent Khongstia
 */

$(document).ready(function () {
                var scope = angular.element($("#createuserCtrl")).scope();
    scope.$apply(function () {
        scope.listUsers();
    });
});

app.controller('createuserCtrl', ['$scope', '$sce', '$compile', function ($scope, $sce, $compile) {
    $scope.user = new Userlogins();
    $scope.users = [];
    $scope.repassword = "";
    $scope.designations = [];
//                $scope.designations = ${designations};
    $scope.department = null;
    $scope.designationgrp = null;
    $scope.departments = "";
    $scope.designationgroups =  "";
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    $scope.reset = function () {
        $scope.user = new Userlogins();
        $scope.department = null;
        $scope.designationgrp = null;
        $scope.repassword = "";
        jQuery("#add").html("Save");
    };

    $scope.save = function () {
        if(!$scope.validateUserForm())
            return false;
        
        $scope.user.password = ($scope.user.password === '') ? "" : sha256_digest($scope.user.password);
        jQuery.ajax({
            type: 'POST',
            url: "./saveUser",
            data: angular.toJson($scope.user),
            //                dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                alert(response);
                $scope.reset();
                $scope.listUsers();
                jQuery("#add").html("Save");
            },
            error: function (xhr) {
                alert(xhr.status + " = " + xhr)
                alert("Sorry, there was an error while trying to process the request.");
            }
        });
    };

    $scope.edit = function (usercode) {
        jQuery("#add").html("Update");
        for (var i = 0; i < $scope.users.length; i++) {
            if ($scope.users[i].usercode === usercode)
                $scope.user = $scope.users[i];
        }
        $scope.department = {
            "key": $scope.user.designation.departments.departmentcode,
            "value": $scope.user.designation.departments.departmentname
        };
        $scope.designationgrp = {
            "key": $scope.user.designation.cdr_grp_id,
            "value": ""
        };
        $scope.listDesignation();
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.listUsers = function () {
        jQuery.ajax({
            type: 'POST',
            url: "./listUsers",
            //                    dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var scope = angular.element($("#createuserCtrl")).scope();
                scope.$apply(function () {
                    scope.users = response;
                    scope.setDataTable(scope.users);
                });
            },
            error: function (xhr) {
                alert(xhr.status + " = " + xhr)
                alert("Sorry, there was an error while trying to process the request.");
            }
        });
    };
    $scope.listDesignation = function () {
        var paran = {};

        paran.departmentcode = ($scope.department !== null) ? $scope.department.key : "";
        paran.cdrgrpid = ($scope.designationgrp.key !== null && $scope.designationgrp.key !== "") ? $scope.designationgrp.key.substring(0, 1) : "";

        if (paran.departmentcode === "" || paran.departmentcode === undefined) {
            return false;
        }

        if (paran.cdrgrpid === "" || paran.cdrgrpid === undefined) {
            return false;
        }
        jQuery.ajax({
            type: 'POST',
            url: "./listDepartmentDesignations",
            data: paran,
            success: function (response) {
                var scope = angular.element($("#createuserCtrl")).scope();
                scope.$apply(function () {
                    scope.designations = response;
                });
            },
            error: function (xhr) {
                alert(xhr.status + " = " + xhr)
                alert("Sorry, there was an error while trying to process the request.");
            }
        });
    };
    $scope.setDataTable = function (obj) {
        jQuery("#displayRecords").html("");
        jQuery("#displayRecords").html("<table id='displayRecordsTable' style='width:100%' border='1'>\n\
                                                </table>");
        jQuery('#displayRecordsTable').DataTable({
            data: obj,
            columns: [
                {
                    "title": "User Code",
                    "data": "usercode",
                }, {
                    "title": "User Name",
                    "data": "officername"
                }, {
                    "title": "Designation",
                    "data": "designation.designationname"
                }, {
                    "title": "Department",
                    "data": "designation.departments.departmentname"
                }, {
                    "title": "Role",
                    "data": "userrole"
                }, {
                    "title": "Status",
                    "data": "enabled"
                }, {
                    "title": "Action",
                    "sortable": false,
                    "data": "usercode",
                    "render": function (data, type, full, meta) {
                        return '<div style="text-align:center"><button style="padding:5px" value="Edit" ng-click="edit(' + data + ')" class="button-primary">Edit</button></div>';
//                                        return '<center><button style="padding:5px" value="Edit" ng-click="edit(' + data + ')">Edit</button></center>';
                    }
                }
            ],
            "sortable": false,
            "columnDefs": [
                {"width": "2%", "targets": 0}
            ],
            "bLengthChange": false,
            createdRow: function (row, data, dataIndex) {
                $compile(angular.element(row).contents())($scope);
            }
        });
    };
    
    $scope.validateUserForm = function() {
//                        alert("validateUserForm");
//                        $scope.user;
        if($scope.user.officername === "" || $scope.user.officername === null){
            jQuery("#officername").focus();
            alert("Officer name cannot be empty");
            return false;
        }
        if($scope.department.key === "" || $scope.department.key === null){
            jQuery("#department").focus();
            alert("Please select department");
            return false;
        }
        if($scope.designationgrp.key === "" || $scope.designationgrp.key === null){
            jQuery("#designationgrp").focus();
            alert("Please select cadre group");
            return false;
        }
        if($scope.user.designation.designationid <= 0 || $scope.user.designation.designationid === null){
            jQuery("#designationid").focus();
            alert("Please select designation");
            return false;
        }
        if($scope.user.username === "" || $scope.user.username === null){
            jQuery("#username").focus();
            alert("username cannot be empty");
            return false;
        }
        if($scope.user.password === "" || $scope.user.password === null){
            jQuery("#passwords").focus();
            alert("password cannot be empty");
            return false;
        }
        if($scope.repassword === "" || $scope.repassword === null){
            jQuery("#repassword").focus();
            alert("password cannot be empty");
            return false;
        }
        if($scope.repassword !== $scope.user.password){
            jQuery("#repassword").focus();
            alert("passwords do not matches");
                return false;
            }
            return true;
        };
    }]);
