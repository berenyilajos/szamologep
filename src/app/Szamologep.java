/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import szamologep.Controller.Calculator;
import szamologep.enums.Operation;
import szamologep.model.TemporaryResult;
import szamologep.view.MainFrame;

/**
 *
 * @author lajos
 */
public class Szamologep {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TemporaryResult temporaryResult = new TemporaryResult(Operation.NONE, 0);
        Calculator calculator = new Calculator(temporaryResult);
        MainFrame sz = new MainFrame(calculator);
    }
}
