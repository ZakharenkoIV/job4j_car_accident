package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    public static List<Accident> loadAllAccidents() {
        return AccidentMem.instOf().getAllAccidents();
    }
}
