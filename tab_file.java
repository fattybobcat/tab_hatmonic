package class1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;



public class tab_file {
   //   public static void main(String[] args) {
        
        String url_http;
        String name;
        String author;
        String genre;
        String complexity;
        String type_harmonica;
        String tone_harmonica;
        FileWriter file_name;
        
        public void setName( String Name ){
            name = Name;
        }
        
        public void setGenre( String Genre ){
            genre = Genre;
        }
        
        public void setAuthor( String Author ){
            author = Author;
        }
        
        public void setComplexity ( String Complexity ){
            complexity = Complexity;
        }
        
        public void setType_harmonica( String Type_harmonica ){
            type_harmonica = Type_harmonica;
        }
        
        public void setTone_harmonica ( String Tone_harmonica ){
            tone_harmonica = Tone_harmonica;
        }
        
        public void setURL ( String URL ){
            url_http = URL;
        }
     
        public String getName(){
            return name;
        }
        
        String getGenre(){
            return genre;
        }
             
        String getAuthor(){
            return author;
        }
        
        String getComplexity (){
            return complexity;
        }
        
        String getType_harmonica(){
            return type_harmonica;
        }
        
        String getTone_harmonica (){
            return tone_harmonica;
        }
        
        String getURL (){
            return url_http;
        }
        
        public void create_file (String Url, String Name) {
            URL url;
            String name_file;
            name_file = Name.replaceAll(" ", "") + ".txt";
            System.out.println("Create file " +  name_file);

        try {
            url = new URL(Url);
            URLConnection conn = url.openConnection();
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            FileWriter out_file_1 = new FileWriter(name_file);
        
            
            String inputLine;
            boolean flag_tab=false;
            boolean flag_tab_name=false;
            
            while ((inputLine = br.readLine()) != null) {
                int st=0;
                int fn=0;
                CharSequence str_t="";
                String s_temp;
                if ( flag_tab_name )
                {
                    inputLine=inputLine.trim();
                    if (inputLine.contains("</h1>"))
                    {
                        flag_tab_name=false;
                        fn = inputLine.indexOf("</h1>");
                        str_t=inputLine.subSequence(st, fn);
                        s_temp=(String) str_t;
                        System.out.println(str_t.subSequence(st, fn));
                        out_file_1.write("Название: " + s_temp.trim()+"\n");
                    }
                    
                }
                if (inputLine.contains("page-title\">"))
                {
                    flag_tab_name=true;
                }
                if (inputLine.contains(">Автор<"))
                {    
                    st = inputLine.lastIndexOf("\">")+2;
                    fn = inputLine.indexOf("</a>");
                    System.out.println(inputLine.subSequence(st, fn));
                    out_file_1.write("Автор: " + inputLine.subSequence(st, fn)+"\n");
                }
                if (inputLine.contains(">Жанр<"))
                {    
                    st = inputLine.lastIndexOf("\">")+2;
                    fn = inputLine.indexOf("</a>");
                    System.out.println(inputLine.subSequence(st, fn));
                    out_file_1.write("Жанр: " + inputLine.subSequence(st, fn)+"\n");
                }
                  if (inputLine.contains(">Сложность исполнения<"))
                {    
                    st = inputLine.lastIndexOf("\">")+2;
                    fn = inputLine.indexOf("</a>");
                    System.out.println(inputLine.subSequence(st, fn));
                    out_file_1.write("Сложность исполнения: " + inputLine.subSequence(st, fn)+"\n");
                }
                  if (inputLine.contains(">Тип губной гармошки<"))
                {    
                    st = inputLine.lastIndexOf("\">")+2;
                    fn = inputLine.indexOf("</a>");
                    System.out.println(inputLine.subSequence(st, fn));
                    out_file_1.write("Тип губной гармошки: " + inputLine.subSequence(st, fn)+"\n");
                }
                if (inputLine.contains(">Тональность<")) 
                {    
                    st = inputLine.lastIndexOf("\">")+2;
                    fn = inputLine.indexOf("</a>");
                    System.out.println(inputLine.subSequence(st, fn));
                    out_file_1.write("Тональность: " + inputLine.subSequence(st, fn)+"\n");
                }
                 if (inputLine.contains("</pre>"))
                    {
                        flag_tab= false;
                    }
                if ( flag_tab )
                {
                   
                    out_file_1.write(inputLine + "\n");
                    System.out.println(inputLine );
                    
                }
                if ( inputLine.contains("tab-content\'>"))
                {
                    flag_tab=true;
                     st = inputLine.lastIndexOf("\'>")+2;
                     fn = inputLine.length();
                      System.out.println(inputLine.subSequence(st, fn));
                        out_file_1.write(inputLine.subSequence(st, fn)+"\n");
                }
              
            }
            br.close();
            out_file_1.close();
            System.out.println("Done");

        } 
        
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}