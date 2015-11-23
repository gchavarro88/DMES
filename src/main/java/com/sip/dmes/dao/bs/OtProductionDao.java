/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IOtProduction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;



@Repository(value = "IOtProduction")
public class OtProductionDao implements IOtProduction
{
    private final static Logger log = Logger.getLogger(OtProductionDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    

}
