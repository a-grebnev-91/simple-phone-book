const requestURL = '/phones' //test url
// const requestURL = 'https://jsonplaceholder.typicode.com/users' //test url

let input;
let table;
let tableHeader = '<tr><th>ФИО</th><th>Организация</th><th>Телефон</th></tr>';
getNames();

document.addEventListener("DOMContentLoaded", function(event) {
    input = document.querySelector('.name');
    input.addEventListener('keyup', getNames);
    table = document.querySelector('table');
});

//with XMLHttpRequest
function getNames() {
    let xhr = new XMLHttpRequest();
    xhr.responseType = 'json';

    xhr.open('GET', requestURL);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let values = xhr.response;
            let htmlForTable = tableHeader;
            for (let id in values) {
                let name = values[id]['name'];
                let organization = values[id]['organization'];
                let number = values[id]['number'];
                if (input === undefined || name.toLowerCase().includes(input.value.toLowerCase(), 0)) {
                    htmlForTable += '<tr><td>' + name + '</td><td>' + organization + '</td><td>' + number + '</td></tr>';
                }
            }
            table.innerHTML = htmlForTable;
        }
    }
    xhr.send();
}