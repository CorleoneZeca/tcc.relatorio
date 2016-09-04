/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tcc.relatorio.negocio.validator;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tcc.relatorio.dominio.PphBeneficiarioEntity;
import org.tcc.relatorio.negocio.exception.util.BCExceptionUtil;
import org.tcc.relatorio.persistencia.BeneficiarioRepo;
import org.tcc.relatorio.hammer.persistencia.exception.BCException;

/**
 *
 * @author jwsouza
 */
public class BeneficiarioValidator extends ValidadorEntity<PphBeneficiarioEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeneficiarioValidator.class);

    @Inject
    private BeneficiarioRepo beneficiarioRepo;

    @Override
    public boolean validaInsert(PphBeneficiarioEntity entity) throws BCException {
        PphBeneficiarioEntity beneficiarioFromBase;
        try {
            valida(entity);
            
            if (!isBlank(entity.getNrCpfBeneficiario())&&(beneficiarioFromBase = beneficiarioRepo.buscarPorCPF(entity.getNrCpfBeneficiario())) != null && beneficiarioFromBase.getId() > 0) {
                entity.getMsg().add("CPF j� cadastrado.");
            }
            if (isMaior(entity.getId(), 0)) {
                entity.getMsg().add("O Beneficiario j� existe na base de dados.");
            }
        } catch (Exception e) {
            throw BCExceptionUtil.prepara(LOGGER, e);
        }
        return entity.isOk();
    }

    @Override
    public boolean validaUpdate(PphBeneficiarioEntity entity) throws BCException {
        valida(entity);
        return entity.isOk();
    }

    @Override
    public boolean valida(PphBeneficiarioEntity entity) {
        if (isNull(entity)) {
            entity = new PphBeneficiarioEntity();
            entity.getMsg().add("O objeto beneficiario est� vazio.");
        }
        if(!isBlank(entity.getNrCpfBeneficiario()) && !isCpf(entity.getNrCpfBeneficiario())){
            entity.getMsg().add("CPF inv�lido.");
        }
//        if(isNull(entity.getPphUnidSaude())){
//            entity.getMsg().add("Selecione uma Unidade Sa�de.");
//        }
        if(isNull(entity.getPphUnidadePagadora())){
            entity.getMsg().add("Selecione uma Unidade Pagadora.");
        }
        if(!isColecao(entity.getPphEnderecoSet())){
            entity.getMsg().add("Preencha o endere�o do Benefici�rio.");
        }
        if(isEmpty(entity.getNmBeneficiario())){
            entity.getMsg().add("Preencha o nome do Benefici�rio.");
        }
        if(isNull(entity.getDtNascimento())){
            entity.getMsg().add("Preencha data de Nascimento do Benefici�rio.");
        }
        if(isEmpty(entity.getNmMae())){
            entity.getMsg().add("Preencha o nome da M�e do Benefici�rio.");
        }
        if(isEmpty(entity.getTpBeneficiario())){
            entity.getMsg().add("Selecione uma Categoria.");
        }else if(entity.getTpBeneficiario().equals("2")&&isNull(entity.getPphBeneficiario())){
            entity.getMsg().add("Selecione o benefici�rio inicial.");
        }
        
        return entity.isOk();
    }
}
