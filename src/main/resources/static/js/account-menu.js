document.addEventListener("DOMContentLoaded", function () {
    let menu = document.querySelectorAll(".my-account__menu-item")

    for (let i = 0; i < menu.length; i++) {
        if (location.href.indexOf("account/change/data") > -1) {
            if (menu[i].querySelector("a[href='/account/change/data']")) {
                menu[i].classList.add("active");
            }
        }
        if (location.href.indexOf("account/change/address") > -1) {
            if (menu[i].querySelector("a[href='/account/change/address']")) {
                menu[i].classList.add("active");
            }
        }
        if (location.href.indexOf("account/data") > -1) {
            if (menu[i].querySelector("a[href='/account/data']")) {
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