document.addEventListener("DOMContentLoaded", function () {
    let input = document.querySelectorAll(".contact-form__field");
    let error = document.querySelector(".contact-form__send-error");
    let form = document.querySelector(".contact-form");
    let button = document.querySelector(".contact-form__submit-button");

    button.addEventListener("click", function() {
        document.getElementById("contact").scrollIntoView(true);
    })

    form.addEventListener("submit", function (e) {
        document.getElementById("home").scrollIntoView();
        let valueName = input[0].value;
        let valuePhoneNumber = input[1].value;
        let valueContent = input[2].value;

        if (!valuePhoneNumber.match(/\d{9}|(\d{3}\s){2}\d{3}/g) && valueName !== null && valueContent !== null) {
            document.getElementById("home").scrollIntoView(true);
            console.log(valuePhoneNumber);
            error.classList.remove("contact-form__send-none");
            input[1].classList.add("contact-form__field--error");
            e.preventDefault();
        } 
    })

    input[1].addEventListener("keyup", function () {
        input[1].classList.remove("contact-form__field--error");
    })

})