module.exports = function (config) {
    config.set({
        frameworks: ['jasmine'],
        singleRun: true,
        browsers: ['PhantomJS'],
        files: [
            '../target/bower/static/js/angular/angular.min.js',
            '../target/bower/static/js/angular-mocks/angular-mocks.js',
            '../target/bower/static/js/angular-route/angular-route.min.js',
            '../src/main/javascript/*.js',
            '../src/test/javascript/*.js'
        ]
    });
};