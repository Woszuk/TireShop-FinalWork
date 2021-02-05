document.addEventListener("DOMContentLoaded", function(){
    let scrollSomething = document.querySelectorAll("input[name='scrollTo']");
    let totalPrice = document.querySelector("input[id='totalPrice']");
    let summary = document.querySelector(".summary");
    let changeQuantity = document.querySelectorAll("a.basket__quantity-link");
    let deleteItem = document.querySelectorAll("a.basket__delete-link");
    let addToBasket = document.querySelectorAll("input.menu-shop__content-basket");
    let changeData = document.querySelector(".my-account__form-button");

    console.log(scrollSomething);
    console.log(changeData);

    window.scrollTo(0, scrollSomething[scrollSomething.length-1].value);
    if(totalPrice != null){
        if(totalPrice.value === 'yes' && summary != null){
            summary.classList.add("show");
            summary.querySelector(".summary__position-arrow").classList.add("none-text");
            summary.querySelector(".summary__position-arrow").querySelector(".summary__arrow").classList.add("change__arrow");
            summary.querySelector(".summary__position-arrow").querySelector(".summary__arrow-text").classList.add("none");
        }
    }

    let last_known_scroll_position = 0;

    window.addEventListener("scroll", function(){
        last_known_scroll_position = window.scrollY
    })

    function changeHref(linkToChange){
        for(let i=0; i<linkToChange.length; i++){
            linkToChange[i].addEventListener("click", function(){
                let href = this.getAttribute("href");
                if(summary.classList.contains("show")){
                    this.href = href + "&scrollTo=" + last_known_scroll_position + "&totalPrice=yes";
                }else {
                    this.href = href + "&scrollTo=" + last_known_scroll_position + "&totalPrice=no";
                }
            })
        }
    }

    changeHref(changeQuantity);
    changeHref(deleteItem);

    function changeInputValue(inputToChange){
        for(let i=0; i<inputToChange.length; i++){
            inputToChange[i].addEventListener("click", function(e){
                scrollSomething[i].value = last_known_scroll_position;
            })
        }
    }

    changeInputValue(addToBasket);

    if(changeData != null){
        changeData.addEventListener("click", function (){
            scrollSomething[0].value = last_known_scroll_position;
        })
    }
})