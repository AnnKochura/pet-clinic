//CUSTOMER
let cityId;
let districtId;

$('#cu_cities').change(function () {
    cityId = $("#cu_cities option:selected").attr("value");
    getXDistrict(cityId);
})

$('#cu_districts').change(function () {
    districtId = $("#cu_districts option:selected").attr("value");
})

$(document).on('keyup', 'input', function () {
    if (this.id != "cu_mail") {
        this.value = this.value.toLocaleUpperCase();
    }
});


function getXDistrict(index) {
    $.ajax({
        url: './customer/getDistrict/' + index,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data) {
                console.log(data)
                addDistrictSelect(data);
            } else {
                console.log("The county registered to the selected city number could not be found.")
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

addDistrictSelect("");

function addDistrictSelect(data) {

    let html = `<option data-subtext="">Make Your Choice</option>`;
    if (data != null) {
        for (let i = 0; i < data.length; i++) {
            const item = data[i];
            html += `<option data-subtext=` + item.did + ` value=` + item.did + `>` + item.dname + `</option>`;
        }
    }
    console.log(html);
    $('#cu_districts').html(html);
    $("#cu_districts").selectpicker('refresh');
}

//----------------------------------------------------------------------------------------------------------------------
//HAYVAN

let PetList = [];
let petTypes = []
let petColors = []

function PetInfo() {
    this.name = null;
    this.chipNumber = null;
    this.earTag = null;
    this.bornDate = null;
    this.type = null;
    this.neutering = null;
    this.breed = null;
    this.color = null;
    this.gender = null;
}

function addPet(pet) {
    PetList.push(pet)
    console.log("addPet --> ", JSON.stringify(pet))
}

let petLength = 0;
let n = 0;

let dateNow = new Date().toLocaleDateString();

let date = dateNow.split(".");

let now = date[2] + "-" + date[1] + "-" + date[0];


$('#btnAdd').click(function () {
    const newForm = `<div class="row" id="form` + n + `">
   
            <form id="` + n + `" class="row p-4">
           <div class="row">
            <div class="col-sm-1">
            <div class="col-sm-4"></div>
            <div class="col-sm-4"><button onclick="removeElement(` + n + `)" class="btn btn-danger">Remove</button></div>
            <div class="col-sm-4"></div></div>
            
            <div class="col-sm-10">
            <div class="row">
              <div class="col-md-4 mb-3">
              <div>
                <label for="` + n + `_name" class="form-label">Patient name</label>
              </div>
                <input type="text"  minlength="1" maxlength="20" name="` + n + `_name" id="` + n + `_name" class="form-control" />
              </div>
   
             <div class="col-md-4 mb-3">
             <div>
                <label for="` + n + `_chipNumber" class="form-label">Chip Number</label></div>
                <input type="number" name="` + n + `_chipNumber" id="` + n + `_chipNumber" class="form-control" minlength="1" maxlength="20" />
              </div>
              
               <div class="col-md-4 mb-3">
               <div>
                <label for="` + n + `_earTag" class="form-label">Report Card / Earring Number</label></div>
                <input type="number" name="` + n + `_earTag" id="` + n + `_earTag"  minlength="1" maxlength="20" class="form-control" />
              </div>
              
                 <div class="col-md-4 mb-3">
                 <div>
                <label for="` + n + `_bornDate" class="form-label">Date of birth</label></div>
                <input value="` + now + `" type="date" name="` + n + `_bornDate" id="` + n + `_bornDate" class="form-control" />
              </div>
              
              <div class="col-md-4 mb-3">
              <div>
                    <label for="` + n + `_type" class="form-label">Type</label>
                </div>
                <select class="selectpicker" name="` + n + `_type" id="` + n + `_type"
                                    data-width="100%"
                    data-show-subtext="true"
                    data-live-search="true">
                <option data-subtext="">Make Your Choice</option>
                </select>
              </div>
              
              <div class="col-md-4 mb-3">
              <div><label for="` + n + `_color" class="form-label">Colour</label></div>
                <select class="selectpicker" name="` + n + `_color" id="` + n + `_color" 
                    data-width="100%"
                    data-show-subtext="true"
                    data-live-search="true">
                <option data-subtext="">Make Your Choice</option>
                </select>
              </div>
              
             <div class="col-md-4 mb-3">
                <div>
                   <label for="` + n + `_breed" class="form-label">Race</label>
                </div>
                
                <select class="selectpicker" name="` + n + `_breed" id="` + n + `_breed"
                    data-width="100%"
                    data-show-subtext="true"
                    data-live-search="true">
                     <option data-subtext="">Make Your Choice</option>
                </select>
             </div>
              
              
              <div class="col-md-4 mb-3">
              <div>
                <label  class="form-label">Gender</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"
                        name="` + n + `_gender" id="` + n + `_gender1" value="1" checked>
                    <label class="form-check-label" for="` + n + `_gender1">Male</label>
                </div>
                
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"
                        name="` + n + `_gender" id="` + n + `_gender2" value="0" checked>
                    <label class="form-check-label" for="` + n + `_gender2">Female</label>
                </div>
             </div>

              <div class="col-md-4 mb-3">
              <div>
                <label  class="form-label">Is barren</label></div>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" name="` + n + `_neutering" id="` + n + `_neutering">
                  <label class="form-check-label" for="` + n + `_neutering">
                  neutered
                  </label>
                </div>
              </div>
              <hr>
 
          </div>
      </div>
            </div>
        </form>`;

    $('#addForms').append(newForm);
    if (petColors.length == 0) {
        getColors(n);
    } else {
        addColorPetSelect(n);
    }

    if (petTypes.length == 0) {
        getTypes(n);
    } else {
        addTypePetSelect(n);
    }

    getBreeds(0, n);


    petLength++;
    n++;
});

$('#btnAdd').click();

let costumerObj;

$('#btnSubmitSend').click(function () {

    let element = 0;

    const cu_name = $('#cu_name').val();
    const cu_surname = $('#cu_surname').val();
    const cu_tel1 = $('#cu_tel1').val();
    const cu_tel2 = $('#cu_tel2').val();
    const cu_mail = $('#cu_mail').val();
    const cu_taxname = $('#cu_taxname').val();
    const cu_note = $('#cu_note').val();
    const cu_tcnumber = $('#cu_tcnumber').val();
    const cu_cities = $('#cu_cities').val();
    const cu_districts = $('#cu_districts').val();
    const cu_group = $('#cu_group').val();
    const cu_address = $('#cu_address').val();
    const cu_rateOfDiscount = $('#cu_rateOfDiscount').val();
    const cu_smsNotice = $('#cu_smsNotice').val();
    const cu_mailNotice = $('#cu_mailNotice').val();

    customerObj = {
        cu_name: cu_name,
        cu_surname: cu_surname,
        cu_tel1: cu_tel1,
        cu_tel2: cu_tel2,
        cu_mail: cu_mail,
        cu_taxname: cu_taxname,
        cu_note: cu_note,
        cu_tcnumber: cu_tcnumber,
        cu_cities: cu_cities,
        cu_districts: cu_districts,
        cu_group: cu_group,
        cu_address: cu_address,
        cu_rateOfDiscount: cu_rateOfDiscount,
        cu_smsNotice: cu_smsNotice,
        cu_mailNotice: cu_mailNotice
    }

    for (let j = 1; j <= petLength; j++) {
        element = $(`div[id=addForms] > div:nth-child(${j}) > form`).attr('id');
        const name = $("#" + element + "_name").val();
        const chipNumber = $("#" + element + "_chipNumber").val();
        const earTag = $("#" + element + "_earTag").val();
        const bornDate = $("#" + element + "_bornDate").val();
        const type = $("#" + element + "_type").val() != "Make Your Choice" ? $("#" + element + "_type").val().split("_")[0] : "0";
        const neutering = $("#" + element + "_neutering").val();
        const breed = $("#" + element + "_breed").val() != "Make Your Choice" ? $("#" + element + "_breed").val().split("_")[0] : "0";
        const color = $("#" + element + "_color").val() != "Make Your Choice" ? $("#" + element + "_color").val().split("_")[0] : "0";
        const gender = document.querySelector(`input[name="${element}_gender"]:checked`).value;

        pet = new PetInfo();
        pet.name = name;
        pet.chipNumber = chipNumber;
        pet.earTag = earTag;
        pet.bornDate = bornDate;
        pet.type = type;
        pet.neutering = neutering;
        pet.breed = breed;
        pet.color = color;
        pet.gender = gender;

        addPet(pet);
    }

    let obj = {
        customerObj: customerObj,
        petList: PetList
    };
    $.ajax({
        url: './customer/add',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',

        success: function (data) {
            if (data == true) {
                window.location = "http://localhost:8090/customerlist";
            } else {
                PetList = [];
                alert("Customer entries did not pass validation.");
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
});

function removeElement(index) {
    $("#form" + index).remove();
    petLength--;
}

function getColors(a) {
    $.ajax({
        url: './customer/getColors/',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data) {
                console.log(data)
                petColors = data;
                addColorPetSelect(a);
            } else {
                console.log("Failed to retrieve colors from database or color table is empty.")
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}


function getTypes(a) {
    $.ajax({
        url: './customer/getTypes/',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data) {
                console.log(data)
                petTypes = data;
                addTypePetSelect(a);
            } else {
                console.log("Failed to get types from database or types table is empty.")
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function getBreeds(index, id) {
    $.ajax({
        url: './customer/getBreeds/' + index,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data) {
                addBreedPetSelect(id, data);
            } else {
                console.log("No race found for the selected species.")
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

$(document).on('change', 'select', function () {
    let key = this.value;
    let index = this.id;
    console.log(key)
    if (key.split("_")[1] == "type") {
        let type_id = key.split("_")[0];
        let index_id = index.split("_")[0];
        console.log("entry....")
        getBreeds(type_id, index_id);
    }
});


function addTypePetSelect(a) {
    data = petTypes;
    console.log(data);
    let html = `<option data-subtext="">Make Your Choice</option>`;
    if (data != null) {
        for (let i = 0; i < data.length; i++) {
            const item = data[i];
            html += `<option data-subtext=` + item.ty_id + ` value=` + item.ty_id + `_type>` + item.ty_name + `</option>`;
        }
    }
    console.log(html)
    $("#" + a + "_type").html(html);
    $("#" + a + "_type").selectpicker('refresh');
}

function addBreedPetSelect(a, data) {

    console.log(data);
    let html = `<option data-subtext="">Make Your Choice</option>`;
    if (data != null) {
        for (let i = 0; i < data.length; i++) {
            const item = data[i];
            html += `<option data-subtext=` + item.br_id + ` value=` + item.br_id + `_breed>` + item.br_name + `</option>`;
        }
    }
    console.log(html)
    console.log(a);
    $("#" + a + "_breed").html(html);
    $("#" + a + "_breed").selectpicker('refresh');
}


function addColorPetSelect(a) {
    data = petColors;
    console.log(data);
    let html = `<option data-subtext="">Make Your Choice</option>`;
    if (data != null) {
        for (let i = 0; i < data.length; i++) {
            const item = data[i];
            html += `<option data-subtext=` + item.color_id + ` value=` + item.color_id + `_color>` + item.color_name + `</option>`;
        }
    }
    console.log(html)
    $("#" + a + "_color").html(html);
    $("#" + a + "_color").selectpicker('refresh');
}
