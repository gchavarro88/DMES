/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials;

import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScInputDimension;
import com.sip.dmes.entitys.ScInputEquivalence;
import java.util.List;

/**
 *
 * @author gchavarr88
 */
public class ScInputBean 
{

    //Declaración de Variables
    private List<ScInput> inputList;//Lista de insumos de la tabla
    private List<ScInputDimension> dimensionInputList;//Lista de dimensiones del insumo
    private List<ScInputEquivalence> equivalenceInputList; //Lista de equivalencias del insumo
//    private List<ScInput>
    
    
    /**
     * Creates a new instance of ScInputBean
     */
    public ScInputBean() 
    {
        
    }
    
}
