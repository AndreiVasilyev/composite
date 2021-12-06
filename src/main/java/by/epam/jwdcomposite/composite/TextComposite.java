package by.epam.jwdcomposite.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextComponent {

    private List<TextComponent> componentsList;
    private TextComponentType type;

    public TextComposite(TextComponentType type) {
        this.componentsList=new ArrayList<>();
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
        StringBuilder builder=new StringBuilder();
        for (TextComponent component:componentsList) {
            builder.append(component);
            builder.append("type.getPrefix()");
        }
        return builder.toString();
    }

    //TODO Comparator
}
