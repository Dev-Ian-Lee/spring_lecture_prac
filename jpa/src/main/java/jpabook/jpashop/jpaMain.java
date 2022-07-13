package jpabook.jpashop;

import jpabook.jpashop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 문제 없으면, commit(DB에 저장)
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
