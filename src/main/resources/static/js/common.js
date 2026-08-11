$(document).ready(function () {
    // 1️⃣ Check if the user is logged in when the page loads
    checkLoggedInUser();

    // 2️⃣ Attach logout handler using event delegation to handle dynamic content
    $(document).on('click', '#logout-link', function(e) {
        e.preventDefault();
        logoutUser();
    });
});

// Function to check if the user is logged in
function checkLoggedInUser() {
    $.ajax({
        url: "/users/current",
        type: "GET",
        
        success: function(response) {
            // Hide login link, show user info
            $("#login-link").hide();
            $("#user-dropdown").show();
            $("#user-name").text(response.fullName || response.username);

            // Show the admin dashboard link only if the user is an admin
            if (response.role === "ADMIN") {
                $("#admin-dashboard").show();
            } else {
                $("#admin-dashboard").hide();
            }
        },
        error: function() {
            // If not logged in, reset navbar elements
            $("#login-link").show();
            $("#user-dropdown").hide();
            $("#admin-dashboard").hide();
        }
    });
}

// Logout function to handle logout and navbar reset
function logoutUser() {
    $.ajax({
        url: "/users/logout",
        type: "GET",
        
        success: function() {
            // Reset navbar elements on logout
            $("#login-link").show();
            $("#user-dropdown").hide();
            $("#admin-dashboard").hide();

            // Redirect to login page
            window.location.href = "/login-form.html";
        },
        error: function() {
            alert("Error logging out. Please try again.");
        }
    });
}
