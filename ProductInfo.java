
/**
 * Product_Info reads the product information from the array stored in the class Data
 * and prints when required
 * @author Jaikaran Saini u3175056
 */
public class ProductInfo
{
    //Initialising the Variables
    public String productID[]={"DC","BEEF","CS","STRAW","EGG2"};
    public String desc[]={"Date Cake","Beef Sandwich","Cheese sandwich","Strawberry Desert","Scrambled Eggs"};
    public double weight[]={134,164,206,177,112};
    public double price[]={59.0,49.50,93.70,157.05,77.15};
    public String BORDER = new String(new char[200]).replace("\0", "*");

    public int ProductSearch(String ordered)
    {
        for(int i=0;i<productID.length;i++)
        {
            if(productID[i].compareTo(ordered)==0)
            {
                return i;
            }
        }
        return -1;
    }
    
    public void printTableProduct()

    {
        for(int i=0;i<productID.length;i++)
        {
            System.out.println(productID[i]+" "+desc[i]+" "+weight[i]+" "+price[i]);
        }

    }
    public void PrintTableProduct( ) //Function to Print Product Table
    {
        System.out.printf("||%.81s||\n",BORDER);
        System.out.printf("||%-81s||\n","Product Table");
        System.out.printf("||%.81s||\n",BORDER);
        System.out.printf("||%-20s|","Product Code");
        System.out.printf("|%-20s|","Description");
        System.out.printf("|%-15s|","Price ");
        System.out.printf("|%-20s||\n","Weight");
        System.out.printf("||%.81s||\n",BORDER);
        for(int x=0;x<productID.length;x++)
        {
             System.out.printf("||%20s|",productID[x]);
             System.out.printf("|%20s|",desc[x]);
             System.out.printf("|$%,14.2f|",price[x]);
             System.out.printf("|%,20.2f||\n",weight[x]);
        }
        System.out.printf("||%.81s||\n",BORDER);
    }
    
}
