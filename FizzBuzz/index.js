const list = document.getElementById("numberList");
const choiceDisplay = document.getElementById("choice");
const choiceInput = document.getElementById("choice-input");
choiceInput.addEventListener("change", handleChangeToNumber);

function handleChangeToNumber(event){
    const number = parseInt(event.target.value);
    choiceDisplay.innerHTML = `Your number: ${number}`;
    //clear list:
    list.innerHTML = "";
        
    for(i = 1; i <= number; i++){
        let innerHtml = i;
        if(i % 3 == 0 && i % 5 == 0){
            innerHtml = "FizzBuzz";
        }else if(i % 3 == 0){
            innerHtml = "Fizz";
        }else if(i % 5 == 0){
            innerHtml = "Buzz";
        }

        const newListItem = document.createElement("li");
        newListItem.innerHTML = "" + innerHtml;
        
        list.appendChild(newListItem);
    }
}