package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class jpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUserName("memberA");
            member.setAge(10);
            em.persist(member);

            Member result = em.createQuery(
                            "select m from Member m where m.userName =:username",
                            Member.class)
                    .setParameter("username", "memberA")
                    .getSingleResult();

            System.out.println("result.getUserName() = " + result.getUserName());

            tx.commit();
        } catch (Exception e) {
            // 문제 있으면, rollback
            tx.rollback();
        } finally {
            // em 반드시 닫아주기
            em.close();
        }

        emf.close();
    }
}
