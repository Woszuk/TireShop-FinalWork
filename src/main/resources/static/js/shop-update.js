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
        if(document.querySelector(".new-item") != null){
            document.querySelector(".new-item").remove();
        }
        el.parentElement.parentElement.classList.toggle("info-active")
        let li = el.parentElement.parentElement.parentElement;
        if(!li.classList.contains("item-edit")){
            if(document.querySelector(".item-edit")){
                changeItemEdit()
            }
            li.classList.toggle("item-edit")
            createInputFile(li)
            changeData(li)
            form(li)
        }
    }))

    function changeItemEdit(){
        let liItemEdit = document.querySelector(".menu-shop__content-item.item-edit");
        liItemEdit.classList.remove("item-edit");
        if(liItemEdit.querySelector(".menu-shop__img.new") != null){
            liItemEdit.querySelector(".menu-shop__img.new").remove();
        }
        if(liItemEdit.querySelector(".span__error-file") != null){
            liItemEdit.querySelector(".span__error-file").remove();
        }
        liItemEdit.querySelector(".menu-shop__content-basket.submit").remove();
        let detail = liItemEdit.querySelectorAll(".input__parameters.detail");
        let detailStrong = liItemEdit.querySelectorAll("strong");
        detailStrong[0].innerHTML = detail[0].value + ".00";
        detailStrong[1].innerHTML = detail[1].value;

        liItemEdit.querySelectorAll(".input__parameters").forEach(el => el.remove());
        liItemEdit.querySelectorAll(".input").forEach(el => el.classList.remove("input"));
    }

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

    function createInputFile (li){
        if(document.querySelector(".img__file") != null){
            document.querySelector(".img__file").remove();
            document.querySelector(".img__file-label").remove();
        }
        let input = document.createElement("input");
        input.type = "file";
        input.id = "file-upload";
        input.name = "file"
        input.accept = "image/*"
        input.className = "img__file";
        li.querySelector(".menu-shop__img-position").appendChild(input);
        input.onchange = function() {
            if(this.files[0].size > 20971520){
                if(document.querySelector(".span__error-file") == null){
                    let form = li.querySelector("form");
                    let divError = document.createElement("div");
                    divError.className = "span__error-file"
                    divError.innerHTML = "Wybrany plik jest za duży";

                    li.insertBefore(divError, form);
                    this.value = "";
                }
            }else {
                let img;
                if(li.querySelector(".menu-shop__img.new") == null){
                    img = document.createElement("img");
                    img.className = "menu-shop__img new";
                    li.querySelector(".menu-shop__img-position").insertBefore(img, li.querySelector(".img__file"))
                }else {
                    img = li.querySelector(".menu-shop__img.new")
                }
                const file = document.querySelector('input[type=file]').files[0];
                const reader = new FileReader();

                reader.addEventListener("load", function () {
                    img.src = reader.result;
                }, false);

                if (file) {
                    reader.readAsDataURL(file);
                }
                if(document.querySelector(".span__error-file") != null){
                    document.querySelector(".span__error-file").remove();
                }
            }
        }

        let label = document.createElement("label")
        label.setAttribute("for", "file-upload")
        label.className = "img__file-label"
        label.innerHTML = "Wybierz";

        li.querySelector(".menu-shop__img-position").appendChild(label);
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
        buy.classList.add("input")
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

        form.appendChild(li.querySelector(".menu-shop__info-container"))
    }
})