package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidentStore;
    private final HashMap<Integer, AccidentType> accidentTypeStore;
    private final HashMap<Integer, Rule> accidentRuleStore;
    private final AtomicInteger accidentCounter;

    public AccidentMem() {
        accidentTypeStore = new HashMap<>();
        accidentTypeStore.put(0, AccidentType.of(0, "Две машины"));
        accidentTypeStore.put(1, AccidentType.of(1, "Машина и человек"));
        accidentTypeStore.put(2, AccidentType.of(2, "Машина и велосипед"));
        accidentRuleStore = new HashMap<>();
        accidentRuleStore.put(0, Rule.of(0, "Статья. 1"));
        accidentRuleStore.put(1, Rule.of(1, "Статья. 2"));
        accidentRuleStore.put(2, Rule.of(2, "Статья. 3"));
        accidentCounter = new AtomicInteger();
        accidentStore = new HashMap<>();
        accidentStore.put(accidentCounter.addAndGet(1), Accident.of(
                accidentCounter.get(),
                "Неправильная парковка",
                "Машина припаркована на газоне",
                "г.Москва ул.Русаковского",
                accidentTypeStore.get(0)));
        accidentStore.put(accidentCounter.addAndGet(1), Accident.of(
                accidentCounter.get(),
                "ДТП",
                "Машина врезалась в столб",
                "г.Москва ул.Народного ополчения",
                accidentTypeStore.get(1)));
        accidentStore.put(accidentCounter.addAndGet(1), Accident.of(
                accidentCounter.get(),
                "ДТП",
                "Наезд на пешехода",
                "г.Самара ул.Ленина",
                accidentTypeStore.get(2)));
    }

    public List<Accident> getAllAccidents() {
        return accidentStore.values().stream().toList();
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accidentCounter.addAndGet(1));
            accident.setType(accidentTypeStore.get(accident.getType().getId()));
            accident.setRules(loadAccidentRulesByRulesId(accident.getRules()));
            accidentStore.put(accidentCounter.get(), accident);
        } else if (accident.getId() <= accidentStore.size()) {
            accident.setType(accidentTypeStore.get(accident.getType().getId()));
            accidentStore.put(accident.getId(), accident);
        }
    }

    public Accident findById(int id) {
        return accidentStore.get(id);
    }

    public List<AccidentType> getAllAccidentTypes() {
        return accidentTypeStore.values().stream().toList();
    }

    public List<Rule> getAllAccidentRules() {
        return accidentRuleStore.values().stream().toList();
    }

    private Set<Rule> loadAccidentRulesByRulesId(Set<Rule> preRules) {
        Set<Rule> resultRules = new HashSet<>();
        for (Rule preRule : preRules) {
            resultRules.add(accidentRuleStore.get(preRule.getId()));
        }
        return resultRules;
    }
}
