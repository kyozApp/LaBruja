var placasOriginales = document.getElementById("selectPlacaId").innerHTML;
var rams1Originales = document.getElementById("selectRamId").innerHTML;
var rams2Originales = document.getElementById("selectRamId2").innerHTML;

function actualizarPlacas() {
    var procesadorId = document.getElementById("selectProcesadorId").value;
    var placaSelect = document.getElementById("selectPlacaId");
    placaSelect.disabled = true;

    placaSelect.innerHTML = placasOriginales;

    document.getElementById("selectRamId").innerHTML = rams1Originales;
    document.getElementById("selectRamId2").innerHTML = rams2Originales;
    document.getElementById("mostrarPrecioPlaca").textContent = "Precio: S/.";
    document.getElementById("mostrarStockPlaca").textContent = "Stock: ";
    document.getElementById("mostrarPrecioRam").textContent = "Precio: S/.";
    document.getElementById("mostrarStockRam").textContent = "Stock: ";
    document.getElementById("mostrarPrecioRam2").textContent = "Precio: S/.";
    document.getElementById("mostrarStockRam2").textContent = "Stock: ";

    if (procesadorId !== "") {
        placaSelect.disabled = false;
        var placas = document.querySelectorAll('#selectPlacaId option:not([value=""])');
        placas.forEach(function (option) {
            if (option.getAttribute('data-procesadores').includes(procesadorId)) {
                option.style.display = 'block';
            } else {
                option.style.display = 'none';
            }
        });
    }
}

function actualizarRams() {
    var placaId = document.getElementById("selectPlacaId").value;
    var ram1Select = document.getElementById("selectRamId");
    var ram2Select = document.getElementById("selectRamId2");
    ram1Select.disabled = true;
    ram2Select.disabled = true;

    ram1Select.innerHTML = rams1Originales;
    ram2Select.innerHTML = rams2Originales;

    document.getElementById("selectRamId").innerHTML = rams1Originales;
    document.getElementById("selectRamId2").innerHTML = rams2Originales;
    document.getElementById("mostrarPrecioRam").textContent = "Precio: S/.";
    document.getElementById("mostrarStockRam").textContent = "Stock: ";
    document.getElementById("mostrarPrecioRam2").textContent = "Precio: S/.";
    document.getElementById("mostrarStockRam2").textContent = "Stock: ";

    if (placaId !== "") {
        ram1Select.disabled = false;
        ram2Select.disabled = false;
        var rams1 = document.querySelectorAll('#selectRamId option:not([value=""])');
        rams1.forEach(function (option) {
            if (option.getAttribute('data-placas').includes(placaId)) {
                option.style.display = 'block';
            } else {
                option.style.display = 'none';
            }
        });
        var rams2 = document.querySelectorAll('#selectRamId2 option:not([value=""])');
        rams2.forEach(function (option) {
            if (option.getAttribute('data-placas').includes(placaId)) {
                option.style.display = 'block';
            } else {
                option.style.display = 'none';
            }
        });
    }
}

document.getElementById("selectProcesadorId").addEventListener("change", function () {
    var select = document.getElementById("selectProcesadorId");
    var selectedOption = select.options[select.selectedIndex];
    var precioProcesador = selectedOption.getAttribute("data-precio");
    if (precioProcesador === null) {
        precioProcesador = "0";
    }
    document.getElementById("mostrarPrecioProcesador").textContent = "Precio: S/." + precioProcesador;
    var stockProcesador = selectedOption.getAttribute("data-stock");
    if (stockProcesador === null) {
        stockProcesador = "0";
    }
    document.getElementById("mostrarStockProcesador").textContent = "Stock: " + stockProcesador;

});

document.getElementById("selectPlacaId").addEventListener("change", function () {
    var select = document.getElementById("selectPlacaId");
    var selectedOption = select.options[select.selectedIndex];
    var precioPlaca = selectedOption.getAttribute("data-precio");
    if (precioPlaca === null) {
        precioPlaca = "0";
    }
    document.getElementById("mostrarPrecioPlaca").textContent = "Precio: S/." + precioPlaca;
    var stockPlaca = selectedOption.getAttribute("data-stock");
    if (stockPlaca === null) {
        stockPlaca = "0";
    }
    document.getElementById("mostrarStockPlaca").textContent = "Stock: " + stockPlaca;
});

document.getElementById("selectRamId").addEventListener("change", function () {
    var select = document.getElementById("selectRamId");
    var selectedOption = select.options[select.selectedIndex];
    var precioRam = selectedOption.getAttribute("data-precio");
    if (precioRam === null) {
        precioRam = "0";
    }
    document.getElementById("mostrarPrecioRam").textContent = "Precio: S/." + precioRam;
    var stockRam = selectedOption.getAttribute("data-stock");
    if (stockRam === null) {
        stockRam = "0";
    }
    document.getElementById("mostrarStockRam").textContent = "Stock: " + stockRam;
});

document.getElementById("selectRamId2").addEventListener("change", function () {
    var select = document.getElementById("selectRamId2");
    var selectedOption = select.options[select.selectedIndex];
    var precioRam2 = selectedOption.getAttribute("data-precio");
    if (precioRam2 === null) {
        precioRam2 = "0";
    }
    document.getElementById("mostrarPrecioRam2").textContent = "Precio: S/." + precioRam2;
    var stockRam2 = selectedOption.getAttribute("data-stock");
    if (stockRam2 === null) {
        stockRam2 = "0";
    }
    document.getElementById("mostrarStockRam2").textContent = "Stock: " + stockRam2;
});

document.getElementById("selectAlmacenamientoId").addEventListener("change", function () {
    var select = document.getElementById("selectAlmacenamientoId");
    var selectedOption = select.options[select.selectedIndex];
    var precioAlmacenamiento = selectedOption.getAttribute("data-precio");
    if (precioAlmacenamiento === null) {
        precioAlmacenamiento = "0";
    }
    document.getElementById("mostrarPrecioAlmacenamiento").textContent = "Precio: S/." + precioAlmacenamiento;
    var stockAlmacenamiento = selectedOption.getAttribute("data-stock");
    if (stockAlmacenamiento === null) {
        stockAlmacenamiento = "0";
    }
    document.getElementById("mostrarStockAlmacenamiento").textContent = "Stock: " + stockAlmacenamiento;
});

document.getElementById("selectTarjetaId").addEventListener("change", function () {
    var select = document.getElementById("selectTarjetaId");
    var selectedOption = select.options[select.selectedIndex];
    var precioTarjeta = selectedOption.getAttribute("data-precio");
    if (precioTarjeta === null) {
        precioTarjeta = "0";
    }
    document.getElementById("mostrarPrecioTarjeta").textContent = "Precio: S/." + precioTarjeta;
    var stockTarjeta = selectedOption.getAttribute("data-stock");
    if (stockTarjeta === null) {
        stockTarjeta = "0";
    }
    document.getElementById("mostrarStockTarjeta").textContent = "Stock: " + stockTarjeta;
});

document.getElementById("selectFuenteId").addEventListener("change", function () {
    var select = document.getElementById("selectFuenteId");
    var selectedOption = select.options[select.selectedIndex];
    var precioFuente = selectedOption.getAttribute("data-precio");
    if (precioFuente === null) {
        precioFuente = "0";
    }
    document.getElementById("mostrarPrecioFuente").textContent = "Precio: S/." + precioFuente;
    var stockFuente = selectedOption.getAttribute("data-stock");
    if (stockFuente === null) {
        stockFuente = "0";
    }
    document.getElementById("mostrarStockFuente").textContent = "Stock: " + stockFuente;
});

document.getElementById("selectCasseId").addEventListener("change", function () {
    var select = document.getElementById("selectCasseId");
    var selectedOption = select.options[select.selectedIndex];
    var precioCasse = selectedOption.getAttribute("data-precio");
    if (precioCasse === null) {
        precioCasse = "0";
    }
    document.getElementById("mostrarPrecioCasse").textContent = "Precio: S/." + precioCasse;
    var stockCasse = selectedOption.getAttribute("data-stock");
    if (stockCasse === null) {
        stockCasse = "0";
    }
    document.getElementById("mostrarStockCasse").textContent = "Stock: " + stockCasse;
});

document.getElementById("selectMonitorId").addEventListener("change", function () {
    var select = document.getElementById("selectMonitorId");
    var selectedOption = select.options[select.selectedIndex];
    var precioMonitor = selectedOption.getAttribute("data-precio");
    if (precioMonitor === null) {
        precioMonitor = "0";
    }
    document.getElementById("mostrarPrecioMonitor").textContent = "Precio: S/." + precioMonitor;
    var stockMonitor = selectedOption.getAttribute("data-stock");
    if (stockMonitor === null) {
        stockMonitor = "0";
    }
    document.getElementById("mostrarStockMonitor").textContent = "Stock: " + stockMonitor;
});

document.getElementById("selectRefrigeracionId").addEventListener("change", function () {
    var select = document.getElementById("selectRefrigeracionId");
    var selectedOption = select.options[select.selectedIndex];
    var precioRefrigeracion = selectedOption.getAttribute("data-precio");
    if (precioRefrigeracion === null) {
        precioRefrigeracion = "0";
    }
    document.getElementById("mostrarPrecioRefrigeracion").textContent = "Precio: S/." + precioRefrigeracion;
    var stockRefrigeracion = selectedOption.getAttribute("data-stock");
    if (stockRefrigeracion === null) {
        stockRefrigeracion = "0";
    }
    document.getElementById("mostrarStockRefrigeracion").textContent = "Stock: " + stockRefrigeracion;
});

document.getElementById("selectAccesorioId").addEventListener("change", function () {
    var select = document.getElementById("selectAccesorioId");
    var selectedOption = select.options[select.selectedIndex];
    var precioAccesorio = selectedOption.getAttribute("data-precio");
    if (precioAccesorio === null) {
        precioAccesorio = "0";
    }
    document.getElementById("mostrarPrecioAccesorio").textContent = "Precio: S/." + precioAccesorio;
    var stockAccesorio = selectedOption.getAttribute("data-stock");
    if (stockAccesorio === null) {
        stockAccesorio = "0";
    }
    document.getElementById("mostrarStockAccesorio").textContent = "Stock: " + stockAccesorio;
});

document.getElementById("selectAccesorioId2").addEventListener("change", function () {
    var select = document.getElementById("selectAccesorioId2");
    var selectedOption = select.options[select.selectedIndex];
    var precioAccesorio2 = selectedOption.getAttribute("data-precio");
    if (precioAccesorio2 === null) {
        precioAccesorio2 = "0";
    }
    document.getElementById("mostrarPrecioAccesorio2").textContent = "Precio: S/." + precioAccesorio2;
    var stockAccesorio2 = selectedOption.getAttribute("data-stock");
    if (stockAccesorio2 === null) {
        stockAccesorio2 = "0";
    }
    document.getElementById("mostrarStockAccesorio2").textContent = "Stock: " + stockAccesorio2;
});

document.getElementById("selectAccesorioId3").addEventListener("change", function () {
    var select = document.getElementById("selectAccesorioId3");
    var selectedOption = select.options[select.selectedIndex];
    var precioAccesorio3 = selectedOption.getAttribute("data-precio");
    if (precioAccesorio3 === null) {
        precioAccesorio3 = "0";
    }
    document.getElementById("mostrarPrecioAccesorio3").textContent = "Precio: S/." + precioAccesorio3;
    var stockAccesorio3 = selectedOption.getAttribute("data-stock");
    if (stockAccesorio3 === null) {
        stockAccesorio3 = "0";
    }
    document.getElementById("mostrarStockAccesorio3").textContent = "Stock: " + stockAccesorio3;
});

document.getElementById("selectAccesorioId4").addEventListener("change", function () {
    var select = document.getElementById("selectAccesorioId4");
    var selectedOption = select.options[select.selectedIndex];
    var precioAccesorio4 = selectedOption.getAttribute("data-precio");
    if (precioAccesorio4 === null) {
        precioAccesorio4 = "0";
    }
    document.getElementById("mostrarPrecioAccesorio4").textContent = "Precio: S/." + precioAccesorio4;
    var stockAccesorio4 = selectedOption.getAttribute("data-stock");
    if (stockAccesorio4 === null) {
        stockAccesorio4 = "0";
    }
    document.getElementById("mostrarStockAccesorio4").textContent = "Stock: " + stockAccesorio4;
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
