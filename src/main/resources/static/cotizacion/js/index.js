document.getElementById("selectProcesadorId").addEventListener("change", function () {
    var select = document.getElementById("selectProcesadorId");
    var selectedOption = select.options[select.selectedIndex];
    var precioProcesador = selectedOption.getAttribute("data-precio");
    if (precioProcesador === null) {
        precioProcesador = "0";
    }
    document.getElementById("mostrarPrecioProcesador").textContent = "Precio seleccionado: S/." + precioProcesador;
});

document.getElementById("selectPlacaId").addEventListener("change", function () {
    var select = document.getElementById("selectPlacaId");
    var selectedOption = select.options[select.selectedIndex];
    var precioPlaca = selectedOption.getAttribute("data-precio");
    if (precioPlaca === null) {
        precioPlaca = "0";
    }
    document.getElementById("mostrarPrecioPlaca").textContent = "Precio seleccionado: S/." + precioPlaca;
});

document.getElementById("selectRamId").addEventListener("change", function () {
    var select = document.getElementById("selectRamId");
    var selectedOption = select.options[select.selectedIndex];
    var precioRam = selectedOption.getAttribute("data-precio");
    if (precioRam === null) {
        precioRam = "0";
    }
    document.getElementById("mostrarPrecioRam").textContent = "Precio seleccionado: S/." + precioRam;
});

document.getElementById("selectRamId2").addEventListener("change", function () {
    var select = document.getElementById("selectRamId2");
    var selectedOption = select.options[select.selectedIndex];
    var precioRam2 = selectedOption.getAttribute("data-precio");
    if (precioRam2 === null) {
        precioRam2 = "0";
    }
    document.getElementById("mostrarPrecioRam2").textContent = "Precio seleccionado: S/." + precioRam2;
});

document.getElementById("selectAlmacenamientoId").addEventListener("change", function () {
    var select = document.getElementById("selectAlmacenamientoId");
    var selectedOption = select.options[select.selectedIndex];
    var precioAlmacenamiento = selectedOption.getAttribute("data-precio");
    if (precioAlmacenamiento === null) {
        precioAlmacenamiento = "0";
    }
    document.getElementById("mostrarPrecioAlmacenamiento").textContent = "Precio seleccionado: S/." + precioAlmacenamiento;
});

document.getElementById("selectTarjetaId").addEventListener("change", function () {
    var select = document.getElementById("selectTarjetaId");
    var selectedOption = select.options[select.selectedIndex];
    var precioTarjeta = selectedOption.getAttribute("data-precio");
    if (precioTarjeta === null) {
        precioTarjeta = "0";
    }
    document.getElementById("mostrarPrecioTarjeta").textContent = "Precio seleccionado: S/." + precioTarjeta;
});

document.getElementById("selectFuenteId").addEventListener("change", function () {
    var select = document.getElementById("selectFuenteId");
    var selectedOption = select.options[select.selectedIndex];
    var precioFuente = selectedOption.getAttribute("data-precio");
    if (precioFuente === null) {
        precioFuente = "0";
    }
    document.getElementById("mostrarPrecioFuente").textContent = "Precio seleccionado: S/." + precioFuente;
});

document.getElementById("selectCasseId").addEventListener("change", function () {
    var select = document.getElementById("selectCasseId");
    var selectedOption = select.options[select.selectedIndex];
    var precioCasse = selectedOption.getAttribute("data-precio");
    if (precioCasse === null) {
        precioCasse = "0";
    }
    document.getElementById("mostrarPrecioCasse").textContent = "Precio seleccionado: S/." + precioCasse;
});

document.getElementById("selectMonitorId").addEventListener("change", function () {
    var select = document.getElementById("selectMonitorId");
    var selectedOption = select.options[select.selectedIndex];
    var precioMonitor = selectedOption.getAttribute("data-precio");
    if (precioMonitor === null) {
        precioMonitor = "0";
    }
    document.getElementById("mostrarPrecioMonitor").textContent = "Precio seleccionado: S/." + precioMonitor;
});

document.getElementById("selectRefrigeracionId").addEventListener("change", function () {
    var select = document.getElementById("selectRefrigeracionId");
    var selectedOption = select.options[select.selectedIndex];
    var precioRefrigeracion = selectedOption.getAttribute("data-precio");
    if (precioRefrigeracion === null) {
        precioRefrigeracion = "0";
    }
    document.getElementById("mostrarPrecioRefrigeracion").textContent = "Precio seleccionado: S/." + precioRefrigeracion;
});

document.getElementById("selectAccesorioId").addEventListener("change", function () {
    var select = document.getElementById("selectAccesorioId");
    var selectedOption = select.options[select.selectedIndex];
    var precioAccesorio = selectedOption.getAttribute("data-precio");
    if (precioAccesorio === null) {
        precioAccesorio = "0";
    }
    document.getElementById("mostrarPrecioAccesorio").textContent = "Precio seleccionado: S/." + precioAccesorio;
});

document.getElementById("selectAccesorioId2").addEventListener("change", function () {
    var select = document.getElementById("selectAccesorioId2");
    var selectedOption = select.options[select.selectedIndex];
    var precioAccesorio2 = selectedOption.getAttribute("data-precio");
    if (precioAccesorio2 === null) {
        precioAccesorio2 = "0";
    }
    document.getElementById("mostrarPrecioAccesorio2").textContent = "Precio seleccionado: S/." + precioAccesorio2;
});

document.getElementById("selectAccesorioId3").addEventListener("change", function () {
    var select = document.getElementById("selectAccesorioId3");
    var selectedOption = select.options[select.selectedIndex];
    var precioAccesorio3 = selectedOption.getAttribute("data-precio");
    if (precioAccesorio3 === null) {
        precioAccesorio3 = "0";
    }
    document.getElementById("mostrarPrecioAccesorio3").textContent = "Precio seleccionado: S/." + precioAccesorio3;
});

document.getElementById("selectAccesorioId4").addEventListener("change", function () {
    var select = document.getElementById("selectAccesorioId4");
    var selectedOption = select.options[select.selectedIndex];
    var precioAccesorio4 = selectedOption.getAttribute("data-precio");
    if (precioAccesorio4 === null) {
        precioAccesorio4 = "0";
    }
    document.getElementById("mostrarPrecioAccesorio4").textContent = "Precio seleccionado: S/." + precioAccesorio4;
});



function actualizarTotal() {
    var precioProcesador = obtenerPrecioSeleccionado("selectProcesadorId");
    var precioPlaca = obtenerPrecioSeleccionado("selectPlacaId");
    var precioRam = obtenerPrecioSeleccionado("selectRamId");
    var precioRam2 = obtenerPrecioSeleccionado("selectRamId2");
    var precioAlmacenamiento = obtenerPrecioSeleccionado("selectAlmacenamientoId");
    var precioTarjeta = obtenerPrecioSeleccionado("selectTarjetaId");
    var precioFuente = obtenerPrecioSeleccionado("selectFuenteId");
    var precioCasse = obtenerPrecioSeleccionado("selectCasseId");
    var precioMonitor = obtenerPrecioSeleccionado("selectMonitorId");
    var precioRefrigeracion = obtenerPrecioSeleccionado("selectRefrigeracionId");
    var precioAccesorio = obtenerPrecioSeleccionado("selectAccesorioId");
    var precioAccesorio2 = obtenerPrecioSeleccionado("selectAccesorioId2");
    var precioAccesorio3 = obtenerPrecioSeleccionado("selectAccesorioId3");
    var precioAccesorio4 = obtenerPrecioSeleccionado("selectAccesorioId4");

    var total = parseFloat(precioProcesador) + parseFloat(precioPlaca) + parseFloat(precioRam) + parseFloat(precioRam2) + parseFloat(precioAlmacenamiento) + parseFloat(precioTarjeta) + parseFloat(precioFuente) + parseFloat(precioCasse) + parseFloat(precioMonitor) + parseFloat(precioRefrigeracion) + parseFloat(precioAccesorio) + parseFloat(precioAccesorio2) + parseFloat(precioAccesorio3) + parseFloat(precioAccesorio4);

    document.getElementById("totalPrecio").textContent = "Total: S/." + total.toFixed(2);
}


function obtenerPrecioSeleccionado(selectId) {
    var select = document.getElementById(selectId);
    var selectedOption = select.options[select.selectedIndex];
    var precio = selectedOption.getAttribute("data-precio");
    if (precio === null) {
        precio = "0";
    }
    return precio;
}

document.getElementById("selectProcesadorId").addEventListener("change", actualizarTotal);
document.getElementById("selectPlacaId").addEventListener("change", actualizarTotal);
document.getElementById("selectRamId").addEventListener("change", actualizarTotal);
document.getElementById("selectRamId2").addEventListener("change", actualizarTotal);
document.getElementById("selectAlmacenamientoId").addEventListener("change", actualizarTotal);
document.getElementById("selectTarjetaId").addEventListener("change", actualizarTotal);
document.getElementById("selectFuenteId").addEventListener("change", actualizarTotal);
document.getElementById("selectCasseId").addEventListener("change", actualizarTotal);
document.getElementById("selectMonitorId").addEventListener("change", actualizarTotal);
document.getElementById("selectRefrigeracionId").addEventListener("change", actualizarTotal);
document.getElementById("selectAccesorioId").addEventListener("change", actualizarTotal);
document.getElementById("selectAccesorioId2").addEventListener("change", actualizarTotal);
document.getElementById("selectAccesorioId3").addEventListener("change", actualizarTotal);
document.getElementById("selectAccesorioId4").addEventListener("change", actualizarTotal);

actualizarTotal();




