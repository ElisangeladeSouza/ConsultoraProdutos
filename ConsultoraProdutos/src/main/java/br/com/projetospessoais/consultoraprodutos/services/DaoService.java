package br.com.projetospessoais.consultoraprodutos.services;

import br.com.projetospessoais.consultoraprodutos.dao.DaoAbstract;
import br.com.projetospessoais.consultoraprodutos.util.jpa.Transactional;

/**
 * Esta classe representa o DaoService. 
 * 
 * @author lisa <elysangeladesouza@gmail.com>
 * @param <T>
 */
public class DaoService<T> extends DaoAbstract<T> {

    public DaoAbstract getDao;

    /**
     * Faz uma consulta no banco de dados baseado em um valor passado como
     * parâmetro e retorna o resultado da consulta.
     * 
     * @param campo
     * @param valor
     * @return 
     */
    @Override
    @Transactional
    public T buscarPorCampo(String campo, Object valor) {
        return (T) getDao.buscarPorCampo(campo, valor);
    }
}
