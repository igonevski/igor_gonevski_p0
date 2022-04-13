package dev.gonevski.listtests;

import dev.gonevski.utilities.XYZList;
import dev.gonevski.utilities.XYZArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class XYZListTests {

    @Test
    void add_items_size(){
        XYZList<String> names = new XYZArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");
        Assertions.assertEquals(3, names.size());
    }

    @Test
    void get_by_index(){
        XYZList<String> names = new XYZArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");
        String result = names.get(1);
        Assertions.assertEquals("B", result);
    }

    @Test
    void many_adds(){
        XYZList<String> names = new XYZArrayList<>();
        for(int i = 0; i <1000; i++){
            names.add("hello");
        }
        Assertions.assertEquals(1000,names.size());
    }

}
