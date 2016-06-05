/** Global namespace. */
var usp = usp || {};
usp.punya = usp.punya || {};
usp.punya.CLIENT_ID =
	'replace this with your web application client ID';

usp.punya.SCOPES =
	'https://www.googleapis.com/auth/userinfo.email';

/**
 * Whether or not the user is signed in.
 * @type {boolean}
 */
usp.punya.signedIn = false;

usp.punya.BOOKS = $.Deferred();

usp.punya.listBooks = function() {
	return usp.punya.BOOKS.promise();
};

/**
 * Lists Books via the API.
 */
usp.punya.loadData = function() {
	console.log(gapi.client);
	var listBooks = $.Deferred();

	gapi.client.bookendpoint.listBooks().then(
			function(resp) {
				//console.log(resp);
				if (resp.status == 200) {
					console.log(resp.body);
					listBooks.resolve(jQuery.parseJSON(resp.body));
				} else {
					listBooks.reject(resp);
				}
			});
	
	$.when(listBooks).done(function(result1) {
		usp.punya.BOOKS.resolve(result1.items || []);
		//console.log(result1);
		$.mobile.changePage("#page_report");
	}).fail(function(failResult) {
		console.log(failResult)
	});
};

/**
 * Initializes the application.
 * @param {string} apiRoot Root of the API's path.
 */
usp.punya.init = function(apiRoot) {
	// Loads the OAuth and helloworld APIs asynchronously, and triggers login
	// when they have completed.
	var apisToLoad;
	var callback = function() {
		if (--apisToLoad == 0) {
			usp.punya.loadData();
		}
		console.log(apisToLoad);
	}

	apisToLoad = 2; // must match number of calls to gapi.client.load()
	gapi.client.load('bookendpoint', 'v1', callback, apiRoot);
	gapi.client.load('oauth2', 'v2', callback);
};