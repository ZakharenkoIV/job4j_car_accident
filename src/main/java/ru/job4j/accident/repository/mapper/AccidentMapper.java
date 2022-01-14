package ru.job4j.accident.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentMapper implements RowMapper<Accident> {

    @Override
    public Accident mapRow(ResultSet rs, int rowNum) throws SQLException {
        Accident accident = new Accident();
        accident.setId(Integer.parseInt(rs.getString("id")));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setType(AccidentType.of(Integer.parseInt(rs.getString("type_id")), null));
        return accident;
    }
}
