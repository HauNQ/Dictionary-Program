/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import common.Validation;
import dao.WordDao;
import java.util.HashMap;
import model.Word;

/**
 *
 * @author Nguyen Quang Hau
 */
public class WordRepo implements IWordRepo {

    private Validation val = new Validation();

    @Override
    public void add() {
        Word word = new Word(val.getString("Enter English: ").toLowerCase(), val.getString("Enter Vietnamese: "));

        try {
            if (WordDao.getInstance().selectByKey(word.getEnglish()) != null) {
                System.out.println("The " + word.getEnglish() + " has been existing on the dictionary.");
                if (val.getString("Do you want to update(y/n): ", "^(y|n|Y|N)$").equalsIgnoreCase("y")) {
                    update(word);
                }
            } else {
                WordDao.getInstance().add(word);
                System.out.println("Successfull Adding");
            }
        } catch (Exception e) {
            System.err.println("Not Successfull Adding");
        }
    }

    @Override
    public void delete() {
        String english = val.getString("Enter english: ").toLowerCase();
        try {
            boolean result = WordDao.getInstance().deteleByKey(english);
            if(result) System.out.println("Deleted");
        } catch (Exception e) {
            System.err.println("Deleting error");
        }
    }

    @Override
    public void update(Word word) {
        try {
            WordDao.getInstance().update(word);
            System.out.println("Updated");
        } catch (Exception e) {
            System.err.println("Updating error ");
        }
    }

    @Override
    public void translate() {
        HashMap<String, String> dictionary = WordDao.getInstance().selectAll();
        String english = val.getString("Enter english: ");
        System.out.print("Vietnamese: ");
        if(dictionary.containsKey(english.toLowerCase())){
            System.out.println(dictionary.get(english.toLowerCase()));
        }
    }

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
