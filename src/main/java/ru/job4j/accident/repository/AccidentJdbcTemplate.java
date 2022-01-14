package ru.job4j.accident.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.mapper.AccidentMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            createAccident(accident);
        } else {
            updateAccident(accident);
        }
    }

    public List<Accident> getAllAccidents() {
        List<Accident> accidents = jdbc.query("select id, name, text, address, type_id "
                + "from accident ", new AccidentMapper());
        for (Accident accident : accidents) {
            loadAccidentWithAllProperties(accident);
        }
        accidents.sort(Comparator.comparingInt(Accident::getId));
        return accidents;
    }

    public Accident findAccidentById(int id) {
        Accident accident = jdbc.query("select id, name, text, address, type_id "
                        + "from accident where id = ?",
                new AccidentMapper(), id)
                .stream().findAny().orElse(null);
        return loadAccidentWithAllProperties(accident);
    }

    public List<AccidentType> getAllAccidentTypes() {
        return jdbc.query("select id, name from accident_types",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public AccidentType findAccidentTypeByAccident(Accident accident) {
        return jdbc.query("select id, name from accident_types where id = ?",
                new BeanPropertyRowMapper<>(AccidentType.class), accident.getType().getId())
                .stream().findAny().orElse(null);
    }

    public List<Rule> getAllAccidentRules() {
        return jdbc.query("select id, name from rule",
                new BeanPropertyRowMapper<>(Rule.class));
    }

    public List<Rule> findAllAccidentRulesByAccidentId(int accidentId) {
        return jdbc.query("select rule.id, rule.name "
                        + "from accident "
                        + "inner join rule_to_accident "
                        + "on accident.id = rule_to_accident.accident_id "
                        + "left join rule "
                        + "on rule.id = rule_to_accident.rule_id "
                        + "where accident.id = ?",
                new BeanPropertyRowMapper<>(Rule.class), accidentId);
    }

    private void createAccident(Accident accident) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "insert into accident (name, text, address, type_id) values (?, ?, ?, ?) ",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getType().getId());
            return statement;
        }, key);
        int newAccidentId;
        if (Objects.requireNonNull(key.getKeys()).size() > 1) {
            newAccidentId = Integer.parseInt(key.getKeys().get("id").toString());
        } else {
            newAccidentId = Objects.requireNonNull(key.getKey()).intValue();
        }
        accident.setId(newAccidentId);
        createRuleToAccidentLinking(accident);
    }

    private void createRuleToAccidentLinking(Accident accident) {
        deleteByAccidentId(accident.getId());
        if (accident.getRules().size() != 0) {
            StringJoiner ruleToAccidentLinking = new StringJoiner(", ");
            for (Rule rule : accident.getRules()) {
                ruleToAccidentLinking.add("(" + rule.getId());
                ruleToAccidentLinking.add(accident.getId() + ")");
            }
            jdbc.update("INSERT INTO rule_to_accident (rule_id, accident_id) "
                    + "VALUES " + ruleToAccidentLinking.toString());
        }
    }

    private void updateAccident(Accident accident) {
        jdbc.update("update accident "
                        + "set name = (?), text = (?), address = (?), type_id = (?) "
                        + "where id = (?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        createRuleToAccidentLinking(accident);
    }

    private Accident loadAccidentWithAllProperties(Accident accident) {
        if (accident != null) {
            accident.setType(findAccidentTypeByAccident(accident));
            accident.setRules(new HashSet<>(findAllAccidentRulesByAccidentId(accident.getId())));
        }
        return accident;
    }

    private void deleteByAccidentId(int accidentId) {
        jdbc.update("DELETE FROM rule_to_accident WHERE accident_id = (?)", accidentId);
    }
}