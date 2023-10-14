/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import model.Word;

/**
 *
 * @author Nguyen Quang Hau
 */
public class WordDao {

    private static WordDao instance;
    private final String fileName = "dictionary.txt";

    public static WordDao getInstance() {
        if (instance == null) {
            synchronized (WordDao.class) {
                if (instance == null) {
                    instance = new WordDao();
                }
            }
        }

        return instance;
    }

    public void add(Word word) throws Exception {
        writeToFile(word, true);
    }
    
    
    public boolean deteleByKey(String english) throws Exception{
        HashMap<String, String> wordHM = selectAll();
        if (wordHM.containsKey(english)) {
            wordHM.remove(english);
            // delete old file
            new File(fileName).delete();

            // insert all data from old file into empty file
            for (String key : wordHM.keySet()) {
                writeToFile(new Word(key, wordHM.get(key)), true);
            }
            
            return true;
        } else {
            System.out.println("The " + english + " does not exist in the dictionary!");
            return false;
        }
    }

    public Word selectByKey(String english) {
        HashMap<String, String> wordHM = selectAll();
        if (wordHM.containsKey(english)) {
            return new Word(english, wordHM.get(english));
        }

        return null;
    }

    public void update(Word word) throws Exception {
        HashMap<String, String> wordHM = selectAll();
        
        if(wordHM.containsKey(word.getEnglish())){
            wordHM.put(word.getEnglish(), word.getVietnamese());

            // delete old file
            new File(fileName).delete();

            // insert all data from old file into empty file
            for (String key : wordHM.keySet()) {
                writeToFile(new Word(key, wordHM.get(key)), true);
            }
        }
    }

    public HashMap<String, String> selectAll() {
        return readFromFile();
    }

    private HashMap<String, String> readFromFile() {
        File file = new File(fileName);
        HashMap<String, String> wordHM = new HashMap<>();
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String data;
                while ((data = br.readLine()) != null) {
                    String[] token = data.split(":");
                    wordHM.put(token[0], token[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return wordHM;
    }

    private void writeToFile(Word word, boolean mode) throws Exception {
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, mode);
        PrintWriter pw = new PrintWriter(fw);
        pw.print(word.getEnglish() + ":" + word.getVietnamese() + "\n");

        pw.flush();
        pw.close();
    }
}
