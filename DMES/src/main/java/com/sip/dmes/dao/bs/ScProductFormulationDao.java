/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScProductFormulation;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScEmployee;
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScProcessProduct;
import com.sip.dmes.entitys.ScStore;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */

@Repository("IScProductFormulation")
public class ScProductFormulationDao  implements  IScProductFormulation
{
    
    private final static Logger log = Logger.getLogger(ScProductFormulationDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    
    public List<ScProductFormulation> getAllProductFormulations() throws Exception
    {
        List<ScProductFormulation> result = null;
        Query query  = entityManager.createNamedQuery("ScProductFormulation.findAll"); 
        try
        {
            result = (List<ScProductFormulation>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los productos",e);
        }
        return result;
    }

    @Override
    @Transactional 
    public void saveProductFormulation(ScProductFormulation productFormulation) throws Exception
    {
        try
        {
            entityManager.persist(productFormulation.getDimension());
            entityManager.persist(productFormulation);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los productos",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteProductFormulation(ScProductFormulation productFormulation) throws Exception
    {
        try
        {
            entityManager.remove(entityManager.contains(productFormulation)?productFormulation:entityManager.merge(productFormulation));
            entityManager.remove(entityManager.contains(productFormulation.getDimension())?productFormulation:entityManager.merge(productFormulation.getDimension()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los productos",e);
        }
    }

    @Override
    @Transactional
    public void updateProductFormulation(ScProductFormulation productFormulation) throws Exception
    {
        try
        {
            if(productFormulation.getProcessProducts() != null && !productFormulation.getProcessProducts().isEmpty())
            {
                for(ScProcessProduct iterator: productFormulation.getProcessProducts())
                {
                    entityManager.merge(iterator);
                }
            }
            entityManager.merge(productFormulation);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los productos",e);
        }
    }

    @Override
    public List<ScPartner> getAllPartners() throws Exception
    {
        List<ScPartner> result = null;
        Query query  = entityManager.createNamedQuery("ScPartner.findAll"); 
        try
        {
            result = (List<ScPartner>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los proveedores",e);
        }
        return result;
    }

    @Override
    public List<ScCostCenter> getAllCostCenter() throws Exception
    {
        List<ScCostCenter> result = null;
        Query query  = entityManager.createNamedQuery("ScCostCenter.findAll"); 
        try
        {
            result = (List<ScCostCenter>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los centros de costo",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveCostCenter(ScCostCenter costCenter) throws Exception
    {
        try
        {
            entityManager.persist(costCenter);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de un centro de costos",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void savePackingUnit(ScPackingUnit packingUnit) throws Exception
    {
        try
        {
            entityManager.persist(packingUnit);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una unidad de empaque",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void saveLocation(ScLocation location) throws Exception
    {
        try
        {
            entityManager.persist(location);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una localización de un producto",e);
            throw e;
        }
    }

    @Override
    public List<ScPackingUnit> getAllPackingUnits() throws Exception
    {
        List<ScPackingUnit> result = null;
        Query query  = entityManager.createNamedQuery("ScPackingUnit.findAll"); 
        try
        {
            result = (List<ScPackingUnit>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia las unidades de empaque",e);
        }
        return result;
    }


    @Override
    public List<ScStore> getAllStores() throws Exception
    {
        List<ScStore> result = null;
        Query query  = entityManager.createNamedQuery("ScStore.findAll"); 
        try
        {
            result = (List<ScStore>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los almacenes",e);
        }
        return result;
    }


    @Override
    public List<ScPriority> getAllPrioritys() throws Exception
    {
        List<ScPriority> result = null;
        Query query  = entityManager.createNamedQuery("ScPriority.findAll"); 
        try
        {
            result = (List<ScPriority>) query.getResultList();
        }
        catch (Exception e) 
        {
            log.error("Error al intentar hacer la persistencia de las prioridades",e);
        }
        return result;
    }
 
    @Override
    public List<ScLocation> getAllInputLocations(ScStore store) throws Exception
    {
        List<ScLocation> result = null;
        Query query  = entityManager.createNamedQuery("ScLocation.findByStore"); 
        query.setParameter("store", store);
        try
        {
            result = (List<ScLocation>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia las localizaciones de producto",e);
        }
        return result;
    }
    
    @Override
    public List<ScMeasureUnit> getAllMeasureUnits() throws Exception
    {
        List<ScMeasureUnit> result = null;
        Query query  = entityManager.createNamedQuery("ScMeasureUnit.findAll"); 
        try
        {
            result = (List<ScMeasureUnit>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las medidas",e);
        }
        return result;
    }
    
    @Override
    public List<ScMoney> getAllMoneys() throws Exception
    {
        List<ScMoney> result = null;
        Query query  = entityManager.createNamedQuery("ScMoney.findAll"); 
        try
        {
            result = (List<ScMoney>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las monedas",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveMeasureUnit(ScMeasureUnit measureUnit) throws Exception
    {
        try
        {
            entityManager.persist(measureUnit);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una medida",e);
            throw e;
        }
    }

    @Override
    public Object getInitialParameters() throws Exception
    {
        Object result = null;
        try
        {
            String sql = "SELECT MAX_SIZE_FILE, EXTENSION, PATH FROM dmes.sc_constants_load_files";
            Query query = entityManager.createNativeQuery(sql);
            result = query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los parámetros iniciales para cargar archivos",e);
        }
        return result;
    }

    @Override
    public List<ScDistributionUnit> getAllDistributionUnits() throws Exception
    {
        List<ScDistributionUnit> result = null;
        Query query  = entityManager.createNamedQuery("ScDistributionUnit.findAll"); 
        try
        {
            result = (List<ScDistributionUnit>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las unidades de distribución",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveDistributionUnit(ScDistributionUnit distributionUnit) throws Exception
    {
        try
        {
            entityManager.persist(distributionUnit);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una unidad de distribución",e);
            throw e;
        }
    }

    @Override
    public ScProductFormulation getProductsById(Long idProduct) throws Exception
    {
        ScProductFormulation result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScProductFormulation.findById");
            query.setParameter("idProduct", idProduct);
            result = (ScProductFormulation) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar un producto",e);
            throw e;
        }
        return result;
    }

    @Override
    public List<ScMachine> getAllMachines() throws Exception
    {
        List<ScMachine> result = null;
        Query query  = entityManager.createNamedQuery("ScMachine.findAll"); 
        try
        {
            result = (List<ScMachine>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las máquinas",e);
        }
        return result;
    }

    @Override
    public List<ScEmployee> getAllEmployees() throws Exception
    {
        List<ScEmployee> result = null;
        Query query  = entityManager.createNamedQuery("ScEmployee.findAll"); 
        try
        {
            result = (List<ScEmployee>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los empleados",e);
        }
        return result;
    }

    @Override
    public List<ScInput> getAllInputs() throws Exception
    {
        List<ScInput> result = null;
        Query query  = entityManager.createNamedQuery("ScInput.findAll"); 
        try
        {
            result = (List<ScInput>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
        return result;
    }

    @Override
    public void saveProcessProduct(ScProcessProduct processProduct) throws Exception
    {
        try
        {
            entityManager.persist(processProduct);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de un proceso de producto",e);
            throw e;
        }
    }

}
