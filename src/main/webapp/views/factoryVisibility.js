/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var factoryLocations = null;

function convertStringToJSON(value)
{
    value = value.replace(/\n/g, '');
    value = JSON.parse(value);
    return value;
}

$(document).ready(function () {

    /**
     * Llamado de funciones
     */
    findLocations();
});


/**
 * Metodo encargado de cargar las fabricas en la pantalla
 */
function getFactoryLocations()
{
$.blockUI({message: '<h1>Cargando...</h1>', overlayCSS: {backgroundColor: '#FFF'}});
var url = "../webServices/factoryVisibility/getFactoryLocations.jsp";
$.ajax
        ({
            url: url,
            method: "POST",
            data:{}
        })
        .done(function (data, status)
        {
            data = convertStringToJSON(data);

            if (data.object !== undefined && data.object !== null)
            {
                var divMain = $("#factory");
//                messageSucess();
                var highDiv = screen.height-300 ; 
                var widhDiv = screen.width-600 ; 
                var rows = 1;
                while(Math.pow(rows, 2) <  data.object.length)
                {
                    rows++;
                }
                for(var i=0; i<data.object.length; i++)
                {
                    var child = "<div id='factory"+i+"' style='height:"+((highDiv/rows )-20)+
                            "px; width:"+((widhDiv/rows)-20)+"px;'><div width='100%'>"+
                            data.object[i].object0.object1+"</div></div>";
                    divMain.append(child);
                    
                    $("#factory"+i).addClass("factory");
//                    $("#factory"+i).css("background-color", "#FFF");
                    getMachinesByFactory(data.object[i].object0.object0,"factory"+i,((highDiv/rows )-20), ((widhDiv/rows)-10));
                }
                //console.log(data.object[0].object0.object0 +" "+data.object[1].object0.object0);
            }
            else
            {
                $.unblockUI();
                messageError();
            }
            $.unblockUI();
        })
        .fail(function (data, status)
        {
            $.unblockUI();
            messageError();
        });
}

/**
 * Metodo encargado de cargar las fabricas en la pantalla
 * @param {number} idLocation de la localización
 * @param {number} idDiv de la localización
 * @param {number} height de la localización
 * @param {number} width de la localización
 * @author Gustavo Chavarro Ortiz
 */
function getMachinesByFactory(idLocation, idDiv, height, width)
{
//$.blockUI({message: '<h1>Cargando...</h1>', overlayCSS: {backgroundColor: '#FFF'}});
var url = "../webServices/factoryVisibility/getMachines.jsp";
$.ajax
        ({
            url: url,
            method: "POST",
            data:
            {
                idLocation: idLocation
            }
        })
        .done(function (data, status)
        {
            data = convertStringToJSON(data);

            if (data.object !== undefined && data.object !== null)
            {
                var divMain = $("#"+idDiv);
                messageSucess();
                var highDiv = height;
                var widhDiv = width;
                var rows = 1;

                while(Math.pow(rows, 2) <  data.object.length)
                {
                    rows++;
                }
                for(var i=0; i < data.object.length; i++)
                {
                    var heighDivChildren = ((highDiv/rows)-40);
                    var widthDivChildren = ((widhDiv/rows)-30);
                    var child = "<div id='machine"+i+idDiv+"' style='height:"+heighDivChildren+
                            "px; width:"+widthDivChildren+"px;'><div width='100%'>"+
                            data.object[i].object0.object1+"</div></div>";
                    divMain.append(child);
                    $("#machine"+i+idDiv).addClass("machine");
                    if(data.object[i].object0.object2 === "1") //Activo
                    {
                        $("#machine"+i+idDiv).css("background-color", "#00FF40");
                        $("#machine"+i+idDiv).css("border-color", "#0B610B");
                    }
                    else if(data.object[i].object0.object2 === "2") //Paro
                    {
                        $("#machine"+i+idDiv).css("background-color", "#F78181");
                        $("#machine"+i+idDiv).css("border-color", "#B40404");
                    }
                    else if(data.object[i].object0.object2 === "3") //Mantenimiento
                    {
                        $("#machine"+i+idDiv).css("background-color", "#FE9A2E");
                        $("#machine"+i+idDiv).css("border-color", "#8A2908");
                    }
                    else if(data.object[i].object0.object2 === "0") //Sin Uso
                    {
                        $("#machine"+i+idDiv).css("background-color", "#A4A4A4");
                        $("#machine"+i+idDiv).css("border-color", "#E6E6E6");
                    }
                    $("#machine"+i+idDiv).css("padding", (2+"px"));
                    $("#machine"+i+idDiv).mouseenter(function(){
                        $(this).css("opacity", '0.4');
                    });
                    $("#machine"+i+idDiv).mouseleave(function(){
                        $(this).css("opacity", '1.0');
                    });
                    $("#machine"+i+idDiv).children().css("padding-top", (heighDivChildren/3)+"px");
                }
                //console.log(data.object[0].object0.object0 +" "+data.object[1].object0.object0);
            }
            else
            {
                $.unblockUI();
                messageError();
            }
            $.unblockUI();
        })
        .fail(function (data, status)
        {
            $.unblockUI();
            messageError();
        });
}