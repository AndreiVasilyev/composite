package by.epam.jwdcomposite.composite;

import by.epam.jwdcomposite.util.DelimiterGenerator;

import java.util.ArrayList;
import java.util.List;


public class TextComposite implements TextComponent {

    private List<TextComponent> componentsList;
    private TextComponentType type;

    public TextComposite(TextComponentType type) {
        this.componentsList = new ArrayList<>();
        this.type = type;
    }

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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String prefix = DelimiterGenerator.componentPrefix(this);
        String postfix;
        builder.append(prefix);
        for (TextComponent component : componentsList) {
            prefix = DelimiterGenerator.componentPrefix(this, component);
            builder.append(prefix);
            builder.append(component);
            postfix = DelimiterGenerator.componentPostfix(this, component);
            builder.append(postfix);
        }
        postfix = DelimiterGenerator.componentPostfix(this);
        builder.append(postfix);

        return builder.toString();
    }

    //TODO Comparator
}
