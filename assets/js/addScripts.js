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

function convertStringToNumber(number) {
    let result = number.replace("+", "");
    result = result.replaceAll("(", "");
    result = result.replaceAll(")", "");
    result = result.replaceAll("-", "");
    if (result.length === 11 && result[0] == 7) {
        result = result.replace("7", "");
    }
    return result;
}

function entryIsValid(entry) {
    if (!entry.name) {
        alert("Нельзя создать безымянный контакт");
        return false;
    }
    if (!entry.number) {
        alert("Нельзя создать контакт без номера телефона");
        return false;
    }
    if (!numberIsValid(number)) {
        alert("Номер содержит недопустимые символы");
        return;
    }
    return true;
}

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
    if (userInputFullName === '') {
        table.innerHTML = '';
        return;
    }
    for (let id in entriesFromServer) {
        let name = entriesFromServer[id]['name'];
        let organization = entriesFromServer[id]['organization'];
        let number = entriesFromServer[id]['numbers'];
        if (name.toLowerCase().includes(userInputFullName, 0)) {
            htmlForTable += '<tr><td>' + name + '</td><td>' + organization + '</td><td>' + number + '</td></tr>';
        }
    }
    table.innerHTML = htmlForTable;
}

function getNumber() {
    let number = document.querySelector(".number").value.trim();
    return convertStringToNumber(number);
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

function numberIsValid(number) {
    return /^\d+$/.test(number)
}

function tryToAddContact() {
    let entry = {
        name: getFullNameByUserInput(),
        organization: document.querySelector(".organization").value.trim(),
        number: getNumber()
    }
    if (!entryIsValid(entry)) {
        return;
    }
    if (!entry.organization) {
        entry.organization = "-";
    }
    let pageContent = document.querySelector('html');
    let xhr = new XMLHttpRequest();
    xhr.open('POST', phonesURL);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            pageContent.innerHTML = xhr.response;
        }
    }
    xhr.send(JSON.stringify(entry));
}
