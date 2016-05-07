/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 * Método encargado de redondear una cantidad recibida a un número de dígitos indicados
 * @author: Gustavo Chavarro Ortiz
 * @param numero valor a redondear
 * @param digitos cantidad de digitos decimales a retornar
 * @return double valor a retornar
 */
function redondear(numero, digitos)
{
    var cifras = 1;
    var position = 0;
    while (position < digitos)
    {
        cifras = 10 * cifras;
        position++;
    }
    var flotante = parseFloat(numero);
    var resultado = Math.round(flotante * cifras) / cifras;
    return resultado;
}

/**
 * Método encargado de de verificar si una cadena esta vacia
 * @author: Gustavo Chavarro Ortiz
 * @param value valor de la cadena a verificar
 * @return boolean indica si esta vacio o no
 */
function isEmpty(value)
{
    var result = true;
    if (value !== undefined && value !== "undefined" &&
            value !== null && value !== 'null')
    {
        value = value.replace(/ /g, '');
        if (value.length > 0)
        {
            result = false;
        }
    }
    return result;
}

/**
 * Método encargado de indicar si una cadena es totalmente numerica
 * @author: Gustavo Chavarro Ortiz
 * @param value cadena recibida a verificar
 * @return boolean inidica si es numerico o no
 */
function isNumeric(value)
{
    var result = false;
    if (/^[0-9]*$/.test(value))
    {
        result = true;
    }
    return result;
}


/**
 * Método encargado de indicar si una cadena es un email
 * @author: Gustavo Chavarro Ortiz
 * @param value cadena recibida a verificar
 * @return boolean inidica si es un email o no
 */
function isEmail(value)
{
    var result = false;
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(value))
    {
        result = true;
    }
    return result;
}



function convertStringToJSON(value)
{
    value = value.replace(/\n/g, '');
    value = JSON.parse(value);
    return value;
}


function setCookie(cname, cvalue, years) 
{
    var d = new Date();
    d.setTime(d.getTime() + (years * 360 * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) 
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1);
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie(nameCookie) 
{
    var cookie = getCookie(nameCookie);
    if (cookie != "") 
    {
        addInfoMessage("Valor Registrado", "El valor de "+nameCookie+" = "+cookie+" ", 5);
        MACHINE_SELECTED = cookie;
        return true;
    } else 
    {
        return false;
    }
}

function getOrderNumber(idValue, creationDate, prefix)
{
    creationDate = creationDate.replace(/ /g, "");
    creationDate = creationDate.replace(/-/g, "");
    creationDate = creationDate.replace(/:/g, "");
    var position = creationDate.indexOf('.');
    creationDate = creationDate.substring(0, position-1);
    return prefix+creationDate+idValue;
}

function getDate(creationDate)
{
    creationDate = creationDate.substring(0, creationDate.length-2);
    return creationDate;
}


function removeAccent(value)
{
    value = value.replace(/á/g, "a");
    value = value.replace(/é/g, "e");
    value = value.replace(/í/g, "i");
    value = value.replace(/ó/g, "o");
    value = value.replace(/ú/g, "u");
    value = value.replace(/Á/g, "A");
    value = value.replace(/É/g, "E");
    value = value.replace(/Í/g, "I");
    value = value.replace(/Ó/g, "O");
    value = value.replace(/Ú/g, "U");
    value = value.replace(/ñ/g, "n");
    value = value.replace(/Ñ/g, "N");
    return value;
}


