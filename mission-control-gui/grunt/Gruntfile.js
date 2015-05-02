module.exports = function (grunt) {

    require('load-grunt-tasks')(grunt, {
        pattern: ['grunt-contrib-*']
    });

    grunt.loadNpmTasks('grunt-karma');

    var sources = [
        '../src/main/javascript/*.js'
    ];

    grunt.initConfig({
        jshint: {
            options: {
                force: true,
                jshintrc: '.jshintrc'
            },
            mc: sources
        },

        uglify:{
            options:{
                beautify : true
            },
            mc: {
                files:[{
                    expand: true,
                    cwd:'../src/main/javascript',
                    src: '**/*.js',
                    dest: '../target/generated-resources/static/js'
                }]
            }
        },

        karma: {
            unit: {
                options: {
                    frameworks: ['jasmine'],
                    singleRun: true,
                    browsers: ['PhantomJS'],
                    files: [
                        '../src/main/resources/static/js/angular.min.js',
                        '../src/main/resources/static/js/angular-mocks.js',
                        '../src/main/javascript/*.js',
                        '../src/test/javascript/*.js'
                    ]
                }
            }
        },

        watch: {
            mc: {
                files: ['.jshintrc', '../src/main/javascript/*.js', '../src/test/javascript/*.js'],
                tasks: ['dev']
            }
        },

        clean: {
            '.grunt': ['.grunt']
        }
    });

    grunt.registerTask('test', ['jshint', 'karma']);


    grunt.registerTask('dev', ['jshint', 'karma', 'uglify']);
    // später den "jasmine" task aufsplitten in "nur testen" und "coverage messen"
    //   => dev (nur testen), build (dev + coverage)
    grunt.registerTask('build', ['dev']);

    grunt.registerTask('default', ['build']);

};


