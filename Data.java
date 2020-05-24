
/**
 * Class Data stores the information for the products and companies in two array,
 * Prints both the arrays in a table format and also provides a search for data.
 * @author Jaikaran Saini u3175056
 */
public class Data
{
    //Initialising variables
    public CompanyInfo Company_Array[]= new CompanyInfo[5];
    String BORDER = new String(new char[200]).replace("\0", "*");
  
    public Data()//Constructor to initialise Arrays with the data
    {
      Company_Array[0]=new CompanyInfo("BFSC","Blue Fish Space Co",false,"MERCY");
      Company_Array[1]=new CompanyInfo("ECP","Elon Cannon Personal",true,"KIT");
      Company_Array[2]=new CompanyInfo("NAASA","NAASA",true,"MERCY");
      Company_Array[3]=new CompanyInfo("ARRG","ARRG",true,"KAT"); 
      Company_Array[4]=new CompanyInfo("PUB","General Public",true,"MAIL");
    }
    
    
    
      
    public void PrintTableCompany( ) //Function to Print Company Table
    {
        System.out.printf("||%.86s||\n",BORDER);
        System.out.printf("||%-86s||\n","Company Table");
        System.out.printf("||%.86s||\n",BORDER);
        System.out.printf("||%-20s|","Company ID");
        System.out.printf("|%-20s|","Full Name");
        System.out.printf("|%-20s|","Pay Tax");
        System.out.printf("|%-20s||","Pickup Bay ID");
        System.out.printf("\n");
        for(int x=0;x<Company_Array.length;x++)
        {
         System.out.printf("||%20s|",Company_Array[x].companyID);
         System.out.printf("|%20s|",Company_Array[x].companyName);
         System.out.printf("|%20s|",Company_Array[x].payTax);
         System.out.printf("|%20s||\n",Company_Array[x].pickupBayID);
         
        }
        System.out.printf("||%.86s||\n",BORDER);
    }
    
    
  

    public CompanyInfo CompanySearch(String Company) //Function to Search for Company in the Array
    {
        for(int x=0;x<Company_Array.length;x++)
           
            if(Company_Array[x].companyID.compareTo(Company) ==0)
                { 
                    return Company_Array[x];
                }
        return null;
    }
   
    

    
}
