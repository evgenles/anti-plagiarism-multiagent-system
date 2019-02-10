document.addEventListener('DOMContentLoaded', fn=>{
    document.getElementById("agents-add-btn").addEventListener('click',fn=>{
        var el = document.createElement("div");
        el.classList.add("row");
        for(var i = 0; i<3;i++){
            let input = document.createElement("input");
            input.classList.add("cell");
            el.appendChild(input);
        }
        document.getElementById("agents-table").children[0].insertAdjacentElement("afterend",el)
    } );
}, false);