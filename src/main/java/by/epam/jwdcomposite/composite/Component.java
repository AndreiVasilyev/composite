package by.epam.jwdcomposite.composite;

public interface Component {
    void action();
    boolean add(Component component);
    boolean remove(Component component);
    Component get(int index);
}
