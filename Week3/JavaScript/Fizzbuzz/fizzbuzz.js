
function fizzbuzz(){
    var input = parseInt(document.getElementById("fizzbuzzInput").value)
    var result = '';
    for (let i = 1; i <= input; i++){
        if (i % 15 == 0){
            result += '<br />FIZZBUZZ';
            console.log('FIZZBUZZ');
        }else if (i%3 == 0){
            result += '<br />FIZZ';
            console.log('FIZZ');
        }else if (i%5 == 0){
            result += '<br />BUZZ';
            console.log('BUZZ');
        }else{
            result += '<br />' + i;
            console.log(i);
        }
    }
    document.getElementById("resultParagraph").innerHTML = result;
    
}

