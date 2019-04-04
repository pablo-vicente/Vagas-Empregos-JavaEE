/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pablo
 */
@Stateless
@LocalBean
public class vagasCandidatadasFachada {

    @PersistenceContext(unitName = "trabalho2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<ejb.Vagascandidatadas> getListaVagaCandidatadas() {
        Query query = em.createNamedQuery("Vagascandidatadas.findAll");
        return query.getResultList();
    }
    public int getMaiorID(){
        Integer maior = 0;
        for(Vagascandidatadas cad : getListaVagaCandidatadas()){
            if(cad.getIdregistro()>maior){
                maior = cad.getIdregistro();
            }
        }
        return maior+1;
    }
    
    public List<ejb.Vagascandidatadas> getListadeIdVagas(){
        Query query = em.createNativeQuery("Select distinct idvagaemprego from Vagascandidatadas");
        return query.getResultList();
    }
    
    public List<ejb.Vagascandidatadas> getListadeIdLogins(){
        Query query = em.createNativeQuery("Select distinct loginusuario from Vagascandidatadas");
        return query.getResultList();
    }
    
    
    
    public List<ejb.Vagascandidatadas> getLoginsDaVaga(Integer idVaga) {
        Query query = em.createNativeQuery("SELECT loginusuario FROM Vagascandidatadas WHERE idvagaemprego = ?");
        query.setParameter(1, idVaga);
        return query.getResultList();
    }
    
}
