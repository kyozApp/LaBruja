document.addEventListener("DOMContentLoaded", function () {
  var enlacesWhatsApp = document.querySelectorAll("a[href*='wa.me']");
  enlacesWhatsApp.forEach(function (enlace) {
    enlace.addEventListener("click", function (event) {
      event.preventDefault(); // Prevenir el comportamiento predeterminado del enlace

      // Obtener el número de teléfono de la URL de WhatsApp
      var numeroWhatsApp = this.getAttribute("href").split("/").pop();

      // Obtener la hora actual
      var horaActual = new Date().getHours();

      // Definir el saludo según la hora actual
      var saludo;
      if (horaActual >= 6 && horaActual < 13) {
        saludo = "Buenos días";
      } else if (horaActual >= 13 && horaActual < 19) {
        saludo = "Buenas tardes";
      } else {
        saludo = "Buenas noches";
      }

      // Construir el mensaje con los productos del carrito
      var mensaje = `¡${saludo}, deseo comprar estos productos!\n\nProductos:\n`;

      // Obtener los productos del carrito del localStorage
      var productosEnCarrito =
        JSON.parse(localStorage.getItem("productosEnCarrito")) || [];

      // Recorrer los productos y agregarlos al mensaje con la cantidad
      productosEnCarrito.forEach(function (producto) {
        mensaje += `${producto.nombre} - ${producto.precio}  x${producto.cantidad}\n`;
      });

      // Codificar el mensaje completo
      mensaje = encodeURIComponent(mensaje);

      // Construir la URL de WhatsApp con el número de teléfono y el mensaje predefinido
      var urlWhatsApp = "https://wa.me/" + numeroWhatsApp + "?text=" + mensaje;

      // Abrir la ventana de WhatsApp con la URL construida
      window.open(urlWhatsApp, "_blank");
    });
  });
});
