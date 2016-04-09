// fis.config.set('modules.parser.sass', 'node-sass');
// fis.config.set('modules.parser.scss', 'node-sass');

//fis.config.set('roadmap.ext.sass', 'css');
//fis.config.set('roadmap.ext.scss', 'css');


fis.config.merge({
	roadmap : {
		ext : {
			less : 'css',
			scss:'css',
			md:'html',
		}
	}
});
