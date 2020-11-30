let mainDiv = document.getElementById('main');
let fizzButton = document.getElementById('startFizz');
fizzButton.addEventListener('click', fizzBuzz);
fizzButton.addEventListener('click', changeBackground);

function fizzBuzz() {
    fizzButton.removeEventListener('click', fizzBuzz);
   
    let upperLimit = document.getElementById('number').value;
    let resultsDiv = document.createElement('div');

    for (let i = 1; i <= upperLimit; i++) {

        let div = document.createElement('div');

        if (i % 3 == 0 && i % 5 == 0) {
            div.innerHTML = 'FIZZBUZZ';
        } else if (i % 3 == 0) {
            div.innerHTML = 'FIZZ';
        } else if (i % 5 == 0) {
            div.innerHTML = 'BUZZ';
        } else {
           div.innerHTML = i;
        }

        let randomNumber = Math.floor(Math.random() * 16777215).toString(16);
        div.style.color = "#" + randomNumber;
        resultsDiv.appendChild(div);
    }

    mainDiv.appendChild(resultsDiv);

    fizzButton.value = 'Reset';
    fizzButton.addEventListener('click', removeResults);
}

function removeResults() {
    fizzButton.removeEventListener('click', removeResults);
    mainDiv.innerHTML = '';
    fizzButton.value = 'FizzBuzz!';
    fizzButton.addEventListener('click', fizzBuzz);
}

function changeBackground() {
    let randomNumber = Math.floor(Math.random() * 7);
    document.body.style.backgroundImage = "url('pictures/pic" + randomNumber + ".jpg'";
}