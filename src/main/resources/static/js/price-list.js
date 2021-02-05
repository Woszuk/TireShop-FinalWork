(() => {
    let active = document.querySelectorAll(".price-list__text");
    let paragraph = document.querySelectorAll(".price-list__paragraph");

    for (let i = 0; i < active.length; i++) {
        active[i].addEventListener("click", function(){
            paragraph[i].classList.toggle("price-list__paragraph--active");
            this.classList.toggle("active");
        });
    }

})();