package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate accidentStore;

    public AccidentService(AccidentJdbcTemplate accidentStore) {
        this.accidentStore = accidentStore;
    }

    public List<Accident> loadAllAccidents() {
        return accidentStore.getAllAccidents();
    }

    public void saveAccident(Accident accident, String[] rIds) {
        if (rIds != null) {
            for (String id : rIds) {
                accident.addRule(Rule.of(Integer.parseInt(id)));
            }
        }
        accidentStore.saveAccident(accident);
    }

    public Accident findById(int id) {
        return accidentStore.findAccidentById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentStore.getAllAccidentTypes();
    }

    public List<Rule> getAccidentRules() {
        return accidentStore.getAllAccidentRules();
    }
}
