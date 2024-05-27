function obtenerProductoSeleccionado(selectId) {
  var select = document.getElementById(selectId);
  var selectedOption = select.options[select.selectedIndex];
  var precio = selectedOption.getAttribute("data-precio");
  var nombre = selectedOption.text;
  if (precio === null) {
    precio = "0";
  }
  return { nombre: nombre, precio: parseFloat(precio) };
}

function generarCotizacion() {
  var productos = {
    Procesador: obtenerProductoSeleccionado("selectProcesadorId"),
    Placa: obtenerProductoSeleccionado("selectPlacaId"),
    Ram: obtenerProductoSeleccionado("selectRamId"),
    "Ram 2": obtenerProductoSeleccionado("selectRamId2"),
    Almacenamiento: obtenerProductoSeleccionado("selectAlmacenamientoId"),
    Tarjeta: obtenerProductoSeleccionado("selectTarjetaId"),
    Fuente: obtenerProductoSeleccionado("selectFuenteId"),
    Case: obtenerProductoSeleccionado("selectCasseId"),
    Monitor: obtenerProductoSeleccionado("selectMonitorId"),
    Refrigeracion: obtenerProductoSeleccionado("selectRefrigeracionId"),
    Accesorio: obtenerProductoSeleccionado("selectAccesorioId"),
    "Accesorio 2": obtenerProductoSeleccionado("selectAccesorioId2"),
    "Accesorio 3": obtenerProductoSeleccionado("selectAccesorioId3"),
    "Accesorio 4": obtenerProductoSeleccionado("selectAccesorioId4"),
  };

  var cotizacion = "Cotización de productos:\n";
  var total = 0;

  for (var categoria in productos) {
    var producto = productos[categoria];
    if (producto.precio > 0) {
      cotizacion += `${categoria}:\n${
        producto.nombre
      }: S/.${producto.precio.toFixed(2)}\n\n`;
      total += producto.precio;
    }
  }

  cotizacion += `Total: S/.${total.toFixed(2)}`;

  localStorage.setItem("cotizacion", cotizacion);

  window.location.href = "/contactanos";
}

document
  .querySelector("#generarCoti")
  .addEventListener("click", generarCotizacion);
