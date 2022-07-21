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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUserName("memberA");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            // 연관관계 편의 메소드
            member.changeTeam(team);

            // setParameter
//            Member resultList = em.createQuery(
//                            "SELECT m FROM Member m WHERE m.userName =:username",
//                            Member.class)
//                    .setParameter("username", "memberA")
//                    .getSingleResult();
//
//            System.out.println("result.getUserName() = " + result.getUserName());

//            // dto 사용해 스칼라 프로젝션
//            List<MemberDTO> resultList = em.createQuery("SELECT new jpql.MemberDTO(m.username, m.age) FROM Member m", MemberDTO.class)
//                    .getResultList();
//
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("memberDTO.getUserName() = " + memberDTO.getUserName());
//            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

//            // 페이징
//            List<Member> resultList = em.createQuery("SELECT m FROM Member m ORDER BY m.age DESC", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();

//            // 조인
//            List<Member> resultList = em.createQuery("SELECT m FROM Member m inner join m.team t", Member.class)
//                    .getResultList();

//            // Enum타입 조회(패키지명 전부 명시)
//            // setParameter, QueryDSL로 대체 가능
//            List<Object[]> resultList = em.createQuery("SELECT m.userName, m.type FROM Member m where m.type = jpql.MemberType.ADMIN")
//                    .getResultList();

            // NamedQuery 사용
            List<Member> resultList = em.createNamedQuery("Member.findByUserName", Member.class)
                    .setParameter("userName", "memberA")
                    .getResultList();

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
