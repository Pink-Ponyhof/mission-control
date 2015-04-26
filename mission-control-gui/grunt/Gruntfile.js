module.exports = function (grunt) {

    require('load-grunt-tasks')(grunt, {
        pattern: ['grunt-contrib-*']
    });

    var sources = [
        '../src/main/javascript/mc.js',
        '../src/main/javascript/utility/**/*.js',
        '../src/main/javascript/backend/**/*.js',
        '../src/main/javascript/views/**/*.js',
        '../src/main/javascript/controllers/**/*.js',
        '../src/main/javascript/app.js'
    ];

    grunt.initConfig({
        jshint: {
            options: {
                force: true,
                jshintrc: '.jshintrc'
            },
            mc: sources
        },

        //jasmine: {
        //    mc: {
        //        src: sources,
        //        options: {
        //            specs: '../src/test/javascript/**/*.spec.js',
        //            vendor: [
        //                '../src/main/resources/custom/static/js/jquery-2.1.3.min.js'
        //            ],
        //            display: 'short',
        //            summary: true,
        //            template: require('grunt-template-jasmine-istanbul'),
        //            templateOptions: {
        //                coverage: '../target/jasmine-istanbul/coverage/coverage.json',
        //                report: '../target/jasmine-istanbul/coverage'
        //            }
        //        }
        //    }
        //},

        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: sources,
                dest: '../target/generated-resources/static/js/mc.min.js'
            }
        },

        watch: {
            mc: {
                files: ['.jshintrc', sources, '../src/test/javascript/**/*.js'],
                tasks: ['dev']
            }
        },

        clean: {
            '.grunt': ['.grunt']
        }
    });

    grunt.registerTask('dev', ['jshint', 'clean', 'concat']);
    // später den "jasmine" task aufsplitten in "nur testen" und "coverage messen"
    //   => dev (nur testen), build (dev + coverage)
    grunt.registerTask('build', ['dev']);

    grunt.registerTask('default', ['build']);

};


