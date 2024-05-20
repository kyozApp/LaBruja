document.addEventListener("DOMContentLoaded", function () {
  const slideTrack = document.querySelector(".slide-track");
  const slides = document.querySelectorAll(".slide");

  const slideWidth = 200; // El ancho de cada slide
  const numSlides = slides.length; // Número de slides
  const totalWidth = slideWidth * numSlides; // Ancho total del track

  // Establece el ancho del track
  slideTrack.style.width = `${totalWidth}px`;

  // Crear una nueva hoja de estilo
  const styleSheet = document.createElement("style");
  document.head.appendChild(styleSheet);

  // Añadir la nueva regla de keyframes
  const keyframes = `
        @keyframes scroll {
            0% {
                transform: translateX(0);
                -webkit-transform: translateX(0);
            }
            100% {
                transform: translateX(-${totalWidth / 2}px);
                -webkit-transform: translateX(-${totalWidth / 2}px);
            }
        }
    `;
  styleSheet.sheet.insertRule(keyframes, styleSheet.sheet.cssRules.length);

  // Actualizar la animación en el slide-track
  slideTrack.style.animation = `scroll 40s linear infinite`;
  slideTrack.style.webkitAnimation = `scroll 40s linear infinite`;
});
