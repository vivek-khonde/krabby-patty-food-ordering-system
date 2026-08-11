//===============================================
//SHOW ALL FOODS → ALSO SHOW CATEGORIES BACK
//===============================================
document.getElementById("show-all-foods").addEventListener("click", () => {
 renderFoods(allFoods);

 // Show categories section again
 document.getElementById("categories-wrapper").style.display = "grid";
});

//Category nav link
document.getElementById("nav-categories").addEventListener("click", function(e){
    e.preventDefault();

    // Show categories section
    document.getElementById("categories-wrapper").style.display = "block";

    // Hide foods section
    document.getElementById("foods-container").parentElement.style.display = "none";

    // Scroll to categories section
    document.getElementById("categories-wrapper").scrollIntoView({ behavior: "smooth" });
});

// Foods nav link
document.getElementById("nav-foods").addEventListener("click", function(e) {
    e.preventDefault();

    // Hide categories section
    document.getElementById("categories-wrapper").style.display = "none";

    // Show foods section
    document.getElementById("foods-container").parentElement.style.display = "block";

    // Render all foods
    renderFoods(allFoods);

    // Scroll to foods section
    document.getElementById("foods-container").parentElement.scrollIntoView({ behavior: "smooth" });
});