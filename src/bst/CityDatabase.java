package bst;

/**
 * This is a City database designed with tree data structure.
 *
 * @author Lakindu Oshadha (lakinduoshadha98@gmail.com)
 */
public class CityDatabase {
    // Initializing Global variables
    private Node root = null;

    /**
     * Inner class for node
     */
    private class Node {
        private String cityName;
        private double longitude;
        private double latitude;
        private Node left;
        private Node right;

        /**
         * Constructor for node
         *
         * @param cityName  City name
         * @param longitude longitude
         * @param latitude  latitude
         */
        protected Node(String cityName, double latitude , double longitude) {
            this.cityName = cityName;
            this.longitude = longitude;
            this.latitude = latitude;
            this.right = null;
            this.left = null;
        }

        /**
         * Prints the details in a node
         *
         */
        private void printNode() {
            System.out.println("City Name : " + this.cityName + " | Coordinates : (" + this.latitude +
                    " , " + this.longitude + ")" );
        }
    }

    /***
     * Creates new node with given details
     *
     * @param cityName City name
     * @param longitude longitude
     * @param latitude  latitude
     * @return  new node
     */
    private Node newNode(String cityName, double latitude, double longitude){
        return new Node(cityName, latitude, longitude);
    }

    /**
     * Creates a new record in the tree recursively
     *
     * @param node root node
     * @param cityName  City Name
     * @param longitude Longitude
     * @param latitude  Latitude
     * @return null
     */
    private Node newRecord(Node node,String cityName, double latitude, double longitude) {
        if(node == null) {
            return newNode(cityName, latitude, longitude);  // Creating new node
        }
        if(cityName.compareTo(node.cityName) < 0) {
            node.left = newRecord(node.left, cityName, latitude, longitude);
        } else if(cityName.compareTo(node.cityName) > 0) {
            node.right = newRecord(node.right, cityName, latitude, longitude);
        }
        return node;
    }

    /**
     * Inserts new city record to the tree
     * This calls the newRecord method with root node to create a new record
     *
     * @param cityName  City name
     * @param longitude longitude
     * @param latitude  latitude
     */
    public void insert(String cityName, double latitude, double longitude) {
        root = newRecord(root, cityName, latitude, longitude);
    }

    /**
     * Deletes the node with given city name.
     *
     * @param node root node
     * @param cityName City name that should be deleted
     * @return null
     */
    private Node delete(Node node, String cityName) {
        if(node == null) {
            return null;
        }
        if(cityName.compareTo(node.cityName) < 0) {
            node.left = delete(node.left, cityName);
        } else if(cityName.compareTo(node.cityName) > 0) {
            node.right = delete(node.right, cityName);
        } else {
            // If the deleting node has one or no child.
            if(node.left == null || node.right == null) {
                if(node.left != null) {
                    return node.left;   // Returns left child of the node if it has a left child
                } else if (node.right != null){
                    return node.right;  // Returns right child of the node if it has a right child
                } else {
                // returning null if node have no child
                    return null;
                }
            } else {        //  If the deleting node has both childs
                // Gets the successor
                Node successor = getSuccessor(node);
                // Setting the successor values to the node
                node.cityName = successor.cityName;
                node.longitude = successor.longitude;
                node.latitude = successor.latitude;
                // deleting the successor node
                node.right = delete(node.right, successor.cityName);
                return node;
            }
        }
        return node;
    }

    /**
     * Deletes the given node (City name should be given)
     * Calls private delete method to delete the node with root node
     *
     * @param cityName  City name of the deleting entry
     */
    public void delete(String cityName) {
        root = delete(root, cityName);
    }

    /**
     * Gets the successor of a node in the tree
     *
     * @param node  Node which the successor should be found.
     * @return  Successor node
     */
    private Node getSuccessor(Node node) {
        if (node == null) {
            return null;
        }
        Node temp = node.right;
        while(temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    /**
     * Searches a node in a tree with a given city name and prints the details
     *
     * @param node  root node
     * @param cityName city name
     * @return null
     */
    private Node search(Node node, String cityName) {
        if(node == null) {
            System.out.println("---Search results---" + "\nERROR : No Results Found");
            return null;
        }

        if(cityName.compareTo(node.cityName) < 0) {
            node.left = search(node.left, cityName);
        } else if(cityName.compareTo(node.cityName) > 0) {
            node.right = search(node.right, cityName);
        } else {
            System.out.println("---Search results---");
            node.printNode();
        }
        return node;
    }

    /**
     * Searches a node by a city name
     * Calls private search method with the root node
     *
     * @param cityName City name of the entry that should be searched
     */
    public void search(String cityName) {
        root = search(root, cityName);
    }

    /**
     * Prints the tree in ascending order.
     *(This class is for my reference and demonstrate the methods)
     *
     * @param node root node
     */
    private void printAscending(Node node){
        if(node == null) {
            return;
        }

        printAscending(node.left);
        node.printNode();
        printAscending(node.right);
    }

    /**
     * Calls printAscending method with root node
     * (This class is for my reference and demonstrate the methods)
     *
     */
    public void printAscending() {
        printAscending(root);
    }

    /**
     * Prints the tree in Descending order.
     *
     * @param node root node
     */
    private void printDescending(Node node){
        if(node == null) {
            return;
        }

        printDescending(node.right);
        node.printNode();
        printDescending(node.left);
    }

    /**
     * Calls printDescending method with root node
     *
     */
    public void printDescending() {
        printDescending(root);
    }

    /**
     * Prints the cities within a given distance(km) from a point(in Decimal degrees).
     *
     * @param node  root node
     * @param longitude longitude of the point
     * @param latitude  latitude of the point
     * @param distance Distance in km
     */
    private void printCitiesInDistance(Node node, double latitude, double longitude, double distance){
        if(node == null) {
            return;
        }
        printCitiesInDistance(node.left, latitude,longitude, distance);
        if(getDistance(longitude,latitude,node.longitude,node.latitude) <= distance) {
            node.printNode();
            return;
        }
        printCitiesInDistance(node.right, latitude, longitude, distance);
    }

    /**
     * Prints the cities within a given distance(km) from a point(in Decimal degrees).
     * Calls private printCitiesInDistance method with root node
     *
     * @param longitude longitude of the point
     * @param latitude latitude of the point
     * @param distance Distance
     */
    public void printCitiesInDistance( double latitude, double longitude, double distance) {
        System.out.println("--- Cities within distance : " + distance + "km from point : (" + longitude +
                "," + latitude + ") ---");
        printCitiesInDistance(root, latitude, longitude, distance);
    }

    /**
     * Finds the distance between two points(In decimal degrees) in km.
     *
     * @param longitude1 longitude of point 1
     * @param latitude1 latitude of point 1
     * @param longitude2 longitude of point
     * @param latitude2 latitude of point
     * @return Distance between two points in km
     */
    public double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        int R = 6371;   // Radius of Earth
        double dLongitude = Math.toRadians(longitude2 - longitude1);
        double dLatitude = Math.toRadians(latitude2 - latitude1);

        double temp1 =  Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(dLongitude / 2) * Math.sin(dLongitude / 2) + Math.sin(dLatitude / 2) * Math.sin(dLatitude / 2);
        double temp2 = 2 * Math.atan2(Math.sqrt(temp1), Math.sqrt(1 - temp1));

        return (R * temp2);
    }
}
