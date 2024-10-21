package com.bezkoder.spring.jpa.onetoone.status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusPublishedConverter implements AttributeConverter<StatusPublished, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusPublished status) {
        if (status == null) {
            return null;
        }
        return status.getId(); // Chuyển enum thành giá trị số để lưu trong cơ sở dữ liệu
    }

    @Override
    public StatusPublished convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return StatusPublished.valueOf(dbData); // Chuyển giá trị số từ cơ sở dữ liệu thành enum
    }
}
