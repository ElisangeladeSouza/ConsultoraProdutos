package br.com.projetospessoas.consultoraprodutos.dao;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author elisangela
 * @param <T>
 */
public abstract class DaoAbstract<T> {
    
    @Inject
    private EntityManager entityManager;

    private Class<T> entity;
    
    public DaoAbstract(Class<T> entityClass) {
        this.entity = entityClass;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void salvar(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entity));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public T findById(Long id) {
       return entityManager.find(entity, id);
    }
    
    public Class<T> getEntity() {
        return entity;
    }

    public void setEntity(Class<T> entity) {
        this.entity = entity;
    }
    
}
