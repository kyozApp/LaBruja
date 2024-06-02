// Función para abrir el modal y deshabilitar el scroll del body
function abrirModal() {
  var divModal = document.getElementById("modal-coti");
  divModal.style.display = "flex";
  document.body.classList.add("no-scroll");
}

// Función para cerrar el modal y habilitar el scroll del body
function cerrarModal(event) {
  var divModal = document.getElementById("modal-coti");
  if (event.target === divModal || event.target.closest(".head-modal button")) {
    divModal.style.display = "none";
    document.body.classList.remove("no-scroll");

    var lista = document.getElementById("lista-personal-lima");
    var opcLima = document.getElementById("cont-opc-lima");
    var btnLima = document.getElementById("btn-opc-modal-lima");
    lista.style.display = "none";
    opcLima.style.display = "flex";
    btnLima.style.display = "block";

    var lista = document.getElementById("lista-personal-arequipa");
    var opcArequipa = document.getElementById("cont-opc-arequipa");
    var btnAqp = document.getElementById("btn-opc-modal-aqp");
    lista.style.display = "none";
    opcArequipa.style.display = "flex";
    btnAqp.style.display = "block";

    volverTextTitulo();
  }
}

//Cambia texto al titulo del modal
function cambiarTextTitulo() {
  var txtTitlleModal = document.getElementById("txt-span-modal");
  txtTitlleModal.textContent = "AL ASESOR";
}

function volverTextTitulo() {
  var txtTitlleModal = document.getElementById("txt-span-modal");
  txtTitlleModal.textContent = "LA SUCURSAL";
}

// Función para ocultar todas las opciones de sucursales
function ocultarOpcLima() {
  var opcLima = document.getElementById("cont-opc-lima");
  var btnAqp = document.getElementById("btn-opc-modal-aqp");
  opcLima.style.display = "none";
  btnAqp.style.display = "none";
}

function ocultarOpcArequipa() {
  var opcArequipa = document.getElementById("cont-opc-arequipa");
  var btnLima = document.getElementById("btn-opc-modal-lima");
  opcArequipa.style.display = "none";
  btnLima.style.display = "none";
}

// Función para mostrar la lista de personal de Lima y ocultar otras opciones
function listaLima() {
  ocultarOpcArequipa();
  var lista = document.getElementById("lista-personal-lima");
    lista.style.display = "flex";
    cambiarTextTitulo();
}

// Función para mostrar la lista de personal de Arequipa y ocultar otras opciones
function listaArequipa() {
  ocultarOpcLima();
  
  var lista = document.getElementById("lista-personal-arequipa");
    lista.style.display = "flex";
    cambiarTextTitulo();
}
