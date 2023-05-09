package com.main.cadma1.infrastructure;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.main.cadma1.domain.DataCadma;

public class DataCadmaSerializer extends StdSerializer<DataCadma> {
    public DataCadmaSerializer() {
        this(null);
    }
    public DataCadmaSerializer(Class<DataCadma> t) {
        super(t);
    }

    @Override
    public void serialize(DataCadma value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

    }

}
