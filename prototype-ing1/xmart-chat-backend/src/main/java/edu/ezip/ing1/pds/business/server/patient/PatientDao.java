package edu.ezip.ing1.pds.business.server.patient;

import edu.ezip.ing1.pds.business.dto.PatientDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {

    public int createPatient(PatientDto patient) throws SQLException {
        final String sql = "INSERT INTO patient (id, nom, prenom, age, sexe, telephone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patient.getId());
            ps.setString(2, patient.getNom());
            ps.setString(3, patient.getPrenom());
            ps.setInt(4, patient.getAge());
            ps.setString(5, patient.getSexe());
            ps.setString(6, patient.getTelephone());

            return ps.executeUpdate();
        }
    }

    public List<PatientDto> listPatients() throws SQLException {
        final String sql = "SELECT id, nom, prenom, age, sexe, telephone FROM patient ORDER BY id";
        List<PatientDto> result = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PatientDto p = new PatientDto();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setAge(rs.getInt("age"));
                p.setSexe(rs.getString("sexe"));
                p.setTelephone(rs.getString("telephone"));
                result.add(p);
            }
        }

        return result;
    }

    public int deletePatient(int id) throws SQLException {
        final String sql = "DELETE FROM patient WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}
