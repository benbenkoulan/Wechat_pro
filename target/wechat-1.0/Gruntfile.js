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
  concat: {
    options: {
        separator: ';'
      },
      dist: {
        src: ['static/js/sesame/list.hbs.js', 'static/tpls/sesame/list.helper.js'],
        dest: 'build/static/js/sesame/list.hbs.js'
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
      cwd:'static/',
      files: ['**/*.js','**/*.css'],
      tasks: ['uglify','cssmin'],
      options: {
        spawn: false
      }
    }
  }
});

  // 加载包含 "uglify" 任务的插件。
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-handlebars');
  grunt.loadNpmTasks('grunt-contrib-watch');
  
  //任务列表。
  grunt.registerTask('handle',['handlebars']);
  grunt.registerTask('concatfile',['concat']);
  grunt.registerTask('optimize',['uglify','cssmin']);
  grunt.registerTask('watchtask',['watch']);
};