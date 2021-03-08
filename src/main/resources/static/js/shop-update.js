document.addEventListener("DOMContentLoaded", function () {
    let dots = document.querySelectorAll(".menu__dots");
    let dotsItemEdit = document.querySelectorAll(".menu__dots-item.edit");
    let infoContainer = document.querySelector(".menu-shop__info-container");

    infoContainer.addEventListener("click", function(){
        let dots = document.querySelector(".menu__dots-info");
        dots.classList.remove("info-active");
    })

    for(let i=0; i<dots.length; i++){
        dots[i].addEventListener("click", function(){
            let info = dots[i].parentElement.querySelector(".menu__dots-info");
            info.classList.toggle("info-active");
        })
    }

    dotsItemEdit.forEach(el => el.addEventListener("click" ,function(){
        el.parentElement.parentElement.classList.toggle("info-active")
        let li = el.parentElement.parentElement.parentElement;
        if(!li.classList.contains("item-edit")){
            li.classList.toggle("item-edit")
            changeData(li)
            form(li)
        }
    }))

    function changeData(li) {
        let parameters = li.querySelector(".menu-shop__content-parameters").querySelectorAll(".menu-shop__content-span");
        let details = li.querySelector(".menu-shop__content-detail").querySelectorAll(".menu-shop__content-span");
        for (let i = 0; i < parameters.length; i++) {
            createInputParameter(parameters[i]);
            parameters[i].classList.add("input");
        }

        for (let i = 0; i < details.length - 1; i++) {
            createInputDetail(details[i].querySelector("strong"));
        }

        save(li.querySelector(".menu-shop__content-buy"), li.querySelector(".menu-shop__content-detail"))
    }

    function createInputParameter(parameter) {
        let input = document.createElement("input");
        input.className = ("input__parameters");
        let classList = parameter.classList;
        input.name = classList[1];
        input.setAttribute("th:file","*{" + classList[1] + "}");
        input.required = true;
        input.placeholder = classList[1]
        input.value = parameter.innerHTML;
        parameter.parentElement.appendChild(input);
    }

    function createInputDetail(detail) {
        let input = document.createElement("input");
        input.className = ("input__parameters detail");
        let classList = detail.parentElement.classList;
        input.name = classList[1];
        input.type = "number";
        input.max = 99999;
        input.setAttribute("th:file","*{" + classList[1] + "}");
        input.required = true;
        if(input.name === "quantity"){
            input.min = 0;
        }else {
            input.min = 1;
        }
        input.placeholder = classList[1]
        let detailInner = detail.innerHTML.split('.');
        input.value = detailInner[0];
        detail.innerHTML = "";
        detail.appendChild(input);
    }

    function save(buy, detail) {
        detail.removeChild(buy);
        let input = document.createElement("input");
        input.className = ("menu-shop__content-basket submit")
        input.type = "submit";
        input.value = "Zapisz";
        detail.appendChild(input)
    }

    function form(li) {
        let form = document.createElement("form")
        form.className = ("form__edit")
        form.method = "post"
        form.enctype = "multipart/form-data"
        form.setAttribute("th:object","${shopProduct}");
        form.action = "/shop/edit"

        li.insertBefore(form, li.querySelector(".menu-shop__info-container"));

        let forms = li.querySelector(".form__edit")
        forms.appendChild(li.querySelector(".menu-shop__info-container"))
    }
})