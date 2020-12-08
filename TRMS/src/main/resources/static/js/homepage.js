"use strict";

checkLogin(populateData);
function populateData()
{
    console.log("populating Data");
    document.getElementById("welcome").innerHTML = "Welcome " + user.name + "!"
}