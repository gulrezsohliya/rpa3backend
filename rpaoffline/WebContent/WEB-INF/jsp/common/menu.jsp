<style>
    .gn-menu-main{
        z-index: 1;
    }
    .gn-menu-main nav li:hover {
        background: #6d7e90;
    }.gn-menu-main nav li:hover >a{
        color: white;
    }
    #menu {
        position: relative;
        width: 60px;
        padding:0;
        top: 120px;
    }#menu:hover{
        background-color:#34495e;
    }
    .gn-menu-wrapper {
        position: fixed;
        top: 120px;
        bottom: 0;
        left: 0;
        overflow: hidden;
        width: 60px; /* will be transitioned to 340px */
        border-top: 1px solid #4d6379;
        background: #34495e;
        transition: transform 0.3s, width 0.3s;
    }
    .gn-scroller {
        position: absolute;
        overflow-y: scroll;
        width: 370px;
        height: 90%;
    }
    .gn-menu {
        border-bottom: 1px solid #4d6379;
        text-align: left;
        font-size: 18px;
    }
    .gn-submenu{
        line-height: 30px;
    }
    .gn-submenu a{
        padding-left: 50px;
        font-size: 15px;
    }
    .gn-subsubmenu a{
        padding-left: 70px;
    }
    .gn-subsubmenu li{
        line-height: 35px;
    }
    .gn-submenu li {
        display:none;
    }
    .gn-submenu li a, .gn-subsubmenu li a {
        color: #c1c9d1
    }
    .gn-menu-wrapper:hover {
        font-size: 18px;
        width: 340px;
    }
    /*  SEE JavaScript for hover css 
        .gn-menu li:hover> ul.gn-submenu li.gn-submenu-li{
            display:block;
        }	
        .gn-submenu li.gn-submenu-li:hover> ul.gn-subsubmenu li.gn-subsubmenu-li{
            display:block;
        }*/
</style>
<nav class="gn-menu-wrapper gn-open-part" id="menuCtrl" ng-controller="menuCtrl">
    <div class="gn-scroller">
        <ul class="gn-menu"  >
            <li ng-repeat='record in menu track by $index'>
                <a class="gn-icon {{record.parenticon}}" href="{{(record.pageurl=='')?'#':record.pageurl}}">{{record.parent}}</a>
                <ul class="gn-submenu" ng-if="record.pageurl == ''">
                    <li class="gn-submenu-li" ng-repeat='subrecord in record.value track by $index'>
                        <a class="gn-icon {{subrecord.submenuicon}}" href="{{(subrecord.pageurl=='')?'#':subrecord.pageurl}}">{{subrecord.submenu}}</a>
                        <ul class="gn-subsubmenu" ng-if="subrecord.pageurl == ''">
                            <li class="gn-subsubmenu-li" ng-repeat='subsubrecord in subrecord.value track by $index'>
                                <a class="gn-icon {{subsubrecord.subsubmenuicon}}" href="{{(subsubrecord.pageurl=='')?'#':subsubrecord.pageurl}}">{{subsubrecord.subsubmenu}}</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li>
            <core:if test="${pageContext.request.userPrincipal.authenticated eq true && sessionScope.user ne null}">
                <a class="gn-icon icon-exit" href='/rpaoffline/logout'>Log Out</a>
            </core:if>
            <core:if test="${pageContext.request.userPrincipal.authenticated eq true && sessionScope.user eq null}">
                <a class="gn-icon icon-exit" href='/rpaoffline/logout'>Log Out</a>
            </core:if>
            </li>
        </ul>
    </div>
</nav>
<script>
    $(document).ready(function () {

        jQuery.ajax({
            type: 'GET',
            url: "./getMenu.htm",
//                dataType: "json",
            contentType: "application/json; charset=utf-8", //comment incase where data sent is non json
            success: function (response) {
            	try{
            		var scope = angular.element($("#menuCtrl")).scope();
                    scope.$apply(function () {
                        scope.menu = JSON.parse(response);
                    });
                    jQuery('.gn-menu li').click(function () {
                        jQuery(this).find('ul.gn-submenu li.gn-submenu-li').stop(true, true).slideDown(500);
                    });
                    jQuery('.gn-menu li').mouseleave(function () {
                        jQuery(this).find('ul.gn-submenu li.gn-submenu-li').stop(true, true).slideUp(500);
                    });
                    jQuery('.gn-submenu li.gn-submenu-li').click(function () {
                        jQuery(this).find('ul.gn-subsubmenu li.gn-subsubmenu-li').stop(true, true).slideDown(500);
                    });
                    jQuery('.gn-submenu li.gn-submenu-li').mouseleave(function () {
                        jQuery(this).find('ul.gn-subsubmenu li.gn-subsubmenu-li').stop(true, true).slideUp(500);
                    });
            	}catch(e){
            		console.error("Error:"+e);
            	}
                
            },
            error: function (xhr) {
                alert(xhr.status + " = " + xhr)
                alert("Sorry, there was an error while trying to process the request.");
            }
        });

    });
    //{"urlcode": 1, "modulecode": 1, "pagename": "home", "pageurl": "home.htm", "pageurlicon": "e900", "subsubmenu": null, "subsubmenuicon": null, "submenu": null, "submenuicon": null, "parent": "Home", "parenticon": "icon-home"}
    var menuapp = angular.module('Menu', []);
    menuapp.controller('menuCtrl', ['$scope', '$sce', function ($scope, $sce) {
            $scope.menu = [];
            $scope.trustHTML = function (post) {
                return $sce.trustAsHtml(post);
            };
        }]);
</script>
