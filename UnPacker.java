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

public class UnPacker
{
    FileOutputStream outstream = null;
    
    public static void main(String arr[]) throws Exception
    {
        UnPacker obj = new UnPacker("combine.txt");
    }
    public UnPacker(String src) throws Exception
    {
        unpack(src);
    }


public void unpack(String filePath)
    {
        try 
        {
            FileInputStream instream = new FileInputStream(filePath);
            byte header[]= new byte[100]; //header/byte cha array 
            int length = 0;
            while((length = instream.read(header,0,100)) > 0)      //joparyanta file madhle 100 byte read hot nahi to paryanta gol gol firnar ahe. ...
            {
                String str = new String(header);                   //to 100 byte cha data apan string made convert karun ghetla

                String ext = str.substring(str.lastIndexOf('\\')); //tya string madli last index "\\" ahe na tyachya pudhchi strig anun denare jya made actual file cha nav asnar ahe . ...
                ext = ext.substring(1);                            // file cha nav milala
                
                String[] words=ext.split("\\s");                   //space made string split keli file name and file size vegli vegli zali ....
                String filename = words[0];                        // words of 0words[0] madya abc.txt alay ani words of 1 words[1] madya 10 alay size of that file .
                int size = Integer.parseInt(words[1]);             // words[1] madli file chi size integer made ghetli.

                byte arr[] = new byte[size];
                instream.read(arr,0,size);
                System.out.println(filename);
                FileOutputStream fout=new FileOutputStream(filename); //file create zali
                fout.write(arr,0,size);
            }
        }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
