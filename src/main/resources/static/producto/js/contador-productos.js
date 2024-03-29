// Obtener todos los elementos con la clase "detalle-producto"
const productos = document.querySelectorAll('.detalle-producto');

// Obtener el elemento donde se mostrará el mensaje de productos
const mensajeProductos = document.getElementById('mensajeProductos');

// Mostrar el mensaje según la cantidad de productos
if (productos.length === 0) {
    mensajeProductos.innerText = 'No hay productos en Stock';
} else if (productos.length === 1) {
    mensajeProductos.innerText = 'Hay 1 producto.';
} else {
    mensajeProductos.innerText = 'Hay ' + productos.length + ' productos.';
}