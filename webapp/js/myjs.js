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


$(document).on("pageinit", "#page_report", function() {
    console.log("pageinit......page_report");
    $('#page_report #btn_add_devotee').attr("my_counter", "0");
    $('#page_report #btn_add_currency').attr("my_counter", "1");
    $('#page_report #list_selected_books').attr("my_counter", "0");
    
    
    $('#page_report .my-book-item').click(function() {
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

$(document).on("pageinit", "#home", function() {
    $('.my-book-item').click(function() {
        storeObject.bookId = $(this).data('id');
        console.log(storeObject.bookId);
        if (storeObject.isSignedIn) {
            $.mobile.changePage("#page_book_details");
        }
    });
});


//Update cart
$(document).on("pageinit","#page_book_details", function() {
    $(".add_to_cart").click(function() {
        console.log("hello usp");
        var orderUnits = $("#page_book_details .order_units").val();
        loading('show'); 
        $.post("AddToCart",
                {
            "book_id": storeObject.bookId,
            "order_units" : orderUnits
                },
                function(data) {
                    console.log(data);
                    loading('hide'); 
                    if (data.indexOf("http://") === 0
                            || data.indexOf("https://") === 0
                            || data.indexOf("/") === 0) {
                        $('#page_signin .signin-link').html("<a href='" + data + "' data-ajax='false'> Sign in </a>");
                        $.mobile.changePage('#page_signin');
                    } else {
                        window.history.back();
                    }
                });
    });
});

$(document).on("pagebeforeshow", "#page_book_details", function(event) {
    console.log("pagebeforeshow");
    loading('show'); 
    $.post("ViewCart",
            {"book_id": storeObject.bookId,},
            function(data) {
                console.log(data);
                $.each(data, function( index, value ) {
                    $("#page_book_details .book_id").text("");
                    $("#page_book_details .book_title").html(value.book.title);
                    $("#page_book_details .book_author").html("by " + value.book.author);
                    $("#page_book_details .available_units").html("Available Units : " + value.book.avaibaleUnits);
                    $("#page_book_details .order_units").val(value.units);
                });
                loading('hide'); 
            });
})

$(document).on("pageinit", "#page_view_cart", function() {
    $('#page_view_cart .cart-item').click(function() {
        storeObject.bookId = $(this).data('id');
        console.log(storeObject.bookId);
        $.mobile.changePage("#page_book_details");
    });


    $('#page_view_cart .place_order').click(function() {
        console.log("Place order");
        loading('show'); 
        $.post("PlaceOrder",
                function(data) {
            console.log(data);
            loading('hide'); 
            $.mobile.changePage("#home");
        });

    });
});

$(document).on("pagebeforeshow", "#page_view_cart", function(event) {
    console.log("pagebeforeshow");
    $('#page_view_cart .list_view').empty();
    loading('show'); 
    $.post("ViewCart",
            {"book_id": "",},
            function(data) {
                console.log(data);
                var content = '';

                $.each(data, function( index, value ) {
                    content += "<li class='cart-item' data-id='"+ value.bookKey +"'><a href='#'><h3>" + value.book.title+"</h3>"
                    + "<p id='no-ellipsis'>by " + value.book.author + " (Author).</p>"
                    + "Units Ordered : <div class='ui-input-text ui-body-c ui-corner-all ui-shadow-inset'>"
                    + "<input type='number' name='number' pattern='[0-9]* id='number-pattern' value='"
                    + value.units + "'  /></div></a> </li>";
                });
                $('#page_view_cart .list_view').append(content);
                $('#page_view_cart .list_view').listview('refresh');

                $('#page_view_cart .cart-item').click(function() {
                    storeObject.bookId = $(this).data('id');
                    console.log(storeObject.bookId);
                    $.mobile.changePage("#page_book_details");
                });
                loading('hide');
            });
});


$(document).on("pagebeforeshow", "#page_view_orders", function(event) {
    console.log("pagebeforeshow");
    $('#page_view_orders .list_view').empty();
    storeObject.viewOrderCursor = '';
    populateViewOrders();
});

function populateViewOrders() {
    loading('show'); 
    $(document).off("scrollstop");
    $.post("ViewOrder",
            {"cursor": storeObject.viewOrderCursor,},
            function(data) {
                console.log(data);
                storeObject.viewOrderCursor = data.cursor;
                var content = '';
                $.each(data.data, function( index, value ) {
                    status = '';
                    if (value.approvalStatus == 0) {
                        status = "<font color='red'>Pending</font>";
                    } else if (value.approvalStatus == 1) {
                        status = "<font color='green'>Approved</font>";
                    }
                    units = '';
                    if (value.units < 0) {
                        units = "<font color='yellow'>" + value.units + "</font>"
                    } else {
                        units = "<font color='red'>" + value.units + "</font>"
                    }
                    content += "<li><a href='#'><h3>" + value.book.title + "</h3>"
                    + "<p id='no-ellipsis'>by "+  value.book.author + ".</p>"
                    + "<p id='no-ellipsis'>Order date : " + new Date(value.date).toString() + "</p>"
                    + "<p id='no-ellipsis'>Units ordered : " + units + "</p>"
                    + "<h4>Status : " + status +"</h4>"
                    + "</a></li>";
                });
                $('#page_view_orders .list_view').append(content);
                $('#page_view_orders .list_view').listview('refresh');
                loading('hide'); 


//              var items = '',
//              last = 10;
//              cont = last + 5;
//              for (var i = last; i < 50; i++) {
//              items += "<li>" + i + "</li>";
//              }
//              $("#page_view_orders .list_view").append(items).listview("refresh");

                $(document).on("scrollstop", checkScroll);
            });
};


///////********* Admin controls ********////////////

$(document).on("pagebeforeshow", "#page_admin_orders", function(event) {
    console.log("pagebeforeshow");
    $('#page_admin_orders .list_view').empty();
    storeObject.viewOrderCursor = '';
    fetchAllOrders();
});



function addAdminEventHandlers() {
    $('.order-accept').click(function() {
        adminUpdateOrders($(this).data('id'), true);
    });

    $('.order-reject').click(function() {
        adminUpdateOrders($(this).data('id'), false);
    });

}

function fetchAllOrders() {
    loading('show'); 
    $(document).off("scrollstop");
    $.post("AdminViewOrders",
            {"cursor": storeObject.viewOrderCursor,},
            function(data) {
                console.log(data);
                storeObject.viewOrderCursor = data.cursor;
                var content = '';
                $.each(data.orders, function( index, value ) {
                    status = '';
                    if (value.approvalStatus == 0) {
                        status = "<font color='red'>Pending</font>";
                    } else if (value.approvalStatus == 1) {
                        status = "<font color='green'>Approved</font>";
                    }

                    units = '';
                    if (value.units > 0) {
                        units = "<font color='yellow'>" + value.units + "</font>"
                    } else {
                        units = "<font color='red'>" + value.units + "</font>"
                    }


                    content += "<li><h3>" + value.book.title + "</h3>"
                    + "<p id='no-ellipsis'>by "+  value.book.author + ".</p>"
                    + "<p id='no-ellipsis'>Order date : " + new Date(value.date).toString("MMM dd") + "</p>"
                    + "<p id='no-ellipsis'>Units ordered : " + units + "</p>"

                    + "<p id='no-ellipsis'>Requester : " + value.requesterEmail + "</p>"
                    + "<h4>Status : " + status +"</h4>"

                    + "<div data-role='controlgroup' data-type='horizontal' class='my-controlgroup'>" 
                    +       "<a href='#' data-role='button' data-icon='arrow-u' class='order-accept' data-id='"+ value.id +"'>Accept</a>"
                    +       "<a href='#' data-role='button' data-icon='arrow-d' class='order-reject' data-id='"+ value.id +"'>Reject</a>"
                    + "</div>"
                    + "</li>";
                });
                $('#page_admin_orders .list_view').append(content);
                $('#page_admin_orders .list_view').listview('refresh');


                $("#page_admin_orders .list_view").trigger('create');

                addAdminEventHandlers();

                loading('hide'); 
                $(document).on("scrollstop", checkScroll);
            });
}

function adminUpdateOrders(orderId, isAccept) {
    console.log("admin update orders");
    loading('show'); 
    $.post("AdminUpdateOrders",
            {
        "order_id": orderId,
        "is_accept" : isAccept
            },
            function(data) {
                console.log(data);
                loading('hide'); 
                if (data.indexOf("http://") === 0
                        || data.indexOf("https://") === 0
                        || data.indexOf("/") === 0) {
                    $('#page_signin .signin-link').html("<a href='" + data + "' data-ajax='false'> Sign in </a>");
                    $.mobile.changePage('#page_signin');
                } else {
                    // Page refresh
                    refreshPage();
                }
            });
}
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
