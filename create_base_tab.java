import class1.tab_file;
     
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;



public class create_base_tab {
    public static void main(String[] args) {
        URL url;
     
        try {
            String a="http://antropinum.ru/tabs";
        //    String a=args[1];
            url = new URL(a);
            URLConnection conn = url.openConnection();
      
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            
            BufferedReader br = new BufferedReader(isr);
            FileWriter out_file_1 = new FileWriter("library.txt");
            
          
            String inputLine;
            boolean flag_tab=true;
            boolean flag_tab_name=false;
            
            while ((inputLine = br.readLine()) != null) {
                int st=0;
                int fn=0;
                CharSequence str_t="";
                String s_temp;
                String name_composition;
                String url_adr;
           
                if (inputLine.contains("class=\"withurl\">") && flag_tab)
                { 
                    //split
                    st = inputLine.lastIndexOf("active\"><a href=\"")+17;
                    fn = inputLine.indexOf("\" class=");
                    url_adr = "http://antropinum.ru"+ (String) inputLine.subSequence(st, fn);
                    out_file_1.write("Ссылка: " + url_adr+"\n");
                    System.out.println(url_adr);
                    st = inputLine.indexOf("class=\"withurl\">")+16;
                    fn = inputLine.indexOf("</a>");
                    name_composition = (String)inputLine.subSequence(st, fn);
                    System.out.println(name_composition.replaceAll(" ", ""));
                    out_file_1.write("Название: " + name_composition+"\n");
                    tab_file file_1;
                    file_1 = new tab_file();
                    file_1.setURL(url_adr);
                    file_1.setName(name_composition);
                    file_1.create_file (url_adr, name_composition);
                }
                if (inputLine.contains("Поиск табулатуры"))
                {
                    flag_tab= false;
                }
                if (inputLine.contains("<a title=\"На следующую страницу\" href=\""))
                {
                    st = inputLine.lastIndexOf("<a title=\"На следующую страницу\" href=\"")+39;
                    fn = inputLine.indexOf("\">›</a>");
                    System.out.println(st);
                    System.out.println(fn);
                    url_adr = "http://antropinum.ru"+ (String) inputLine.subSequence(st, fn);
                    System.out.println(url_adr);
                    
                    out_file_1.write("след страница: " + url_adr+"\n");
                }
              
            }
            br.close();
            out_file_1.close();
            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
	