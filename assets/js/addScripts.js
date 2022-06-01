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

//этот метод говнокод (сайд эфект)
function convertStringToNumber(number) {
    number = number.replace("+", "");
    number = number.replaceAll("(", "");
    number = number.replaceAll(")", "");
    number = number.replaceAll("-", "");
    if (number.length === 11 && number[0] == 7) {
        number = number.replace("7", "");
    }
    console.log(number);
    return /^\d+$/.test(number);
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
    console.log(userInputFullName);
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
    let name = getFullNameByUserInput();
    let organization = document.querySelector(".organization").value.trim();
    let number = document.querySelector(".number").value.trim();
    if (!name) {
        alert("Нельзя создать безымянный контакт");
        return;
    }
    if (!number) {
        alert("Нельзя создать контакт без номера телефона");
        return;
    }
    if (!convertStringToNumber(number)) {
        alert("Номер содержит недопустимые символы");
        return;
    }
    if (!organization) {
        organization = "-";
    }
    let entry = {
        name,
        organization,
        number
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
