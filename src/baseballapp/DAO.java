/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseballapp;

import java.util.List;

public interface DAO<T> {
    T get(String code);
    List<T> getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
}