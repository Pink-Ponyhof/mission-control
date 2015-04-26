jQuery(function ($) {

    'use strict';

    //Init views
    var mcView = new mc.views.McView();

    //Init backend
    var callBackend = new mc.backend.CallBackend();
    var restClient = new mc.backend.RestClient({
        baseUrl: '../..',
        callBackend: callBackend
    });

    //Init controllers
    var mcController = new mc.controllers.McController({
        restClient: restClient,
        view: mcView
    });

    var configName = getUrlParameter('configName');
    if (configName) {
        mcController.switchToConfig(configName);
    }
    else {
        mcController.switchToOverview();
    }

    function getUrlParameter(sParam) {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++) {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] === sParam) {
                return sParameterName[1];
            }
        }
    }

});
