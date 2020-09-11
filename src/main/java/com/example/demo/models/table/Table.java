package com.example.demo.models.table;

import java.util.List;
import java.util.stream.Collectors;

public class Table {
    private final String FIELD_PATTERN = "%s %s";
    private final String fieldsString;
    private final List<TableField> fields;
    private final String name;


    public Table(List<TableField> fields, String modelName) {
        this.fields = fields;
        this.name = modelName.concat("s");
        fieldsString = createFieldsString(fields);
    }

    public List<TableField> getFields() {
        return fields;
    }

    public String getFieldsString() {
        return fieldsString;
    }

    public String getName() {
        return name;
    }

    private String createFieldsString(List<TableField> fields) {
        return fields
                .stream()
                .map(x -> String.format(FIELD_PATTERN, x.getFieldName(), x.getFieldType()))
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return "Table{" +
                "FIELD_PATTERN='" + FIELD_PATTERN + '\'' +
                ", fieldsString='" + fieldsString + '\'' +
                ", fields=" + fields +
                ", name='" + name + '\'' +
                '}';
    }
}
