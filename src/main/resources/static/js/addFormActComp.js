/**
 * la fonction addElmt ajoute un element html a un element parent
 *
 * @param {string} elmIdParent selecteur CSS
 * @param {string} noeudCre Type de nodeName / noeud
 * @param {string} attNoeudCre Nom de attibute class du noeud crée
 * @param {string} attNoeudCreName ajout un attribut name
 * @param {string} newContent contenu text de noeudCre, ne prend pas en charge le html
 *
 */
function addElmt(elmIdParent, noeudCre, attNoeudCre,attNoeudCreName="", newContent) {

    let elmAddParent = document.querySelector(elmIdParent);
    let newElm = document.createElement(noeudCre);
    let elmtNewContent = document.createTextNode(newContent);

    newElm.setAttribute('class', attNoeudCre);
    newElm.setAttribute('name', attNoeudCreName);
    newElm.appendChild(elmtNewContent);
    elmAddParent.appendChild(newElm)

}



let addInput = document.querySelector("#addInput");
let competence = document.querySelector("#competence");

let nb=0
addInput.addEventListener("click", ()=>{
    let competenceValueTrim = competence.value.trim();
    if (!competenceValueTrim == "") {

        addElmt("#formActivitesCompetence","input","form-control",`competence${++nb}` ,competenceValueTrim)
        addElmt("#textCompetence","p","form-control",competenceValueTrim,competenceValueTrim)

        //console.log(competenceValueTrim);

    }else{
        console.log("champ vide");
    }

})


let btnActivite = document.querySelector("#btnActivite");
let activite = document.querySelector("#activite");
let nbClick = 0

btnActivite.addEventListener("click", (event) => {
    let activiteValueTrim = activite.value.trim();
    console.log("activiteValueTrim : "+ activiteValueTrim + " |nbClick : "+nbClick );
    event.preventDefault();
    if (!activiteValueTrim == "" && nbClick == 0) {

        addElmt("#formActivitesCompetence","input","form-control",`Activite${nbClick+1}` ,activiteValueTrim)
        addElmt("#textActivite", "p", "form-control", activiteValueTrim, activiteValueTrim)
        nbClick +=1
        console.log("nbClick " + nbClick);

    }
})

