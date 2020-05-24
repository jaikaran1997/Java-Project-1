
/**
 * Company_Info read the company information from the array stored in the class Data
 * and prints when required
 * @author Jaikaran Saini u3175056
 */
public class CompanyInfo
{
    //Defining the Variables
    public String companyID;
    public String companyName;
    public boolean payTax;
    public String pickupBayID;
    
    //Constructor to Initialise values
    public CompanyInfo(String ccompanyID, String ccompanyName, boolean ppayTax, String ppickupBayID)
    {
      companyID=ccompanyID;
      companyName=ccompanyName;
      payTax=ppayTax;
      pickupBayID=ppickupBayID;
    }

}
