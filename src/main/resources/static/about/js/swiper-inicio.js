var swiper = new Swiper(".mySwiper", {
    slidesPerView: 10,
    spaceBetween: 0,
    loop: true,
    freeMode: true,
    lazy: true,
    autoplay: {
        delay: 2000,
        pauseOnMouseEnter: true,
    },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    }
});
