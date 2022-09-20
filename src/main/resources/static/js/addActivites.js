import {addElmt} from './addFormActComp.js'

let btnActivite = document.querySelector("#btnActivite");
let activite = document.querySelector("#activite");
let nbClick = 0

btnActivite.addEventListener("click", (event) => {
    let activiteValueTrim = activite.value.trim();
    console.log("activiteValueTrim : "+ activiteValueTrim + " |nbClick : "+nbClick );
    event.preventDefault();
    if (!activiteValueTrim == "" && nbClick == 0) {
        addElmt("#formActivites","input","form-control","activites" ,activiteValueTrim)
        addElmt("#nomActivite", "p", "form-control", activiteValueTrim, activiteValueTrim)
        nbClick +=1
        console.log("nbClick " + nbClick);

    }
})