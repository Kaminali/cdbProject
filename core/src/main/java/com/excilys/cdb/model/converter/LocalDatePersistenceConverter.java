package com.excilys.cdb.model.converter;

import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
/**
 * 
 * a class for define the convert method to convert a sql date to a LocalDate
 *
 */
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, java.sql.Date> {

  @Override
  public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
    if (entityValue != null) {
      return java.sql.Date.valueOf(entityValue);
    }
    return null;
  }

  @Override
  public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
    if (databaseValue != null) {
      return databaseValue.toLocalDate();
    }
    return null;
  }
}