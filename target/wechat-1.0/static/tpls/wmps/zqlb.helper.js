Handlebars.registerHelper('compare', function(id) {
    if (window.tenderDetailUrl) {
        return window.tenderDetailUrl + id;
    } else {
    	return '#';
    }
})