var listDiv = document.getElementById('fizzBuzzDiv');

function runFizzBuzz(){
  listDiv.innerHTML = " ";
  var userNumber = document.getElementById('userNumber').value;
  var ol = document.createElement('ol');

  for(let i = 1; i <= userNumber; i++){
    var li;
    li = document.createElement('li');

    if(i % 3 == 0 && i % 5 != 0){
      li.innerHTML = 'Fizz';
      ol.appendChild(li);
      /*console.log('Fizz');*/
    }
    else if(i % 5 == 0 && i % 3 != 0){
      li.innerHTML = 'Buzz';
      ol.appendChild(li);
      /*console.log('Buzz');*/
    }
    else if(i % 3 == 0 && i % 5 == 0){
      li.innerHTML = 'FizzBuzz';
      ol.appendChild(li);
      /*console.log('FizzBuzz');*/
    }
    else if (i % 3 != 0 && i % 5 != 0){
      li.innerHTML = i;
      ol.appendChild(li);
      /*console.log(i);*/
    }
  }
  listDiv.appendChild(ol);
}
