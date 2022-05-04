package com.example.servermaintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DataRowRepository extends JpaRepository<DataRow, Long> {
    ArrayList<DataRow> findByFirstName(String FirstName);
    ArrayList<DataRow> findAll();
}