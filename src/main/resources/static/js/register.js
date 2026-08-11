$(document).ready(function () {
    $('#register-form').submit(function(e) {
        e.preventDefault();

        let formData = {
            fullName: $('#fullName').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            phone: $('#phone').val()
        };

        $.ajax({
            url: '/users/register', // Your Spring Boot POST endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
            	alert("User registered successfully!");

            	//$('#message').text('User registered successfully! Username: ' + response.fullName).removeClass('text-danger').addClass('text-success');
            },
            error: function(err) {
            	alert("Error registering user.");
                //$('#message').text('Error registering user.').removeClass('text-success').addClass('text-danger');
            }
        });
    });
});