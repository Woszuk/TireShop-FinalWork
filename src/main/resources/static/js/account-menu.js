document.addEventListener("DOMContentLoaded", function () {
    let menu = document.querySelectorAll(".my-account__menu-item")

    for (let i = 0; i < menu.length; i++) {
        if (location.href.indexOf("account/change/details") > -1) {
            if (menu[i].querySelector("a[href='/account/change/details']")) {
                menu[i].classList.add("active");
            }
        }
        if (location.href.indexOf("account/change/address") > -1) {
            if (menu[i].querySelector("a[href='/account/change/address']")) {
                menu[i].classList.add("active");
            }
        }
        if (location.href.indexOf("account/details") > -1) {
            if (menu[i].querySelector("a[href='/account/details']")) {
                menu[i].classList.add("active");
            }
        }
        if (location.href.indexOf("account/orders") > -1) {
            if (menu[i].querySelector("a[href='/account/orders']")) {
                menu[i].classList.add("active");
            }
        }
        if (location.href.indexOf("account/change/password") > -1) {
            if (menu[i].querySelector("a[href='/account/change/password']")) {
                menu[i].classList.add("active");
            }
        }
    }
})