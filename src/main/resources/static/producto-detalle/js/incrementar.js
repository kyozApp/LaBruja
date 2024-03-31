document.addEventListener('DOMContentLoaded', function () {
    const input = document.querySelector('.custom-input');
    const btnIncrementar = document.querySelector('.btn-incrementar');
    const btnDecrementar = document.querySelector('.btn-decrementar');

    btnIncrementar.addEventListener('click', function () {
        const valorActual = parseInt(input.value);
        const maximo = parseInt(input.getAttribute('max'));
        if (valorActual < maximo) {
            input.value = valorActual + 1;
        }
    });

    btnDecrementar.addEventListener('click', function () {
        const valorActual = parseInt(input.value);
        const minimo = parseInt(input.getAttribute('min'));
        if (valorActual > minimo) {
            input.value = valorActual - 1;
        }
    });
});
