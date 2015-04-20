package br.edu.ifpb.monteiro.ads.infosaude.atendimento.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Create the Entity Manager Factory and Entity Manager to serve the DAO classes. 
 * 
 * This class is to be created on application scope, being used by all execution of program. At be
 * instanced, this object call the Entity Manager receiving the Unity Persistence as parameter. It
 * too create and close an Entity Manager to each request of DAO.
 * 
 * @see TransactionInterceptor
 * 
 ** Based on estructure of codes of Algaworks GitHub Repository (http://github.com/algaworks)
 */
@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory entityManagerFactory;

    public EntityManagerProducer() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("InfoSaudePU");
    }

    /* This method is a EntityManager Producer at each request, by the Annotations bellow.*/
    @Produces
    @RequestScoped
    public EntityManager create() {
        return entityManagerFactory.createEntityManager();
    }
    
    /* This method close the EntityManager when is requested */
    public void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }
}
