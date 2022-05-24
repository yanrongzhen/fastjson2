package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson2.JSONWriter;

import java.util.ArrayList;
import java.util.List;

public class SerializeWriter {
    final JSONWriter raw;

    public JSONWriter getRaw() {
        return raw;
    }

    final ListWrapper<PropertyFilter> propertyFilters;
    final ListWrapper<ValueFilter> valueFilters;

    public SerializeWriter() {
        this(JSONWriter.of());
    }

    public SerializeWriter(JSONWriter raw) {
        this.raw = raw;
        this.propertyFilters = new ListWrapper<>();
        this.valueFilters = new ListWrapper<>();
    }

    public void writeNull() {
        this.raw.writeNull();
    }

    public void write(String text) {
        this.raw.writeString(text);
    }

    public List<PropertyFilter> getPropertyFilters() {
        return propertyFilters;
    }

    public List<ValueFilter> getValueFilters() {
        return valueFilters;
    }

    class ListWrapper<T>
            extends ArrayList<T> {
        public boolean add(T filter) {
            if (filter instanceof PropertyFilter) {
                raw.getContext().setPropertyFilter((PropertyFilter) filter);
            }
            if (filter instanceof ValueFilter) {
                raw.getContext().setValueFilter((ValueFilter) filter);
            }
            return super.add(filter);
        }
    }

    public String toString() {
        return raw.toString();
    }
}
