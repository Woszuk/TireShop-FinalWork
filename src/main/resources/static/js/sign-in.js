document.addEventListener("DOMContentLoaded", function () {
    let inputs = document.querySelectorAll(".banner__register .sign-in__form-input")
    let labels = document.querySelectorAll(".banner__register .sign-in__form-label")
    let fields = document.querySelectorAll(".banner__register .sign-in__field")
    let successLogged = document.querySelector(".banner__sign-in.success")

    setTimeout(function () {
        if (successLogged != null) {
            successLogged.classList.remove("banner__sign-in--display");
        }
    }, 2000);

    function valid(i) {
        if (inputs[i].value.length >= 1) {
            labels[i].classList.add("sign-in__form-input--valid");
        } else {
            labels[i].classList.remove("sign-in__form-input--valid");
        }
    }

    for (let i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener("keyup", function () {
            valid(i);
        })
    }

    let errors = document.querySelectorAll(".banner__register .sign-in__field .sign-in__form-error")
    if (errors.length >= 1) {
        for (let i = 0; i < fields.length; i++) {
            for (let j = 0; j < fields[i].children.length; j++) {
                if (fields[i].children[j].classList.contains("sign-in__form-error")) {
                    fields[i].classList.add("error")
                }
            }
            valid(i);
        }
    }
})