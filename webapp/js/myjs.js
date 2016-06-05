/**
 * 
 */

//Get book details.

//Store object
var storeObject = {
		isSignedIn : false,
		bookId : '',
		viewOrderCursor :'',
}

var CURRENCY = ["AUD", "INR"]
function loading(showOrHide) {
	setTimeout(function(){
		$.mobile.loading(showOrHide);
	}, 1); 
}

String.format = function() {
    // The string containing the format items (e.g. "{0}")
    // will and always has to be the first argument.
    var theString = arguments[0];
    
    // start with the second argument (i = 1)
    for (var i = 1; i < arguments.length; i++) {
        // "gm" = RegEx options for Global search (more than one instance)
        // and for Multiline search
        var regEx = new RegExp("\\{" + (i - 1) + "\\}", "gm");
        theString = theString.replace(regEx, arguments[i]);
    }
    
    return theString;
}

$(document).on("pageinit", "#page_loading", function() {
	console.log("pageinit......page_loading");
	loading('show');
});



$(document).on("pageinit", "#page_report", function() {
	console.log("pageinit......page_report");
	$('#page_report #btn_add_devotee').attr("my_counter", "0");
	$('#page_report #btn_add_currency').attr("my_counter", "1");
	$('#page_report #list_selected_books').attr("my_counter", "0");
	updateBookOptions = function() {
		bookOptions = "";
		console.log("uma");
		usp.punya.listBooks().done(function(books){
			for (var i = 0; i < books.length; i++) {
				bookOptions = bookOptions +
						String.format('<li><a href="#" class="my-book-item" data-rel="dialog"'
								+ 'data-id="{0}" data-title="{1}">{1}</a></li>',
								books[i].id, books[i].title);
			}
			$('#page_report #list_books').empty().append(bookOptions).listview('refresh');
		});
	};
	
	updateBookOptions();
	$('#page_report').on('click', '.my-book-item', function() {
		my_counter=$('#page_report #list_selected_books').attr("my_counter");
		console.log(my_counter);
		bookId = $(this).data('id');
		bookTitle = $(this).data('title');
		

		$('#page_report #list_selected_books').append(
				'<li id="book_id_'+ bookId + '">'
				+ '<a href="#" > <label>'+ bookTitle +'</label>'
				+ '<input type="number" value="0">'
				+ '</a>'
				+ '<a href="#" class="delete" data-id="'+ bookId + '">Delete</a>'
				+ '</li>');
		$('#page_report #list_selected_books').listview('refresh');
		updateBookOptions();
		$('input').textinput(); 

		// clear the search filter
		$('input[data-type="search"]').val("");
		$('input[data-type="search"]').trigger("keyup");
	});

			$('#page_report #list_selected_books').on('click', '.delete', function() {
				bookId = $(this).data('id');
				$('#page_report #list_selected_books #book_id_' + bookId).remove();
			});

			$('#page_report #btn_add_devotee').click(function() {
				my_counter=$('#page_report #btn_add_devotee').attr("my_counter");
				console.log(my_counter);
				$('#page_report #list_devotee').append(
						'<li id="devotee_name_' + my_counter + '">'
						+ '<a>'
						+ '<input type="text" id="devotee_name_' + my_counter + '">'
						+ '</a>'
						+ '<a href="#" class="delete" data-id="'+ my_counter +'">Delete</a>'
						+ '</li>');
				$('#page_report #list_devotee').listview('refresh');
				$('#page_report #devotee_name_' + my_counter).textinput();
				$('#page_report #btn_add_devotee').attr("my_counter", my_counter + 1);
			});

			$('#page_report #list_devotee').on('click', '.delete', function() {
				devoteeId = $(this).data('id');
				console.log(devoteeId);
				$('#page_report #list_devotee #devotee_name_' + devoteeId).remove();
			});


			// Add more currency
			$('#page_report #btn_add_currency').click(function() {
				my_counter=$('#page_report #btn_add_currency').attr("my_counter");
				console.log(my_counter);
				var options = "";
				for(var i=0, len=CURRENCY.length; i < len; i++) {
					options += '<option value="'+CURRENCY[i]+'">'+CURRENCY[i]+'</option>'
				}

				$('#page_report #list_currency').append(
						'<li id="currency_id_' + my_counter + '">'
						+ '<a>'
						+ '<div class="ui-grid-a">'
						+ '<div class="ui-block-a">'
						+ '<input type="number" value="0" id="currency_value_'+my_counter+'"/>'
						+ '</div>'
						+ '<div class="ui-block-b">'
						+ '<select id="currency_type_"'+ my_counter +'" data-theme="b">'
						+ options
						+ '</select>'
						+ '</div>'
						+ '</div> </a>'
						+ '<a href="#" class="currency_delete" data-id="'+ my_counter +'">Delete</a>'
						+ '</li>');
				$('#page_report #list_currency').listview('refresh');
				//$('input').textinput();
				$('#page_report #currency_value_'+my_counter).textinput();
				$('select').selectmenu();
				$('#page_report #btn_add_currency').attr("my_counter", parseInt(my_counter) + 1);
			});

			// Remove currency row
			$('#page_report #list_currency').on('click', '.currency_delete', function() {
				currencyId = $(this).data('id');
				console.log(currencyId);
				$('#page_report #list_currency #currency_id_' + currencyId).remove();
			});

});

//////////////////******Scrolling Infinite list*******//////////////////////////

	/* check scroll function */
	function checkScroll() {
		console.log("checkScroll");
		var activePage = $.mobile.pageContainer.pagecontainer("getActivePage"),
		screenHeight = $.mobile.getScreenHeight(),
		contentHeight = $(".ui-content", activePage).outerHeight(),
		header = $(".ui-header", activePage).outerHeight() - 1,
		scrolled = $(window).scrollTop(),
		footer = $(".ui-footer", activePage).outerHeight() - 1,
		scrollEnd = contentHeight - screenHeight + header + footer;
		if (scrolled >= scrollEnd) {
			console.log("adding...");
			addMore(activePage[0]);
		}
	}

	/* add more function */
	function addMore(page) {
		console.log(page.id);
		if (page.id === 'page_view_orders') {
			console.log("addMore...");
			populateViewOrders();
		}
		if (page.id === 'page_admin_orders') {
			console.log("addMore...");
			fetchAllOrders();
		}
	}

	function refreshPage() {
		$.mobile.changePage(
				window.location.href,
				{
					allowSamePageTransition : true,
					transition              : 'none',
					showLoadMsg             : false,
					reloadPage              : true
				}
		);
	}

	/* attach if scrollstop for first time */
	$(document).on("scrollstop", checkScroll);



	/**** AutoComplete ****/
	$("input[data-type='search']").keyup(function () {
		if ($(this).val() === '') {
			$(this).closest('form').next("[data-role=listview]").children().addClass('ui-screen-hidden');
		}
	});

	$('a.ui-input-clear').click(function () {
		$(this).closest('input').val('');
		$(this).closest('input').trigger('keyup');
	});

	$("li").click(function () {
		var text = $(this).find('.ui-link-inherit').text();
		$(this).closest('[data-role=listview]').prev('form').find('input').val(text);
		$(this).closest('[data-role=listview]').children().addClass('ui-screen-hidden');
	});
