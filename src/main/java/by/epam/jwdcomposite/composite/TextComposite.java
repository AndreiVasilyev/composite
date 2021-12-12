package by.epam.jwdcomposite.composite;

import java.util.ArrayList;
import java.util.List;

import static by.epam.jwdcomposite.util.DelimiterDetector.*;

public class TextComposite implements TextComponent {

    private List<TextComponent> componentsList;
    private TextComponentType type;

    public TextComposite(TextComponentType type) {
        this.componentsList = new ArrayList<>();
        this.type = type;
    }

    public TextComposite(TextComponentType type, List<TextComponent> components) {
        this.componentsList = components;
        this.type = type;
    }

    @Override
    public List<TextComponent> getComponentsList() {
        return componentsList;
    }

    public boolean add(TextComponent component) {
        return componentsList.add(component);
    }

    public boolean remove(TextComponent component) {
        return componentsList.remove(component);
    }

    @Override
    public TextComponentType getTextComponentType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite composite = (TextComposite) o;

        if (!componentsList.equals(composite.componentsList)) return false;
        return type == composite.type;
    }

    @Override
    public int hashCode() {
        int result = componentsList.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String prefix = componentPrefix(this);
        String postfix;
        builder.append(prefix);
        for (TextComponent component : componentsList) {
            prefix = componentPrefix(this, component);
            builder.append(prefix);
            builder.append(component);
            postfix = componentPostfix(this, component);
            builder.append(postfix);
        }
        postfix = componentPostfix(this);
        builder.append(postfix);

        return builder.toString();
    }
}
