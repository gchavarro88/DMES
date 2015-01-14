/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScDocuments;
import com.sip.dmes.entitys.ScUsers;
import java.util.List;

/**
 *
 * @author gchavarro88
 */
public interface IFsDocuments
{
    public void createDocument(ScDocuments scDocuments)throws Exception;
    
    public void updateDocument(ScDocuments scDocuments)throws Exception;
    
    public void deleteteDocumentById(ScDocuments scDocuments)throws Exception;
    
    public List<ScDocuments> getAllDocumentsByUser(ScUsers  scUser)throws Exception;
    
}
