/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var laskuri = 0;

function lisaa() {
    laskuri++;

    var artikkeli = document.createElement("article");
    var teksti = document.createElement("p");
    teksti.appendChild(document.createTextNode(laskuri + ". " + haeTask("eka")));
    artikkeli.appendChild(teksti);

    document.getElementById("osio").appendChild(artikkeli);
}

function haeTask(tunnus) {
    return String(document.getElementById(tunnus).value);
}