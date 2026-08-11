// submit form
$(document).ready(function() {
    $("#create-food-order-request-form").submit(function(e) {
        e.preventDefault();

        const cartData = (JSON.parse(localStorage.getItem("cart")) || []).map(item => ({
            foodId: item.id,
            qty: item.qty,
            price: item.price
        }));

        if(cartData.length === 0){
            alert("Cart is empty!");
            return;
        }

        const orderData = {
            fullName: $("#fullName").val(),
            phone: $("#phone").val(),
            email: $("#email").val(),
            address: $("#address").val(),
            paymentType: $("#paymentType").val(),
            cart: cartData
        };

        fetch("/api/orders", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(orderData)
        })
        .then(res => {
            if (!res.ok) throw new Error("Order submission failed");
            return res.json();
        })
        .then(data => {
            alert("Order placed successfully!");

            // Clear cart and refresh UI
            localStorage.removeItem("cart");
            loadCartData();

            // Reset form fields
            $("#create-food-order-request-form")[0].reset();
        })
        .catch(err => alert(err.message));
    });
    
   });