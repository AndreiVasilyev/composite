package by.epam.jwdcomposite.composite;

import java.util.List;

public interface TextComponent {
    String toString();
    TextComponentType getTextComponentType();
    List<TextComponent> getComponentsList();
}
