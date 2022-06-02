const requestURL = '/phones' //test url
// const requestURL = 'https://jsonplaceholder.typicode.com/users' //test url

let input;
let table;
let values;

loadData();

document.addEventListener("DOMContentLoaded", function(event) {
    input = document.querySelector('.name');
    input.addEventListener('keyup', tableUpdate); //change getNamesToUtilsMethod
    table = document.querySelector('table');
});

function tableUpdate() {
    table.innerHTML = getTableContent(values, input.value);
}

function loadData() {
    let xhr = new XMLHttpRequest();
    xhr.responseType = 'json';
    xhr.open('GET', requestURL);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            values = xhr.response;
            tableUpdate();
        }
    }
    xhr.send();
}