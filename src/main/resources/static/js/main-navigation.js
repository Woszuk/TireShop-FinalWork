(() => {
    let button = document.querySelector(".main-navigation__mobile-button");
    let buttonExit = document.querySelectorAll(".main-navigation__menu-mobile--exit");
    let menu = document.querySelector(".main-navigation__menu");
    let div = document.querySelector(".main-navigation__menu--open");
    let iconNavigate = document.querySelectorAll(".main-navigation__menu-item");
    let account = document.querySelector(".main-navigation__mobile-account");
    let backMenu = document.querySelector(".main-navigation__menu-mobile");

    const toggleClass = () => {
        if(window.outerWidth <= 991){
            menu.classList.toggle("main-navigation__menu--open");
            div = document.querySelector(".main-navigation__menu--open");
        }
    }

    for(let i=0; i<iconNavigate.length-1; i++){
        iconNavigate[i].addEventListener("click", toggleClass);
    }

    const exitMenu = () => {
        div.classList.remove("main-navigation__menu--open");
    }

    const accountToggle = () => {
        if(window.outerWidth <= 991){
            menu.classList.toggle("main-navigation__menu--open");
            account.classList.toggle("main-navigation__menu--open");
            div = document.querySelector(".main-navigation__menu--open");
            backMenu = document.querySelector(".main-navigation__menu-mobile::before");
        }
    }

    iconNavigate[iconNavigate.length-1].addEventListener("click", accountToggle);
    backMenu.addEventListener("click", accountToggle);
    button.addEventListener("click", toggleClass);

    for(let i=0; i<buttonExit.length; i++){
        buttonExit[i].addEventListener("click", exitMenu);
    }
   
})();