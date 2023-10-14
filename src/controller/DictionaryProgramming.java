/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import repo.WordRepo;
import view.Menu;

/**
 *
 * @author Nguyen Quang Hau
 */
public class DictionaryProgramming extends Menu<String> {

    private WordRepo wordRepo;

    public DictionaryProgramming() {
        super("=======Dictionary Program=======", new String[]{"Add Word", "Delete Word", "Translate", "Exit"});
        wordRepo = new WordRepo();
    }

    @Override
    public void excute(int n) {
        switch (n) {
            case 1:
                wordRepo.add();
                break;
            case 2:
                wordRepo.delete();
                break;
            case 3:
                wordRepo.translate();
                break;
        }
    }

}
