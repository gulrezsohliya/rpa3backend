<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            input[type=radio]{
                margin: 5px 0 5px 30px;
            }
            td,th{
                font-size: 12px;
            }
            input[type=search]{
                margin-bottom: 5px;
            }
        </style>
    </head>
    <body ng-app="CommonApp">
        <%@include file="../common/header.jsp" %> 
        <div class='containerBody' id="examSubjectCtrl" ng-controller="examSubjectCtrl">
            <h2 class="title">Examination Subject Initialization</h2>
            <div  style='width:80%;margin: 15px auto 0'>
                <form id="examSubjectForm" name="examSubjectForm">
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        <tr class="form-group has-feedback">
                            <td class="title">Examnination Name :</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="examinationsubjectname" name="examinationsubjectname"
									ng-model="examSubject.examinationsubjectname" required/>        
								<span id="examinationsubjectnameMsg"></span>
								<span class="alert alert-danger" 
									ng-show="examSubjectForm.examinationsubjectname.$pristine && examSubjectForm.examinationsubjectname.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Description :</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="description" name="description"
									ng-model="examSubject.description" 	/>        
								<!-- <span id="descriptionMsg"></span>
								<span class="alert alert-danger" 
									ng-show="examSubjectForm.description.$pristine && examSubjectForm.description.$invalid"> 
									Required
								</span> -->
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button type="button" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="examSubject.$invalid">Add</button>
                                <button name="button" id="edit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="examSubject.$invalid">Update</button>
                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
            <div id="displayRecords" style='width:80%;margin: 15px auto 50px auto;'></div>
        </div>
        <script src="resources/application/js/controllers/examsubjects.js"></script>
    </body>
</html>
