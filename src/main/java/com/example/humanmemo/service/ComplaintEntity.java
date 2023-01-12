package com.example.humanmemo.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Value
@Table("complaints")
public class ComplaintEntity {
    @Id
    Long id;
    String title;
    @Column("reg_date_on")
    Date regDateOn;
}
