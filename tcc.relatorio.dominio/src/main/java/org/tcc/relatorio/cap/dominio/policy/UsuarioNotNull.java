/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tcc.relatorio.cap.dominio.policy;

import org.tcc.relatorio.cap.dominio.BaseEntity;
import org.tcc.relatorio.cap.dominio.UsuarioEntity;
import org.tcc.relatorio.cap.dominio.util.IPolicy;
import org.tcc.relatorio.cap.dominio.util.PolicyHelper;
import org.tcc.relatorio.cap.dominio.util.PolicyOp;
import static org.tcc.relatorio.cap.dominio.util.PolicyOp.INSERT;
import static org.tcc.relatorio.cap.dominio.util.PolicyOp.UPDATE;

/**
 *
 * @author roger
 */
public class UsuarioNotNull implements IPolicy {

    private static final int min_UserId = 4;

    /**
     * Construtor.
     *
     * @param name
     */
    public UsuarioNotNull(String name) {
    }

    @Override
    public <T extends BaseEntity> boolean apply(T entity, PolicyOp op) {
        UsuarioEntity usuario = (UsuarioEntity) entity;

        if (op == INSERT) {

            PolicyHelper.notNullNotEmpty(usuario, usuario.getUserId(), "UserId n�o pode ser nulo");
            PolicyHelper.notNullNotEmpty(usuario, usuario.getNome(), "Nome do usu�rio n�o pode ser nulo");
//            PolicyHelper.notNull(usuario, usuario.getDataInicio(), "Data de Inicio deve ser fornecida");
            PolicyHelper.notNullNotEmpty(usuario, usuario.getEmail(), "EMail � um campo obrigat�rio");
            if(usuario.getEmail()!=null && !usuario.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                PolicyHelper.notNull(usuario, null, "Email inv�lido.");
            }
            PolicyHelper.notNull(usuario, usuario.getInstituicoes(), "Selecione uma institui��o.");
            //PolicyHelper.notNullNotEmpty(usuario, usuario.getSenha(),   "A senha deve ser informada");
//            if(usuario.getSenha().length() < min_UserId) {
//                usuario.getMsg().add("A Senha deve ter no m�nimo " + min_UserId + " letras");
//            }
//            if(usuario.getUserId().length() < min_UserId) {
//                usuario.getMsg().add("O Id do usu�rio deve ter no m�nimo " + min_UserId + " letras");
//            }
        } else if (op == UPDATE) {
            PolicyHelper.notNullNotEmpty(usuario, usuario.getUserId(), "UserId n�o pode ser nulo");
            PolicyHelper.notNullNotEmpty(usuario, usuario.getNome(), "Nome do usu�rio n�o pode ser nulo");
//            PolicyHelper.notNull(usuario, usuario.getDataInicio(), "Data de Inicio deve ser fornecida");
            PolicyHelper.notNullNotEmpty(usuario, usuario.getEmail(), "EMail � um campo obrigat�rio");
            PolicyHelper.notNullNotEmpty(usuario, usuario.getSenha(), "A senha deve ser informada");
            if(usuario.getEmail()!=null && !usuario.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                PolicyHelper.notNull(usuario, null, "Email inv�lido.");
            }
            PolicyHelper.notNull(usuario, usuario.getInstituicoes(), "Selecione uma institui��o.");
            if (usuario.getSenha().length() < min_UserId) {
                usuario.getMsg().add("A Senha deve ter no m�nimo " + min_UserId + " letras");
            }
            if (usuario.getUserId().length() < min_UserId) {
                usuario.getMsg().add("O Id do usu�rio deve ter no m�nimo " + min_UserId + " letras");
            }
        }

        return true;
    }
}
