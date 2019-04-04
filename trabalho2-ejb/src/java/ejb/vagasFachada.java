/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;
import javax.persistence.Query;
/**
 *
 * @author pablo
 */
@Stateless
@LocalBean
public class vagasFachada {
  @PersistenceContext (name = "trabalho2-ejbPU") 
    EntityManager em;  
  
     private String areaAt;
     private String idEMP;

    public String getAreaAt() {
        return areaAt;
    }

    public void setAreaAt(String areaAt) {
        this.areaAt = areaAt;
    }
    
    public String getIEMP() {
        return idEMP;
    }

    public void setIdEMP(String idEMP) {
        this.idEMP = idEMP;
    }

    public void persist(Object object) {
       em.persist(object);
    }
    
    
    // Metodo que retorna a lista de clientes armazenada na tabela Clientes
    public List<ejb.Vagasemprego> getListaVagas() {
        Query query = em.createNamedQuery("Vagasemprego.findAll");
        return query.getResultList();
    }

    public List<ejb.Vagasemprego> getListVagasDisponiveis(){
        return em.createQuery("SELECT c FROM Vagasemprego c WHERE c.atuacao like :custName").setParameter("custName", areaAt.toUpperCase()).getResultList();
    }
    
    public List<ejb.Vagasemprego> getListVagasDisponiveisEmpregador(){
        return em.createQuery("SELECT c FROM Vagasemprego c WHERE c.idempregado like :custName1").setParameter("custName1", idEMP).getResultList();
    }
    
    public void fecharVaga(String id){
        em.createQuery("delete FROM Vagasemprego c WHERE c.idvaga = :custName2").setParameter("custName2", Integer.parseInt(id)).executeUpdate();
        em.createQuery("delete FROM Vagascandidatadas c WHERE c.idvagaemprego = :custName3").setParameter("custName3", Integer.parseInt(id)).executeUpdate();
    }
    
    public Integer getMaiorID(){
        Integer maior = 0;
        for(Vagasemprego cad : getListaVagas()){
            if(cad.getIdvaga()>maior){
                maior = cad.getIdvaga();
            }
        }
        return maior+1;
    }
}
