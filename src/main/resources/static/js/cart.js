$(document).ready(function() {

    // ------------------------------------------------------
    // 1️⃣ Get current user ID
    // ------------------------------------------------------
    let currentUserId = null;

    function loadCurrentUser(callback) {
        $.ajax({
            url: "/users/current",
            method: "GET",
            success: function(user) {
                currentUserId = user.id;
                if (callback) callback();
            },
            error: function() {
                currentUserId = null; // guest
                if (callback) callback();
            }
        });
    }

    function getCartKey() {
        return currentUserId ? `cart_${currentUserId}` : 'cart_guest';
    }

    // ------------------------------------------------------
    // 2️⃣ Load cart data from localStorage for the checkout page
    // ------------------------------------------------------
    function loadCartData() {
        const cart = (JSON.parse(localStorage.getItem(getCartKey())) || []).map(item => ({
            id: item.id,
            title: item.title,
            image: item.image,
            qty: item.qty,
            price: item.price
        }));

        const tbody = $('#food-table tbody');
        tbody.empty();

        let grandTotal = 0;

        if (cart.length === 0) {
            tbody.append('<tr><td colspan="7" class="text-center">No items in cart</td></tr>');
        } else {
            cart.forEach((item, index) => {
                const total = item.price * item.qty;
                grandTotal += total;
                tbody.append(`
                    <tr>
                        <td>${index + 1}</td>
                        <td><img src="${item.image}" alt="${item.title}" style="width:50px;height:50px;"></td>
                        <td>${item.title}</td>
                        <td>₹${item.price}</td>
                        <td>${item.qty}</td>
                        <td>₹${total}</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-sm btn-danger remove-item" data-id="${item.id}">x</button>
                        </td>
                    </tr>
                `);
            });

            tbody.append(`
                <tr style="font-weight: bold;">
                    <td colspan="7" class="text-center">Grand Total: ₹${grandTotal.toFixed(2)}</td>
                </tr>
            `);
        }
    }

    // ------------------------------------------------------
    // 3️⃣ Remove item from cart (still localStorage)
    // ------------------------------------------------------
    $(document).on('click', '.remove-item', function() {
        const itemId = $(this).data('id');
        let cart = JSON.parse(localStorage.getItem(getCartKey())) || [];
        cart = cart.filter(item => String(item.id) !== String(itemId));
        localStorage.setItem(getCartKey(), JSON.stringify(cart));
        loadCartData();
    });

    // ------------------------------------------------------
    // 4️⃣ Submit order to backend
    // ------------------------------------------------------
    $("#create-food-order-request-form").submit(function(e) {
        e.preventDefault();

        const cartData = (JSON.parse(localStorage.getItem(getCartKey())) || []).map(item => ({
            foodId: item.id,
            qty: item.qty,
            price: item.price
        }));

        if (cartData.length === 0) {
            alert("Cart is empty!");
            return;
        }

        const orderData = {
            userId: currentUserId, // Important: link order to user
            fullName: $("#fullName").val(),
            phone: $("#phone").val(),
            email: $("#email").val(),
            address: $("#address").val(),
            paymentType: $("#paymentType").val(),
            cart: cartData,
            status: "Pending" // Default status
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

            // Clear localStorage cart
            localStorage.removeItem(getCartKey());
            loadCartData();

            // Reset form fields
            $("#create-food-order-request-form")[0].reset();

            // Optional: load user orders from backend to show in profile page
            loadUserOrders();
        })
        .catch(err => alert(err.message));
    });

    // ------------------------------------------------------
    // 5️⃣ Load user info into form fields
    // ------------------------------------------------------
    loadCurrentUser(function() {
        if (currentUserId) {
            $.ajax({
                url: "/users/current",
                method: "GET",
                success: function(user) {
                    $("#fullName").val(user.fullName);
                    $("#email").val(user.email);
                    $("#phone").val(user.phone);
                },
                error: function() {
                    window.location.href = "/login-form.html?redirect=place-order";
                }
            });
        }

        // Load cart items
        loadCartData();
    });

    // ------------------------------------------------------
    // 6️⃣ Optional: function to load user orders from backend
    // ------------------------------------------------------
    function loadUserOrders() {
        $.ajax({
            url: `/api/orders?userId=${currentUserId}`,
            method: "GET",
            success: function(orders) {
                const tbody = $('#orders-table tbody');
                tbody.empty();

                if (orders.length === 0) {
                    tbody.append('<tr><td colspan="7" class="text-center">No orders found</td></tr>');
                    return;
                }

                orders.forEach((order, index) => {
                    const canDelete = order.status === "Pending"; // Only allow deletion if not processed
                    tbody.append(`
                        <tr>
                            <td>${index + 1}</td>
                            <td>${order.cart.map(i => i.foodId).join(", ")}</td>
                            <td>${order.status}</td>
                            <td>
                                ${canDelete ? `<button class="btn btn-danger delete-order" data-id="${order.id}">Delete</button>` : ''}
                            </td>
                        </tr>
                    `);
                });
            }
        });
    }

    // ------------------------------------------------------
    // 7️⃣ Delete order from backend
    // ------------------------------------------------------
    $(document).on('click', '.delete-order', function() {
        const orderId = $(this).data('id');

        fetch(`/api/orders/${orderId}`, { method: 'DELETE' })
            .then(res => {
                if (!res.ok) throw new Error("Cannot delete this order! It may already be processing.");
                alert("Order deleted successfully!");
                loadUserOrders();
            })
            .catch(err => alert(err.message));
    });

});
