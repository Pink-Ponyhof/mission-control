module.exports = function (grunt) {

    require('load-grunt-tasks')(grunt, {
        pattern: ['grunt-contrib-*', 'grunt-karma', 'grunt-bower-task']
    });


    var sources = [
        '../src/main/javascript/*.js'
    ];

    grunt.initConfig({

        bower: {
            install: {
                options: {
                    targetDir: '../target/bower/static/',
                    install: true,
                    layout: 'byType'
                }
            }
        },

        jshint: {
            options: {
                force: true,
                jshintrc: '.jshintrc'
            },
            mc: sources
        },

        uglify: {
            mc: {
                files: [{
                    expand: true,
                    cwd: '../src/main/javascript',
                    src: '**/*.js',
                    dest: '../target/generated-resources/static/js'
                }]
            }
        },

        karma: {
            unit: {
                options: {
                    configFile: 'karma.conf.js'
                }
            }
        },

        watch: {
            jshint: {
                files: ['.jshintrc', '../src/main/javascript/*.js', '../src/test/javascript/*.js'],
                tasks: ['jshint']
            },
            test: {
                files: ['../src/test/javascript/*.js'],
                tasks: ['test']
            },
            bower: {
                files: ['bower.json'],
                tasks: ['build']
            },
            src: {
                files: ['../src/main/javascript/*.js'],
                tasks: ['build']
            }
        },

        clean: {
            '.grunt': ['.grunt']
        }
    });

    grunt.registerTask('test', ['karma']);

    grunt.registerTask('build', ['bower','jshint','test', 'uglify']);

    grunt.registerTask('build-skip-tests', ['bower','jshint','uglify']);

    grunt.registerTask('default', ['build']);

};


