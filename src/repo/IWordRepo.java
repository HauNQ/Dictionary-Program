/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import model.Word;

/**
 *
 * @author Nguyen Quang Hau
 */
public interface IWordRepo {
    void add();
    void delete();
    void update(Word word);
    void search();
    void translate();
}
