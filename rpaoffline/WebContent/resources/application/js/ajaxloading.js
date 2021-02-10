jQuery(document).ajaxSend(function(){
    jQuery("#ajaxLoading").fadeIn();
});
jQuery(document).ajaxComplete(function(){
    jQuery("#ajaxLoading").fadeOut();
});