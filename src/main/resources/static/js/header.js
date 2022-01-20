$.ajax({
    url: '/profile/getPhoto',
    type: 'GET',
    contentType: "application/json",
    dataType: 'json',
    success: function (data) {
        if (data) {
            console.log("/uploads/userphotolist/" + data);
            if (data.profilePhoto == "anonim.jpg") {
                $('#profileImage').attr('src', "/images/profiles/anonim.jpg");
            } else {
                $('#profileImage').attr('src', "/uploads/userphotolist/" + data.profilePhoto);
            }
        } else {
            console.log("There is no photo in the database.");
        }
    },
    error: function (err) {
        console.log(err)
    }
})