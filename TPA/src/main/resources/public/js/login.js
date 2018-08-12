$(document).ready( function() {

    $("#loginButton").on("click", function () {

        var password = $("#password").val();

        $("#password").val(md5(password));

    })

});