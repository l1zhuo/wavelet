import java.io.IOException;
import java.net.URI;
import java.util.*;

class myHandler implements URLHandler {
   
    ArrayList<String> webArray = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            String wholeList = "David's search list: " ;
            for(String s: webArray){
                wholeList = wholeList + s +" ";
            }
            return wholeList; 
        } else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                webArray.add(parameters[1]);
                return String.format("New String added: %s", parameters[1]);
            }
            return "need a string to add";
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                String searchQuery = parameters[1];
                if (parameters[0].equals("s")) {
                    String allFoundString = "";
                    for (String s: webArray){
                        if(s.contains(searchQuery)){
                            allFoundString = allFoundString + s + "; "; 
                        }
                    }
                    return allFoundString;
                }
            }
            return "404 Not Found!";
        }
    }
}
public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new myHandler());
    }
}