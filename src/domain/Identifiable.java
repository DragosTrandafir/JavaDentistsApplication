package domain;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

// generic superclass - we can create how many particular classes we want and replace ID with String,Int etc...
public interface Identifiable<ID> {
    public ID getId();
    public void setId(ID id);
}
