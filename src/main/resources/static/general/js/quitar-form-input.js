document.addEventListener("DOMContentLoaded", function () {
  const input = document.getElementById("buscador-producto");

  // Escuchar el evento keydown en el campo de entrada
  input.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
      // Prevenir la acción por defecto (envío del formulario)
      event.preventDefault();
      // Otros códigos que quieras ejecutar al presionar Enter en el input
    }
  });
});
