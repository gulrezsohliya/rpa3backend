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
        <div class='containerBody' id="otherCatCtrl" ng-controller="otherCatCtrl">
            <h2 class="title">Other Categories Initialization</h2>
            <div  style='width:80%;margin: 15px auto 0'>
                <form id="otherCatForm" name="otherCatForm">
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        <tr class="form-group has-feedback">
                            <td class="title">Other Category :</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="othercategorydescription" name="othercategorydescription"
									ng-model="otherCat.othercategorydescription" required/>        
								<span id="othercategorydescriptionMsg"></span>
								<span class="alert alert-danger" 
									ng-show="otherCat.othercategorydescription.$pristine && otherCat.othercategorydescription.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button type="button" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="otherCatForm.$invalid">Add</button>
                                <button name="button" id="edit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="otherCatForm.$invalid">Update</button>
                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
            <div id="displayRecords" style='width:80%;margin: 15px auto 50px auto;'></div>
        </div>
        <script src="resources/application/js/controllers/othercategories.js"></script>
    </body>
</html>
