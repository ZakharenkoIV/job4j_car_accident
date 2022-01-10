package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> store;

    public AccidentMem() {
        store = new HashMap<>();
        store.put(1, Accident.of(
                0,
                "Неправильная парковка",
                "Машина припаркована на газоне",
                "г.Москва ул.Русаковского"));
        store.put(2, Accident.of(
                1,
                "ДТП",
                "Машина врезалась в столб",
                "г.Москва ул.Народного ополчения"));
        store.put(3, Accident.of(
                2,
                "ДТП",
                "Наезд на пешехода",
                "г.Самара ул.Ленина"));
    }

    public List<Accident> getAllAccidents() {
        return store.values().stream().toList();
    }

    public void saveAccident(Accident accident) {
        if (!store.containsValue(accident)) {
            store.put(store.size(), accident);
        }
    }

    private static final class Lazy {
        private static final AccidentMem INST = new AccidentMem();
    }

    public static AccidentMem instOf() {
        return Lazy.INST;
    }
}
