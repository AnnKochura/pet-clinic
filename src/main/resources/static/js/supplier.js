function getAllRows() {
    $.ajax({
        url: '/supplier/getAllSupplier',
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
    if(data.length<1){
        $('#error_message').show();
        $('#supplier_control').hide();
    }
    for (let i = 0; i < data.length; i++) {
        const item = data[i];
        let statu = item.supplier_statu == '1'?'Active':'Passive';
        debugger;
        html += `<tr role="row" class="odd">
            <td>`+item.supplier_id+`</td>
            <td>`+item.supplier_name+`</td>
            <td>`+item.supplier_mail+`</td>
            <td>`+item.supplier_tel+`</td>
            <td>`+statu+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="basic mixed styles example">
                <button onclick="deleteSupplier(`+item.supplier_id+`)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="updateSupplier(`+item.supplier_id+`)" type="button" class="btn btn-outline-primary "><i class="far fa-edit"></i></button>
               </div>
            </td>
          </tr>`;
    }
    if(data.length>0){
        $('#error_message').hide();
        $('#supplier_control').show();
        $('#tableRows').html(html);
    }

}

function deleteSupplier(index){
    if (confirm("Are you sure you want to delete?")) {
        $.ajax({
            url: '/supplier/delete/' + index,
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

function updateSupplier(index){
    $.ajax({
        url: '/supplier/update/' + index,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            debugger;
            if (data[0] == true) {
                $('#supplier_name').val(data[1].supplier_name);
                $('#supplier_mail').val(data[1].supplier_mail);
                $('#supplier_tel').val(data[1].supplier_tel);
                if(data[1].supplier_statu == "1"){
                    $("#supplier_statu").prop("checked", true)
                }else{
                    $("#supplier_statu").prop("checked", false)
                }
                $('#supplier_updateBtn').click();
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

$("#supplier_updateBtn").click(function() {
    if(proControl != null){
        processType(2)
        process = 2;
    }else{
        processType(3)
        process = 3;
    }
});

$("#supplier_insertBtn").click(function() {
    if(proControl != null){
        $('#supplier_name').val("");
        $('#supplier_mail').val("");
        $('#supplier_tel').val("");
        processType(1)
        process = 1;
    }else{
        processType(3)
        process = 3;
    }
});

function processType(processType){
    $.ajax({
        url: '/supplier/processtype/' + processType,
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
if ($('#supplier_insertBtn').val() == "true") {
    if(process == null){
        proControl = process;
    }

    if(process == 1){
        $('#supplier_insertBtn').click();
    }else{
        $('#supplier_updateBtn').click();
    }

}


/* supplier search start*/
$(document).on('keyup', '#supplier_search', function (e) {
    let strSearch = $("#supplier_search").val();
    console.log(strSearch);
    if (strSearch.trim() != "") {
        getAllSearchList(strSearch);
    } else {
        getAllRows();
    }
})

function getAllSearchList(strSearch) {
    $.ajax({
        url: '/supplier/search/' + strSearch,
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
/* supplier search finish*/