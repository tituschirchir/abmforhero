package client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecordBase;

public abstract class AvroHelper extends SpecificRecordBase {
    @Override
    @JsonIgnore
    public abstract Schema getSchema();

    @Override
    @JsonIgnore
    public abstract Object get(int field);
}
