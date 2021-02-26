document.addEventListener("DOMContentLoaded", function(){
    let summaryArrow = document.querySelector(".summary__position-arrow");
    let arrow = document.querySelector(".summary__arrow");
    let arrowText = document.querySelector(".summary__arrow-text")
    let summary = document.querySelector(".summary");

    if(summaryArrow != null){
        summaryArrow.addEventListener("click", function(){
            this.classList.toggle("none-text")
            summary.classList.toggle("show");
            arrow.classList.toggle("change__arrow")
            arrowText.classList.toggle("none")
        })
    }
})