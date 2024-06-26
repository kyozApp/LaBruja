//PARA ABRIR MENU---------------------------------------------------------------------------------------------
let modal = document.getElementById("modal");
let cerrarModalBtn = document.getElementById("cerrar-modal");
let contModal = document.querySelector(".cont-modal");
let linkModal = document.querySelectorAll(".link-menu-modal");

function abrirModal() {
    modal.style.display = "flex";
    setTimeout(() => {
        modal.classList.add("show");
    }, 10); // Pequeña demora para activar la transición
    document.body.style.overflow = "hidden"; // Deshabilitar scroll
}

function cerrarModal() {
    modal.classList.remove("show");
    setTimeout(() => {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Habilitar scroll
    }, 200); // Esperar la duración de la animación
}

cerrarModalBtn.addEventListener("click", cerrarModal);

linkModal.forEach(link => {
    link.addEventListener("click", cerrarModal);
});

modal.addEventListener("click", function (event) {
    if (!contModal.contains(event.target)) {
        cerrarModal();
    }
});

//EFECTO DE OCULTAR Y APARECER DEL HEADER AL DESPLAZARME ARRIBA O ABAJO-----------------------------------------
document.addEventListener("DOMContentLoaded", function () {
    let lastScrollTop = 0; // Variable para almacenar la posición del scroll anterior
    let header = document.querySelector("header");

    // Establecer el estilo position: sticky en el header
    header.style.position = "sticky";

    window.addEventListener("scroll", function () {
        let scrollTop = window.pageYOffset || document.documentElement.scrollTop; // Posición actual del scroll
        if (scrollTop > lastScrollTop) {
            // Scroll hacia abajo
            header.style.transform = "translateY(-100%)"; // Ocultar el header hacia arriba
        } else {
            // Scroll hacia arriba
            header.style.transform = "translateY(0)"; // Mostrar el header
        }

        lastScrollTop = scrollTop <= 0 ? 0 : scrollTop; // Asegurarse de no tener valores negativos
    });
});