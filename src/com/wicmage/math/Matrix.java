package com.wicmage.math;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private Integer rowNumber, columnNumber;
    private List<Integer> values;

    public static Matrix create() {
        return new Matrix();
    }

    public static Matrix create(Integer rowNumber, Integer columnNumber) {
        return new Matrix(rowNumber, columnNumber);
    }

    public static Matrix create(Integer rowNumber, Integer columnNumber, List<Integer> values) {
        return new Matrix(rowNumber, columnNumber, values);
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix c = create(a.getNumberOfRows(), b.getNumberOfColumns());
        if(!a.getNumberOfColumns().equals(b.getNumberOfRows()))
            return null;

        for (int i = 0; i < a.getNumberOfRows(); i++)
            for (int j = 0; j < b.getNumberOfColumns(); j++) {
                int aux = 0;
                for (int k = 0; k < a.getNumberOfColumns(); k++)
                    aux+=a.getValueFromMatrix(i,k)*b.getValueFromMatrix(k,j);
                c.add(aux);
            }
        return c;
    }

    public Matrix() {
        this(1,1, new ArrayList<Integer>());
    }

    public Matrix(Integer rowNumber, Integer columnNumber) {
        this(rowNumber, columnNumber, new ArrayList<Integer>());
    }

    public Matrix(Integer rowNumber, Integer columnNumber, List<Integer> values) {
        this.rowNumber=rowNumber;
        this.columnNumber=columnNumber;
        this.values=new ArrayList<Integer>(values);
    }

    public List<Integer> getValues() {
        return this.values;
    }

    public Integer getNumberOfRows() {
        return this.rowNumber;
    }

    public Integer getNumberOfColumns() {
        return this.columnNumber;
    }

    public Integer getValueFromMatrix(Integer row, Integer column) {
        if(row >= rowNumber || column >= columnNumber)
            return null;
        else
            return values.get(row*columnNumber + column);
    }

    public void add(Integer value) {
        values.add(value);
    }

    public void addValues(List<Integer> mValues) {
        this.values = new ArrayList<Integer>(mValues);
        fillWithZeros();
    }

    public List<Position> getPositions(Integer a) {
        List<Position> res = new ArrayList<Position>();
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                if(getValueFromMatrix(i,j).equals(a)) {
                    res.add(Position.create(i,j));
                }
            }
        }
        return res;
    }

    public void fillWithZeros() {
        int ultimoValor = values.size();
        int totalMatriz = rowNumber*columnNumber;
        if(ultimoValor<totalMatriz)
            for(int i=ultimoValor;i<totalMatriz;i++)
                values.add(0);
    }

    @Override
    public String toString() {
        String res = "Matrix de " + rowNumber + "x" + columnNumber + ":\n";
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++)
                res+=getValueFromMatrix(i, j)+"\t";
            res+="\n";
        }
        return res;
    }
}
