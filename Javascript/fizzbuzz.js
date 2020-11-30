
function fizz(){
    let number = document.getElementById('usermessage').value; 
    // ↑ we get the values from the text box with the id 'username'
    console.log(number);
    if (number !== null){
        for (var x = 1; x <= number; x++ ){
            var par = document.createElement("p");
            // ↑ create a new tag set "<p></p>" and refer to it in js as 'par'
            var node = document.createTextNode(`${x}`);
            // ↑ node will hold the text we would like to
            if(x % 15 == 0){
                node = document.createTextNode('FIZZBUZZ');
                par.appendChild(node);
                document.getElementById("ans").appendChild(par);
            } 
            else if(x % 3 == 0){
                node = document.createTextNode('FIZZ');
                par.appendChild(node);
                document.getElementById("ans").appendChild(par);
            } 
            else if(x % 5 == 0){
                node = document.createTextNode('BUZZ');
                par.appendChild(node);
                document.getElementById("ans").appendChild(par);
            } 
            else {
                par.appendChild(node);
                document.getElementById("ans").appendChild(par);
            } 
        }
    }
}
function fizzy(){
    let number = document.getElementById('usermessage').value;
    console.log(number);
    if (number !== null){
        for (var x = 1; x<= number; x++ ){
            var par = document.createElement("p");
            par.className = 'results';
            if(x % 15 == 0) document.writeln('FIZZBUZZ');
            if(x % 3 == 0) document.writeln('FIZZ');
            if(x % 5 == 0) document.writeln('BUZZ');
            else document.writeln(x + "<br >");
        }
    }
}
function buzz(){
    let number = document.getElementById('usermessage').value;
    if (number !== null) {
        for (let x = 0; x < number; x++) {
            if(x % 15 == 0) console.log("FIZZBUZZ");
            if(x % 3 == 0) console.log("FIZZ");
            if(x % 5 == 0) console.log("BUZZ");
            console.log(x);
        }
    }
}

  