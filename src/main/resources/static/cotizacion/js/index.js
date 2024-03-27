document.getElementById('selectId').addEventListener('change', function () {
    var precioProcesador = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioProcesador)) {
        precioProcesador = 0;
    }
    document.getElementById('precioProcesador').textContent = 'Precio: S/. ' + precioProcesador.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('placaId').addEventListener('change', function () {
    var precioPlaca = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioPlaca)) {
        precioPlaca = 0;
    }
    document.getElementById('precioPlaca').textContent = 'Precio: S/. ' + precioPlaca.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('ramId').addEventListener('change', function () {
    var precioRam = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioRam)) {
        precioRam = 0;
    }
    document.getElementById('precioRam').textContent = 'Precio: S/. ' + precioRam.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('almacenamientoId').addEventListener('change', function () {
    var precioAlmacenamiento = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioAlmacenamiento)) {
        precioAlmacenamiento = 0;
    }
    document.getElementById('precioAlmacenamiento').textContent = 'Precio: S/. ' + precioAlmacenamiento.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('tarjetaId').addEventListener('change', function () {
    var precioTarjeta = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioTarjeta)) {
        precioTarjeta = 0;
    }
    document.getElementById('precioTarjeta').textContent = 'Precio: S/. ' + precioTarjeta.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('fuenteId').addEventListener('change', function () {
    var precioFuente = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioFuente)) {
        precioFuente = 0;
    }
    document.getElementById('precioFuente').textContent = 'Precio: S/. ' + precioFuente.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('casseId').addEventListener('change', function () {
    var precioCasse = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioCasse)) {
        precioCasse = 0;
    }
    document.getElementById('precioCasse').textContent = 'Precio: S/. ' + precioCasse.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('monitorId').addEventListener('change', function () {
    var precioMonitor = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioMonitor)) {
        precioMonitor = 0;
    }
    document.getElementById('precioMonitor').textContent = 'Precio: S/. ' + precioMonitor.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('refrigeracionId').addEventListener('change', function () {
    var precioRefrigeracion = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioRefrigeracion)) {
        precioRefrigeracion = 0;
    }
    document.getElementById('precioRefrigeracion').textContent = 'Precio: S/. ' + precioRefrigeracion.toFixed(2);

    calcularPrecioTotal();
});

document.getElementById('accesorioId').addEventListener('change', function () {
    var precioAccesorio = parseFloat(this.options[this.selectedIndex].getAttribute('data-precio'));
    if (isNaN(precioAccesorio)) {
        precioAccesorio = 0;
    }
    document.getElementById('precioAccesorio').textContent = 'Precio: S/. ' + precioAccesorio.toFixed(2);

    calcularPrecioTotal();
});

function calcularPrecioTotal() {
    var precioProcesador = parseFloat(document.getElementById('precioProcesador').textContent.replace('Precio: S/. ', ''));
    var precioPlaca = parseFloat(document.getElementById('precioPlaca').textContent.replace('Precio: S/. ', ''));
    var precioRam = parseFloat(document.getElementById('precioRam').textContent.replace('Precio: S/. ', ''));
    var precioAlmacenamiento = parseFloat(document.getElementById('precioAlmacenamiento').textContent.replace('Precio: S/. ', ''));
    var precioTarjeta = parseFloat(document.getElementById('precioTarjeta').textContent.replace('Precio: S/. ', ''));
    var precioFuente = parseFloat(document.getElementById('precioFuente').textContent.replace('Precio: S/. ', ''));
    var precioCasse = parseFloat(document.getElementById('precioCasse').textContent.replace('Precio: S/. ', ''));
    var precioMonitor = parseFloat(document.getElementById('precioMonitor').textContent.replace('Precio: S/. ', ''));
    var precioRefrigeracion = parseFloat(document.getElementById('precioRefrigeracion').textContent.replace('Precio: S/. ', ''));
    var precioAccesorio = parseFloat(document.getElementById('precioAccesorio').textContent.replace('Precio: S/. ', ''));
    var precioTotal = precioProcesador + precioPlaca + precioRam + precioAlmacenamiento + precioTarjeta + precioFuente + precioCasse + precioMonitor + precioRefrigeracion + precioAccesorio;
    document.getElementById('precioTotal').textContent = 'Precio Total: S/. ' + precioTotal.toFixed(2);
}

