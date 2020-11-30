function fizzbuzzFun() {
    let upperLimit = document.getElementById("uli").value;

    if (upperLimit < 1 || upperLimit > 100) {
        document.getElementById("ull").textContent = "You did not enter a number between 1 and 100. Please enter a number between 1 and 100.";
        return;
    }
    else {
        document.getElementById("ull").textContent = "Please enter a number from 1-100.";
    }

    


    let overallString = ``;

    for (let i = 1; i <= upperLimit; i++) {
        if (i % 15 === 0) {
            overallString += `FIZZBUZZ<br>`;
        }
        else if (i % 3 === 0) {
            //console.log("FIZZ");
            overallString += `FIZZ<br>`;
        }
        else if (i % 5 === 0) {
            //console.log("BUZZ");
            overallString += `BUZZ<br>`;
        }
        else {
            //console.log(i);
            overallString += i + " ";
        }

        let body = document.getElementsByTagName("body")[0];
        let usedP = body.getElementsByTagName("p")[0];

        if (!usedP) {
            usedP = document.createElement("p");
            usedP.innerHTML = overallString;
            body.appendChild(usedP);
        }
        else {
            usedP.innerHTML = overallString;
        }

        var hexcode = '#';
        for (let j = 0; j < 6; j++) {
            var letterOrNumber = Math.floor(Math.random() * 2);
            if (letterOrNumber === 0)
                hexcode += '' + Math.floor(Math.random() * 10);
            else {
                hexcode += '' + String.fromCharCode(Math.floor(Math.random() * 6) + 65);
            }
        }
        usedP.style = "color: " + hexcode;



    }
}