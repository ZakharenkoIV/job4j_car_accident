package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> loadAllAccidents() {
        return accidentMem.getAllAccidents();
    }

    public void saveAccident(Accident accident, String[] rIds) {
        if (rIds != null) {
            for (String id : rIds) {
                accident.addRule(Rule.of(Integer.parseInt(id)));
            }
        }
        accidentMem.saveAccident(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentMem.getAllAccidentTypes();
    }

    public List<Rule> getAccidentRules() {
        return accidentMem.getAllAccidentRules();
    }
}
