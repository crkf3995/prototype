package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "patient")
public class PatientDto {
    public int id;
    public String nom;
    public String prenom;
    public int age;
    public String sexe;
    public String telephone;

    public PatientDto() {}

    @JsonProperty("id")
    public int getId() { return id; }
    @JsonProperty("id")
    public void setId(int id) { this.id = id; }

    @JsonProperty("nom")
    public String getNom() { return nom; }
    @JsonProperty("nom")
    public void setNom(String nom) { this.nom = nom; }

    @JsonProperty("prenom")
    public String getPrenom() { return prenom; }
    @JsonProperty("prenom")
    public void setPrenom(String prenom) { this.prenom = prenom; }

    @JsonProperty("age")
    public int getAge() { return age; }
    @JsonProperty("age")
    public void setAge(int age) { this.age = age; }

    @JsonProperty("sexe")
    public String getSexe() { return sexe; }
    @JsonProperty("sexe")
    public void setSexe(String sexe) { this.sexe = sexe; }

    @JsonProperty("telephone")
    public String getTelephone() { return telephone; }
    @JsonProperty("telephone")
    public void setTelephone(String telephone) { this.telephone = telephone; }
}
