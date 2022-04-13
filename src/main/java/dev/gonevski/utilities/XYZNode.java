package dev.gonevski.utilities;

public class XYZNode <T> {
    public T data;
    public XYZNode<T> next;

    public XYZNode(T data) {
        this.data = data;
        this.next = null;
    }
}
