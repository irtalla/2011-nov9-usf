
checkLogin(displayForm);

async function displayForm()
{
    let dataDiv = document.getElementById("data");

    let url = baseUrl + "/events/types/all";

    let response = await fetch(url);

    let eventTypes = null;
    if (response.status === 200)
    {
        eventTypes = await response.json();
        console.log("Got Event Types");
    }
    else
    {
        console.log("Could not get Event Types!");
    }
    //console.log(user);
    //console.log(eventTypes);
    let eventForm = document.createElement("form");
    if (eventTypes)
    {
        let typeText = document.createElement("label");
        typeText.innerHTML = "Please Select the Type of Event (expected % reimbursement)";
        

        for(i of eventTypes)
        {
            //console.log(i);
            let option = document.createElement("input");
            option.setAttribute("type","radio");
            option.setAttribute("name", "eventType");
            option.setAttribute("value",i.id);
            let text = document.createElement("label");
            text.innerHTML = i.name + " (" + i.reimbursementPercent + "%)";
            eventForm.appendChild(option);
            eventForm.appendChild(text);
            eventForm.appendChild(document.createElement("br"));
        }
        dataDiv.appendChild(typeText);
    }

    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));
    let text = document.createElement("label");
    text.innerHTML = "Please Provide the Following Information Regarding the Event.";
    eventForm.appendChild(text);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));


    let date = document.createElement("input");
    let textDate = document.createElement("label");
    date.setAttribute("type","date");
    textDate.innerHTML = "Date: ";
    eventForm.appendChild(textDate);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(date);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));

    let time = document.createElement("input");
    let textTime = document.createElement("label");
    time.setAttribute("type","time");
    textTime.innerHTML = "Time: ";
    eventForm.appendChild(textTime);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(time);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));

    let location = document.createElement("input");
    let textLocation = document.createElement("label");
    location.setAttribute("type","text");
    textLocation.innerHTML = "Location: ";
    eventForm.appendChild(textLocation);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(location);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));

    let description = document.createElement("input");
    let textDescription = document.createElement("label");
    description.setAttribute("type","text");
    textDescription.innerHTML = "Description: ";
    eventForm.appendChild(textDescription);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(description);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));

    let cost = document.createElement("input");
    let textCost = document.createElement("label");
    cost.setAttribute("type","number");
    textCost.innerHTML = "Cost: ";
    eventForm.appendChild(textCost);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(cost);
    

    dataDiv.appendChild(eventForm);
}