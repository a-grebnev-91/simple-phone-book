function getPrettyNumbers(numbers) {
    let result = [];
    for (number of numbers) {
        number = "" + number;
        let len = number.length;
        if (len == 10) {
            number = "+7 (" + number;
            number = number.slice(0, 7) + ") " + number.slice(7, 10) + "-" + number.slice(10, 12) + "-" + number.slice(12);
        } else if (len == 7) {
            number = number.slice(0, 4) + "-" + number.slice(4, 7) + "-" + number.slice(7);
        } else if (len == 4) {
            number = number.slice(0, 3) + "-" + number.slice(3);
        }
        result.push(number);
        return result;
    }
}
