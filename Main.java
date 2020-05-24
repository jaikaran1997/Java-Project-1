
/**
 * Main initialises the program and all the other classes, provides an menu for the user
 * and performs a few tasks like printing invoice and priting robot ticket.
 * @author Jaikaran Saini u3175056
 */
import java.util.*;
import java.io.*;
public class Main
{
    //Initialising the Variables
    public int numberOrdered=0;
    public double totalCost=0;
    public double totalWeight=0;
    public double packingCost=0;
    public double orbitCost=18127.0;
    public double invoiceCost=0;
    public boolean quitprogram=false;
    public boolean numberCheck =false;
    public boolean foundcompany=false;
    public boolean allZero =false;
    public String inputString;
    public String companyOrdered;
    public String[] orderNumbers= new String[5];
    public String MENUBORDER = new String(new char[200]).replace("\0", "-");
    public String invoiceBORDER = new String(new char[200]).replace("\0", "_");

    public ProductInfo product= new ProductInfo();
    public Data data = new Data();
    public Scanner in= new Scanner(System.in);

    public Main()
    {
        System.out.print("\u000c");
        //Menu with a conditional loop
        while(quitprogram==false)
        {
            System.out.printf("|%.37s|",MENUBORDER);
            System.out.printf("\n|%-37s|\n","MENU");
            System.out.printf("|%-37s|\n","Warehouse System");
            System.out.printf("|%.37s|",MENUBORDER);
            System.out.printf("\n|%-5s|","Q");
            System.out.printf("|%-30s|","Quit");
            System.out.printf("\n|%-5s|","C");
            System.out.printf("|%-30s|","Print Company Table");
            System.out.printf("\n|%-5s|","F");
            System.out.printf("|%-30s|","Process Food Table");
            System.out.printf("\n|%-5s|","QU");
            System.out.printf("|%-30s|","Generate Quote");
            System.out.printf("\n|%-5s|","QF");
            System.out.printf("|%-30s|","Generate Quote to File");
            System.out.printf("\n|%.37s|\n",MENUBORDER);

            //Switch for the Menu 
            System.out.println("Enter a Choice:");
            String choice= in.nextLine();
            choice = choice.toUpperCase();
            switch(choice)
            {
                case "Q":
                {   
                    //Case to Quit Program
                    System.out.printf("|%.37s|\n",MENUBORDER);
                    System.out.printf("|%-37s|","You Chose: ");
                    System.out.printf("\n|%5s|","Q");
                    System.out.printf("|%30s|","Quit");
                    System.out.printf("\n|%.37s|\n",MENUBORDER);
                    quitprogram=true;
                    System.exit(0);
                    break;
                }   

                case "C":
                {   
                    //Case to Print Company Table
                    System.out.printf("|%.37s|\n",MENUBORDER);
                    System.out.printf("|%-37s|","You Chose:");
                    System.out.printf("\n|%5s|","C");
                    System.out.printf("|%30s|","Print Company Table");
                    System.out.printf("\n|%.37s|\n",MENUBORDER);
                    data.PrintTableCompany();
                    break;
                }

                case "F":
                {   
                    //Case to Print Product Table
                    System.out.printf("|%.37s|\n",MENUBORDER);
                    System.out.printf("|%-37s|","You Chose:");
                    System.out.printf("\n|%5s|","W");
                    System.out.printf("|%30s|","Print Product Table");
                    System.out.printf("\n|%.37s|\n",MENUBORDER);
                    product.PrintTableProduct();
                    break;
                }

                case "QU":
                {   
                    foundcompany=false;
                    System.out.printf("|%.37s|\n",MENUBORDER);
                    System.out.printf("|%-37s|","You Chose:");
                    System.out.printf("\n|%5s|","QU");
                    System.out.printf("|%30s|","Generate Quote");
                    System.out.printf("\n|%.37s|\n",MENUBORDER);
                    //Error Check for User Input
                    QuoteInput();
                    if(allZero==false)
                    {
                        QuoteHeader(companyOrdered);
                        QuotePrint();
                    }
                    else
                    {
                        System.out.println("\nError! All quantities Zero");
                    }
                    break;
                }

                case "QF":
                {   
                    boolean spaceCheck=false;
                    allZero=false;
                    System.out.printf("|%.37s|\n",MENUBORDER);
                    System.out.printf("|%-37s|","You Chose:");
                    System.out.printf("\n|%5s|","QF");   
                    System.out.printf("|%30s|","Quote to FILE");
                    System.out.printf("\n|%.37s|\n",MENUBORDER);
                    QuoteInput();
                    if(!allZero)
                    {
                        do
                        {
                            System.out.println("Enter filename to Write the quote(do not add .txt): ");
                            inputString=in.nextLine();
                            if(inputString.trim().length() > 0)
                            {
                                inputString=inputString+".txt";
                                QuoteFile(inputString);
                                spaceCheck=true;
                            }

                            else
                            {
                                System.out.println("Error! Empty String");
                            }
                        }while(!spaceCheck);
                    }
                    else
                    {
                        System.out.println("Error! All Quantities Zero, Skipping write to File!");
                    }
                    break;
                }

                default:
                {
                    //Default Case if Invalid User Input 
                    System.out.printf("|%.37s|\n",MENUBORDER);
                    System.out.printf("|%37s|\n","Invalid Choice");
                    System.out.printf("|%.37s|\n",MENUBORDER);
                }
            }
        }
    }

    public void Quote(int number, int x)
    {   
        orderNumbers[x]=String.valueOf(number);
    }

    public void QuoteInput()
    {
        invoiceCost=0;
        packingCost=0;
        totalWeight=0;
        totalCost=0;
        orbitCost=18127.0;
        do
        {
            System.out.print("Enter Company ID:");
            companyOrdered= in.nextLine();
            companyOrdered= companyOrdered.toUpperCase();
            CompanyInfo company = data.CompanySearch(companyOrdered);
            if(company==null) 
            {
                System.out.println("Company "+companyOrdered+" not found");
            }
            else
            {
                foundcompany=true;
            }
        }while(foundcompany==false);

        //Error Check for User Input
        for(int x=0;x<product.productID.length;x++)
        {
            numberOrdered=0;
            numberCheck=false;
            do{
                System.out.print("\nEnter number of "+ product.desc[x]+" to be ordered: ");
                inputString=in.nextLine();
                boolean numberEntered=false;
                try
                {
                    numberOrdered= Integer.parseInt(inputString.trim());
                    numberEntered=true;
                }
                catch(NumberFormatException nfe)
                {
                    System.out.println("Error! Enter a number!");
                }

                if(numberEntered)
                {
                    if(numberOrdered<0)
                    {
                        System.out.println("Error! Enter a number greater than 0!");

                    }
                    else
                    {
                        if(numberOrdered>24)
                        {
                            System.out.println("Error! Maximum allowed is 24!");
                        }
                        else
                        {
                            numberCheck=true;
                        }
                    }
                }
            }while(numberCheck==false);
            Quote(numberOrdered,x);
        } 

        for(int x=0;x<product.productID.length;x++)
        {
            allZero=false;
            if(orderNumbers[x].compareTo("0")==0)
            {
                allZero=true;
            }
            else
            {
                allZero=false;
                break;
            }   
        }
    }

    public void QuoteHeader(String companyOrdered)
    {
        CompanyInfo company = data.CompanySearch(companyOrdered);
        String head="Quotation For "+company.companyID+" "+company.companyName+" Pickup at "+company.pickupBayID;
        System.out.printf("|%.69s|\n",invoiceBORDER);
        System.out.printf("|%-69s|\n",head);
        System.out.printf("|%.69s|\n",invoiceBORDER); 
        System.out.printf("|%-10s","Item");
        System.out.printf("|%-10s","Number");
        System.out.printf("|%-15s","Cost");
        System.out.printf("|%-15s","Total Cost");
        System.out.printf("|%-15s|\n","Total Weight");
        System.out.printf("|%.10s",invoiceBORDER);
        System.out.printf("|%.10s",invoiceBORDER);
        System.out.printf("|%.15s",invoiceBORDER);
        System.out.printf("|%.15s",invoiceBORDER);
        System.out.printf("|%.15s|\n",invoiceBORDER);
    }

    public void QuotePrint()
    {
        invoiceCost=0;
        packingCost=0;
        totalWeight=0;
        totalCost=0;
        orbitCost=18127.0;
        for(int x=0;x<product.productID.length;x++)
        {
            if(Integer.parseInt(orderNumbers[x])!=0)
            {
                int number=Integer.parseInt(orderNumbers[x]);
                double cost=0;
                double weight=0;
                cost=product.price[x]*number;
                weight=product.weight[x]*number;
                System.out.printf("|%-10s",product.productID[x]);
                System.out.printf("|%-10s",orderNumbers[x]);
                System.out.printf("|$%,14.2f",product.price[x]);
                System.out.printf("|$%,14.2f",cost);  
                System.out.printf("|%,-15.2f|\n",weight);
                totalWeight=totalWeight+weight;
                totalCost=totalCost+cost;
            }
        }
        int i = (int) Math.ceil(totalWeight/1000);

        if(i>5)
        {
            if(i>15)
            {
                packingCost=457.0*i;
            }
            else
            {
                packingCost=6823.50;

            }
        }
        else
        {
            packingCost=3192.0;
        }
        orbitCost=orbitCost*i;
        invoiceCost=totalCost+packingCost+orbitCost;
        System.out.printf("|%.69s|\n",invoiceBORDER);
        System.out.printf("|%-23s %,23.2f %-21s|\n","Total Weight: ",totalWeight," g");
        System.out.printf("|%-23s %23s %-21s|\n","Billing Weight: ",i," kg");
        System.out.printf("|%-34s $%-,33.2f|\n","Total Food Cost:",totalCost);
        System.out.printf("|%-34s $%-,33.2f|\n","Packing Cost:",packingCost);
        System.out.printf("|%-34s $%-,33.2f|\n","Orbit Cost:",orbitCost);
        System.out.printf("|%-34s $%-,33.2f|\n","Total Cost:",invoiceCost);
        System.out.printf("|%.69s|\n",invoiceBORDER);
    }

    public void QuoteFile(String inputString)
    {
        invoiceCost=0;
        packingCost=0;
        totalWeight=0;
        totalCost=0;
        PrintWriter outFile=null;
        try
        {
            outFile = new PrintWriter(inputString);
        }
        catch (IOException e)
        {
            System.out.println("Error creating file: "+inputString);    
        }

        CompanyInfo company = data.CompanySearch(companyOrdered);
        String head="Quotation For "+company.companyID+" "+company.companyName+" Pickup at "+company.pickupBayID;
        outFile.printf("|%.69s|%n",invoiceBORDER);
        outFile.printf("|%-69s|%n",head);
        outFile.printf("|%.69s|%n",invoiceBORDER); 
        outFile.printf("|%-10s","Item");
        outFile.printf("|%-10s","Number");
        outFile.printf("|%-15s","Cost");
        outFile.printf("|%-15s","Total Cost");
        outFile.printf("|%-15s|%n","Total Weight");
        outFile.printf("|%.10s",invoiceBORDER);
        outFile.printf("|%.10s",invoiceBORDER);
        outFile.printf("|%.15s",invoiceBORDER);
        outFile.printf("|%.15s",invoiceBORDER);
        outFile.printf("|%.15s|%n",invoiceBORDER);

        for(int x=0;x<product.productID.length;x++)
        {
            if(Integer.parseInt(orderNumbers[x])!=0)
            {
                int number=Integer.parseInt(orderNumbers[x]);
                double cost=0;
                double weight=0;
                cost=product.price[x]*number;
                weight=product.weight[x]*number;
                outFile.printf("|%-10s",product.productID[x]);
                outFile.printf("|%-10s",orderNumbers[x]);
                outFile.printf("|$%,14.2f",product.price[x]);
                outFile.printf("|$%,14.2f",cost);  
                outFile.printf("|%,-15.2f|%n",weight);
                totalWeight=totalWeight+weight;
                totalCost=totalCost+cost;
            }
        }

        int i = (int) Math.ceil(totalWeight/1000);

        if(i>5)
        {
            if(i>15)
            {
                packingCost=457.0*i;
            }
            else
            {
                packingCost=6823.50;

            }
        }
        else
        {
            packingCost=3192.0;
        }
        orbitCost=orbitCost*i;
        invoiceCost=totalCost+packingCost+orbitCost;
        outFile.printf("|%.69s|%n",invoiceBORDER);
        outFile.printf("|%-23s %,23.2f %-21s|%n","Total Weight: ",totalWeight," g");
        outFile.printf("|%-23s %23s %-21s|%n","Billing Weight: ",i," kg");
        outFile.printf("|%-34s $%-,33.2f|%n","Total Food Cost:",totalCost);
        outFile.printf("|%-34s $%-,33.2f|%n","Packing Cost:",packingCost);
        outFile.printf("|%-34s $%-,33.2f|%n","Orbit Cost:",orbitCost);
        outFile.printf("|%-34s $%-,33.2f|%n","Total Cost:",invoiceCost);
        outFile.printf("|%.69s|%n",invoiceBORDER);
        outFile.close();
    }

}
