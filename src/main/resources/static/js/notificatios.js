// /js/notifications.js
function showMessage(message, type = 'success') {
    // Create notification container if it doesn't exist
    let container = document.getElementById('notification-container');
    if (!container) {
        container = document.createElement('div');
        container.id = 'notification-container';
        container.style.position = 'fixed';
        container.style.top = '20px';
        container.style.right = '20px';
        container.style.zIndex = '9999';
        container.style.width = '300px';
        document.body.appendChild(container);
    }

    // Create message element
    const msg = document.createElement('div');
    msg.innerText = message;
    msg.style.padding = '10px 15px';
    msg.style.marginBottom = '10px';
    msg.style.borderRadius = '5px';
    msg.style.color = '#fff';
    msg.style.fontFamily = 'Arial, Helvetica, sans-serif';
    msg.style.boxShadow = '0 4px 10px rgba(0,0,0,0.2)';
    msg.style.opacity = '0';
    msg.style.transition = 'all 0.5s ease';

    // Type-based styling
    if(type === 'success') msg.style.backgroundColor = '#44bd32';
    else if(type === 'error') msg.style.backgroundColor = '#e84118';
    else if(type === 'info') msg.style.backgroundColor = '#0097e6';
    else msg.style.backgroundColor = '#718093';

    container.appendChild(msg);

    // Animate in
    setTimeout(() => {
        msg.style.opacity = '1';
        msg.style.transform = 'translateY(0)';
    }, 50);

    // Auto remove
    setTimeout(() => {
        msg.style.opacity = '0';
        msg.style.transform = 'translateY(-20px)';
        setTimeout(() => msg.remove(), 500);
    }, 3000);
}
