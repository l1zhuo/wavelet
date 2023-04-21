import java.io.IOException;
import java.net.URI;
import java.util.*;

class StringHandler implements URLHandler{
    ArrayList<String> stringList = new ArrayList<>();
    
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            String wholeList = "";
            for(String s: stringList){
                wholeList +="\n "+s;
            }
            return wholeList; 
        } else if (url.getPath().equals("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            String wholeList = "";
            if (parameters[0].equals("s")) {
                stringList.add(parameters[1]);
                for(String s: stringList){
                    wholeList +="\n "+s;
                }
                return wholeList;
            }
            return "need a string to add";
        } else {
            return "404 Not Found!";
        }
    }
}
public class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new StringHandler());
    }
}
