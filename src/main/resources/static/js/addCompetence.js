import {addElmt} from './addFormActComp.js'

let addInput = document.querySelector("#addInput");
let competence = document.querySelector("#competence");


addInput.addEventListener("click", ()=>{
    let competenceValueTrim = competence.value.trim();
    if (!competenceValueTrim == "") {
        addElmt("#formCompetence","input","form-control","competence" ,competenceValueTrim)
        addElmt("#textCompetence","p","form-control",competenceValueTrim,competenceValueTrim)
        //console.log(competenceValueTrim);

    }else{
        console.log("champ vide");
    }

})