/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "VAGASCANDIDATADAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vagascandidatadas.findAll", query = "SELECT v FROM Vagascandidatadas v")
    , @NamedQuery(name = "Vagascandidatadas.findByIdregistro", query = "SELECT v FROM Vagascandidatadas v WHERE v.idregistro = :idregistro")
    , @NamedQuery(name = "Vagascandidatadas.findByLoginusuario", query = "SELECT v FROM Vagascandidatadas v WHERE v.loginusuario = :loginusuario")
    , @NamedQuery(name = "Vagascandidatadas.findByIdvagaemprego", query = "SELECT v FROM Vagascandidatadas v WHERE v.idvagaemprego = :idvagaemprego")})
public class Vagascandidatadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDREGISTRO")
    private Integer idregistro;
    @Size(max = 30)
    @Column(name = "LOGINUSUARIO")
    private String loginusuario;
    @Column(name = "IDVAGAEMPREGO")
    private Integer idvagaemprego;

    public Vagascandidatadas() {
    }

    public Vagascandidatadas(Integer idregistro, String loginusuario, Integer idvagaemprego) {
        this.idregistro = idregistro;
        this.loginusuario = loginusuario;
        this.idvagaemprego = idvagaemprego;
    }

    public Vagascandidatadas(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public Integer getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public String getLoginusuario() {
        return loginusuario;
    }

    public void setLoginusuario(String loginusuario) {
        this.loginusuario = loginusuario;
    }

    public Integer getIdvagaemprego() {
        return idvagaemprego;
    }

    public void setIdvagaemprego(Integer idvagaemprego) {
        this.idvagaemprego = idvagaemprego;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregistro != null ? idregistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vagascandidatadas)) {
            return false;
        }
        Vagascandidatadas other = (Vagascandidatadas) object;
        if ((this.idregistro == null && other.idregistro != null) || (this.idregistro != null && !this.idregistro.equals(other.idregistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Vagascandidatadas[ idregistro=" + idregistro + " ]";
    }
    
}
