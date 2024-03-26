/*------------------------------------------------------------SWIPER CODE---------------------------------------------------- */
var swiper = new Swiper(".swiper-container", {
  slidesPerView: 4,
  spaceBetween: 4,
  slidesPerGroup: 4,
  loop: true,
  autoplay: {
    delay: 3000,
    disableOnInteraction: false,
  },
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
    dynamicBullets: true,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  breakpoints: {
    0: {
      slidesPerView: 1,
      spaceBetween: 1, // puedes ajustar el espacio entre slides para dispositivos móviles
    },
    375: {
      slidesPerView: 1,
      spaceBetween: 0,
    },
    520: {
      slidesPerView: 2,
      spaceBetween: 15,
    },
    768: {
      slidesPerView: 3,
      spaceBetween: 20,
    },
    1000: {
      slidesPerView: 4,
      spaceBetween: 20,
    },
  },
});
/*------------------------------------------------------------FIN SWIPER------------------------------------------------------- */

/*------------------------------------------------------------Slider Main------------------------------------------------------- */
var swiper = new Swiper(".mySwiper", {
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
});
/*------------------------------------------------------------Cierre de Slide Main------------------------------------------------------- */
