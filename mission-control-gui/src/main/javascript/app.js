(function () {
    'use strict';

    var mcApp = angular.module('missionControlApp', ['ngRoute', 'mcControllers']);

    mcApp.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                when('/', {
                    templateUrl: 'config-overview.html',
                    controller: 'OverviewCtrl'
                }).
                when('/:configName', {
                    templateUrl: 'config-detail.html',
                    controller: 'ConfigDetailCtrl'
                }).
                otherwise({
                    redirectTo: '/'
                });
        }]);

}());
