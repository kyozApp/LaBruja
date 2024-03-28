// Obtenemos todos los enlaces del sidebar
var links = document.querySelectorAll(".sidebar a");

// Iteramos sobre cada enlace
links.forEach(function (link) {
  // Agregamos un event listener para el evento click
  link.addEventListener("click", function (event) {
    // Quitamos la clase 'active' de todos los enlaces
    links.forEach(function (item) {
      item.classList.remove("active");
    });
    // Agregamos la clase 'active' al enlace clicado
    this.classList.add("active");
  });
});
