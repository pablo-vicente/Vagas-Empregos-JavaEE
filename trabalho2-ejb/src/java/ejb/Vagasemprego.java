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
@Table(name = "VAGASEMPREGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vagasemprego.findAll", query = "SELECT v FROM Vagasemprego v")
    , @NamedQuery(name = "Vagasemprego.findByIdvaga", query = "SELECT v FROM Vagasemprego v WHERE v.idvaga = :idvaga")
    , @NamedQuery(name = "Vagasemprego.findByTipo", query = "SELECT v FROM Vagasemprego v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "Vagasemprego.findByAtuacao", query = "SELECT v FROM Vagasemprego v WHERE v.atuacao = :atuacao")
    , @NamedQuery(name = "Vagasemprego.findByEmpresa", query = "SELECT v FROM Vagasemprego v WHERE v.empresa = :empresa")
    , @NamedQuery(name = "Vagasemprego.findByDescricao", query = "SELECT v FROM Vagasemprego v WHERE v.descricao = :descricao")
    , @NamedQuery(name = "Vagasemprego.findByRemuneracao", query = "SELECT v FROM Vagasemprego v WHERE v.remuneracao = :remuneracao")
    , @NamedQuery(name = "Vagasemprego.findByIdempregado", query = "SELECT v FROM Vagasemprego v WHERE v.idempregado = :idempregado")})
public class Vagasemprego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDVAGA")
    private Integer idvaga;
    @Size(max = 30)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 30)
    @Column(name = "ATUACAO")
    private String atuacao;
    @Size(max = 30)
    @Column(name = "EMPRESA")
    private String empresa;
    @Size(max = 30)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "REMUNERACAO")
    private Integer remuneracao;
    @Size(max = 30)
    @Column(name = "IDEMPREGADO")
    private String idempregado;

    public Vagasemprego() {
    }

    public Vagasemprego(Integer idvaga, String tipo, String atuacao, String empresa, String descricao, Integer remuneracao, String idempregado) {
        this.idvaga = idvaga;
        this.tipo = tipo;
        this.atuacao = atuacao;
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.idempregado = idempregado;
    }

    public Vagasemprego(Integer idvaga) {
        this.idvaga = idvaga;
    }

    public Integer getIdvaga() {
        return idvaga;
    }

    public void setIdvaga(Integer idvaga) {
        this.idvaga = idvaga;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAtuacao() {
        return atuacao;
    }

    public void setAtuacao(String atuacao) {
        this.atuacao = atuacao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(Integer remuneracao) {
        this.remuneracao = remuneracao;
    }

    public String getIdempregado() {
        return idempregado;
    }

    public void setIdempregado(String idempregado) {
        this.idempregado = idempregado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvaga != null ? idvaga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vagasemprego)) {
            return false;
        }
        Vagasemprego other = (Vagasemprego) object;
        if ((this.idvaga == null && other.idvaga != null) || (this.idvaga != null && !this.idvaga.equals(other.idvaga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Vagasemprego[ idvaga=" + idvaga + " ]";
    }
    
}
