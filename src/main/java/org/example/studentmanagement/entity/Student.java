package org.example.studentmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;
    @Entity
    public class Student {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private int id;
        private String nom;
        private String prenom;
        @Temporal(TemporalType.DATE)
        private Date dateNaissance;
        public Student() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public Date getDateNaissance() {
            return dateNaissance;
        }

        public void setDateNaissance(Date dateNaissance) {
            this.dateNaissance = dateNaissance;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }
    }

