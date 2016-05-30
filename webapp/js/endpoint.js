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


/**
 * Lists Books via the API.
 */
usp.punya.listBooks = function() {
	console.log(gapi.client);
	gapi.client.bookendpoint.listBooks().execute(
			function(resp) {
				console.log(resp);
				if (!resp.code) {
					resp.items = resp.items || [];
					for (var i = 0; i < resp.items.length; i++) {
						console.log(resp.items[i]);
					}
				}
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
			usp.punya.listBooks();
		}
		console.log(apisToLoad);
	}

	apisToLoad = 2; // must match number of calls to gapi.client.load()
	gapi.client.load('bookendpoint', 'v1', callback, apiRoot);
	gapi.client.load('oauth2', 'v2', callback);
};