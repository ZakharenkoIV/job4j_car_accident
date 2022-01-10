package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> store;
    private final AtomicInteger accidentCounter;

    public AccidentMem() {
        store = new HashMap<>();
        accidentCounter = new AtomicInteger();
        store.put(accidentCounter.addAndGet(1), Accident.of(
                accidentCounter.get(),
                "Неправильная парковка",
                "Машина припаркована на газоне",
                "г.Москва ул.Русаковского"));
        store.put(accidentCounter.addAndGet(1), Accident.of(
                accidentCounter.get(),
                "ДТП",
                "Машина врезалась в столб",
                "г.Москва ул.Народного ополчения"));
        store.put(accidentCounter.addAndGet(1), Accident.of(
                accidentCounter.get(),
                "ДТП",
                "Наезд на пешехода",
                "г.Самара ул.Ленина"));
    }

    public List<Accident> getAllAccidents() {
        return store.values().stream().toList();
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accidentCounter.addAndGet(1));
            store.put(accidentCounter.get(), accident);
        } else if (accident.getId() <= store.size()) {
            store.put(accident.getId(), accident);
        }
    }

    public Accident findById(int id) {
        return store.get(id);
    }
}
