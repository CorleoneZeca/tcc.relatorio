/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tcc.relatorio.dominio;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.tcc.relatorio.hammer.persistencia.dominio.DomainObject;

/**
 *
 * @author roger
 */
@MappedSuperclass
public abstract class BaseEntity extends DomainObject<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @Column(name = "VER")
    private Integer versao;
    
    
//    @Column(name = "NOME", nullable = false, length = 50)
//    private String nome;

    /**
     * M�todo geral para leitura de IDs.
     *
     * @return Id da Entity
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * M�todo geral para escrita de IDs.
     *
     * @param pId Id da Entity
     */
    @Override
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * @return the versao
     */
    public Integer getVersao() {
        return versao;
    }

    /**
     * @param versao the versao to set
     */
    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    /**
     * verifica se a entity foi validada corretmente.
     *
     * @return True se entidade esta ok.
     */
    public boolean isOk() {
        return getMsg().isEmpty();
    }

    /**
     * @return the nome
     */
//    public String getNome() {
//        return nome;
//    }
//
//    /**
//     * @param nome the nome to set
//     */
//    public void setNome(String nome) {
//        this.nome = nome;
//    }

    /**
     * Lista as policies anotadas para a entidade.
     * 
     * @return Lista das policies.
     * @throws Exception Erro de acesso as anota��es da classe.
     */
 /*  public List<IPolicy> getPolicies() throws Exception {
        return PolicyHelper.getPolicies(this);
    }*/
    
    
    
}
