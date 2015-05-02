(function () {
    'use strict';

    var mcApp = angular.module('missionControlApp', []);


    mcApp.controller('OverviewCtrl', ['$scope', '$http', function ($scope, $http) {

        $http.get('../../')
            .success(function(data) {
                $scope.configurations = data;
            });
    }]);
}());
