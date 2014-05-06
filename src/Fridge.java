import java.util.ArrayList;

public class Fridge
{
    // This Class will hold all of our food objects, and give us the stuff we
// need

    private ArrayList<Food> foodList;


    public Fridge()
    {
        foodList = new ArrayList<Food>();
    }


    // adds the food item to our fridge
    public void addFood(Food item)
    {
        foodList.add(item);
    }


    // deletes the passed food item
    public boolean deleteFood(Food item)
    {

        return foodList.remove(item);

    }


    // deletes the food at the index
    public Food deleteFoodAt(int index)
    {
        return foodList.remove(index);

    }


    // returns a list of all expired food
    public ArrayList<Food> deleteExpiredFood()
    {
        return null;
    }


    // returns food expiring in x days
    private ArrayList<Food> getFoodExpinDays(int days)
    {
        ArrayList<Food> expFood = new ArrayList<Food>();
        for (int i = 0; i < foodList.size(); i++)
        {
            if (foodList.get(i).daysTellExpiration() <= days)
            {
                expFood.add(foodList.get(i));
            }
        }

        return expFood;
    }


    // returns number of things in fridge
    public int size()
    {
        return foodList.size();
    }


    public String expFoodinString(int days)
    {
        ArrayList<Food> expFood = this.getFoodExpinDays(days);
        String result = "Foods expiring in " + days + " day(s):\n\n";

        for (int i = 0; i < expFood.size(); i++)
        {
            result = result + expFood.get(i).toString() + "\n";
        }

        return result;
    }


    public String toString()
    {
        String result = "Foods   \n";

        for (int i = 0; i < foodList.size(); i++)
        {
            result =
                result + "(" + (i) + ") " + foodList.get(i).toString() + "\n";
        }

        return result;
    }

}
