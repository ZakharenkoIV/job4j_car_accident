package ru.job4j.accident.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id
                && Objects.equals(name, accident.name)
                && Objects.equals(text, accident.text)
                && Objects.equals(address, accident.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, address);
    }

    public static Accident of(int id, String name, String text, String address, AccidentType type) {
        Accident accident = new Accident();
        accident.setId(id);
        accident.setName(name);
        accident.setText(text);
        accident.setAddress(address);
        accident.setType(type);
        return accident;
    }
}