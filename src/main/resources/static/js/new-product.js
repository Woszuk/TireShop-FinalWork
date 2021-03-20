document.addEventListener("DOMContentLoaded", function () {
    let parameters = ["brand", "model", "size"];
    let newProduct = document.querySelector(".new-product");
    let ul = document.querySelector(".menu-shop__content-list");

    if(newProduct != null){
    newProduct.addEventListener("click", function(){
            if(document.querySelector(".item-edit") != null){
                changeItemEdit();
            }
            if(document.querySelector(".new-item") === null){
                createItem();
                let newItem = document.querySelector(".menu-shop__content-item.new-item")
                let div = document.createElement("div");
                div.className = "menu-shop__info-container";
                newItem.appendChild(div);
                createInfo(newItem);
                createDetails(newItem)
                form(newItem)
            }else {
                let newItem = document.querySelector(".menu-shop__content-item.new-item");
                let brandFocus = newItem.querySelector(".brand").focus();
                const interval = setInterval(function (){
                    newItem.classList.toggle("green");
                }, 500)
                setTimeout(function (){
                    clearInterval(interval);
                }, 3000)
            }
        })
    }

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

    function createItem (){
        let li = document.createElement("li");
        li.className = "menu-shop__content-item";
        li.classList.add("new-item");
        ul.appendChild(li);
    }

    function createInfo(newItem){
       if(document.querySelector(".img__file") != null && document.querySelector(".img__file-label") != null) {
           document.querySelector(".img__file").remove();
           document.querySelector(".img__file-label").remove();
       }
       let divContainer = newItem.querySelector(".menu-shop__info-container")
       let info = document.createElement("div")
       info.className = "menu-shop__info";
       if(location.href.indexOf("wheel-rim") > -1){
           info.classList.add("wheel-rim--info");
       }
       divContainer.appendChild(info);

       let imgDiv = document.createElement("div")
       imgDiv.className = "menu-shop__img-position"
       if(location.href.indexOf("wheel-rim") > -1){
          imgDiv.classList.add("wheel-rim--position");
       }
       info.appendChild(imgDiv)

       let input = document.createElement("input");
       input.type = "file";
       input.id = "file-upload";
       input.name = "file"
       input.accept = "image/*"
       input.className = "img__file";
       input.onchange = function() {
       if(this.files[0]){
            if(this.files[0].size > 20971520){
               if(newItem.querySelector(".span__error-file") == null){
                   let form = newItem.querySelector("form");
                   let divError = document.createElement("div");
                   divError.className = "span__error-file"
                   divError.innerHTML = "Wybrany plik jest za duży";

                   newItem.insertBefore(divError, form);
                   this.value = "";
               }
           }else {
               let img;
               if(newItem.querySelector(".menu-shop__img") == null){
                   img = document.createElement("img");
                   img.className = "menu-shop__img";
                   if(location.href.indexOf("wheel-rim") > -1){
                        img.classList.add("wheel-rim--img");
                   }
                   newItem.querySelector(".menu-shop__img-position").insertBefore(img, newItem.querySelector(".img__file"))
               }else {
                   img = newItem.querySelector(".menu-shop__img")
               }

               const file = document.querySelector("input[type=file]").files[0];
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
    }

    imgDiv.appendChild(input);

    let label = document.createElement("label")
    label.setAttribute("for", "file-upload")
    label.className = "img__file-label"
    label.innerHTML = "Dodaj zdjęcie";

    imgDiv.appendChild(label);

    let parametersDiv = document.createElement("div")
    parametersDiv.className = "menu-shop__content-parameters";
    info.appendChild(parametersDiv);
    parameters.forEach(el => createInputParameter(newItem, el))
    }


    function createInputParameter(newItem, type) {
        let input = document.createElement("input");
        input.className = ("input__parameters");
        input.classList.add(type)
        input.name = type;
        if(type === "brand"){
            input.autofocus = true;
        }
        input.setAttribute("th:file","*{" + type + "}");
        input.required = true;
        input.placeholder = type
        newItem.querySelector(".menu-shop__content-parameters").appendChild(input);
    }

    function createDetails(newItem) {
        let divContainer = newItem.querySelector(".menu-shop__info-container")
        let divDetails = document.createElement("div");
        divDetails.className = "menu-shop__content-details";
        divContainer.appendChild(divDetails);
        let divDetail = document.createElement("div");
        divDetail.className = "menu-shop__content-detail";
        divDetails.appendChild(divDetail);

        span(divDetail)
        save(divDetail)
    }

    function span(divDetail){
        let span = document.createElement("span");
        span.className = "menu-shop__content-span price position";
        span.innerHTML = "Cena: ";

        let strong1 = document.createElement("strong");
        span.appendChild(strong1);

        createInputDetail(strong1, "price")
        span.innerHTML += " zł/szt."
        divDetail.appendChild(span);

        let span2 = document.createElement("span");
        span2.className = "menu-shop__content-span quantity position";
        span2.innerHTML = "Dostępnych sztuk: "
        divDetail.appendChild(span2);

        let strong2 = document.createElement("strong");
        span2.appendChild(strong2);

        createInputDetail(strong2, "quantity")
    }

    function createInputDetail(strong, type) {
        let input = document.createElement("input");
        input.className = ("input__parameters detail");
        input.name = type;
        input.type = "number";
        input.setAttribute("th:file","*{" + type + "}");
        input.required = true;
        if(input.name === "quantity"){
            input.min = 0;
        }else {
            input.min = 1;
        }
        input.placeholder = type;
        strong.appendChild(input);
    }

    function save(detail) {
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
        form.setAttribute("th:object","${shopProduct}");
        form.enctype = "multipart/form-data"
        form.action = "/shop/new"

        li.insertBefore(form, li.querySelector(".menu-shop__info-container"));

        form.appendChild(li.querySelector(".menu-shop__info-container"))
    }
})