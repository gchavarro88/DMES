/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScPartner;
import java.util.List;

/**
 *
 * @author guschavor
 */
public interface IScPartner
{
    
    public List<ScPartner> findAllPartners() throws Exception;
    
    public ScPartner findPartnerById(ScPartner scPartner) throws Exception;
    
    public void updatePartner(ScPartner scPartner) throws Exception;
    
    public void deletePartner(ScPartner scPartner) throws Exception;
    
    public void createPartner(ScPartner scPartner) throws Exception;
    
    
}
