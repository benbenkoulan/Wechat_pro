module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      build: {
        expand : true,
        cwd : 'static/js/',
        src: '**/*.js',
        dest: 'static/js/'
      }
    },
    cssmin:{
      options:{
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      build: {
        expand : true,
        cwd : 'static/css/',
        src: '*.css',
        dest: 'static/css/'
      }
    },
    handlebars:{
      compile:{
        options:{
          namespace:'eloancnTemplate'
        },
        files:[
          {
            expand:true,
            cwd:'static/tpls/',
            src:'**/*.handlebars',
            dest:'build/static/js/',
            ext:'.hbs.js'
          }
        ]
      }
    },
    rev: {
      options: {
        algorithm: 'md5',
        length: 5
      },
      files: {
        src: ['static/js/*.js']
      }
    },
    useminPrepare: {
     html: ['static/html/*.html'],
     options: {
      dest: 'static/html'             
    }
  },
  usemin: { 
    html: ['static/html/index.html']
    /*,options: {
      assetsDirs: ['static/js']
    }*/
  },
  concat: {
    options: {
        separator: ';'
      },
      dist: {
        src: ['static/js/wmps/list.hbs.js', 'static/tpls/wmps/list.helper.js'],
        dest: 'build/static/js/wmps/list.hbs.js'
      }
  },
  sass: {                              // Task 
    dist: {                            // Target 
      files: [{
        expand: true,
        cwd: 'static/scss/',
        src: ['**/*.scss'],
        dest: 'static/css/',
        ext: '.css'
      }]
    }
  },
  watch: {
    scripts: {
      cwd:'static/scss/',
      files: ['**/*.scss'],
      tasks: ['sasscss'],
      options: {
        spawn: false
      }
    }
  }
});

  // 加载包含 "uglify" 任务的插件。
  /*grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-handlebars');*/
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-sass');
  /*grunt.loadNpmTasks('grunt-rev');
  grunt.loadNpmTasks('grunt-usemin');*/

  // 默认被执行的任务列表。
  //grunt.registerTask('default', ['watch'/*,'uglify','cssmin',,'jshint','handlebars'*/]);
  /*grunt.registerTask('default', ['useminPrepare','rev','usemin']);
  grunt.registerTask('handle',['handlebars']);
  grunt.registerTask('concatfile',['concat']);
  grunt.registerTask('optimize',['uglify','cssmin']);*/
  grunt.registerTask('watchtask',['watch'])
  grunt.registerTask('sasscss',['sass'])
};