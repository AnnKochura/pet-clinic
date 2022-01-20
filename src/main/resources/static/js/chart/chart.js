let ls = [];
let lsName = [];
let lsNumber = [];
let petTypeKey = [];
let petTypeValue = [];

function getCustomerRegisterDayOfWeeks() {
    $.ajax({
        url: '/dashboard/dayOfWeeks/',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data != null) {
                for (let i = 0; i<7; i++){
                    ls[i] = data[i];
                }
            }
            deneme();
        },
        error: function (err) {
            console.log(err)
        }
    })
}
getCustomerRegisterDayOfWeeks();


function getAllPetTypeCount() {
    $.ajax({
        url: '/dashboard/petTypeCount/',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            debugger;
            if (data != null) {
                petTypeKey = Object.keys(data);
                petTypeValue = Object.values(data);
            }
            debugger;
            deneme2();
        },
        error: function (err) {
            console.log(err)
        }
    })
}
getAllPetTypeCount();


function deneme() {
    // Line chart
    new Chart(document.getElementById("chartjs-line"), {
        type: "line",
        data: {
            labels: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
            datasets: [{
                label: "The number of customers",
                fill: true,
                backgroundColor: "transparent",
                borderColor: window.theme.primary,
                data: [ls[0], ls[1], ls[2], ls[3], ls[4], ls[5], ls[6]]
            }]
        },
        options: {
            maintainAspectRatio: false,
            legend: {
                display: false
            },
            tooltips: {
                intersect: false
            },
            hover: {
                intersect: true
            },
            plugins: {
                filler: {
                    propagate: false
                }
            },
            scales: {
                xAxes: [{
                    reverse: true,
                    gridLines: {
                        color: "rgba(0,0,0,0.05)"
                    }
                }],
                yAxes: [{
                    ticks: {
                        stepSize: 500
                    },
                    display: true,
                    borderDash: [5, 5],
                    gridLines: {
                        color: "rgba(0,0,0,0)",
                        fontColor: "#fff"
                    }
                }]
            }
        }
    });
}


function deneme2() {
    // Polar Area chart
    new Chart(document.getElementById("chartjs-polar-area"), {
        type: "polarArea",
        data: {
            labels: petTypeKey,
            datasets: [{
                label: "Model S",
                data: petTypeValue,
                backgroundColor: [
                    window.theme.primary,
                    window.theme.success,
                    window.theme.danger,
                    window.theme.warning,
                    window.theme.info,
                    window.theme.secondary,
                    window.theme.temp1,
                    window.theme.temp2,
                ]
            }]
        },
        options: {
            maintainAspectRatio: false
        }
    });
}