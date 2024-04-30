document.addEventListener("DOMContentLoaded", function(){
    var label_open_silebar = document.querySelector("label[for='open-silebar']");
    var label_close_silebar = document.querySelector("label[for='close-silebar']");
    var img_open_silebar = document.querySelector(".img-menu");

    var btn_open_silebar = document.querySelector(".abrir-slider");

    var contenedor_silebar = document.getElementById("background-silebar");
    var silevar = document.getElementById("contenedor-silebar");

    label_open_silebar.addEventListener("click", function(){
        openSidebar(); // Llama a la función para abrir el sidebar
    });

    btn_open_silebar.addEventListener("click", function () {
      openSidebar(); // Llama a la función para abrir el sidebar
    });

    label_close_silebar.addEventListener("click", function(){
        closeSidebar(); // Llama a la función para cerrar el sidebar
    });

    img_open_silebar.addEventListener("click", function(){
        openSidebar(); // Llama a la función para abrir el sidebar cuando se hace clic en la imagen
    });

    function openSidebar() {
        silevar.style.display = "block"; // Mostrar el sidebar
        setTimeout(() => {
            silevar.style.left = "0"; // Mover a la posición inicial
        }, 50); // Pequeña espera antes de mover para permitir que se muestre el sidebar primero
        contenedor_silebar.style.display = "block"; // Mostrar el fondo del sidebar
    }

    function closeSidebar() {
        silevar.style.left = "-300px"; // Mover a la izquierda
        setTimeout(() => {
            silevar.style.display = "none"; // Ocultar después de la animación
            contenedor_silebar.style.display = "none"; // Ocultar el fondo del sidebar
        }, 300); // Esperar la duración de la animación (0.3s)
    }
    
});

window.addEventListener('scroll', function() {
    var stickyDiv = document.querySelector('.sub-nivel-header-mobil');
    var scrollPosition = window.scrollY;

    if (scrollPosition > 197) {
        stickyDiv.style.backgroundColor = "red";
    } else {
        stickyDiv.style.backgroundColor = "black";
    }
});