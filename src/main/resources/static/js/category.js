function getAllRows() {
    $.ajax({
        url: '/category/getAllCategory',
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
        $('#category_control').hide();
    }
    for (let i = 0; i < data.length; i++) {
        const item = data[i];
        html += `<tr role="row" class="odd">
            <td>` + item.category_id + `</td>
            <td>` + item.category_title + `</td>
            <td>` + item.category_detail + `</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="basic mixed styles example">
                <button onclick="deleteCategory(` + item.category_id + `)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="updateCategory(` + item.category_id + `)" type="button" class="btn btn-outline-primary "><i class="far fa-edit"></i></button>
               </div>
            </td>
          </tr>`;
    }
    if (data.length > 0) {
        $('#error_message').hide();
        $('#category_control').show();
        $('#tableRows').html(html);
    }

}

function deleteCategory(index) {
    if (confirm("Are you sure you want to delete?")) {
        $.ajax({
            url: '/category/delete/' + index,
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

function updateCategory(index) {
    $.ajax({
        url: '/category/update/' + index,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            debugger;
            if (data[0] == true) {
                $('#category_title').val(data[1].category_title);
                $('#category_detail').val(data[1].category_detail);
                $('#category_updateBtn').click();
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

$("#category_updateBtn").click(function () {
    if (proControl != null) {
        processType(2)
        process = 2;
    } else {
        processType(3)
        process = 3;
    }
});

$("#category_insertBtn").click(function () {
    if (proControl != null) {
        $('#category_name').val("");
        processType(1)
        process = 1;
    } else {
        processType(3)
        process = 3;
    }
});

function processType(processType) {
    $.ajax({
        url: '/category/processtype/' + processType,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
        },
        error: function (err) {
        }
    })
}

let proControl = true;
if ($('#category_insertBtn').val() == "true") {
    console.log(process)
    if (process == null) {
        proControl = process;
    }

    if (process == 1) {
        $('#category_insertBtn').click();
    } else {
        $('#category_updateBtn').click();
    }

}


/* category search start*/
$(document).on('keyup', '#category_search', function (e) {
    let strSearch = $("#category_search").val();
    console.log(strSearch);
    if (strSearch.trim() != "") {
        getAllSearchList(strSearch);
    } else {
        getAllRows();
    }
})

function getAllSearchList(strSearch) {
    $.ajax({
        url: '/category/search/' + strSearch,
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

/* category search finish*/