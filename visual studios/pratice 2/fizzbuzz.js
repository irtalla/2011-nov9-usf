var createBtn = document.getElementsByTagName('button')[0];
var holder = document.getElementById('div1')
createBtn.onclick = fizzbuzzs;

function fizzbuzzs(){
var fizz1 = "fizzbuzz";

var numb = document.getElementById('enterednum').value;
;
var div = document.createElement('div');
var divlist = document.createElement('ol');

for( var i = 1; i <= numb; i++){
    
    if((i % 3 == 0)&&(i % 5 == 0)){
        var divhold = document.createElement('li');
        divhold.innerHTML= "fizzbuzz";
        divlist.appendChild(divhold);   
    } 

    else if(i % 3 == 0) {
        var divhold = document.createElement('li');
        divhold.innerHTML= "fizz";
        divlist.appendChild(divhold);   
    } 
    else if(i % 5 == 0) {
        var divhold = document.createElement('li');
        divhold.innerHTML= "buzz";
        divlist.appendChild(divhold);   
    } 
    else {
        var divhold = document.createElement('li');
        divhold.innerHTML= i;
        divlist.appendChild(divhold);   
    } 
}
holder.appendChild(divlist);
createBtn.innerHTML = 'Remove List';
createBtn.onclick = removeList;

}
function removeList(){
    createBtn.removeEventListener('click', removeList);
    holder.innerHTML = '';
    createBtn.innerHTML = 'Create List';
    createBtn.onclick = fizzbuzzs;

}