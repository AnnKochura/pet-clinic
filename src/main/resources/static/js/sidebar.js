$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
});


$('ul li a').on('click', function(){
    $('ul li a').removeClass('selected');
    $(this).addClass('selected');
});