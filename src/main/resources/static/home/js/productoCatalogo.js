document.addEventListener("DOMContentLoaded", function () {
  // Obtener el nombre de la marca de la URL
  var params = new URLSearchParams(window.location.search);
  var marca = params.get("marca");

  // Filtrar los productos por marca después de cargar la página
  if (marca) {
    filtrarProductosPorMarca(marca);
  }
});

function filtrarProductosPorMarca(marca) {
  // Convertir el nombre de la marca a minúsculas para una comparación insensible a mayúsculas
  marca = marca.toLowerCase();

  // Obtener todos los productos en la página
  var productos = document.querySelectorAll(
    ".contenedor-productos .detalle-producto"
  );

  // Recorrer todos los productos y mostrar solo los que contienen el nombre de la marca en su título
  productos.forEach(function (producto) {
    var tituloProducto = producto
      .querySelector(".titulo-producto a")
      .textContent.toLowerCase();

    if (tituloProducto.includes(marca)) {
      producto.style.display = "block";
    } else {
      producto.style.display = "none";
    }
  });
}
