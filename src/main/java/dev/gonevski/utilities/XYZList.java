package dev.gonevski.utilities;
// List should dynamically resize
// allow me to insert elements and have the order of insertion maintained
// does allow duplicates
public interface XYZList<XYZEntity> {
    void add(XYZEntity XYZElement);

    XYZEntity get(int XYZIndex);

    int size();
}