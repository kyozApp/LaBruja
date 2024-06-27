let modalWsp = document.getElementById("modal-wsp");
let btnWsp = document.querySelector(".btn-wsp");

function desplegarModalWdp() {
    if (modalWsp.style.display === "none" || modalWsp.style.display === "") {
        modalWsp.style.display = "block";
        btnWsp.classList.add("btn-wsp-hover");
    } else {
        modalWsp.style.display = "none";
        btnWsp.classList.remove("btn-wsp-hover");
    }
}

function enviarMensaje() {
    let mensejeWsp = document.getElementById("mensaje");
    let mensaje = mensejeWsp.value;

    const telefono = "51985872694"; // Reemplaza con tu número de teléfono
    const url = `https://wa.me/${telefono}?text=${encodeURIComponent(mensaje)}`;

    window.open(url, '_blank');
}