(function () {
    'use strict';

    var mcControllers = angular.module('mcControllers', []);

    mcControllers.controller('OverviewCtrl', ['$scope', '$http', function ($scope, $http) {

        $http.get('../../')
            .success(function (data) {
                $scope.configurations = data;
            });
    }]);

    mcControllers.controller('ConfigDetailCtrl', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
        $scope.configName = $routeParams.configName;

        $scope.theStuff = 'theStuff';

        $http.get('../../' + $scope.configName)
            .success(function (data) {
                $scope.config = data;
            });

        $scope.submitValues = function () {
            $http.post('../../' + $scope.configName, $scope.config.configValues)
                .success(function (data) {
                    $scope.config = data;
                });
        };
    }]);
}());
