public class Guitar {
    String name;
    String color;
    int price;
    String tuning;
    String typeOfStrings;
    boolean Acoustic;

    public Guitar(String name, String color, int price, String tuning, String typeOfStrings, boolean acoustic) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.tuning = tuning;
        this.typeOfStrings = typeOfStrings;
        this.Acoustic = acoustic;

        System.out.format("Your Guitar: %s, %s ,%d $, %s, %s, Acoustic %b", name,color,price,tuning,typeOfStrings,acoustic);
    }

}
