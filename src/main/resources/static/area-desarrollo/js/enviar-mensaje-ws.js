function enviarMensaje() {
    const nombre = document.getElementById('nombre').value;
    const servicio = document.getElementById('serv').value;
    const mensaje = document.getElementById('descripccion').value;
    const selectServicio = document.getElementById('serv');
    const labelServicio = selectServicio.previousElementSibling;

    // Remove previous error styles if any
    selectServicio.classList.remove('input-error');
    labelServicio.classList.remove('label-error');

    // Add change event to remove error styles on valid selection
    selectServicio.addEventListener('change', () => {
        selectServicio.classList.remove('input-error');
        labelServicio.classList.remove('label-error');
    });

    if (servicio === "") {
        selectServicio.classList.add('input-error');
        labelServicio.classList.add('label-error');
        return;
    }

    const textoMensaje = `Nombre: ${nombre}\nServicio: ${servicio}\nMensaje: ${mensaje}`;
    const telefono = "51985872694"; // Reemplaza con tu número de teléfono
    const url = `https://wa.me/${telefono}?text=${encodeURIComponent(textoMensaje)}`;

    window.open(url, '_blank');
}
