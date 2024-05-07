/*<![CDATA[*/
document.addEventListener("DOMContentLoaded", function () {
  // Define una función para cargar la parte dinámicamente
  function cargarParteDinamicamente() {
    // Aquí incluye el código HTML que deseas cargar dinámicamente
    var parteHTML = `<li th:each="producto : ${vistaLaptops}"
                        class="productos-filtrados display-none">
                        <a class="link-producto"
                        th:href="@{/catalogo/detalle/producto/{id}(id=${producto.laptopId}, tipo='laptop')}">
                        <div class="div-produc-filt">
                        <div class="cont-img-produc-fitl">
                        <img class="img-produc-search"
                        th:src="'data:image/' + ${producto.typeImage} + ';base64,' + ${producto.base64Image}"
                        alt="imagen-producto-buscado">
                        </div>
                        <div class="cont-detalle-produc-fitl">
                        <h4 class="nombre-produc-search" th:text="${producto.nombre}">
                        </h4>
                        <p class="precio-produc-search">S/.<span
                        th:text="${producto.precio}"></span></p>
                        </div>
                        </div>
                        </a>
                        </li>`;

    // Agrega la parte HTML al contenedor deseado
    var contenedor = document.querySelector(".grupo-productos-busqueda");
    contenedor.innerHTML = parteHTML;
  }

  // Llama a la función para cargar la parte dinámicamente
  cargarParteDinamicamente();
});
/*]]>*/
