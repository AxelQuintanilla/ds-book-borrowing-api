package com.telus.ds.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Table(name = "DISCOGRAPHYS")
public class Discography {
  public Discography(){}

  public Discography(String commercialName, Integer year,
                     String legalName) {
    super();
    this.legalName = legalName;
    this.commercialName = commercialName;
    this.year = year;
  }

  @Id
  @Column(name="DISCOGRAPHY_ID", updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="COMMERCIAL_NAME", updatable = false)
  @NotNull(message = "Commercial name is required")
  private String commercialName;

  @Column(name="LEGAL_NAME", updatable = false)
  @NotNull(message = "Legal name is required")
  private String legalName;

  @Column(name="year", updatable = false)
  @NotNull(message = "YEAR is required")
  private Integer year;
}
