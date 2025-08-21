package com.fastdye.almacen.infrastructure.persistence.adapter;

import com.fastdye.almacen.domain.ports.out.ActivityProcessPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;

@Repository
public class JdbcActivityProcessAdapter implements ActivityProcessPort {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public int procesarActividad(int idActividad, String usuario) {
        return jdbc.execute((Connection con) -> {
            try (CallableStatement cs = con.prepareCall(
                    "{ call dbo.Sp_Scal_ProcesarActividadAIngresoMP(?, ?, ?) }")) {
                cs.setInt(1, idActividad);                // @idActividad
                cs.setString(2, usuario);                 // @usuario (NVARCHAR(10))
                cs.registerOutParameter(3, java.sql.Types.INTEGER); // @idIngresoCreado OUTPUT
                cs.execute();
                return cs.getInt(3);
            }
        });
    }
}
