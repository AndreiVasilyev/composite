package by.epam.jwdcomposite.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class Symbol implements TextComponent {

    private static final Logger log = LogManager.getLogger();

    private TextComponentType type;
    private char value;

    public Symbol(TextComponentType type, char value) {
        this.type = type;
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public TextComponentType getTextComponentType() {
        return type;
    }

    @Override
    public List<TextComponent> getComponentsList() {
        log.warn("Unsupported operation in class {}", this.getClass());
        throw new UnsupportedOperationException("Unsupported operation in class" + this.getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        if (value != symbol.value) return false;
        return type == symbol.type;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
