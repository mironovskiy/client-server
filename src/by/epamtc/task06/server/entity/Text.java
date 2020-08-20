package by.epamtc.task06.server.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text implements Component, Serializable {
    private List<Component> components = new ArrayList<Component>();

    public void addComponent(Component component){
        components.add(component);
    }

    public void addComponents(Component componentsArr[]) {
        for (int i = 0; i < componentsArr.length; i++) {
            components.add(componentsArr[i]);
        }
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public Text(){}

    @Override
    public void print() {
        for (Component component: components) {
            component.print();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Text)) return false;
        Text text = (Text) o;
        return Objects.equals(components, text.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return "Text{" +
                "components=" + components +
                '}';
    }
}
