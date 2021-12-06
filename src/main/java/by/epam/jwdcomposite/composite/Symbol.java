package by.epam.jwdcomposite.composite;

public class Symbol implements TextComponent {

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
    public String toString() {
        return String.valueOf(value);
    }
}
