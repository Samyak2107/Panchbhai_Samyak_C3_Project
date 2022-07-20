import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        boolean flag = false;
        LocalTime currentTime = getCurrentTime();
        if(currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)){
            flag = true;
        }
        return flag;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    public Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int getItemPrice(String itemName) throws itemNotFoundException {
        Item item = findItemByName(itemName);
        if (item == null)
            throw new itemNotFoundException(itemName);
        return item.getPrice();
    }

    public int getOrderTotal(List<String> itemNames) throws itemNotFoundException {
        int orderTotal = 0;
        for (int i = 0; i < itemNames.size(); i++) {
            Item item = findItemByName(itemNames.get(i));
            if (item == null) {
                throw new itemNotFoundException(itemNames.get(i));
            }
            orderTotal += item.getPrice();
        }
        return orderTotal;
    }

}
