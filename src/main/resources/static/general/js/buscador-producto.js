document.addEventListener("keyup", (e) => {
  const searchInput = document.querySelector("#buscador-producto");
  const searchText = searchInput.value.trim().toLowerCase();
  const productosFiltrados = document.querySelectorAll(".productos-filtrados");
  let productosMostrados = 0; // Contador de productos mostrados

  productosFiltrados.forEach((producto) => {
    const nombreProducto = producto
      .querySelector(".nombre-produc-search")
      .textContent.trim()
      .toLowerCase();
    if (searchText === "") {
      // Si el input está vacío, ocultar todos los productos
      producto.classList.add("display-none");
    } else if (nombreProducto.includes(searchText) && productosMostrados < 7) {
      // Si el nombre del producto contiene el texto buscado y aún no se han mostrado 7 productos, mostrarlo
      producto.classList.remove("display-none");
      productosMostrados++;
    } else {
      // Si no cumple las condiciones anteriores, ocultarlo
      producto.classList.add("display-none");
    }
  });

  const busquedaProductos = document.querySelector(".busqueda-productos");
  if (productosMostrados >= 1) {
    busquedaProductos.style.borderStyle = "double";
  } else {
    busquedaProductos.style.borderStyle = "none";
  }
});

window.addEventListener("scroll", function () {
  var scrollPosition = window.scrollY;
  var busquedaProductos = document.querySelector(".busqueda-productos");

  if (scrollPosition > 197) {
    busquedaProductos.style.zIndex = "14";
  } else {
    busquedaProductos.style.zIndex = "20";
  }
});

const searchInput = document.querySelector("#buscador-producto");
const busquedaProductos = document.querySelector(".busqueda-productos");

let blurTimeout;

searchInput.addEventListener("blur", function () {
  // Agregar un pequeño retraso antes de ocultar .busqueda-productos
  blurTimeout = setTimeout(() => {
    busquedaProductos.classList.add("display-none");
  }, 200); // 200 milisegundos de retraso (puedes ajustar este valor según sea necesario)
});

// Cancelar el retraso si se enfoca el input antes de que ocurra el evento "blur"
searchInput.addEventListener("focus", function () {
    clearTimeout(blurTimeout);
    busquedaProductos.classList.remove("display-none");
});