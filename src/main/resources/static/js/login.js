// Handle login form submission
$(document).ready(function () {
    $('#login-form').submit(function(e) {
        e.preventDefault();

        let formData = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            url: '/users/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            

            success: function(response) {
            	alert("Login successful!");
                /* $('#message')
                    .text('Login successful!')
                    .removeClass('text-danger')
                    .addClass('text-success'); */

                // 1️⃣ Update the navbar immediately
                checkLoggedInUser();

                // 2️⃣ Redirect based on the user role after a short delay
                setTimeout(function() {
                    if (response.role === "ADMIN") {
                        window.location.href = "/admin-dashboard.html";  
                    } else {
                        window.location.href = "/";  
                    }
                }, 500); // Delay for the message to show
            },

            error: function(err) {
            	alert("Invalid email or password!");
                /* $('#message')
                    .text('Invalid email or password!')
                    .removeClass('text-success')
                    .addClass('text-danger'); */
            }
        });
    });
});