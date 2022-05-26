const phonesURL = '/phones'

let inputs;
let entriesFromServer
let table;
let tableHeader = '<tr><th>ФИО</th><th>Организация</th><th>Телефон</th></tr>';


document.addEventListener("DOMContentLoaded", function(event) {
    inputs = document.querySelectorAll('.name');
    inputs.forEach(function(input, i, inputs){
        input.addEventListener('keyup', getNames)
    });
    table = document.querySelector('table');
    loadData();
});

function getFullNameByUserInput() {
    let userInputFullName = '';
    let userInputFirstName = '';
    let userInputLastName = '';
    let userInputPatronymic = '';
    inputs.forEach(function (input, i, inputs) {
        if (input.name === 'firstName') {
            userInputFirstName += input.value.trim();
        } else if (input.name === 'lastName') {
            userInputLastName += input.value.trim();
        } else {
            userInputPatronymic += input.value.trim();
        }
        userInputFullName = userInputLastName + ' ' + userInputFirstName + ' ' + userInputPatronymic;
    });
    return userInputFullName.trim();
}

function getNames(event) {
    let htmlForTable = tableHeader;
    let userInputFullName = getFullNameByUserInput().toLowerCase();
    console.log(userInputFullName);
    if (userInputFullName === '') {
        table.innerHTML = '';
        return;
    }
    for (let id in entriesFromServer) {
        let name = entriesFromServer[id]['name'];
        console.log(name);
        let organization = entriesFromServer[id]['organization'];
        let number = entriesFromServer[id]['number'];
        if (name.toLowerCase().includes(userInputFullName, 0)) {
            htmlForTable += '<tr><td>' + name + '</td><td>' + organization + '</td><td>' + number + '</td></tr>';
        }
    }
    table.innerHTML = htmlForTable;
}

function loadData() {
    let xhr = new XMLHttpRequest();
    xhr.responseType = 'json';
    xhr.open('GET', phonesURL);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            entriesFromServer = xhr.response;
        }
    }
    xhr.send();
}

function tryToAddContact() {
    //сюда добавить проверку на валидность (проверку по номеру наверное лучше отдать на сервер)
    let pageContent = document.querySelector('html');
    let xhr = new XMLHttpRequest();
    xhr.open('POST', phonesURL);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            pageContent.innerHTML = xhr.response;
        }
    }
    xhr.send();
}
