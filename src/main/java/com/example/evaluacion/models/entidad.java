package com.example.evaluacion.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class entidad {
    
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "Id", nullable = false, length = 36)
     private String Id;

     @Column(name = "Title", nullable = true, length = 36)
     private String Title;

     @Column(name = "due_date", nullable = true, length = 10)
     private Date due_date;

     @Column(name = "assigned_to", nullable = true, length = 50)
     private String assigned_to;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private status status;

    public enum status{
        finalizada,
        pendiente,
        vencida,
        terminada_vencida,
    }
}
