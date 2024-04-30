document.addEventListener("DOMContentLoaded", function () {
  var botonCarritoSidebar = document.getElementById("boton_carrito_sidebar");
  var label_close_silebar = document.querySelector("label[for='close-silebar-carrito']");

  var contenedor_silebar = document.getElementById("background-silebar-carrito");
  var silevar = document.getElementById("contenedor-silebar-carrito");

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
      // Dentro de la función openSidebar()
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

    // Variable para calcular la cantidad total de productos
    var cantidadTotalProductos = 0;
    // Variable para calcular el precio total de los productos
    var precioTotalProductos = 0;

    // Crear elementos para mostrar en el sidebar
    productosEnCarrito.forEach(function (producto) {
      // Incrementar la cantidad total de productos
      cantidadTotalProductos += producto.cantidad;
      // Incrementar el precio total de los productos
      precioTotalProductos +=
        parseFloat(producto.precio.replace("S/. ", "")) * producto.cantidad;

      // Resto del código para mostrar los productos en el sidebar
      // ...
    });

    // Mostrar la cantidad total de productos y el precio total en el DOM
    document.getElementById("cantidad_productos_obtenidos").textContent =
      cantidadTotalProductos;
    document.getElementById("precio_total_productos").textContent =
      "S/. " + precioTotalProductos.toFixed(2);
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

  function agregarAlCarrito(producto) {
    // Obtener los productos del localStorage
    var productosEnCarrito =
      JSON.parse(localStorage.getItem("productosEnCarrito")) || [];

    // Verificar si el producto ya está en el carrito
    var productoExistente = productosEnCarrito.find(
      (p) => p.nombre === producto.nombre
    );

    if (productoExistente) {
      // Si el producto ya está en el carrito, incrementar su cantidad
      productoExistente.cantidad++;
    } else {
      // Si el producto no está en el carrito, agregarlo con cantidad 1
      producto.cantidad = 1;
      productosEnCarrito.push(producto);
    }

    // Guardar la lista actualizada en el localStorage
    localStorage.setItem(
      "productosEnCarrito",
      JSON.stringify(productosEnCarrito)
    );
  }

  // Obtener el botón de agregar al carrito y agregar un evento de clic
  var botonAgregarCarrito = document.getElementById("boton_carrito_sidebar");
  botonAgregarCarrito.addEventListener("click", function () {
    // Obtener los datos del producto
    var producto = {
      id: Date.now(),
      nombre: document.querySelector("#titulo-fetch").textContent,
      precio: document.querySelector("#precio-fetch").textContent,
      imagen: document.querySelector("#img-fetch").getAttribute("src"),
      cantidad: 1, // Agregamos la propiedad cantidad con valor inicial 1
    };

    // Agregar el producto al carrito
    agregarAlCarrito(producto);

    // Actualizar el sidebar
    openSidebar();
  });
});


window.addEventListener("scroll", function () {
  var stickyDiv = document.querySelector(".sub-nivel-header-mobil");
  var scrollPosition = window.scrollY;

  if (scrollPosition > 197) {
    stickyDiv.style.backgroundColor = "red";
  } else {
    stickyDiv.style.backgroundColor = "black";
  }
});
