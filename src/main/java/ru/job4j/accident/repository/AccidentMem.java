package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents;

    public AccidentMem() {
        this.accidents = new HashMap<>();
        accidents.put(1, new Accident().of(
                0,
                "Неправильная парковка",
                "Машина припаркована на газоне",
                "г.Москва ул.Русаковского"));
        accidents.put(2, new Accident().of(
                1,
                "ДТП",
                "Машина врезалась в столб",
                "г.Москва ул.Народного ополчения"));
        accidents.put(3, new Accident().of(
                2,
                "ДТП",
                "Наезд на пешехода",
                "г.Самара ул.Ленина"));
    }

    public List<Accident> getAllAccidents() {
        return accidents.values().stream().toList();
    }
}
