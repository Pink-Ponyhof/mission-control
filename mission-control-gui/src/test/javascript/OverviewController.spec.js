describe('OverviewCtrl', function () {

    var scope, ctrl, $httpBackend;

    beforeEach(module('missionControlApp'));

    beforeEach(inject(function (_$httpBackend_, $rootScope, $controller) {
        scope = $rootScope.$new();
        $httpBackend = _$httpBackend_;
        ctrl = $controller('OverviewCtrl', {$scope: scope, $httpBackend: $httpBackend});
    }));

    it('should create "configurations" model with 2 configurations', inject(function (_$httpBackend_, $rootScope, $controller) {
        $httpBackend.expectGET('../../')
            .respond([
                {'name': 'Fancy'},
                {'name': 'Pony'}
            ]);
        $httpBackend.flush();
        expect(scope.configurations.length).toBe(2);
    }));

});