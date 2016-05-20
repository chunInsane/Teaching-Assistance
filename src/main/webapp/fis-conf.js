// fis.config.set('modules.parser.sass', 'node-sass');
// fis.config.set('modules.parser.scss', 'node-sass');

//fis.config.set('roadmap.ext.sass', 'css');
//fis.config.set('roadmap.ext.scss', 'css');


// fis.config.merge({
// 	roadmap : {
// 		ext : {
// 			less : 'css',
// 			scss:'css',
// 			md:'html',
// 		}
// 	}
// });

fis.config.set('livereload.port', 8133);

fis.config.set('modules.parser.sass', 'node-sass');
fis.config.set('modules.parser.scss', 'node-sass');

fis.config.set('roadmap.ext.sass', 'css');
fis.config.set('roadmap.ext.scss', 'css');

fis.config.set('roadmap.path',[
    {
        reg:/^\/resources\/scss\/(.*)/i,
        release:'/resources/css/$1'
    },
]);
