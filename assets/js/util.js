let tableHeader = '<tr><th>ФИО</th><th>Организация</th><th>Телефон</th></tr>';

function getPrettyNumbers(numbers) {
    let result = [];
    for (number of numbers) {
        let len = number.length;
        if (len == 10) {
            number = "+7 (" + number;
            number = number.slice(0, 7) + ") " + number.slice(7, 10) + "-" + number.slice(10, 12) + "-" + number.slice(12);
        } else if (len == 7) {
            number = number.slice(0, 3) + "-" + number.slice(3, 5) + "-" + number.slice(5);
        } else if (len == 4) {
            number = number.slice(0, 2) + "-" + number.slice(2);
        }
        result.push(number);
    }
    return result;

}

function getTableContent(entries, userInput) {
    let htmlForTable = tableHeader;
    for (let id in entries) {
        let entry = entries[id];
        let name = entry.name;
        let organization = entry.organization;
        if (!organization) {
            organization = "-";
        }
        let numbers = entry.numbers;
        numbers = splitNumbers(numbers);
        numbers = getPrettyNumbers(numbers);
        numbers = numbers.join("</br>");
        if (name.toLowerCase().includes(userInput.toLowerCase(), 0)) {
            htmlForTable += '<tr><td>' + name + '</td><td>' + organization + '</td><td>' + numbers + '</td></tr>';
        }
    }
    return htmlForTable;
}

function splitNumbers(numbers) {
    let result = numbers.split(",");
    for (number of numbers) {
        number = number.trim();
    }
    return result;
}
