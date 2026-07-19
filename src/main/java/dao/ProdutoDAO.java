package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Produto;
import java.util.List;

public class ProdutoDAO {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("supermercado-pu");


    public void inserir(Produto produto) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        }

        catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        finally {
            em.close();
        }
    }

    public Produto buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Produto.class, id);
        }

        finally {
            em.close();
        }
    }

    public List<Produto> buscarTodos() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("FROM Produto", Produto.class).getResultList();
        }

        finally {
            em.close();
        }
    }

    public void atualizar(Produto produto) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        }

        catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        finally {
            em.close();
        }
    }

    public void deletar(Integer id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);

            if (produto != null) {
                em.remove(produto);
            }

            em.getTransaction().commit();
        }

        catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        finally {
            em.close();
        }
    }
}