import org.testng.annotations.Test;

public class GuitarTests {

    @Test
    public void test001() {
        Guitar myGuitar01 = new Guitar("Fender","Black", 799, "C" , "Steel", true );

    }

    @Test
    public void test002() {
        Guitar myGuitar02 = new Guitar("Yamaha", "Red", 849, "Am", "Steel", false);
    }
}
