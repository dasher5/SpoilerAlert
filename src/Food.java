import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Food
{
    private String   foodName;
    private Calendar expdate;
    private String   description;
    private String   owner;
    private boolean  fridge;


    public Food()
    {
        expdate = new GregorianCalendar(2000, 01, 01);
        fridge = true;

    }


    public Food(String name)
    {
        expdate = new GregorianCalendar(2000, 01, 01);
        fridge = true;
        this.setName(name);

    }


    public Food(int exp)
    {
        expdate = new GregorianCalendar(2000, 01, 01);

        fridge = true;
        this.setExpDate(exp);
    }


    public long daysTellExpiration()
    {
        long expday = expdate.getTimeInMillis();
        long currentday = Calendar.getInstance().getTimeInMillis();

        long diff = currentday - expday;
        diff = diff / 86400000;

        return diff;

    }


    public void setExpDate(int daystotal)
    {
        Calendar curcal = Calendar.getInstance();
        expdate.set(
            curcal.get(Calendar.YEAR),
            curcal.get(Calendar.MONTH),
            curcal.get(Calendar.DATE));
        expdate.add(Calendar.DAY_OF_MONTH, -daystotal);

    }


    public String getName()
    {
        return foodName;
    }


    public void setName(String newname)
    {
        foodName = newname;
    }


    public String getOwner()
    {
        return description;
    }


    public void setOwner(String own)
    {
        owner = own;
    }


    public String toString()
    {
        return foodName + ": " + daysTellExpiration()
            + " day(s) tell expiration, owned by: " + owner;
    }

}
