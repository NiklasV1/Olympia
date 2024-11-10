import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OlympicEntityTest {

    static class OlympicEntityTestClass extends OlympicEntity {
        public OlympicEntityTestClass(int id, String name) {
            super(id, name);
        }
    }

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new OlympicEntityTestClass(0, "test"));
        assertThrows(IllegalArgumentException.class, () -> new OlympicEntityTestClass(-1, "test"));

        assertThrows(IllegalArgumentException.class, () -> new OlympicEntityTestClass(1, null));
        assertThrows(IllegalArgumentException.class, () -> new OlympicEntityTestClass(1, ""));

        assertDoesNotThrow(() -> new OlympicEntityTestClass(20, "testEntity"));
    }

    @Test
    void getId() {
        OlympicEntityTestClass testClass = new OlympicEntityTestClass(20, "testEntity");
        assertEquals(20, testClass.getId());
    }

    @Test
    void getName() {
        OlympicEntityTestClass testClass = new OlympicEntityTestClass(20, "testEntity");
        assertEquals("testEntity", testClass.getName());
    }

    @Test
    void testEquals() {
        OlympicEntityTestClass testClass1 = new OlympicEntityTestClass(20, "testEntity1");
        OlympicEntityTestClass testClass2 = new OlympicEntityTestClass(20, "testEntity2");
        OlympicEntityTestClass testClass3 = new OlympicEntityTestClass(40, "testEntity3");

        assertEquals(testClass1, testClass2);
        assertNotEquals(testClass1, testClass3);

        String testString = "test";
        //noinspection AssertBetweenInconvertibleTypes
        assertNotEquals(testClass1, testString);
    }

    @Test
    void testHashCode() {
        OlympicEntityTestClass testClass1 = new OlympicEntityTestClass(20, "testEntity1");
        OlympicEntityTestClass testClass2 = new OlympicEntityTestClass(20, "testEntity2");
        OlympicEntityTestClass testClass3 = new OlympicEntityTestClass(40, "testEntity3");

        assertEquals(testClass1.hashCode(), testClass2.hashCode());
        assertNotEquals(testClass1.hashCode(), testClass3.hashCode());
    }

    @Test
    void compareTo() {
        OlympicEntityTestClass testClass1 = new OlympicEntityTestClass(10, "testEntity1");
        OlympicEntityTestClass testClass2 = new OlympicEntityTestClass(20, "testEntity2");
        OlympicEntityTestClass testClass3 = new OlympicEntityTestClass(20, "testEntity3");
        OlympicEntityTestClass testClass4 = new OlympicEntityTestClass(30, "testEntity4");

        assertEquals(-1, testClass1.compareTo(testClass2));
        assertEquals(0, testClass2.compareTo(testClass3));
        assertEquals(1, testClass4.compareTo(testClass3));
    }
}