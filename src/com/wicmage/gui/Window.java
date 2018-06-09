package com.wicmage.gui;

import com.wicmage.math.Matrix;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Window extends JFrame implements ActionListener {

    //Declaración de matrices
    private JTextField[][] matrixA, matrixB;
    private JLabel[][] matrixC;

    //Declaración de botones útiles
    private JTextField aCustomRow, bCustomColumn, nCustomValue;
    private JButton result, actualizeMatrix;
    private JLabel explanation, m, p, n;

    //Declaración de la memoria para valores
    private Integer mValue, pValue, nValue;

    public Window() {
        super();
        windowConfig();
        components();
    }

    private void windowConfig() {
        this.setTitle("Multiplica matrices");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void components() {
        //Initialize the components
        // Anm X Bmp = Cnp

        aCustomRow = new JTextField();
        bCustomColumn = new JTextField();
        nCustomValue = new JTextField();

        actualizeMatrix = new JButton();
        result = new JButton();

        explanation = new JLabel();
        m = new JLabel();
        p = new JLabel();
        n = new JLabel();

        //Set default values
        actualizeMatrix.setText("Actualizar");
        actualizeMatrix.setBounds(150, 50, 120, 30);
        actualizeMatrix.addActionListener(this);
        result.setText("Resultado");
        result.setBounds(280, 50, 120, 30);
        result.addActionListener(this);

        explanation.setText("*La multiplicación la expresamos de la forma Amn X Bnp = Cmp");
        m.setText("m");
        p.setText("p");
        n.setText("n");

        explanation.setBounds(20, 80, 800, 30);
        m.setBounds(20,20,30,30);
        p.setBounds(60,20,30,30);
        n.setBounds(100,20,30,30);

        aCustomRow.setText("3");
        aCustomRow.setBounds(20, 50, 30, 30);
        bCustomColumn.setText("3");
        bCustomColumn.setBounds(60, 50, 30, 30);
        nCustomValue.setText("3");
        nCustomValue.setBounds(100, 50, 30, 30);

        //Add components
        this.add(explanation);
        this.add(m);
        this.add(p);
        this.add(n);
        this.add(aCustomRow);
        this.add(bCustomColumn);
        this.add(nCustomValue);
        this.add(actualizeMatrix);
        this.add(result);

        actualizar(3,3,3);
    }

    private void actualizar(int aRows, int bColumns, int commonAColumnBRow) {

        if(matrixA!=null || matrixB!=null || matrixC!=null) {
            Arrays.stream(matrixA).forEach(i -> Arrays.stream(i).forEach(j -> remove(j)));
            Arrays.stream(matrixB).forEach(i -> Arrays.stream(i).forEach(j -> remove(j)));
            Arrays.stream(matrixC).forEach(i -> Arrays.stream(i).forEach(j -> remove(j)));
        }

        matrixA = new JTextField[aRows][commonAColumnBRow];
        matrixB = new JTextField[commonAColumnBRow][bColumns];
        matrixC = new JLabel[aRows][bColumns];

        //Add matrix A
        for(int i=0;i<aRows;i++) {
            int yPos = i*50+110;
            for(int j=0;j<commonAColumnBRow;j++) {
                matrixA[i][j] = new JTextField();
                matrixA[i][j].setBounds(j*50+20,yPos,30,30);
                this.add(matrixA[i][j]);
            }
        }

        //Add matrix B
        for(int i=0;i<commonAColumnBRow;i++) {
            int yPos = i*50+110;
            for(int j=0;j<bColumns;j++) {
                matrixB[i][j] = new JTextField();
                matrixB[i][j].setBounds(j*50+commonAColumnBRow*50+20+20,yPos,30,30);
                this.add(matrixB[i][j]);
            }
        }

        //Add matrix C
        for(int i=0;i<aRows;i++) {
            int yPos = i*50+110;
            for(int j=0;j<bColumns;j++) {
                matrixC[i][j] = new JLabel();
                matrixC[i][j].setBounds(j*50+commonAColumnBRow*50+20+20+bColumns*50+20+20,yPos,30,30);
                this.add(matrixC[i][j]);
            }
        }

        mValue=aRows;
        pValue=bColumns;
        nValue=commonAColumnBRow;

        setSize(0,0);
        setSize(1000,1000);
    }

    public void igual() {
        Matrix a = Matrix.create(mValue,nValue);
        Matrix b = Matrix.create(nValue,pValue);

        List<Integer> matrixA = new ArrayList<Integer>();
        for (int i = 0; i < mValue; i++)
            for (int j = 0; j < nValue; j++)
                matrixA.add(Integer.parseInt(this.matrixA[i][j].getText()));
        a.addValues(matrixA);

        List<Integer> matrixB = new ArrayList<Integer>();
        for (int i = 0; i < nValue; i++)
            for (int j = 0; j < pValue; j++)
                matrixB.add(Integer.parseInt(this.matrixB[i][j].getText()));
        b.addValues(matrixB);

        Matrix c = Matrix.multiply(a,b);
        for (int i = 0; i < mValue; i++)
            for (int j = 0; j < pValue; j++)
                matrixC[i][j].setText(c.getValueFromMatrix(i,j).toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        switch(text) {
            case "Actualizar":
                actualizar(Integer.parseInt(aCustomRow.getText()) , Integer.parseInt(bCustomColumn.getText()), Integer.parseInt(nCustomValue.getText()));
                break;

            case "Resultado":
                igual();
                break;
        }
    }
}
