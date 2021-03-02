document.addEventListener("DOMContentLoaded", function () {
    let input = document.querySelector(".postal-code");

    input.addEventListener("keydown", function(event){
        if(input.value.length === 2 && event.key != "Backspace" && event.key != "-"){
            input.value += '-'
        }

        if(input.value.length === 4 && event.key === "Backspace"){
            input.value = input.value.substring(0,input.value.length-1);
        }
    })
})