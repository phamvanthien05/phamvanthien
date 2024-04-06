import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class documents {
    ArrayList<textModel> document = new ArrayList<>();

    public synchronized int demTongSoTu(String data){
        String[] cactu;
        if (data == null || data.isEmpty()){
            return 0;
        }else {
            cactu = data.split("\\s+");
        }

        return (int) Arrays.stream(cactu).flatMap(
                word -> Arrays.stream(word.split("\\s+")))
                .count();
    }


    public void saveToFile(String data){
        try {
        	File f = new File("/data.txt");
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(data);
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(){
        String data = "";
        try {
            FileReader fileReader = new FileReader("src/Java/data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                data += (line + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}