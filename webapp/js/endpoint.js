/** Global namespace. */
var usp = usp || {};
usp.punya = usp.punya || {};
usp.punya.CLIENT_ID =
	'816356369753-2fk8iao9pvdqbl4inor9qanfuuuimcbp.apps.googleusercontent.com';

usp.punya.SCOPES =
	'https://www.googleapis.com/auth/userinfo.email';

/**
 * Whether or not the user is signed in.
 * @type {boolean}
 */
usp.punya.signedIn = false;

usp.punya.BOOKS = $.Deferred();
usp.punya.CURRENCY = $.Deferred();

usp.punya.listBooks = function() {
	return usp.punya.BOOKS.promise();
};

usp.punya.listCurrency = function() {
	return usp.punya.CURRENCY.promise();
};

/**
 * Lists Books via the API.
 */
usp.punya.loadData = function() {
	console.log(gapi.client);
	var listBooks = $.Deferred();
	var listCurrency = $.Deferred();
	
	var batch = gapi.client.newBatch();

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

	gapi.client.currencyendpoint.listCurrency().then(
			function(resp) {
				//console.log(resp);
				if (resp.status == 200) {
					console.log(resp.body);
					listCurrency.resolve(jQuery.parseJSON(resp.body));
				} else {
					listBooks.reject(resp);
				}
			});
	
	$.when(listBooks, listCurrency).done(function(books, currency) {
		console.log(currency);
		usp.punya.BOOKS.resolve(books.items || []);
		usp.punya.CURRENCY.resolve(currency.items || []);
		//console.log(result1);
		$.mobile.changePage("#page_report");
	}).fail(function(failResult) {
		console.log(failResult)
	});
};




function prms_gapi_load_by(api,v, apiRoot) {
	var df = $.Deferred();
	// gapi.client.setApiKey(constants.gapi.api_key);
	if (apiRoot) {
		gapi.client.load(api, v, function(){
			console.log("load success:" + api);
			df.resolve("loaded:" + api);
		}, apiRoot);
	} else {
		gapi.client.load(api, v, function(){
			console.log("load success:" + api);
			df.resolve("loaded:" + api);
		});
	}
	return df.promise();
}

function prms_gapi_auth(mode){
	var df = $.Deferred();
	gapi.auth.authorize(
			{
				client_id: usp.punya.CLIENT_ID, 
				scope: usp.punya.SCOPES, immediate: mode
			},
			function(res) {
				if(!gapi.auth || !gapi.auth.getToken()){
					df.reject("gapi.auth not exists");
				}

				if (res && !res.error) {
					console.log("auth success");
					df.resolve(res);
				} else {
					console.log("auth failed..retry");
					df.reject(res);
				}
			});
	return df.promise();
}


usp.punya.loadApis = function(apiRoot) {
	$.when(prms_gapi_load_by('bookendpoint', 'v1', apiRoot),
			prms_gapi_load_by('currencyendpoint', 'v1', apiRoot),
			prms_gapi_load_by('oauth2', 'v2')).done(
					function(bookendpoint, currencyendpoint, oauth2) {
				usp.punya.loadData();
			}).fail(function(failResult) {
				
				console.log(failResult)
			}); 
}
/**
 * Initializes the application.
 * @param {string} apiRoot Root of the API's path.
 */
usp.punya.init = function(apiRoot) {
	// Ask for immediate auth_token. If fails, show user consent screen.
	prms_gapi_auth(true).done(
			function(res) {
				console.log("auth callback");
				usp.punya.loadApis(apiRoot);
			}).fail(function(res) {
				// show user consent screen.
				prms_gapi_auth(false).done(
						function(res) {
							console.log("auth callback on userconsent");
							usp.punya.loadApis(apiRoot);
						});
			});
};