document.addEventListener("DOMContentLoaded", function(){
    let orderRadioList = document.querySelectorAll("input[name=delivery]");
    let itemPrice = document.querySelector(".summary__price-span.itemPrice");
    let deliveryPrice = document.querySelector(".summary__price-span.deliveryPrice");
    let totalPrice = document.querySelector(".summary__price-span.totalPrice");


    for(let i=0; i<orderRadioList.length; i++){
        if(orderRadioList[i].checked){
            deliveryPrice.innerHTML = orderRadioList[i].parentElement.parentElement.parentElement.querySelector(".order__price-span").innerHTML;
            totalPrice.innerHTML = parseInt(deliveryPrice.innerHTML) + parseInt(itemPrice.innerHTML) +".00";
        }
        orderRadioList[i].addEventListener('change', function(){
            if(orderRadioList[i].checked){
                deliveryPrice.innerHTML = orderRadioList[i].parentElement.parentElement.parentElement.querySelector(".order__price-span").innerHTML;
                totalPrice.innerHTML = parseInt(deliveryPrice.innerHTML) + parseInt(itemPrice.innerHTML) +".00";
            }
        })
    }
})