package e2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Matrix<I > {
    int rowNum,columnNum;
    Arrays matrix = new ArrayList<>();

    //Constructors
    public Matrix(int rowNum, int columnNum){//Constructor por filas y columnas
        this.rowNum = rowNum;
        this.columnNum = columnNum;
    }
    public Matrix(int[][] matrix){//Constructor array bidimensional
        int temp=0;
        if (matrix.length > 0) temp = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != temp) throw new NullPointerException();
        }
        this.matrix = matrix;

    }

    //Util Methods
    public int getRowNum(){ return rowNum; }//Lectura de filas

    public int getColumn(){ return columnNum; }//Lectura de columnas

    public int getCell(int i,int j){ return matrix[i][j]; }//Lectura de celda concreta

    public void setCell(int i, int j){ this.matrix[i][j] = getCell(i,j); }//Escritura de celda concreta

    public static int[][] copyMatrixBi(Matrix m){

    }

    public static int[] copyMatrixUni(Matrix m){

    }

    public static String matrixStr(){ }

    public Iterator<Matrix> iterator(){
        Iterator it = new RowColumnIterator();
        return it;
    }


    





}
