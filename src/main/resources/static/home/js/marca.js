document.addEventListener("DOMContentLoaded", function () {
  var imagenes = document.querySelectorAll(".logos-slide img");

  imagenes.forEach(function (imagen) {
    imagen.addEventListener("click", function () {
      var nombreMarca = imagen.id; // Obtener el nombre de la marca
      window.location.href =
        "/catalogo/producto?marca=" + encodeURIComponent(nombreMarca); // Redirigir a la página de productos con el nombre de la marca como parámetro
      filtrarSubcategoriasPorMarca(nombreMarca); // Filtrar subcategorías
    });
  });

  // Función para filtrar subcategorías por marca
  function filtrarSubcategoriasPorMarca(marca) {
    var subcategorias = document.querySelectorAll(
      ".producto-encabezado ul li a"
    );
    subcategorias.forEach(function (subcategoria) {
      if (subcategoria.textContent === marca) {
        subcategoria.parentNode.style.display = "block";
      } else {
        subcategoria.parentNode.style.display = "none";
      }
    });
  }
});
