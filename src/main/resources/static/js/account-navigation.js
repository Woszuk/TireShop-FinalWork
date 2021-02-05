(() => {
    let account = document.querySelectorAll(".main-navigation__account-item--link");
    let accountMobile = document.querySelectorAll(".main-navigation__mobile-account .main-navigation__account-item--link");
    let signIn = document.querySelector(".banner__sign-in");
    let closeSignIn = document.querySelector(".banner__sign-in--close");
    let closeRegister = document.querySelector(".banner__register--close");
    let register = document.querySelector(".banner__register");
    let newAccount = document.querySelector(".register");
    let haveAccount = document.querySelector("span.sign-in__check-paragraph");

    function removeClassList(){
        let menu = document.querySelector(".main-navigation__menu--open");
        if(menu!= null){
            menu.classList.toggle("main-navigation__menu--open");
        }
    }

    function toggleSignIn() {
        removeClassList();
        signIn.classList.toggle("banner__sign-in--display");
        scroll();
        window.scrollTo(0,0);
    }

    function toggleRegister() {
        removeClassList();
        register.classList.toggle("banner__sign-in--display")
        scroll();
        window.scrollTo(0,0);
    }

    function toggleSignReg() {
        signIn.classList.toggle("banner__sign-in--display");
        register.classList.toggle("banner__sign-in--display")
    }

    if(accountMobile!= null){
        accountMobile[0].addEventListener("click", toggleSignIn);
        accountMobile[1].addEventListener("click", toggleRegister)
    }

    account[0].addEventListener("click", toggleSignIn);
    account[1].addEventListener("click", toggleRegister)
    closeSignIn.addEventListener("click", toggleSignIn);
    closeRegister.addEventListener("click", toggleRegister);
    newAccount.addEventListener("click", toggleSignReg);
    haveAccount.addEventListener("click", toggleSignReg);
})();