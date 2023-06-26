import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class FindLocation{

    private static final String URL_PREFIX = "https://ipinfo.io";
    private static final String URL_SUFFIX = "/json";

    private final String ipData;

    public FindLocation(){
        this.ipData = getData();
    }
    private String getData() {

        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String URL;
        System.out.println("Enter an IP Adress, or leave empty to use current IP");
        String ip = scan.nextLine();

        if (ip.equals("")) URL = URL_PREFIX+URL_SUFFIX;
        else URL = URL_PREFIX+"/"+ip+URL_SUFFIX;


        try {
            URL url = new URL(URL);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while((br.readLine()) != null){
                sb.append(br.readLine());

            }
            br.close();
            return sb.toString();

        }catch (MalformedURLException MURLE){
            System.out.println(MURLE);
            return "URLException Found";
        }
         catch (IOException IOE){
             System.out.println(IOE);
            return "IO Exception found";
        }


    }

    public String toString(){
        return ipData;
    }


    //testmain
    public static void main(String[] args) {
        FindLocation location = new FindLocation();
        System.out.println(location);
    }


}