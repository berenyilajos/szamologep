/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szamologep.view;

import szamologep.controller.Calculator;
import szamologep.enums.Operation;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author lajos
 */
public class MainFrame extends JFrame implements ActionListener, KeyListener {

    private Calculator calculator;

    private JPanel mainPanel, bottomPanel;
    
    private JPanel[] row;
    private JButton[] button;
    private String[] buttonString;
    private int[] dimW;
    private int[] dimH;
    private Dimension displayDimension;
    private Dimension regularDimension;
    private Dimension equalAndClearButDimension;
    private modifJTextArea display;
    private JLabel label;
    private Font font, font2, font3;
    private final String align = "    ";
    

    public MainFrame(Calculator calculator) throws HeadlessException {
        super("Számológép");
        this.calculator = calculator;
        setDesign();
        this.setSize(320, 360);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        GridLayout grid = new GridLayout(7, 1);
        mainPanel = new JPanel(grid);
        row = new JPanel[8];
        button = new JButton[22];
        buttonString = new String[] {"C", "CE", "◀",
                                    "7", "8", "9", "/",
                                    "4", "5", "6", "*",
                                    "1", "2", "3", "-",
                                    "0", ".", "+/-", "+",
                                    "√x", "x²", "=" };
        dimW = new int[] {240,70,144};
        dimH = new int[] {35, 40};
        displayDimension = new Dimension(dimW[0], dimH[0]);
        regularDimension = new Dimension(dimW[1], dimH[1]);
        equalAndClearButDimension = new Dimension(dimW[2], dimH[1]);
        display = new modifJTextArea(1, 16);
        label = new JLabel(align);
    
        font = new Font("Times new Roman", Font.BOLD, 18);
        font2 = new Font("Times new Roman", Font.ROMAN_BASELINE, 12);
        font3 = new Font("Times new Roman", Font.BOLD, 16);
        
        FlowLayout f = new FlowLayout(FlowLayout.LEFT,1,8);
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 4,3);
        for(int i = 0; i < 8; i++) {
            row[i] = new JPanel();
        }
        row[0].setLayout(f);
        row[1].setLayout(f1);
        for(int i = 2; i < 8; i++) {
            row[i].setLayout(f2);
        }
        
        for(int i = 0; i < 22; i++) {
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setFont(font3);
            button[i].addActionListener(this);
        }

        display.setFont(font);
        display.setEditable(true);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);
        display.setText("0");
        display.addKeyListener(this);
        
        button[0].setPreferredSize(equalAndClearButDimension);
        button[21].setPreferredSize(equalAndClearButDimension);
        for (int i = 1; i < 21; i++) {
            button[i].setPreferredSize(regularDimension);
        }
        
        label.setFont(font2);
        row[0].add(label, BorderLayout.SOUTH);
        this.add(row[0], BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        
        row[1].add(display);
        mainPanel.add(row[1]);
        
        for(int i = 0; i < 3; i++) {
            row[2].add(button[i]);
        }
        mainPanel.add(row[2]);
        
        for(int i = 3; i < 7; i++) {
            row[3].add(button[i]);
        }
        mainPanel.add(row[3]);
        
        for(int i = 7; i < 11; i++) {
            row[4].add(button[i]);
        }
        mainPanel.add(row[4]);
        
        for(int i = 11; i < 15; i++) {
            row[5].add(button[i]);
        }
        mainPanel.add(row[5]);
        
        for(int i = 15; i < 19; i++) {
            row[6].add(button[i]);
        }
        mainPanel.add(row[6]);
        
        for(int i = 19; i < 22; i++) {
            row[7].add(button[i]);
        }
        mainPanel.add(row[7]);
        
        bottomPanel = new JPanel(f);
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Double.parseDouble(display.getText());
        } catch (Exception ex) {
            display.setText("0");
        }
        JButton btn = (JButton) e.getSource();
        switch (btn.getText()){
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9": addNumber(btn.getText()); break;
            case ".": addDot(); break;
            case "+/-": getPosNeg(); break;
            case "+":
            case "-":
            case "*":
            case "/": getResult(Operation.valueByName(btn.getText())); break;
            case "√x": getSqrt(); break;
            case "x²": getPow2(); break;
            case "=": getResult(); break;
            case "◀":  back(); break;
            case "CE": clearEntry(); break;
            case "C": clear();
            default: break;
        }
        try {
            Double.parseDouble(display.getText());
        } catch (Exception ex) {
            display.setText("0");
            actionPerformed(e);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        e.consume();
        try {
            Double.parseDouble(display.getText());
        } catch (Exception ex) {
            display.setText("0");
        }
        switch (code) {
            case KeyEvent.VK_0:
            case KeyEvent.VK_NUMPAD0: addNumber("0"); break;
            case KeyEvent.VK_1:
            case KeyEvent.VK_NUMPAD1: addNumber("1"); break;
            case KeyEvent.VK_2:
            case KeyEvent.VK_NUMPAD2: addNumber("2"); break;
            case KeyEvent.VK_3:
            case KeyEvent.VK_NUMPAD3: addNumber("3"); break;
            case KeyEvent.VK_4:
            case KeyEvent.VK_NUMPAD4: addNumber("4"); break;
            case KeyEvent.VK_5:
            case KeyEvent.VK_NUMPAD5: addNumber("5"); break;
            case KeyEvent.VK_6:
            case KeyEvent.VK_NUMPAD6: addNumber("6"); break;
            case KeyEvent.VK_7:
            case KeyEvent.VK_NUMPAD7: addNumber("7"); break;
            case KeyEvent.VK_8:
            case KeyEvent.VK_NUMPAD8: addNumber("8"); break;
            case KeyEvent.VK_9:
            case KeyEvent.VK_NUMPAD9: addNumber("9"); break;
            case KeyEvent.VK_MULTIPLY: getResult(Operation.MULTIPLY); break;
            case KeyEvent.VK_PLUS: getResult(Operation.PLUS); break;
            case KeyEvent.VK_ADD: getResult(Operation.PLUS); break;
            case KeyEvent.VK_MINUS: getResult(Operation.MINUS); break;
            case KeyEvent.VK_SUBTRACT: getResult(Operation.MINUS); break;
            case KeyEvent.VK_COMMA: addDot(); break;
            case KeyEvent.VK_SEPARATOR: addDot(); break;
            case KeyEvent.VK_DIVIDE: getResult(Operation.DIVIDE); break;
            case KeyEvent.VK_ENTER: getResult(); break;
            case KeyEvent.VK_EQUALS: getResult(); break;
            case KeyEvent.VK_BACK_SPACE: back(); break;
            case KeyEvent.VK_DELETE: clearEntry();
            default: break;
        }
        try {
            Double.parseDouble(display.getText());
        } catch (Exception ex) {
            display.setText("0");
            keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        e.consume();
    }
    
    private void addNumber(String btnText){
        if (display.getText().length() > 20) return;
        if (display.getText().equals("0")) {
            display.setText(btnText);
        } else {
//            display.setText(display.getText() + btnText);
            display.append(btnText); // csak override után működik!
        }
    }
    
    private void addDot(){
        if (!display.getText().isEmpty() && !display.getText().contains(".")) {
//            display.setText(display.getText() + ".");
            display.append("."); // csak override után működik!
        }
    }
    
    private void back(){
        if (!display.getText().isEmpty()){
            try {
                String txt = display.getText().substring(0, display.getText().length() - 1);
                if (txt.equals("-") || txt.equals("") || txt.equals("-0")){
                    display.setText("0");
                    return;
                }
                if (!txt.isEmpty()) {
                    Double.parseDouble(txt);
                }
                display.setText(txt);
            } catch (Exception e) {
                //ha a txt nem parselhető double-ra, akkor nem csinálunk semmit.
            } 
        }
    }
    
    private void getPow2() {
        try {
            double number = Double.parseDouble(display.getText());
            display.setText(format(calculator.getPow2(number)));
            label.setText(align);
        } catch(NumberFormatException e) {

        }
    }
    
    private void getSqrt() {
        try {
            double number = Double.parseDouble(display.getText());
            display.setText(format(calculator.getSqrt(number)));
            label.setText(align);
        } catch(NumberFormatException e) {

        }
    }
    
    public String format(double d){
        if(d == (long) d) {
            return String.format("%d",(long)d);
        } else {
            return String.format("%s",d);
        }
    }
    
    private void getResult(Operation func){
        label.setText(align + format(calculator.getResult(display.getText(), func)) + "  " + func);
        display.setText("0");
    }
    
    private void getResult(){
            double number = Double.parseDouble(display.getText());
        label.setText(align);
        display.setText(format(calculator.getResult(number)));
    }
    
    private void clearEntry(){
        display.setText("0");
    }
    
    private void clear() {
            display.setText("0");
            calculator.clear();
            label.setText(align);
    }
    
    private void getPosNeg() {
        if (display.getText().isEmpty() || display.getText().equals("0")) return;
        if (display.getText().substring(0, 1).equals("-")) {
            display.setText(display.getText().substring(1));
        } else {
            display.setText("-" + display.getText());
        }
    }
    
    private void setDesign() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        }
    }
}

class modifJTextArea extends JTextArea {

    modifJTextArea(int rows, int coloumns) {
        super(rows, coloumns);
    }
    
    @Override
    public String getText(){
        String txt = super.getText();
        if (txt.isEmpty()) {
            return txt;
        }
        if (txt.substring(0, 1).equals(".") && txt.substring(txt.length() - 1).equals("-")){
            txt = "-" + txt.substring(1, txt.length() - 1) + ".";
            return txt;
        }
        if (txt.substring(txt.length() - 1).equals("-")) {
            txt = "-" + txt.substring(0, txt.length() - 1);
        }
        if (txt.substring(0, 1).equals(".")) {
            txt = txt.substring(1) + ".";
        }
        return txt;
    }
    
    @Override
    public void setText(String txt){
        if (txt.isEmpty()){
            super.setText(txt);
            return;
        }
        if (txt.substring(0, 1).equals("-") && txt.substring(txt.length() - 1).equals(".")){
            txt = "." + txt.substring(1, txt.length() - 1) + "-";
            super.setText(txt);
            return;
        }
        if (txt.substring(0, 1).equals("-")) {
            txt = txt.substring(1) + "-";
        }
        if (txt.substring(txt.length() - 1).equals(".")) {
            txt = "." + txt.substring(0, txt.length() - 1);
        }
        super.setText(txt);
    }
    
    @Override
    public void append(String txt){
        this.setText(this.getText() + txt);
    }
}
