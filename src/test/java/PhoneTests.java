import org.testng.annotations.Test;

public class PhoneTests {
    @Test
    public void test001() {
        Phone myPhone01;
        myPhone01 = new Phone();


        Phone myPhone02 = new Phone("Huawei", 64, 750);
    }

    @Test
    public void test002() {

        Phone myNewPhone001 = new Phone("Samsung","Silvergray", 128, 1199);

    }

}
