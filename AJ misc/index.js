'use strict';

// function greet(name) {
//        console.log('Suh ' + name);
// }

// function square(number) {
//       return number * number;
// }
// let message = "Hello";
// let name = 'AJ'
// let number = 4


// console.log(square(2)); 


// console.log(message + name);
// console.log(`${message}, ${name}. The square of ${number} is ${square(number)}`);


//  let isGreater = number > 1; // > returns true if number is greater than 1
//  alert(isGreater);
// let age = Number(6);
// alert(typeof age);
// alert(age);

// let b = 6
// let a = 5
// let c = 1

// function solve(a, b, c) {
//       var result = (-1 * b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
//       var result2 = (-1 * b - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
//       return result + "<br>" + result2;
// }



// let a = null
// let b = 0
// let c = (a >= b)


// if (c == true) {
//         console.log('Fuck me');
// }



// let name = prompt('What is your name', 'Enter Name Here');
// alert(`Suh, ${name}!`)


// let javaName = prompt('What is the "official" name of JS?');
// if (javaName == ('ECMAScript')) { //must match
//         alert('Dope') ;
// } else if (javaName == ('ecmascript')) {
//         alert('Dope') ;
// } else {
//         alert('What the fuck bro') ;
// }

// let value = prompt('tell me a number');
// if (value > 0 ) {
//         alert('higher');
// } else if (value < 0) {
//         alert('lower');
// } else {
//         alert('0');
// }0


// let value = 45

// if (!(value >= 14 && value <= 90)) {
//         console.log('cool!');
// }




// let userName = prompt("Who's there?");
// if (userName == 'Admin') {
//         let userEnteredPassword = prompt("Password");
//         if (userEnteredPassword == 'TheMaster') {
//                 alert('Success');
//         } else if (userEnteredPassword == '' || userEnteredPassword == null) {
//                 alert('Cancelled');
//         } else {
//                 alert('Login Failed');
//         }
        
// } else if (userName == null) {
//         alert('Cancelled');
// }       else {
//         alert("I don't know you!");
// }


// let i = 0
// while (i < 3) {
//         alert(i);
//         ++i;
// }


// for (let i = 1; i <= 10; i++) {
//         if (i % 2 == 0) {
//                 alert (i);
//         }
// }


// let i = 0;
// while ( i < 3) {
//         alert( `number ${i}!` );
//         i++;
//       }


// let num; 

// do {
//         num = prompt('enter num over 100');
// } while (num <= 100 && num); 



// let n = prompt('solve for all prime numbers up to this number');

// nextPrime:
// for (let i = 2; i <= n; i++) { // for each i...

//   for (let j = 2; j < i; j++) { // look for a divisor..
//     if (i % j == 0) continue nextPrime; // not a prime, go next i
//   }

//   alert( i ); // a prime
// }


// let browser = prompt('What is your browser?');

// if (browser == 'Chrome' 
// || browser == 'FireFox' 
// || browser == 'Safari' 
// || browser == 'Opera') {
//         alert('We Support these too');
// } else if (browser == 'Edge') {
//         alert("You've got the Edge!");
// } else {
//         alert('This Browser is unsupported');
// }




// let a = prompt('a?')
// switch (a) {
//         case '0':
//                 alert ('0');
//                 break;
        
//         case '1': 
//                 alert ('1');
//                 break;
//         case '2':
//         case '3':
//                 alert ('2,3');
//                 break;
// }






// function square (x, n) {
//         let result = x;

//         for (let i = 1; i < n; i++) {
//                 result *= x;
//         }
//         return result;
// }

// let x = prompt('x');
// let n = prompt('power');

// if (n < 1) {
//   alert(`Power ${n} is not supported, use a positive integer`);
// } else {
//   alert( square(x, n) );
// }





// function square (x, n) {
//         let result = (x ** n);
//         return result
// }

// let x = prompt('x');
// let n = prompt('n');

// if (n < 1) {
//   alert(`Power ${n} is not supported, use a positive integer`);
// } else {
//   alert( square(x, n) );
// }



// function ask(question, yes, no) {
//         if (confirm(question)) yes()
//         else no();
//       }
      
//       function showOk() {
//         alert( "You agreed." );
//       }
      
//       function showCancel() {
//         alert( "You canceled the execution." );
//       }
      
//       // usage: functions showOk, showCancel are passed as arguments to ask
//       ask("Do you agree?", showOk, showCancel);


// function ask(question, yes, no) {
//         if (confirm(question)) yes()
//         else no()
// }

// ask(
//         "Do you agree?", 
//         () => alert('you agreed'),
//         () => alert('You cancelled the execution')
// );


// let salaries = {
//         John: 100,
//         Ann: 160,
//         Pete: 130
//       }

// let sum = 0;
// for (let key in salaries) {
//   sum += salaries[key];
// }
// alert(sum)




// let menu = {
//         width: 200,
//         height: 300,
//         title: "My menu"
//       };

// function mult () {
//         for ( let key in menu) {
//              if ( typeof menu[key] == 'number') {
//                 menu[key] += menu[key];
//                 console.log(key);
//                 console.log(menu[key]);
                
//         } else {
//                 console.log(key);
//                 console.log(menu[key])
//              }

//         }
// }
// mult ()
// console.log (menu.width )



// this.a/b are set as props of the calc object, then the functions inside edit the values
// let calculator = {
//         read() {
//               this.a = +prompt('a?');
//               this.b = +prompt('b?');
//         },
//         sum() {
//                 return this.a + this.b;

//         },
//         mul() {
//                 return this.a * this.b;
//         },
// }
// calculator.read();
// alert( calculator.sum() );
// alert( calculator.mul() );
// alert(typeof calculator.a)


// let obj = {
//         // toString handles all conversions in the absence of other methods
//         toString() {
//           return "2";
//         }
//       };
      
//       alert( obj + 2);


// function Calculator() {

//   this.read = function() {
//     this.a = +prompt('a?', 0);
//     this.b = +prompt('b?', 0);
//   };

//   this.sum = function() {
//     return this.a + this.b;
//   };

//   this.mul = function() {
//     return this.a * this.b;
//   };
// }

// let calculator = new Calculator();
// calculator.read();

// alert( "Sum=" + calculator.sum() );
// alert( "Mul=" + calculator.mul() );


// function Accumulator (startingValue) {
//         this.value = startingValue;
//         this.read = function() {
//                 this.value += +prompt('value?')
//         };


// };
// let accumulator = new Accumulator(6); // initial value 1

// accumulator.read(); // adds the user-entered value
// accumulator.read(); // adds the user-entered value

// alert(accumulator.value); // shows the sum of these values



// let user = {
//         name: 'john'};

// function ucFirst(user){
//         for (let key in user) {
//                 return user[key][0].toUpperCase() + user[key].slice(1)
//         };
// };
// alert (ucFirst(user))



// // spam filter/word searcher
// let spamWords = [
//         'viagra',
//         'xxx',
//         'titties'
// ];

// function checkSpam (bodyOfEmail) {
//         for (let i=0; i<= spamWords.length; i++) {

//                 let y = i
//                 let x = bodyOfEmail.toLowerCase()
                        
//                 if ((x.includes(spamWords[y])) == true) {
//                 return ('true: ' + (spamWords[y]));
                
//                 }
//         };
// };    

// console.log (checkSpam('buy ViAgRA now'))
// console.log (checkSpam('free xxxxx'))
// console.log (checkSpam("innocent rabbit"))
// console.log (checkSpam("innocent rabbit xxx"))
// console.log (checkSpam('buy ViAgRA now'))
// console.log (checkSpam('titties'))
// let x = 0;
// function logMessageFromInput() {
//     let message = document.getElementById('messageInput').value;
//     if (message < 1 || message > 100) {
//         console.log ("Please input a number between 1 and 100")
//     } else {
//         x =message +1
//         for (let i = 1; i <=message; i++) {
//             if (i % 3 == 0 && i % 5 == 0) {
//                 console.log ("Fizzbuzz");
//             } else if (i % 3 == 0) {
//                 console.log ("Fizz");
//             } else if (i % 5 == 0) {
//                 console.log ("Buzz");
//             } else {
//                 console.log(i);
//             }
//         }
//     }

// }




var createTableBtn = document.getElementsByTagName('button')[0];
var tableDiv = document.getElementById('tableDiv');

createTableBtn.onclick = createTable;


function createTable() {
    createTableBtn.removeEventListener('click', createTable);
    let message = document.getElementById('messageInput').value;

    var table = document.createElement('table');

    for (let i = 0; i <=message; i++) {
        var tr;
        tr = document.createElement('tr');
        var td;
        if (i==0) {
            td = document.createElement('th');
            td.innerHTML = `FizzBuzz up to ${message}`;
            tr.appendChild(td);
        }
        else if (i % 3 == 0 && i % 5 == 0)  {
            td = document.createElement('td');
            td.innerHTML = 'FizzBuzz';
            tr.appendChild(td);
        }else if (i % 3 == 0) {
            td = document.createElement('td');
            td.innerHTML = 'Fizz';
            tr.appendChild(td);
        }else if (i % 5 == 0) {
            td = document.createElement('td');
            td.innerHTML = 'Buzz';
            tr.appendChild(td);
        }else {
            td = document.createElement('td');
            td.innerHTML = i;
            tr.appendChild(td);
        }
        table.appendChild(tr);

    }
    tableDiv.appendChild(table);

    createTableBtn.innerHTML = 'Remove Table';
    createTableBtn.onclick = removeTable;
}

function removeTable() {
    createTableBtn.removeEventListener('click', removeTable);
    tableDiv.innerHTML = '';
    createTableBtn.innerHTML = 'Create Table';
    createTableBtn.onclick = createTable;
}



// console.log('hello world')