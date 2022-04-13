package dev.gonevski.utilities;

import java.util.Arrays;

public class XYZArrayList<XYZEntity> implements XYZList<XYZEntity> {

    private Object [] XYZelements; // elements witin the array
    private int XYZcurrentIndex; // index in array

    public XYZArrayList(){
        // Construct of the array list of elements and their indices
        this.XYZelements = new Object[10];
        this.XYZcurrentIndex = 0;
    }

    @Override
    public void add(XYZEntity XYZElement) {
        if(XYZcurrentIndex < this.XYZelements.length){
            this.XYZelements[XYZcurrentIndex] = XYZElement;
        }
        else{
            // resizing logic
            this.XYZelements = Arrays.copyOf(this.XYZelements, this.XYZelements.length + 10);
            this.XYZelements[XYZcurrentIndex] = XYZElement;
        }
        this.XYZcurrentIndex++;

    }

    @Override
    public XYZEntity get(int XYZIndex) {
        return (XYZEntity) this.XYZelements[XYZIndex];
    }

    @Override
    public int size() {
        return this.XYZcurrentIndex;
    }

}
