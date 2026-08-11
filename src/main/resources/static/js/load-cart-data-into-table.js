// Load cart data into table
$(document).ready(function() {
    function loadCartData() {
        const cart = (JSON.parse(localStorage.getItem('cart')) || []).filter(item => item.id > 0).map(item => ({
            foodId: Number(item.id),
            title: item.title,
            image: item.image,
            qty: item.qty,
            price: item.price
        }));

        const tbody = $('#food-table tbody');
        tbody.empty();

        if (cart.length === 0) {
            tbody.append('<tr><td colspan="7" class="text-center">No items in cart</td></tr>');
        } else {
            cart.forEach((item, index) => {
                const total = item.price * item.qty;
                tbody.append(`
                    <tr>
                        <td>${index + 1}</td>
                        <td><img src="${item.image}" alt="${item.title}" style="width:50px;height:50px;"></td>
                        <td>${item.title}</td>
                        <td>$${item.price}</td>
                        <td>${item.qty}</td>
                        <td>$${total}</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-sm btn-danger remove-item" data-id="${item.id}">x</button>
                        </td>
                    </tr>
                `);
            });
        }
    }

    // Remove item from cart
    $(document).on('click', '.remove-item', function() {
        const itemId = $(this).data('id');
        let cart = JSON.parse(localStorage.getItem('cart')) || [];
        cart = cart.filter(item => String(item.id) !== String(itemId));
        localStorage.setItem('cart', JSON.stringify(cart));
        loadCartData();
    });

    // Initial load
    loadCartData();
});
