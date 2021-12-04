package by.epam.jwdcomposite.composite;

public class Symbol implements Component {
    @Override
    public void action() {
        System.out.println("action in" +this.getClass().getSimpleName());
    }

    @Override
    public boolean add(Component component) {
        return false;
    }

    @Override
    public boolean remove(Component component) {
        return false;
    }

    @Override
    public Component get(int index) {
        return null;
    }
}
