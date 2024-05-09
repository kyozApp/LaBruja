document.addEventListener("DOMContentLoaded", function () {
  var botonCarritoSidebar = document.getElementById("boton_abrir--slider");
  var label_close_silebar = document.querySelector(
    "label[for='close-silebar-carrito']"
  );
  var contenedor_silebar = document.getElementById(
    "background-silebar-carrito"
  );
  var silevar = document.getElementById("contenedor-silebar-carrito");
  const input = document.getElementById("cantidadProductoInput");

  botonCarritoSidebar.addEventListener("click", function () {
    openSidebar(); // Llama a la función para abrir el sidebar
  });

  label_close_silebar.addEventListener("click", function () {
    closeSidebar(); // Llama a la función para cerrar el sidebar
  });

  function openSidebar() {
    silevar.style.display = "block"; // Mostrar el sidebar
    setTimeout(() => {
      silevar.style.right = "0"; // Mover a la posición inicial a la derecha
    }, 50); // Pequeña espera antes de mover para permitir que se muestre el sidebar primero
    contenedor_silebar.style.display = "block"; // Mostrar el fondo del sidebar

    // Obtener los productos del localStorage
    var productosEnCarrito =
      JSON.parse(localStorage.getItem("productosEnCarrito")) || [];

    // Mostrar los valores en el sidebar
    var contenedorSidebar = document.getElementById(
      "silebar-subcategoria-carrito"
    );

    // Limpiar el contenido anterior del sidebar
    contenedorSidebar.innerHTML = "";

    // Crear elementos para mostrar en el sidebar
    var totalAPagar = 0; // Variable para calcular el total a pagar

    if (productosEnCarrito.length === 0) {
      // Si no hay productos en el carrito, ocultar el mensaje y el botón
      document.getElementById("cantidad_productos_obtenidos").textContent =
        "No hay artículos en su carrito";
      document.getElementById("precio_total_productos").textContent = "";
      document.querySelector(".comprar_productos_carrito").style.display =
        "none";
      document.querySelector(".total_pagar_productos").style.display = "none";
    } else {
      // Si hay productos en el carrito, mostrar los detalles del producto y el total a pagar
      productosEnCarrito.forEach(function (producto) {
        var productoContainer = document.createElement("div");
        var descriptionContainer = document.createElement("div");
        var containerPrecioIncrmento = document.createElement("div");
        var botonEliminarProducto = document.createElement("button");
        botonEliminarProducto.textContent = "X";
        botonEliminarProducto.classList.add("boton-eliminar-carrito");
        botonEliminarProducto.onclick = () => {
          filtradoEliminarProducto(producto.id); // Pasar el ID del producto como argumento
        };
        var cantidadProductoTotal = document.createElement("span");
        cantidadProductoTotal.textContent = `x${producto.cantidad}`;
        var productName = document.createElement("h1");
        var productPrice = document.createElement("p");
        var productImage = document.createElement("img");

        // Configurar los valores de los elementos
        productName.textContent = producto.nombre;
        productPrice.textContent = producto.precio;
        productImage.setAttribute("src", producto.imagen);

        // Agregar clases y estilos si es necesario
        productoContainer.style.display = "flex";
        productoContainer.style.gap = "2rem";
        productoContainer.style.alignItems = "center";
        productoContainer.style.margin = "1rem 1rem";
        productoContainer.style.paddingBottom = "1rem";
        productoContainer.style.borderBottom = "2px dashed red";
        productImage.style.width = "80px";
        productImage.style.height = "80px";
        productImage.style.marginRight = "10px";
        descriptionContainer.style.display = "flex";
        descriptionContainer.style.flexDirection = "column";
        descriptionContainer.style.justifyContent = "space-between";
        descriptionContainer.style.alignItems = "center";
        productName.style.fontSize = "12px";
        productName.style.margin = "0px";
        containerPrecioIncrmento.style.display = "flex";
        containerPrecioIncrmento.style.alignItems = "center";
        cantidadProductoTotal.style.paddingLeft = "11px";

        // Calcular el precio total del producto
        var precioProducto = parseFloat(producto.precio.replace("S/. ", ""));
        var precioTotalProducto = precioProducto * producto.cantidad;
        totalAPagar += precioTotalProducto;

        // Agregar elementos al contenedor de la barra lateral
        productoContainer.appendChild(productImage);
        descriptionContainer.appendChild(productName);
        containerPrecioIncrmento.appendChild(productPrice);
        containerPrecioIncrmento.appendChild(cantidadProductoTotal);
        descriptionContainer.appendChild(containerPrecioIncrmento);
        productoContainer.appendChild(descriptionContainer);
        productoContainer.appendChild(botonEliminarProducto);
        contenedorSidebar.appendChild(productoContainer);
      });

      // Mostrar el total a pagar y el botón COMPRAR PRODUCTOS
      document.getElementById("precio_total_productos").textContent =
        "S/. " + totalAPagar.toFixed(2);
      document.querySelector(".comprar_productos_carrito").style.display =
        "block";
    }

    // Actualizar el contador de productos en el carrito
    actualizarContadorCarrito();
  }

  function filtradoEliminarProducto(id) {
    // Obtener los productos del localStorage
    var productosEnCarrito =
      JSON.parse(localStorage.getItem("productosEnCarrito")) || [];

    // Filtrar el producto que coincide con el ID proporcionado
    var nuevosProductosEnCarrito = productosEnCarrito.filter(
      (producto) => producto.id !== id
    );

    // Guardar los productos actualizados en el localStorage
    localStorage.setItem(
      "productosEnCarrito",
      JSON.stringify(nuevosProductosEnCarrito)
    );

    input.value = 1;

    // Actualizar el sidebar
    openSidebar();
  }

  function closeSidebar() {
    silevar.style.right = "-300px"; // Mover a la derecha para cerrar el sidebar
    setTimeout(() => {
      silevar.style.display = "none"; // Ocultar después de la animación
      contenedor_silebar.style.display = "none"; // Ocultar el fondo del sidebar
    }, 300); // Esperar la duración de la animación (0.3s)
  }

  function actualizarContadorCarrito() {
    // Obtener los productos del localStorage
    var productosEnCarrito =
      JSON.parse(localStorage.getItem("productosEnCarrito")) || [];

    // Calcular la cantidad total de productos
    var cantidadTotalProductos = productosEnCarrito.reduce(function (
      total,
      producto
    ) {
      return total + producto.cantidad;
    },
    0);

    // Actualizar el contador en el DOM
    document.getElementById("cantidad-contador-carrito").textContent =
      cantidadTotalProductos;

    // Actualizar la cantidad de productos en el mensaje del carrito
    var cantidadProductosObtenidos = document.getElementById(
      "cantidad_productos_obtenidos"
    );

    if (cantidadTotalProductos === 0) {
      cantidadProductosObtenidos.textContent = `No hay artículos en su carrito`;
    } else {
      cantidadProductosObtenidos.textContent = `Hay ${cantidadTotalProductos} ${
        cantidadTotalProductos === 1 ? "artículo" : "artículos"
      } en su carrito`;
    }
  }

  actualizarContadorCarrito();
});

// window.addEventListener("scroll", function () {
//   var stickyDiv = document.querySelector(".barra-bus-btn");
//   var scrollPosition = window.scrollY;

//   if (scrollPosition > 197) {
//     stickyDiv.style.backgroundColor = "red";
//   } else {
//     stickyDiv.style.backgroundColor = "black";
//   }
// });
