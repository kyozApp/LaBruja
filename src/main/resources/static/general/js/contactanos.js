document.addEventListener("DOMContentLoaded", function () {
  var enlacesWhatsApp = document.querySelectorAll("a[href*='wa.me']");
  enlacesWhatsApp.forEach(function (enlace) {
    enlace.addEventListener("click", function (event) {
      event.preventDefault(); // Prevenir el comportamiento predeterminado del enlace

      // Obtener el número de teléfono de la URL de WhatsApp
      var numeroWhatsApp = this.getAttribute("href").split("/").pop();

      // Construir el mensaje con los productos del carrito
      var mensaje =
        "¡La Bruja Store, la tienda online que mejor te atiende!\n\nProductos:\n";

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
