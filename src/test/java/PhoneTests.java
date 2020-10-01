import org.testng.Assert;
import org.testng.annotations.Test;

public class PhoneTests {
    @Test
    public void test001() {
        Phone myPhone001 = new Phone("Huawei", 64, 750);
        myPhone001.call("654654654");
    }

    @Test
    public void test002() {
        Phone myNewPhone002 = new Phone("Samsung","Silvergray", 128, 1199);
        myNewPhone002.sendMessage("654654665465465465");
        Assert.assertEquals(myNewPhone002.getModel(), "iPhone");

    }

}
