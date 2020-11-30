var fizzButton = document.getElementsByTagName('button')[0];
var fizzDiv = document.getElementById('kek');


fizzButton.onclick = printList;

function printList() {
    fizzButton.removeEventListener('click', printList);

    var n = prompt("Fizzbuzz to what number? (Max 100)", "<type integer here>");

    if (n <= 100) {
        var fizzList = document.createElement('ol');

        for (i = 1; i <= n; i++) {
            var seq = document.createElement('li');
            if ((i % 3 == 0) && (i % 5 == 0)) {
                seq.innerHTML = "Fizzbuzz";
            } else if (i % 3 == 0) {
                seq.innerHTML = "Fizz";
            } else if (i % 5 == 0) {
                seq.innerHTML = "Buzz";
            } else {
                seq.innerHTML = i;
            }
            fizzList.appendChild(seq);
        }
        fizzDiv.appendChild(fizzList);
    }

    fizzButton.innerHTML = 'Start Over';
    fizzButton.onclick = removeList;
}

function removeList() {
    fizzButton.removeEventListener('click', removeList);
    fizzDiv.innerHTML = '';
    fizzButton.innerHTML = 'Fizz Buzz';
    fizzButton.onclick = printList;
}
