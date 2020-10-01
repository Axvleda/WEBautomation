public class Phone {
    String model;
    String color;
    int amoutofMemory;
    int price;

    public Phone() {
        System.out.println("myPhone simple Constructer called.");


    }

    public Phone(String model, int amoutofMemory, int price) {
        this.model = model;
        this.amoutofMemory = amoutofMemory;
        this.price = price;

        System.out.format("myPhone Constructer called. %10s - %4d %4d", model,amoutofMemory,price);

    }

    public Phone(String model, String color, int amoutofMemory, int price) {
        this.model = model;
        this.color = color;
        this.amoutofMemory = amoutofMemory;
        this.price = price;

        System.out.format("myPhone Constructer called. %10s - %10s - %4d %4d", model,color,amoutofMemory,price);
    }

    public void sendMessage(String phoneNumber){
        System.out.println("Message sent to: " + phoneNumber);
    }

    public void call(String phoneNumber){
        System.out.println("Phone call to: " + phoneNumber);
    }

    public String getModel() {
        return model;
    }
}
