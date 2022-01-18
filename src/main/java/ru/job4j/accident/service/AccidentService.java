package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.List;

@Service
@Transactional
public class AccidentService {
    private final AccidentRepository accidentStore;
    private final AccidentTypeRepository accidentTypeStore;
    private final RuleRepository ruleStore;

    public AccidentService(AccidentRepository accidentStore,
                           AccidentTypeRepository accidentTypeStore,
                           RuleRepository ruleStore) {
        this.accidentStore = accidentStore;
        this.accidentTypeStore = accidentTypeStore;
        this.ruleStore = ruleStore;
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
        accidentStore.save(accident);
    }

    public Accident findById(int id) {
        return accidentStore.findAccidentById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentTypeStore.getAllAccidentTypes();
    }

    public List<Rule> getAccidentRules() {
        return ruleStore.getAllRules();
    }
}
