let deptorCustomer = [];
// The maximum amount that the debtor can enter in the payment part
let maxNumber = 0;
// Selected Customer
let customerId;
$('#cu_id').change(function () {
    customerId = $("#cu_id option:selected").attr("value");
    console.log(customerId);
    if(customerId != 0){
        for (let i = 0; i < deptorCustomer.length; i++) {
            if(deptorCustomer[i].cuId == customerId){
                maxNumber = (deptorCustomer[i].totalPrice - deptorCustomer[i].totalPayment);
                $("#pin_price").attr('max', maxNumber);
                $("#pin_price").val(maxNumber);
                break;
            }
        }
    }else{
        maxNumber = 0;
        $("#pin_price").attr('max', maxNumber);
        $("#pin_price").val(maxNumber);
    }
})

$("#pin_price").attr('max', maxNumber);
$("#pin_price").val(maxNumber);

// Customers in debt
$('#modalPayIn').click(function (){
    $.ajax({
        url: '/payment/debtorCustomer',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data){
            deptorCustomer = data;
            if(data != null){
                console.log(data);
                appendCustomerOptions(data);
            } else{

            }
        },
        error: function (err){
            console.log(err);
        }
    });
})

function appendCustomerOptions(data) {
    let html = `<option data-subtext="" value="0">Choose Customer</option>`;
    for (let i = 0; i < data.length; i++) {
        const item = data[i];
        html += `<option value="` + item.cuId + `" data-subtext="` + item.totalPrice + ` - `+item.totalPayment+`">` + item.cuName + `</option>`;
    }

    $('#cu_id').html(html);
    $('#cu_id').selectpicker("refresh");
}


$(document).ready(function () {
    $('[id^=pin_price]').keypress(validateNumber);
});


$(document).on('keyup', '#pin_price', function (e) {
    const value = $("#pin_price").val();
    if (value > maxNumber) {
        $("#pin_price").val(maxNumber);
    }
})

function validateNumber(event) {
    var key = window.event ? event.keyCode : event.which;
    if (event.keyCode === 8 || event.keyCode === 46) {
        return true;
    } else if (key < 48 || key > 57) {
        return false;
    } else {
        return true;
    }
};

//******PayOut***********

$('#modalPayOut').click(function (){
    $.ajax({
        url: '/payment/getPayOutInfo',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data){
            if(data != null){
                console.log(data);
                maxPayOutNumber = data.totalPayIn - data.totalPayOut;
                $("#pout_price").attr('max', maxPayOutNumber);
                $("#pout_price").val(maxPayOutNumber);

            } else{
                console.log("Error has occurred.")
            }
        },
        error: function (err){
            console.log(err);
        }
    });
})

$(document).ready(function () {
    $('[id^=pout_price]').keypress(validateNumber);
});

$(document).on('keyup', '#pout_price', function (e) {
    const value = $("#pout_price").val();
    if (value > maxPayOutNumber) {
        $("#pout_price").val(maxPayOutNumber);
    }
})

if ($('#modalPayOut').val() == "true") {
    $('#modalPayOut').click();
}

if ($('#modalPayIn').val() == "true") {
    $('#modalPayIn').click();
}

function deletePay(index, type) {
    if (confirm("Are you sure you want to delete?")) {
        $.ajax({
            url: '/payment/delete/' + type + '/' + index,
            type: 'GET',
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                if (data == true) {
                    getAllRows();
                } else {
                    alert("The information to be deleted does not exist")
                }
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
}

/* payment search start*/
$(document).on('keyup', '#pay_search', function (e) {
    let strSearch = $("#pay_search").val();
    console.log(strSearch);
    if (strSearch.trim() != "") {
        getAllSearchList(strSearch);
    } else {
        getAllRows();
    }
})

function getAllSearchList(strSearch) {
    $.ajax({
        url: '/payment/searchlist/'+ payment_process + '/' + strSearch,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data != null) {
                rows(data);
            } else {
                alert("The search result is empty.")
                rows(null);
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}
/* payment search finish*/

let payment_process = 2;
$('#all_process').click(function (){
    payment_process = 2;
    if ($('#all_process').hasClass('active')) {
        $("#pay_search").val("")
        $("#in_process").removeClass("active");
        $("#out_process").removeClass("active");
        getAllRows();

    }else{
        $('#all_process').addClass("active");
    }
})
$('#in_process').click(function (){
    console.log("login clicked")
    payment_process = 0;
    if ($('#in_process').hasClass('active')) {
        $("#pay_search").val("")
        $("#all_process").removeClass("active");
        $("#out_process").removeClass("active");
        getAllRows();
    }else{
        $('#in_process').addClass("active");
    }
})
$('#out_process').click(function (){
    payment_process = 1;
    if ($('#out_process').hasClass('active')) {
        $("#pay_search").val("")
        $("#all_process").removeClass("active");
        $("#in_process").removeClass("active");
        getAllRows();
    }else{
        $('#out_process').addClass("active");
    }
})


// getAll
function getAllRows() {
    $.ajax({
        url: '/payment/getPayAllInfo/' + payment_process,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            debugger;
            if (data != null) {
                rows(data);
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}
getAllRows();


function rows(data) {
    debugger;
    console.log(data)
    let html = ``
    if(data.length<1){
        $('#error_message').show();
        $('#payment_control').hide();
    }
    for (let i = 0; i < data.length; i++) {
        const item = data[i];
        let processType = item.processType == 0 ? 'Checkout Login' : 'Cash Out';
        let paymentType = item.paymentType == 1 ? 'Cash' : item.paymentType == 2 ? 'Card' : 'Transfer';
        html += `<tr role="row" class="odd">
            <td>`+item.cuName+`</td>
            <td>`+processType+`</td>
            <td>`+paymentType+`</td>
            <td>`+item.payPrice+`</td>
            <td>`+item.payDetail+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="basic mixed styles example">
                <button onclick="deletePay(`+item.id+`,`+item.processType+`)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
               </div>
            </td>
          </tr>`;
    }
    if(data.length>0){
        $('#error_message').hide();
        $('#payment_control').show();
        $('#tableRows').html(html);
    }
}
