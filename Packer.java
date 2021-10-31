import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Packer
{
    FileOutputStream outstream = null;//characteristics of the class 
    String ValidExt[] = {".txt",".c",".java",".cpp",".php"};//characteristics of the class 
    
 
    public static void main(String are[]) throws Exception 
    {
        Packer obj = new Packer("C:\\Users\\atharva\\Desktop\\git_Repo\\File_Packer-Unpacker_\\Demo","combine.txt");
    }
    public Packer(String src, String Dest) throws Exception
    {
        File outfile =new File(Dest);//combine.txt create zali
        File infile = null;      //reference for next code
        outstream = new FileOutputStream(Dest);//jar file create kelyavarti kahi lihycha asel tr file outputstream cha object create kaycha.
        File folder = new   File(src);
        String arr[] = folder.list();
        System.setProperty("user.dir",src);//to consider current directory of the project for any operations   

        listAllFiles(src);//Travels  directory "DemoX"
    }

    public void listAllFiles(String path)//folder cha nav ahe je aplyala file name travel karycha ahe
    {
        try(Stream<Path> paths = Files.walk(Paths.get(path)))
        {
            paths.forEach(filePath ->
                          {
                              if (Files.isRegularFile(filePath))
                              { 
                                  try
                                  {
                                      String name = filePath.getFileName().toString(); //file name milala.. that is ex."abc.txt" 
                                      String ext = name.substring(name.lastIndexOf(".")); // .txt,.c, .java files :for Line No 19
                                      
                                      List<String> list = Arrays.asList(ValidExt);//Control gos to line no 19

                                      if(list.contains(ext))
                                      {
                                          File file=new File(filePath.getFileName().toString());
                                          
                                          Pack(file.getAbsolutePath());
                                      }
                                  }
                                  catch (Exception e)
                                  { 
                                              System.out.println(e);
                                  }
                              }
                          });
        }
        catch (IOException e)
        {
            System.out.println(e);
            e.printStackTrace(); 
        }
    }


public void Pack(String filePath)//read that data
    {
        FileInputStream instream = null;
        
        try
        {
            byte[] buffer = new byte[1024];
            int length;
            byte temp[] = new byte[100];//Header
            
            File fobj = new File(filePath);
            String Header = filePath+" "+fobj.length();
            for (int i = Header.length(); i < 100; i++)
            Header += " ";
            
            temp = Header.getBytes();
            
            instream = new FileInputStream(filePath);
            outstream.write(temp, 0, temp.length);
            
            while((length = instream.read(buffer)) > 0)
            {
                outstream.write(buffer, 0, length);
            }
            instream.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}