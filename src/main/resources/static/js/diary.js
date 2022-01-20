if ($('#modalButton').val() == "true") {
    $('#modalButton').click();
}

$("#search").keyup(function () {

    const ara = $("#search").val()
    if (ara != "") {
        getAllRowsAfterSearching(ara);
    } else {
        getAllRows();
    }

})


function getAllRows() {
    $.ajax({
        url: '/diary/diaryList',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data != null) {
                rows(data);
            } else {
                alert("There is no agenda in the database yet.")
                rows(null);
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

getAllRows();

function getAllRowsAfterSearching(ara) {
    $.ajax({
        url: '/diary/diaryList/' + ara,
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


function rows(data) {
    console.log(data)
    let html = ``
    if (data.length < 1) {
        $('#error_message').show();
        $('#diary_control').hide();
    }
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        html += `<tr role="row" class="odd">
            <td>` + itm.diary_id + `</td>
            <td>` + itm.usname + `</td>
            <td>` + itm.cuname + `</td>
            <td>` + itm.diary_title + `</td>
            <td>` + itm.diary_description + `</td>
            <td>` + itm.diary_date + `</td>
            <td>` + itm.diary_time + `</td>
            <td>` + convertStatu(itm.diary_statu) + `</td>
         
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncdeleteDiary(` + itm.diary_id + `)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
               </div>
            </td>
          </tr>`;
    }
    if (data.length > 0) {
        $('#error_message').hide();
        $('#diary_control').show();
        $('#tableDiary').html(html);
    }
}

function fncdeleteDiary(diary_id) {
    if (confirm("Are you sure you want to delete?")) {
        $.ajax({
            url: '/diary/delete/' + diary_id,
            type: 'GET',
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                if (data == true) {
                    alert("Deleted.");
                    $("#search").keyup();
                } else {
                    alert("The product that was attempted to be deleted was not found in the database.")
                    $("#search").keyup;
                }
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
}

function convertStatu(vat) {
    if (vat == "true") {
        return "Active";
    } else {
        return "Passive";
    }
}
