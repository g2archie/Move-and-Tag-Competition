/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmpsd;

/**
 *
 * @author g2arc
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Created by fuxiaofeng on 2017/2/20.
 */
public class FileReader {

    ArrayList read(String path){
        try{
            File filename = new File(path);

            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);

            ArrayList list = new ArrayList();
            String line = "";

            while(line != null){
                line = br.readLine();
                list.add(line);
            }

            return list;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
