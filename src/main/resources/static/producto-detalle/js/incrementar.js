document.addEventListener("DOMContentLoaded", function () {
  const input = document.getElementById("cantidadProductoInput");
  const btnIncrementar = document.querySelector(".btn-incrementar");
  const btnDecrementar = document.querySelector(".btn-decrementar");
  const botonAgregarCarrito = document.getElementById("boton_carrito_sidebar");
  const label_close_silebar = document.querySelector(
    "label[for='close-silebar-carrito']"
  );
  const contenedor_silebar = document.getElementById(
    "background-silebar-carrito"
  );
  const silevar = document.getElementById("contenedor-silebar-carrito");

  // Variable para almacenar la cantidad de productos
  let cantidadProductos = 1;

  btnIncrementar.addEventListener("click", function () {
    // Obtener el valor actual del input
    const valorActual = parseInt(input.value);
    // Obtener el valor máximo permitido (stock)
    const maximo = parseInt(input.getAttribute("max"));
    // Verificar si el valor actual es menor que el máximo permitido
    if (valorActual < maximo) {
      // Incrementar la cantidad de productos si no se ha alcanzado el máximo
      input.value = valorActual + 1;
      // Actualizar la variable cantidadProductos
      cantidadProductos = parseInt(input.value);
      return;
    }
  });

  btnDecrementar.addEventListener("click", function () {
    // Verificar que la cantidad no sea menor que 1
    if (cantidadProductos > 1) {
      // Decrementar la cantidad de productos
      cantidadProductos -= 1;
      // Actualizar el valor del input
      input.value = cantidadProductos;
    }
  });

  botonAgregarCarrito.addEventListener("click", function () {
    agregarAlCarrito(); // Llama a la función para agregar al carrito
  });

  label_close_silebar.addEventListener("click", function () {
    closeSidebar(); // Llama a la función para cerrar el sidebar
  });

  function openSidebar() {
    // Limpiar el contenido anterior del sidebar
    var contenedorSidebar = document.getElementById(
      "silebar-subcategoria-carrito"
    );
    contenedorSidebar.innerHTML = "";

    silevar.style.display = "block"; // Mostrar el sidebar
    setTimeout(() => {
      silevar.style.right = "0"; // Mover a la posición inicial a la derecha
    }, 50); // Pequeña espera antes de mover para permitir que se muestre el sidebar primero
    contenedor_silebar.style.display = "block"; // Mostrar el fondo del sidebar

    actualizarTotal(); // Actualizar el total al abrir el sidebar

    // Obtener los productos del localStorage
    var productosEnCarrito =
      JSON.parse(localStorage.getItem("productosEnCarrito")) || [];

    // Mostrar los valores en el sidebar
    var contenedorSidebar = document.getElementById(
      "silebar-subcategoria-carrito"
    );

    // Variable para calcular la cantidad total de productos
    var cantidadTotalProductos = 0;
    var totalAPagar = 0;

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
      cantidadProductoTotal.textContent = "x" + producto.cantidad; // Mostrar la cantidad actual del producto
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

      // Incrementar la cantidad total y el precio total de los productos
      cantidadTotalProductos += producto.cantidad;
      totalAPagar +=
        parseFloat(producto.precio.replace("S/. ", "")) * producto.cantidad;
    });

    // Mostrar la cantidad total de productos en el DOM
    document.getElementById("cantidad_productos_obtenidos").textContent =
      cantidadTotalProductos === 0
        ? `No hay artículos en su carrito`
        : `Hay ${cantidadTotalProductos} ${
            cantidadTotalProductos === 1 ? "artículo" : "artículos"
          } en su carrito`;

    // Mostrar la cantidad total de productos en el carrito
    document.getElementById("cantidad-contador-carrito").textContent =
      cantidadTotalProductos;

    // Mostrar o ocultar el mensaje "No hay artículos en su carrito" y el botón "COMPRAR PRODUCTOS"
    if (cantidadTotalProductos === 0) {
      document.querySelector(".total_pagar_productos").style.display = "none";
      document.querySelector(".comprar_productos_carrito").style.display =
        "none";
    } else {
      document.querySelector(".total_pagar_productos").style.display = "block";
      document.querySelector(".comprar_productos_carrito").style.display =
        "block";
    }
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

  function actualizarTotal() {
    var productosEnCarrito =
      JSON.parse(localStorage.getItem("productosEnCarrito")) || [];

    var totalAPagar = 0;
    productosEnCarrito.forEach(function (producto) {
      totalAPagar +=
        parseFloat(producto.precio.replace("S/. ", "")) * producto.cantidad;
    });

    document.getElementById("precio_total_productos").textContent =
      "S/. " + totalAPagar.toFixed(2);
  }
  // Función para agregar un producto al carrito
  function agregarAlCarrito() {
    const cantidad = parseInt(document.querySelector(".custom-input").value);

    const productosEnCarrito =
      JSON.parse(localStorage.getItem("productosEnCarrito")) || [];

    const producto = {
      id: Date.now(),
      nombre: document.querySelector("#titulo-fetch").textContent,
      precio: document.querySelector("#precio-fetch").textContent,
      imagen: document.querySelector("#img-fetch").getAttribute("src"),
      cantidad: cantidad,
    };

    const productoExistente = productosEnCarrito.find(
      (p) => p.nombre === producto.nombre
    );

    if (productoExistente) {
      productoExistente.cantidad = cantidad;
    } else {
      productosEnCarrito.push(producto);
    }

    localStorage.setItem(
      "productosEnCarrito",
      JSON.stringify(productosEnCarrito)
    );

    openSidebar();
  }

  // Evento para mostrar el sidebar cuando se haga clic en "Agregar al carrito"
  botonAgregarCarrito.addEventListener("click", function () {
    openSidebar();
  });
});

// window.addEventListener("scroll", function () {
//     var stickyDiv = document.querySelector(".sub-nivel-header-mobil");
//     var scrollPosition = window.scrollY;

//     if (scrollPosition > 197) {
//       stickyDiv.style.backgroundColor = "red";
//     } else {
//       stickyDiv.style.backgroundColor = "black";
//     }
//   });
