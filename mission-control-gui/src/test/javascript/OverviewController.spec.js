describe('OverviewCtrl', function () {

    var scope, ctrl, $httpBackend;

    beforeEach(module('mcControllers'));

    beforeEach(inject(function (_$httpBackend_, $rootScope, $controller) {
        scope = $rootScope.$new();
        $httpBackend = _$httpBackend_;
        ctrl = $controller('OverviewCtrl', {$scope: scope, $httpBackend: $httpBackend});
    }));

    it('should get the current configurations on startup', function () {
        $httpBackend.expectGET('../../')
            .respond([
                {'name': 'Fancy'},
                {'name': 'Pony'}
            ]);
        $httpBackend.flush();
        expect(scope.configurations.length).toBe(2);
    });

});