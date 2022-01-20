function getAllRows() {
    $.ajax({
        url: '/customerlist/getAllCustomer',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
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
    console.log(data)
    let html = ``
    if (data.length < 1) {
        $('#error_message').show();
        $('#customer_control').hide();
    }
    for (let i = 0; i < data.length; i++) {
        const item = data[i];
        html += `<tr role="row" class="odd">
            <td>` + item.cu_id + `</td>
            <td>` + item.cu_name + `</td>
            <td>` + item.cu_surname + `</td>
            <td>` + item.cu_tel1 + `</td>
            <td>` + item.cu_mail + `</td>
            <td>` + item.cu_tcnumber + `</td>
            <td>` + item.customerGroup.cu_gr_name + `</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="basic mixed styles example">           
                <a href="http://localhost:8090/customerinfo/` + item.cu_id + `" type="button"  class="btn btn-outline-primary "><i class="far fa-eye"></i></a>
               </div>
            </td>
          </tr>`;
    }
    if (data.length > 0) {
        $('#error_message').hide();
        $('#customer_control').show();
        $('#tableCustomerList').html(html);
    }

}


$(document).on('keyup', '#searchCustomer', function (e) {
    let strSearch = $("#searchCustomer").val();
    console.log(strSearch);
    if (strSearch.trim() != "") {
        getAllSearchList(strSearch);
    } else {
        getAllRows();
    }
})

function getAllSearchList(strSearch) {
    $.ajax({
        url: '/customerlist/search/' + strSearch,
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