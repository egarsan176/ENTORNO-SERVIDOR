/**
 * Este código crea un reloj que se actualiza cada segundo y lo muestra por pantalla
 */

let espacio = document.getElementById("reloj");

window.addEventListener("onload", reloj);

function reloj() {
    let  fechaActual = new Date();
    
    let horas = fechaActual.getHours();
    let minutos = fechaActual.getMinutes();
    let segundos = fechaActual.getSeconds();
  
    // // Si la hora, minuto o segundo son menores que 10, se añade un 0
    if(horas < 10) { horas = '0' + horas; }
    if(minutos < 10) { minutos = '0' + minutos; }
    if(segundos < 10) { segundos = '0' + segundos; }

  
    espacio.textContent = "Son las "+horas+":" +minutos+":"+segundos +".";
  }

setInterval(reloj,1000);

