$(function() {
	
	
	// solving the active menu problem
	switch (menu) {

	case 'Feeds':
		$('#Feeds').addClass('nav-left-link-active');
		break;
	case 'Posts':
		$('#Posts').addClass('nav-left-link-active');
		break;
	case 'Followers':
		$('#Followers').addClass('nav-left-link-active');
		break;
	case '"Following"':
		$('#"Following"').addClass('nav-left-link-active');
		break;
			
	}
	
});