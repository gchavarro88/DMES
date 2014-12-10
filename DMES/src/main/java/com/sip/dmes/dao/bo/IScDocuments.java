/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScDocuments;
import java.util.List;

public interface IScDocuments 
{
    
    public List<ScDocuments> getAllDocuments()throws Exception;
    
    public ScDocuments findByIdDocument(ScDocuments scDocuments)throws Exception;
    
    public ScDocuments findByDocumentName(ScDocuments scDocuments)throws Exception;
    
    public List<ScDocuments> findByIdMachine(ScMachine scMachine)throws Exception;
    
    public void createDocument(ScDocuments scDocuments)throws Exception;
    
    public void updateDocument(ScDocuments scDocuments)throws Exception;
    
    public void deleteDocumentById(ScDocuments scDocuments)throws Exception;
    
    public void deleteDocumentByIdMachine(ScMachine scMachine)throws Exception;

}
