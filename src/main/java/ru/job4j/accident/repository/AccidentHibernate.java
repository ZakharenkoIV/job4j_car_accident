package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void saveAccident(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            if (accident.getId() == 0) {
                session.save(accident);
            } else {
                session.update(accident);
            }
            session.getTransaction().commit();
        }
    }

    public List<Accident> getAllAccidents() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("SELECT a "
                                    + "FROM Accident a "
                                    + "left join fetch a.type t "
                                    + "left join fetch a.rules r",
                            Accident.class)
                    .list();
        }
    }

    public Accident findAccidentById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("SELECT a "
                                    + "FROM Accident a "
                                    + "left join fetch a.type t "
                                    + "left join fetch a.rules r "
                                    + "where a.id =: id",
                            Accident.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public List<AccidentType> getAllAccidentTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public List<Rule> getAllAccidentRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }
}