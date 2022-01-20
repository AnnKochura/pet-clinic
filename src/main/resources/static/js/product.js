function getAllRows() {
    $.ajax({
        url: '/product/getAllProduct',
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
        $('#product_control').hide();
    }
    for (let i = 0; i < data.length; i++) {
        const item = data[i];
        debugger;
        let statu = item.product_statu == '1' ? 'Active' : 'Passive';
        html += `<tr role="row" class="odd">
            <td>` + item.product_id + `</td>
            <td>` + item.product_name + `</td>
            <td>` + item.product_unit + `</td>
            <td>` + item.category.category_title + `</td>
            <td>` + item.product_kdv + `</td>
            <td>` + item.product_alis + `</td>
            <td>` + item.product_satis + `</td>
            <td>` + item.product_stokMiktari + `</td>
            <td>` + statu + `</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="basic mixed styles example">
                <button onclick="deleteProduct(` + item.product_id + `)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="updateProduct(` + item.product_id + `)" type="button" class="btn btn-outline-primary "><i class="far fa-edit"></i></button>
               </div>
            </td>
          </tr>`;
    }
    if (data.length > 0) {
        $('#error_message').hide();
        $('#product_control').show();
        $('#tableRows').html(html);
    }

}

function updateProduct(index) {
    $.ajax({
        url: '/product/update/' + index,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data[0] == true) {
                $('#product_name').val(data[1].product_name);
                $('#product_unit').val(data[1].product_unit);
                $('#product_unit').selectpicker("refresh");
                $('#category').val(data[1].category);
                $('#category').selectpicker("refresh");
                $('#product_kdv').val(data[1].product_kdv);
                $('#product_kdv').selectpicker("refresh");
                $('#product_alis').val(data[1].product_alis);
                $('#product_satis').val(data[1].product_satis);
                $('#product_stokMiktari').val(data[1].product_stokMiktari);
                if (data[1].product_statu == "1") {
                    $("#product_statu").prop("checked", true)
                } else {
                    $("#product_statu").prop("checked", false)
                }
                $('#product_updateBtn').click();
            } else {
                alert("The field to be edited does not exist")
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

let process;

$("#product_updateBtn").click(function () {
    if (proControl != null) {
        processType(2)
        process = 2;
    } else {
        processType(3)
        process = 3;
    }
});


$(document).on('click', '#modalButton', function (e) {

    if (proControl != null) {
        $('#product_name').val("");
        processType(1)
        process = 1;
    } else {
        processType(3)
        process = 3;
    }
});

let proControl = true;
if ($('#modalButton').val() == "true") {
    console.log(process)
    if (process == null) {
        proControl = process;
    }

    if (process == 1) {
        $('#modalButton').click();
    } else {
        $('#product_updateBtn').click();
    }

}


$(document).on('keyup', '#searchProduct', function (e) {
    let strSearch = $("#searchProduct").val();
    console.log(strSearch);
    if (strSearch.trim() != "") {
        getAllSearchList(strSearch);
    } else {
        getAllRows();
    }
})

function getAllSearchList(strSearch) {
    $.ajax({
        url: '/product/search/' + strSearch,
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


function deleteProduct(index) {
    if (confirm("Are you sure you want to delete?")) {
        $.ajax({
            url: '/product/delete/' + index,
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

function processType(processType) {
    $.ajax({
        url: '/product/processtype/' + processType,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
        },
        error: function (err) {
        }
    })
}

