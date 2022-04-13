package dev.gonevski.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class XYZLinkedList <T> {

    // Points to the front and back of the LinkedList.
    public XYZNode<T> head = null;
    public
    XYZNode<T> tail = null;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void add(T data) {
        XYZNode<T> newNode = new XYZNode(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public T get(int index) {
        XYZNode<T> iterator = head;

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is less than 0 or above the list's size.");
        }

        for (int i = 0; i < index; i++) {
            iterator = iterator.next;
        }

        return iterator.data;
    }

    public boolean checkIfExists(String name) {
        XYZNode<T> iterator = head;

        for(int i = 0; i < size; i++) {
            T element = iterator.data;

            try {
                if (Objects.equals(element.getClass().getMethod("getUserLogin").invoke(element), name)) {
                    return true;
                }
            } catch (NoSuchMethodException e) {
                continue;
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
            iterator = iterator.next;
        }
        return false;
    }

    @Override
    public String toString() {
        XYZNode<T> current = head;
        String result = "";

        while (current != null) {
            result += current.data;

            if (current != tail) {
                result += "\n";
            }
            current = current.next;
        }

        return result;
    }

}
