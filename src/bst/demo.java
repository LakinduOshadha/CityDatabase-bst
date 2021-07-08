package bst;


/**
 * for CityDatabase.java demonstration
 *
 * @author Lakindu Oshadha (lakinduoshadha98@gmail.com)
 */
public class demo {
    /**
     * main
     * demonstrates the CityDatabase class
     *
     * @param args
     */
    public static void  main(String [] args) {
        // This is a Demonstration of CityDatabase Class
        CityDatabase DB = new CityDatabase();
        System.out.println("This is a Demonstration of CityDatabase Class");
        System.out.println("--------------------------------------------------------------");
        // Insertion of records
        DB.insert("Colombo", 6.927079, 79.861244);
        DB.insert("Chicago", 41.881832, -87.623177);
        DB.insert("Sydney", -33.865143, 151.209900);
        DB.insert("Polonnaruwa",1.234567,2.123456);
        DB.insert("Jaffna", 4.56789,5.456789);
        DB.insert("Matara",9.87456,6.145455);

        // Printing the records in Ascending order
        System.out.println("< Printing of records in Ascending order after insertion >");
        DB.printAscending();

        System.out.println("--------------------------------------------------------------");

        // Deletion of the record Jaffna
        DB.delete("Jaffna");

        // Printing of records in Ascending order After Deletion of the record Jaffna
        System.out.println("< Printing of records in Ascending order After Deletion of the record Jaffna >");
        DB.printAscending();

        System.out.println("--------------------------------------------------------------");

        // Searching the record Polonnaruwa
        System.out.println("< Searching the record Polonnaruwa >");
        DB.search("Polonnaruwa");

        System.out.println("--------------------------------------------------------------");

        // Printing the records in Descending order
        System.out.println("Printing the records in Descending order");
        DB.printDescending();

        System.out.println("--------------------------------------------------------------");

        // Printing the cities within 2000km from point (0,0)
        System.out.println("Printing the cities within 2000km from point (0,0)");
        DB.printCitiesInDistance(0,0,2000);


    }
}
