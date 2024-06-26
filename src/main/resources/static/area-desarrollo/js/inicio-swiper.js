var swiper = new Swiper(".mySwiper", {
    slidesPerView: 1,
    spaceBetween: 0,
    loop: true,
    // autoplay: {
    //     delay: 2000,
    //     pauseOnMouseEnter: true,
    // },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
    breakpoints: {
        780: {
            slidesPerView: 2
        },
        1120: {
            slidesPerView: 3
        }
    }
});