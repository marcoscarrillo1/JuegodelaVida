package Estructuras;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElementoLSTest {
    ElementoLS dato = new ElementoLS();

    @Test
    public void getData() {
        assertEquals(dato.getData(),null);
    }
    @Test
    public void testSetData() {
        dato.setData(23);
        assertEquals(dato.getData(),23);
    }
}