package com.comercio.service;

import com.comercio.dto.ComercianteCsvDTO;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ReporteServiceImpl implements IReporteService {

    private final JdbcTemplate jdbcTemplate;

    public ReporteServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public byte[] generarCsvComerciantesActivos() {
    	List<ComercianteCsvDTO> data = jdbcTemplate.execute(
    		    "BEGIN ? := SYSTEM.PKG_COMERCIANTES.GET_COMERCIANTES_ACTIVOS(); END;",
    		    (CallableStatementCallback<List<ComercianteCsvDTO>>) cs -> {
    		        cs.registerOutParameter(1, OracleTypes.CURSOR);
    		        cs.execute();

    		        ResultSet rs = (ResultSet) cs.getObject(1);
    		        List<ComercianteCsvDTO> lista = new ArrayList<>();
    		        while (rs.next()) {
    		            ComercianteCsvDTO dto = new ComercianteCsvDTO();
    		            dto.setRazonSocial(rs.getString("RAZON_SOCIAL"));
    		            dto.setMunicipio(rs.getString("MUNICIPIO"));
    		            dto.setTelefono(rs.getString("TELEFONO"));
    		            dto.setEmail(rs.getString("EMAIL_COMERCIANTE"));
    		            dto.setFechaRegistro(rs.getDate("FECHA_REGISTRO").toLocalDate());
    		            dto.setEstado(rs.getString("ESTADO"));
    		            dto.setCantidadEstablecimientos(rs.getInt("CANT_ESTABLECIMIENTOS"));
    		            dto.setTotalIngresos(rs.getBigDecimal("TOTAL_INGRESOS"));
    		            dto.setCantidadEmpleados(rs.getInt("CANTIDAD_EMPLEADOS"));
    		            lista.add(dto);
    		        }
    		        return lista;
    		    }
        );

        StringBuilder sb = new StringBuilder();
        sb.append("Razon Social|Municipio|Telefono|Email|Fecha Registro|Estado|Cant. Establecimientos|Total Ingresos|Cant. Empleados\n");
        for (ComercianteCsvDTO dto : data) {
            sb.append(dto.getRazonSocial()).append("|")
              .append(dto.getMunicipio()).append("|")
              .append(Optional.ofNullable(dto.getTelefono()).orElse("")).append("|")
              .append(Optional.ofNullable(dto.getEmail()).orElse("")).append("|")
              .append(dto.getFechaRegistro()).append("|")
              .append(dto.getEstado()).append("|")
              .append(dto.getCantidadEstablecimientos()).append("|")
              .append(dto.getTotalIngresos()).append("|")
              .append(dto.getCantidadEmpleados()).append("\n");
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}
