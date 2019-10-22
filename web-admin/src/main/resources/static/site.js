function createCell( value){
    let cell = document.createElement("div");
    cell.classList.add("cell");
    cell.innerText = value;
    return cell;
};
function toFormData(form, multipleFile = false) {
    var fdata = new FormData();
    var elements = form.querySelectorAll("input, select, textarea");
    for (var i = 0; i < elements.length; ++i) {
        var element = elements[i];

        var name = element.name;
        var value = element.value;

        if (name) {
            if (element.getAttribute("type") == "file") {
                if (!multipleFile) {
                    fdata.append(name, element.files[0]);
                }
                else {
                    for (var j = 0; j < element.files.length; ++j)
                        fdata.append(name, element.files[j])
                }
            }
            else  if (element.getAttribute("type") == "checkbox"){
                fdata.append(name, element.checked);
            }
            else {
                fdata.append(name, value);
            }
        }
    }
    return fdata;
};

function setTaskData(evt){
    fetch('/tasks/'+evt.currentTarget.id)
        .then(response => response.json())
        .then(response=> {
            console.log(response);
            let taskContainer = document.getElementById('taskInfoTable');
            taskContainer.innerText = "";
            for (const [key, value] of Object.entries(response)) {
                let el = document.createElement("div");
                el.classList.add("row");
                if(key!="log")el.appendChild(createCell(key));
                el.appendChild(createCell(value));
                taskContainer.appendChild(el);
            }
            location.href="#taskInfo";
        });

}
function createTaskEntity(task){
    let el = document.createElement("div");
    el.classList.add("row");
    el.appendChild(createCell(task.id));
    el.appendChild(createCell(task.agentId));
    el.appendChild(createCell(task.name));
    el.appendChild(createCell(task.isActive));
    el.appendChild(createCell(task.completedPercentage));
    el.appendChild(createCell(task.needPercentage));
    el.id = task.id;
    el.addEventListener('click', setTaskData,false)
    return el;
}
function createAgentEntity(task){
    let el = document.createElement("div");
    el.classList.add("row");
    el.appendChild(createCell(task.id));
    el.appendChild(createCell(task.ip));
    el.appendChild(createCell(task.hasTask));
    el.id = task.id;
    return el;
}
function updateDateByTimer(){
    if(location.href.includes('#tasks')){
        fetch('/tasks/list')
            .then(response => response.json())
            .then(response=> {
                let table = document.getElementById("tasks-table");
                for (let task of response) {
                    let row = table.find((r)=>r.id == task.id);
                    if(row) {
                        row.children[3].innerText = task.isActive;
                        row.children[4].innerText = task.completedPercentage;
                    }
                    else{
                        let el = createTaskEntity(task);
                        table.children[0].insertAdjacentElement("afterend", el)
                    }
                }
            });
    }
    setTimeout(updateDateByTimer, 10000);
}
document.addEventListener('DOMContentLoaded', fn => {
    updateDateByTimer();
    document.getElementById("taskLink").addEventListener('click', ev => {
        fetch('/tasks/list')
            .then(response => response.json())
            .then(response=>{
                console.log(response);
                let table = document.getElementById("tasks-table");
                while (table.children.length>1) {
                    table.removeChild(table.lastChild);
                }
                for(let task of response) {
                    let el = createTaskEntity(task);
                    table.children[0].insertAdjacentElement("afterend", el)
                }
            })
            .catch(error => console.error('Error:', error));
    });
    let taskAddForm = document.getElementById('newTaskForm');
    taskAddForm.addEventListener("submit", (e) => {
        e.preventDefault();
        fetch("/tasks/add", {
            method: "POST",
            body: toFormData(taskAddForm, false),
            credentials: 'same-origin',
        }).then((response) => console.log(response));
    });
    document.getElementById("agentLink").addEventListener('click', ev => {
        fetch('/agents/list')
            .then(response => response.json())
            .then(response=>{
                console.log(response);
                let table = document.getElementById("agents-table");
                while (table.children.length>1) {
                    table.removeChild(table.lastChild);
                }
                for(let task of response) {
                    let el = createAgentEntity(task);
                    table.children[0].insertAdjacentElement("afterend", el)
                }
            })
            .catch(error => console.error('Error:', error));
    });
    // document.getElementById("agents-add-btn").addEventListener('click', fn => {
    //     var el = document.createElement("div");
    //     el.classList.add("row");
    //     for (var i = 0; i < 3; i++) {
    //         let input = document.createElement("input");
    //         input.classList.add("cell");
    //         el.appendChild(input);
    //     }
    //     document.getElementById("agents-table").children[0].insertAdjacentElement("afterend", el)
    // });
}, false);