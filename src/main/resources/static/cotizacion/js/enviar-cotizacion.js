document.addEventListener("DOMContentLoaded", function () {
  // Selecciona todos los elementos .cont-personal
  var contenedoresPersonal = document.querySelectorAll(".cont-personal");

  // Itera sobre cada contenedor .cont-personal
  contenedoresPersonal.forEach(function (contenedor) {
    // Dentro de cada contenedor, selecciona los enlaces con la clase .enlace-whatsapp
    var enlaces = contenedor.querySelectorAll(".enlace-whatsapp");

    // Añade un evento click a cada enlace
    enlaces.forEach(function (enlace) {
      enlace.addEventListener("click", function (event) {
        event.preventDefault(); // Evita la redirección por defecto del enlace
        var telefono = enlace.getAttribute("data-tlfno");
        var mensaje = generarCotizacion(); // Suponiendo que esta función ya está definida y retorna el mensaje
        // Genera la URL de WhatsApp con el mensaje
        var url =
          "https://wa.me/51" + telefono + "?text=" + encodeURIComponent(mensaje);
        // Redirige a la URL de WhatsApp
        window.open(url, "_blank");
      });
    });
  });
});

function generarCotizacion() {
  let cotizacion =
    "Buen día, realicé esta cotización dentro de la página, quisiera más información:\n\n";
  let precioFinal = 0;

  // Procesador
  const procesadorSelect = document.getElementById("selectProcesadorId");
  const procesadorOption =
    procesadorSelect.options[procesadorSelect.selectedIndex];
  if (procesadorOption.value !== "") {
    cotizacion += `PROCESADOR: ${
      procesadorOption.text
    }     STOCK: ${procesadorOption.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${procesadorOption.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(procesadorOption.getAttribute("data-precio"));
  }

  // Placa
  const placaSelect = document.getElementById("selectPlacaId");
  const placaOption = placaSelect.options[placaSelect.selectedIndex];
  if (placaOption.value !== "") {
    cotizacion += `PLACA MADRE: ${
      placaOption.text
    }     STOCK: ${placaOption.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${placaOption.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(placaOption.getAttribute("data-precio"));
  }

  // RAM 1
  const ram1Select = document.getElementById("selectRamId");
  const ram1Option = ram1Select.options[ram1Select.selectedIndex];
  if (ram1Option.value !== "") {
    cotizacion += `RAM 1: ${
      ram1Option.text
    }     STOCK: ${ram1Option.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${ram1Option.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(ram1Option.getAttribute("data-precio"));
  }

  // RAM 2
  const ram2Select = document.getElementById("selectRamId2");
  const ram2Option = ram2Select.options[ram2Select.selectedIndex];
  if (ram2Option.value !== "") {
    cotizacion += `RAM 2: ${
      ram2Option.text
    }     STOCK: ${ram2Option.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${ram2Option.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(ram2Option.getAttribute("data-precio"));
  }

  // SSD
  const ssdSelect = document.getElementById("selectAlmacenamientoId");
  const ssdOption = ssdSelect.options[ssdSelect.selectedIndex];
  if (ssdOption.value !== "") {
    cotizacion += `SSD: ${ssdOption.text}     STOCK: ${ssdOption.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${ssdOption.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(ssdOption.getAttribute("data-precio"));
  }

  // Tarjeta
  const tarjetaSelect = document.getElementById("selectTarjetaId");
  const tarjetaOption = tarjetaSelect.options[tarjetaSelect.selectedIndex];
  if (tarjetaOption.value !== "") {
    cotizacion += `TARJETA: ${
      tarjetaOption.text
    }     STOCK: ${tarjetaOption.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${tarjetaOption.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(tarjetaOption.getAttribute("data-precio"));
  }

  // Fuente
  const fuenteSelect = document.getElementById("selectFuenteId");
  const fuenteOption = fuenteSelect.options[fuenteSelect.selectedIndex];
  if (fuenteOption.value !== "") {
    cotizacion += `FUENTE: ${
      fuenteOption.text
    }     STOCK: ${fuenteOption.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${fuenteOption.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(fuenteOption.getAttribute("data-precio"));
  }

  // Case
  const caseSelect = document.getElementById("selectCasseId");
  const caseOption = caseSelect.options[caseSelect.selectedIndex];
  if (caseOption.value !== "") {
    cotizacion += `CASE: ${
      caseOption.text
    }     STOCK: ${caseOption.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${caseOption.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(caseOption.getAttribute("data-precio"));
  }

  // Monitor
  const monitorSelect = document.getElementById("selectMonitorId");
  const monitorOption = monitorSelect.options[monitorSelect.selectedIndex];
  if (monitorOption.value !== "") {
    cotizacion += `MONITOR: ${
      monitorOption.text
    }     STOCK: ${monitorOption.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${monitorOption.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(monitorOption.getAttribute("data-precio"));
  }

  // Refrigeración
  const refrigeracionSelect = document.getElementById("selectRefrigeracionId");
  const refrigeracionOption =
    refrigeracionSelect.options[refrigeracionSelect.selectedIndex];
  if (refrigeracionOption.value !== "") {
    cotizacion += `REFRIGERACIÓN: ${
      refrigeracionOption.text
    }     STOCK: ${refrigeracionOption.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${refrigeracionOption.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(refrigeracionOption.getAttribute("data-precio"));
  }

  // Accesorio 1
  const accesorio1Select = document.getElementById("selectAccesorioId");
  const accesorio1Option =
    accesorio1Select.options[accesorio1Select.selectedIndex];
  if (accesorio1Option.value !== "") {
    cotizacion += `ACCESORIO 1: ${
      accesorio1Option.text
    }     STOCK: ${accesorio1Option.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${accesorio1Option.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(accesorio1Option.getAttribute("data-precio"));
  }

  // Accesorio 2
  const accesorio2Select = document.getElementById("selectAccesorioId2");
  const accesorio2Option =
    accesorio2Select.options[accesorio2Select.selectedIndex];
  if (accesorio2Option.value !== "") {
    cotizacion += `ACCESORIO 2: ${
      accesorio2Option.text
    }     STOCK: ${accesorio2Option.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${accesorio2Option.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(accesorio2Option.getAttribute("data-precio"));
  }

  // Accesorio 3
  const accesorio3Select = document.getElementById("selectAccesorioId3");
  const accesorio3Option =
    accesorio3Select.options[accesorio3Select.selectedIndex];
  if (accesorio3Option.value !== "") {
    cotizacion += `ACCESORIO 3: ${
      accesorio3Option.text
    }     STOCK: ${accesorio3Option.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${accesorio3Option.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(accesorio3Option.getAttribute("data-precio"));
  }

  // Accesorio 4
  const accesorio4Select = document.getElementById("selectAccesorioId4");
  const accesorio4Option =
    accesorio4Select.options[accesorio4Select.selectedIndex];
  if (accesorio4Option.value !== "") {
    cotizacion += `ACCESORIO 4: ${
      accesorio4Option.text
    }     STOCK: ${accesorio4Option.getAttribute(
      "data-stock"
    )}     PRECIO: S/.${accesorio4Option.getAttribute("data-precio")}\n`;
    precioFinal += parseFloat(accesorio4Option.getAttribute("data-precio"));
  }

  cotizacion += `\nPRECIO TOTAL: S/.${precioFinal.toFixed(2)}`;
  return cotizacion;
}
