/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var factoryLocations = null;

$(document).ready(function () {





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
                        alert(data.object.object1);
                    }
                    else if (data.message === null)
                    {
                        window.parent.addInfoMessage(MESSAGE_TITTLE_ERROR_ADMINISTRATOR, MESSAGE_ERROR_ADMINISTRATOR, 5);
                        var urlLoginPage = "Login.html";
                        $(location).attr('href', urlLoginPage);
                        $.unblockUI();
                    }
                    else
                    {
                        alert("Error no controlado");
                        $.unblockUI();
                    }
                    $.unblockUI();
                })
                .fail(function (data, status)
                {
                    alert("Fabricas no encontradas");
                    $.unblockUI();
                });
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Llamado de funciones
     */
    
});
