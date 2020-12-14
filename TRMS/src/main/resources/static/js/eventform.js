
checkLogin(displayForm);

async function displayForm()
{
    let dataDiv = document.getElementById("data");

    let url = baseUrl + "/events/types/";

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
    let eventForm = document.createElement("div");
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
            option.setAttribute("class", "radiobtn");
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

    appendTextInput(eventForm, "Event Name:", "name");

    let date = document.createElement("input");
    let textDate = document.createElement("label");
    date.setAttribute("type","date");
    date.setAttribute("id", "date");
    textDate.innerHTML = "Date: ";
    eventForm.appendChild(textDate);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(date);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));

    let time = document.createElement("input");
    let textTime = document.createElement("label");
    time.setAttribute("type","time");
    time.setAttribute("id", "time");
    textTime.innerHTML = "Time: ";
    eventForm.appendChild(textTime);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(time);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));

    /*
    let location = document.createElement("input");
    let textLocation = document.createElement("label");
    location.setAttribute("type","text");
    textLocation.innerHTML = "Location: ";
    eventForm.appendChild(textLocation);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(location);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));
    */

    appendTextInput(eventForm, "Location: ", "location");

    /*
    let description = document.createElement("input");
    let textDescription = document.createElement("label");
    description.setAttribute("type","text");
    textDescription.innerHTML = "Description: ";
    eventForm.appendChild(textDescription);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(description);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));
    */

    appendTextInput(eventForm, "Description: ", "description");

    /*
    let cost = document.createElement("input");
    let textCost = document.createElement("label");
    cost.setAttribute("type","number");
    cost.setAttribute("id","cost");
    textCost.innerHTML = "Cost: ";
    eventForm.appendChild(textCost);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(cost);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));
    */

    appendNumericInput(eventForm, "Cost: ", "cost");

    //end of event info

    //begin reimbursement form info
    /*
    let gradeFormat = document.createElement("input");
    let textGradeFormat = document.createElement("label");
    gradeFormat.setAttribute("type", "text");
    textGradeFormat.innerHTML = "Grading Format: ";
    eventForm.appendChild(textGradeFormat);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(gradeFormat);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));
    */
    //appendTextInput(eventForm, "Grading Format: ", "gradeFormat");


    let gradeFormatResponse = await fetch(baseUrl + "/forms/gradingformats/all");
    let allGradeFormats = await gradeFormatResponse.json();

    if (allGradeFormats)
    {
        let typeText = document.createElement("label");
        typeText.innerHTML = "Please Select the Grading Format for the Event";
        eventForm.appendChild(typeText);
        eventForm.appendChild(document.createElement("br"));
        

        for(i of allGradeFormats)
        {
            //console.log(i);
            let option = document.createElement("input");
            option.setAttribute("type","radio");
            option.setAttribute("name", "gradeFormat");
            option.setAttribute("value",i.id);
            option.setAttribute("class", "radiobtn");
            let text = document.createElement("label");
            text.innerHTML = i.name;
            eventForm.appendChild(option);
            eventForm.appendChild(text);
            eventForm.appendChild(document.createElement("br"));
        }
        
    }
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));



    /*
    let passGrade = document.createElement("input");
    let textPassGrade = document.createElement("label");
    passGrade.setAttribute("type", "text");
    textPassGrade.innerHTML = "Passing Grade: ";
    eventForm.appendChild(textPassGrade);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(passGrade);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));
    */
   appendTextInput(eventForm, "Passing Grade: ", "passGrade")
    /*
    let justification = document.createElement("input");
    let textJustification = document.createElement("label");
    justification.setAttribute("type", "text");
    textJustification.innerHTML = "Justification: ";
    eventForm.appendChild(textJustification);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(justification);
    eventForm.appendChild(document.createElement("br"));
    eventForm.appendChild(document.createElement("br"));
    */

    appendTextInput(eventForm, "Justification: ", "justification");

    //appendNumericInput(form, "Reimbursement Amount: ");
    
    appendNumericInput(eventForm, "Work Hours Missed", "hoursMissed");


    let validateButton = document.createElement("button");
    validateButton.textContent = "Submit Form!";
    validateButton.addEventListener("click" , function () {validateData(eventForm);});
    
    
    eventForm.appendChild(validateButton);

    dataDiv.appendChild(eventForm);
}

function appendTextInput(form, description, id)
{
    let element = document.createElement("input");
    let textElement = document.createElement("label");
    let idnum = id;
    element.setAttribute("type", "text");
    element.setAttribute("id", idnum);
    textElement.innerHTML = description;
    form.appendChild(textElement);
    form.appendChild(document.createElement("br"));
    form.appendChild(element);
    form.appendChild(document.createElement("br"));
    form.appendChild(document.createElement("br"));
}

function appendNumericInput(form, description, id)
{
    let element = document.createElement("input");
    let textElement = document.createElement("label");
    let idnum = id;
    element.setAttribute("type", "number");
    element.setAttribute("id", idnum);
    textElement.innerHTML = description;
    form.appendChild(textElement);
    form.appendChild(document.createElement("br"));
    form.appendChild(element);
    form.appendChild(document.createElement("br"));
    form.appendChild(document.createElement("br"));
}

 async function validateData(form)
{

    console.log(form.childNodes);
    valid = false;

    let reimbursetypeId;
    for(e of form.childNodes)
    {
        if(e.name == "eventType")
        {
            if(e.checked == true)
            {
                valid = true;
                reimbursetypeId = e.getAttribute("value");
            }
            else{
                console.log(e.checked);
            }
        }
    }

    

    if(!valid)
    {
        alert("Please select an Event Type!");
        return;
    }
    valid = false;

    let gradeFormatId;
    for(e of form.childNodes)
    {
        if(e.name == "gradeFormat")
        {
            if(e.checked == true)
            {
                valid = true;
                gradeFormatId = e.getAttribute("value");
            }
            else{
                console.log(e.checked);
            }
        }
    }

    if(!valid)
    {
        alert("Please select a Grade Format!");
        return;
    }

    let selectedGradeFormatResponse = await fetch(baseUrl + "/forms/gradingformats/" + gradeFormatId); 
    let selectedGradeFormat = await selectedGradeFormatResponse.json();

    valid = true;
    
    let cost = 0;
    for(e of form.childNodes)
    {
        if (e.tagName == "INPUT")
        {
            if(e.value == "")
            {
                valid = false;
            }
            if (e.id == "cost")
            {
                cost = parseFloat(e.value);
            }
        }
    }

    if(!valid)
    {
        alert("Please fil out all Text Boxes!");
        return;
    }

    let response = await fetch(baseUrl + "/events/types/" + reimbursetypeId);
    let reimbursetype;
    if (response.status === 200)
    {
        reimbursetype = await response.json();
        console.log(reimbursetype);
    }
    else{
        console.log("failed to get type, code " + response.status);
    }

    

    for(e of form.childNodes)
    {
        if (e.getAttribute("type"))
        {
            e.disabled = true;
        }
    }

    let text = document.createElement("label");
    console.log(cost);
    console.log(reimbursetype.reimbursementPercent);

    console.log(parseFloat(reimbursetype.reimbursementPercent));
    let estimatedReimbursement = (parseFloat(reimbursetype.reimbursementPercent) / 100) * cost;

    let reimbursmentLimit = 1000;

    let currentReimbursementResponse = await fetch(baseUrl + "/forms/reimbursement");

    let allReimbursement = await currentReimbursementResponse.json();
    let currentReimbursement = 0;

    for (f of allReimbursement)
    {
        if (user.id == f.employeeId)
        {
            currentReimbursement += f.reimbursementAmount;
        }
    }

    if (currentReimbursement + estimatedReimbursement > reimbursmentLimit)
    {
        estimatedReimbursement = reimbursmentLimit - currentReimbursement;
        if (estimatedReimbursement < 0)
        {
            estimatedReimbursement = 0;
        }
        estimatedReimbursement += " Reimbursement Limit Reached!";
    }


    text.innerHTML = "Estimated reimbursement: " + estimatedReimbursement;
    alert("Estimated reimbursement: " + estimatedReimbursement);

    form.appendChild(document.createElement("br"));
    form.appendChild(document.createElement("br"));
    form.appendChild(text);
    
    /*
    Event DATA:
    id serial primary key,
	name varchar(60),
	event_type_id int references event_type,
	event_date date,
	event_time time,
	event_location varchar(60),
	event_description varchar(200),
	event_cost numeric(10,2)
    */

    /*
    Reimbursement form DATA:
    id serial primary key,
	file_date date,
	employee_id int references employee,
	event_id int references reimbursement_event,
	grading_format_id int references grading_format,
	passing_grade varchar(60),
	justification varchar(200),
	reimbursement_amount numeric(10,2),
	time_missed_hours int,
	stage_id int references stage,
	stage_entry_date date,
	status_id int references status 
    */
    let event;
    let reimbursememntForm;


    let name;
    let type = reimbursetype;
    let date;
    let time;
    let location;
    let description;
    let evcost;
    //end event data


    //end event info
    let today = new Date();
    //begin reimbursement form info
    let id = -1;
    let fileDate = today.getFullYear() + "-" + (today.getMonth()+1) + "-" + (today.getDate()+1);
    let employeeId;
    let eventId;
    let gradingFormat = selectedGradeFormat;
    let passingGrade;
    let justification;
    let reimbursementAmount = estimatedReimbursement;
    let hoursMissed;
    let stage = -1; //get from db
    let stageEntryDate = today.getFullYear() + "-" + (today.getMonth()+1) + "-" + (today.getDate()+1);
    let status = -1; // get from db

    for(e of form.childNodes)
    {
        if (e.tagName == "INPUT")
        {
            
            if(e.id == "name")
            {
                name = e.value;
            }
            else if (e.id == "date")
            {
                date = e.value;
            }
            else if (e.id == "time")
            {
                time = e.value;
            }
            else if (e.id == "location")
            {
                location = e.value;
            }
            else if (e.id == "description")
            {
                description = e.value;
            }
            else if (e.id == "cost")
            {
                evcost = e.value;
            }
            


            else if (e.id == "passGrade")
            {
                passingGrade = e.value;
            }
            else if (e.id == "justification")
            {
                justification = e.value;
            }
            else if (e.id == "hoursMissed")
            {
                hoursMissed = e.value;
            }
            


        }
    }

    time += ":" + today.getSeconds();
    

    //add an extra day to date, compatability sucks
    for(let i = date.length-1;  i >= 0; i--)
    {
        if (date.charAt(i) == "-")
        {
            let cut = date.substring(0,i+1);

            let day = date.substring(i+1);
            day = parseInt(day);
            day += 1;
            date = cut + day.toString();
            //console.log(cut + " " + day);
            break;
        }
    }
    //console.log("Event Date " + date);
    //alert("stop");
    event = {id: -1, name: name, type: type, date: date, time: time};
    

    let eventResponse = await fetch(baseUrl + "/events", {method: "POST", body: JSON.stringify(event)});
    event.id = await eventResponse.json(); 

    console.log(event);

    let stageResponse = await fetch(baseUrl + "/forms/stages/1");
    stage = await stageResponse.json();

    console.log(stage);

    let statusResponse = await fetch(baseUrl + "/forms/statuses/1");
    status = await statusResponse.json();

    console.log(status);
    

    employeeId = user.id;
    eventId = event.id;

    console.log(gradingFormat);

    reimbursememntForm = {id: id, fileDate: fileDate, employeeId: employeeId, eventId: eventId, gradingFormat: gradingFormat, passingGrade: passingGrade, justification: justification, reimbursementAmount: reimbursementAmount, hoursMissed: hoursMissed, stage: stage, stageEntryDate: stageEntryDate, status: status};

    let reimbursementResponse = await fetch(baseUrl + "/forms/reimbursement", {method: "POST", body: JSON.stringify(reimbursememntForm)});
    reimbursememntForm.id = await reimbursementResponse.json();

    console.log(reimbursememntForm);
    
    window.location.replace('homepage.html');

}

