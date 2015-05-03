describe('OverviewCtrl', function () {

    var scope, ctrl, $httpBackend, $routeParams, data;

    beforeEach(module('mcControllers'));

    beforeEach(inject(function (_$httpBackend_, $rootScope, $controller) {
        scope = $rootScope.$new();
        $httpBackend = _$httpBackend_;
        $routeParams = {'configName': 'Fancy'};
        ctrl = $controller('ConfigDetailCtrl', {$scope: scope, $httpBackend: $httpBackend, $routeParams: $routeParams});

        data = {
            'name': 'Fancy',
            'configValues': [
                {
                    'key': 'key1',
                    'val': 'val1'
                },
                {
                    'key': 'key2',
                    'val': 'val2'
                }
            ]
        };
    }));

    it('should apply the configName on startup', inject(function () {

        expect(scope.configName).toBe('Fancy');
    }));

    it('should get the config given in $routeParams on startup', inject(function () {

        $httpBackend.expectGET('../../Fancy')
            .respond(data);
        $httpBackend.flush();
        expect(scope.config).toEqual(data);
    }));

    it('should post new config on submitValues', inject(function () {
        $httpBackend.expectGET('../../Fancy')
            .respond(data);
        $httpBackend.flush();
        var newData = {
            'name': 'Fancy',
            'configValues': [
                {
                    'key': 'key1',
                    'val': 'newVal1'
                },
                {
                    'key': 'key2',
                    'val': 'newVal2'
                }
            ]
        };

        scope.config = newData;

        scope.submitValues();
        $httpBackend.expectPOST('../../Fancy', newData.configValues)
            .respond(newData);
        $httpBackend.flush();
    }));

    it('should apply server values on submitValues', inject(function () {
        $httpBackend.expectGET('../../Fancy')
            .respond(data);
        $httpBackend.flush();
        var newData = {
            'name': 'Fancy',
            'configValues': [
                {
                    'key': 'key1',
                    'val': 'newVal1'
                },
                {
                    'key': 'key2',
                    'val': 'newVal2'
                }
            ]
        };

        scope.submitValues();
        $httpBackend.expectPOST('../../Fancy', data.configValues)
            .respond(newData);
        $httpBackend.flush();

        expect(scope.config).toEqual(newData);
    }));


});
