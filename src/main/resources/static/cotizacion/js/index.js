document.addEventListener('DOMContentLoaded', function () {
    var procesadorSelector = document.getElementById('procesador');
    var precioProcesador = document.getElementById('precioProcesador');

    procesadorSelector.addEventListener('change', function () {
        // Obtener el precio del procesador seleccionado
        var precioSeleccionado = procesadorSelector.options[procesadorSelector.selectedIndex].getAttribute('data-precio');
        // Actualizar el precio en la interfaz
        precioProcesador.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Repetir este proceso para los demás selectores de productos
    // Puedes agregar más eventos change para los otros selectores de productos aquí
    // Segundo select para placas
    var placaSelector = document.getElementById('placa');
    var precioPlaca = document.getElementById('precioPlaca');

    placaSelector.addEventListener('change', function () {
        var precioSeleccionado = placaSelector.options[placaSelector.selectedIndex].getAttribute('data-precio');
        precioPlaca.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Tercer select para memorias RAM
    var ramSelector = document.getElementById('ram');
    var precioRam = document.getElementById('precioRam');

    ramSelector.addEventListener('change', function () {
        var precioSeleccionado = ramSelector.options[ramSelector.selectedIndex].getAttribute('data-precio');
        precioRam.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Cuarto select para tarjetas de video
    var tarjetaSelector = document.getElementById('tarjeta');
    var precioTarjeta = document.getElementById('precioTarjeta');

    tarjetaSelector.addEventListener('change', function () {
        var precioSeleccionado = tarjetaSelector.options[tarjetaSelector.selectedIndex].getAttribute('data-precio');
        precioTarjeta.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Quinto select para almacenamientos
    var almacenamientoSelector = document.getElementById('almacenamiento');
    var precioAlmacenamiento = document.getElementById('precioAlmacenamiento');

    almacenamientoSelector.addEventListener('change', function () {
        var precioSeleccionado = almacenamientoSelector.options[almacenamientoSelector.selectedIndex].getAttribute('data-precio');
        precioAlmacenamiento.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Sexto select para fuentes de poder
    var fuenteSelector = document.getElementById('fuente');
    var precioFuente = document.getElementById('precioFuente');

    fuenteSelector.addEventListener('change', function () {
        var precioSeleccionado = fuenteSelector.options[fuenteSelector.selectedIndex].getAttribute('data-precio');
        precioFuente.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Séptimo select para cases
    var casseSelector = document.getElementById('casse');
    var precioCasse = document.getElementById('precioCasse');

    casseSelector.addEventListener('change', function () {
        var precioSeleccionado = casseSelector.options[casseSelector.selectedIndex].getAttribute('data-precio');
        precioCasse.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Octavo select para monitores
    var monitorSelector = document.getElementById('monitor');
    var precioMonitor = document.getElementById('precioMonitor');

    monitorSelector.addEventListener('change', function () {
        var precioSeleccionado = monitorSelector.options[monitorSelector.selectedIndex].getAttribute('data-precio');
        precioMonitor.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Noveno select para refrigeración
    var refrigeracionSelector = document.getElementById('refrigeracion');
    var precioRefrigeracion = document.getElementById('precioRefrigeracion');

    refrigeracionSelector.addEventListener('change', function () {
        var precioSeleccionado = refrigeracionSelector.options[refrigeracionSelector.selectedIndex].getAttribute('data-precio');
        precioRefrigeracion.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Décimo select para accesorio 1
    var accesorio1Selector = document.getElementById('accesorio1');
    var precioAccesorio1 = document.getElementById('precioAccesorio1');

    accesorio1Selector.addEventListener('change', function () {
        var precioSeleccionado = accesorio1Selector.options[accesorio1Selector.selectedIndex].getAttribute('data-precio');
        precioAccesorio1.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Undécimo select para accesorio 2
    var accesorio2Selector = document.getElementById('accesorio2');
    var precioAccesorio2 = document.getElementById('precioAccesorio2');

    accesorio2Selector.addEventListener('change', function () {
        var precioSeleccionado = accesorio2Selector.options[accesorio2Selector.selectedIndex].getAttribute('data-precio');
        precioAccesorio2.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Duodécimo select para accesorio 3
    var accesorio3Selector = document.getElementById('accesorio3');
    var precioAccesorio3 = document.getElementById('precioAccesorio3');

    accesorio3Selector.addEventListener('change', function () {
        var precioSeleccionado = accesorio3Selector.options[accesorio3Selector.selectedIndex].getAttribute('data-precio');
        precioAccesorio3.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Décimo tercer select para accesorio 4
    var accesorio4Selector = document.getElementById('accesorio4');
    var precioAccesorio4 = document.getElementById('precioAccesorio4');

    accesorio4Selector.addEventListener('change', function () {
        var precioSeleccionado = accesorio4Selector.options[accesorio4Selector.selectedIndex].getAttribute('data-precio');
        precioAccesorio4.textContent = 'Precio: S/. ' + precioSeleccionado;
    });

    // Función para generar la cotización
    // Función para generar la cotización
    function generarCotizacion() {
        // Obtener los valores seleccionados y sus precios
        var procesadorSeleccionado = document.getElementById('procesador').value;
        var precioProcesador = document.getElementById('procesador').options[document.getElementById('procesador').selectedIndex].getAttribute('data-precio');
        var placaSeleccionada = document.getElementById('placa').value;
        var precioPlaca = document.getElementById('placa').options[document.getElementById('placa').selectedIndex].getAttribute('data-precio');
        var ramSeleccionada = document.getElementById('ram').value;
        var precioRam = document.getElementById('ram').options[document.getElementById('ram').selectedIndex].getAttribute('data-precio');
        var tarjetaSeleccionada = document.getElementById('tarjeta').value;
        var precioTarjeta = document.getElementById('tarjeta').options[document.getElementById('tarjeta').selectedIndex].getAttribute('data-precio');
        var almacenamientoSeleccionado = document.getElementById('almacenamiento').value;
        var precioAlmacenamiento = document.getElementById('almacenamiento').options[document.getElementById('almacenamiento').selectedIndex].getAttribute('data-precio');
        var fuenteSeleccionada = document.getElementById('fuente').value;
        var precioFuente = document.getElementById('fuente').options[document.getElementById('fuente').selectedIndex].getAttribute('data-precio');
        var casseSeleccionada = document.getElementById('casse').value;
        var precioCasse = document.getElementById('casse').options[document.getElementById('casse').selectedIndex].getAttribute('data-precio');
        var monitorSeleccionado = document.getElementById('monitor').value;
        var precioMonitor = document.getElementById('monitor').options[document.getElementById('monitor').selectedIndex].getAttribute('data-precio');
        var refrigeracionSeleccionada = document.getElementById('refrigeracion').value;
        var precioRefrigeracion = document.getElementById('refrigeracion').options[document.getElementById('refrigeracion').selectedIndex].getAttribute('data-precio');
        var accesorio1Seleccionado = document.getElementById('accesorio1').value;
        var precioAccesorio1 = document.getElementById('accesorio1').options[document.getElementById('accesorio1').selectedIndex].getAttribute('data-precio');
        var accesorio2Seleccionado = document.getElementById('accesorio2').value;
        var precioAccesorio2 = document.getElementById('accesorio2').options[document.getElementById('accesorio2').selectedIndex].getAttribute('data-precio');
        var accesorio3Seleccionado = document.getElementById('accesorio3').value;
        var precioAccesorio3 = document.getElementById('accesorio3').options[document.getElementById('accesorio3').selectedIndex].getAttribute('data-precio');
        var accesorio4Seleccionado = document.getElementById('accesorio4').value;
        var precioAccesorio4 = document.getElementById('accesorio4').options[document.getElementById('accesorio4').selectedIndex].getAttribute('data-precio');

        // Calcular el precio total
        var precioTotal = parseFloat(precioProcesador) + parseFloat(precioPlaca) + parseFloat(precioRam) + parseFloat(precioTarjeta) + parseFloat(precioAlmacenamiento) + parseFloat(precioFuente) + parseFloat(precioCasse) + parseFloat(precioMonitor) + parseFloat(precioRefrigeracion) + parseFloat(precioAccesorio1) + parseFloat(precioAccesorio2) + parseFloat(precioAccesorio3) + parseFloat(precioAccesorio4);

        // Construir el mensaje de WhatsApp
        var mensaje = "Hola La Bruja Store, realizé una cotización en su página:\n\n";
        mensaje += "Procesador: " + procesadorSeleccionado + " - Precio: S/. " + precioProcesador + "\n";
        mensaje += "Placa: " + placaSeleccionada + " - Precio: S/. " + precioPlaca + "\n";
        mensaje += "Memoria RAM: " + ramSeleccionada + " - Precio: S/. " + precioRam + "\n";
        mensaje += "Tarjeta de Video: " + tarjetaSeleccionada + " - Precio: S/. " + precioTarjeta + "\n";
        mensaje += "Almacenamiento: " + almacenamientoSeleccionado + " - Precio: S/. " + precioAlmacenamiento + "\n";
        mensaje += "Fuente de Poder: " + fuenteSeleccionada + " - Precio: S/. " + precioFuente + "\n";
        mensaje += "Casse: " + casseSeleccionada + " - Precio: S/. " + precioCasse + "\n";
        mensaje += "Monitor: " + monitorSeleccionado + " - Precio: S/. " + precioMonitor + "\n";
        mensaje += "Refrigeración: " + refrigeracionSeleccionada + " - Precio: S/. " + precioRefrigeracion + "\n";
        mensaje += "Accesorio 1: " + accesorio1Seleccionado + " - Precio: S/. " + precioAccesorio1 + "\n";
        mensaje += "Accesorio 2: " + accesorio2Seleccionado + " - Precio: S/. " + precioAccesorio2 + "\n";
        mensaje += "Accesorio 3: " + accesorio3Seleccionado + " - Precio: S/. " + precioAccesorio3 + "\n";
        mensaje += "Accesorio 4: " + accesorio4Seleccionado + " - Precio: S/. " + precioAccesorio4 + "\n";
        mensaje += "Precio Total: S/. " + precioTotal.toFixed(2);

        // Codificar el mensaje para usarlo en un enlace de WhatsApp
        var mensajeCodificado = encodeURIComponent(mensaje);

        // Abrir la ventana de WhatsApp con el mensaje predefinido
        window.open('https://wa.me/51927993661?text=' + mensajeCodificado, '_blank');
    }

    // Obtener el botón de generar cotización
    var botonCotizacion = document.querySelector('button');

    // Agregar un evento de clic al botón
    botonCotizacion.addEventListener('click', generarCotizacion);
});
