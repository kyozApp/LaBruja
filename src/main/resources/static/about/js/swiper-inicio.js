var swiper = new Swiper(".mySwiper", {
    slidesPerView: 9,
    spaceBetween: 25,
    loop: true,
    speed: 2000,
    autoplay: {
        delay: 0,
        disableOnInteraction: false,
        pauseOnMouseEnter: true,
    },    
    lazy: {
        loadOnTransitionStart: true,
        loadPrevNext: true,
    },
});
