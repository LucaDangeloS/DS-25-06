package e2;

import java.util.*;

public class Matrix implements Iterable<Integer> {
    private final ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
    private boolean rowIterating = false; //if false, iteration goes rowColumn
    private int rowNum, columnNum;
    public enum Iteration_mode {
        RowColumn,
        ColumnRow;
    }
    public enum copy_mode {
        Row,
        Column
    }
    //Constructors
    Matrix(int rowNum, int columnNum) {//Constructor por filas y columnas
        if (rowNum > 0 && columnNum > 0) {
            this.rowNum = rowNum;
            this.columnNum = columnNum;

            for (int i = 0; i < rowNum; i++) {
                this.matrix.add(i, new ArrayList<>());
                for (int j = 0; j < columnNum; j++) {
                    this.matrix.get(i).add(0);
                }
            }
        } else throw new IllegalArgumentException();
    }
    Matrix(int[][] matrix){//Constructor array bidimensional
        int temp=0;

        if (matrix.length > 0) temp = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != temp) throw new IllegalArgumentException();
        }

        for (int i = 0; i< matrix.length; i++) {
            this.matrix.add(i, new ArrayList<>());
            this.rowNum++;
            for (int j : matrix[i]) {
                this.matrix.get(i).add(j);
                this.columnNum++;
            }
        }
        this.columnNum /= rowNum;
    }
    //-----------
    //Util Methods

    public int[][] copyMatrixBi(){
        Iterator<Integer> it = this.rowColumnIterator();
        int[][] aux = new int[rowNum][columnNum];
        int column=0, row=0;

        while(it.hasNext()) {
            aux[row][column] = it.next();
            column++;
            if (column == columnNum && row < rowNum-1) {
                row++;
                column=0;
            }

        }

        return aux;
    }

    public int[] copyMatrixUni(copy_mode mode, int index){
        Iterator<Integer> it;
        ArrayList output = new ArrayList<Integer>();
        int aux=0, boundaries=0;

        if (index <= 0) throw new IllegalArgumentException();

        if (mode == copy_mode.Row) {
            if (index <= rowNum) {
                it = this.rowColumnIterator();
                boundaries = columnNum;
            }
            else throw new IllegalArgumentException();
        } else if (index <= columnNum) {
            it = this.columnRowIterator();
            boundaries = rowNum;
        }
        else throw new IllegalArgumentException();

        while(aux < index*boundaries) {
            if (aux < (index-1)*boundaries)
                it.next();
            else output.add(it.next());
            aux++;
        }

        return output.stream().mapToInt(x -> (int) x).toArray();
    }
    //----------
    //Setters
    public void setCell(int fila, int columna, int valor) {
        if ((fila > rowNum || columna > columnNum) || (fila <= 0 || columna <= 0)) throw new IllegalArgumentException();
        else this.matrix.get(fila-1).set(columna-1,valor);
    } //Escritura de celda concreta

    public void setIterationMode(Iteration_mode iter) {
        this.rowIterating = (iter == Iteration_mode.RowColumn);
    }

    //--------
    //Getters
    public String matrixStr(){
        StringBuilder aux = new StringBuilder();
        Iterator<Integer> it = this.rowColumnIterator();
        int aux_counter = columnNum;

        while(it.hasNext()) {
            if(aux_counter == columnNum) aux.append('[');
            aux_counter--;
            aux.append(it.next());
            if (it.hasNext() && aux_counter > 0) aux.append(", ");
            else if(aux_counter == 0) {
                aux_counter = columnNum;
                aux.append("]\n");
            }
        }
        return aux.toString();
    }

    public int getCell(int row, int column){
        if ((row > rowNum || column > columnNum) || (row <= 0 || column <= 0)) throw new IllegalArgumentException();
        else return this.matrix.get(row-1).get(column-1);
    } //Lectura de celda concreta

    public int getRowNum(){ return this.rowNum; }//Lectura de filas
    public int getColumn(){ return this.columnNum; }//Lectura de columnas
    //--------

    private RowColumnIterator rowColumnIterator() {
        return new RowColumnIterator();
    }

    private ColumnRowIterator columnRowIterator() {
        return new ColumnRowIterator();
    }

    //Subclases de iteradores
    private class RowColumnIterator implements Iterator<Integer>{
        private final Iterator<Integer> inner_iterator;

        RowColumnIterator() {
            Collection<Integer> inner_collection = new ArrayList<>();

            for (ArrayList<Integer> i : matrix) {
                inner_collection.addAll(i);
            }
            this.inner_iterator = inner_collection.iterator();
        }

        @Override
        public boolean hasNext() {
            if (inner_iterator.hasNext()) return true;
            else return false;
        }

        @Override
        public Integer next() {
            return inner_iterator.next();
        }

        @Override
        public void remove() { throw new UnsupportedOperationException("Operación no válida."); }
    }

    private class ColumnRowIterator implements Iterator<Integer> {
        private final Iterator<Integer> inner_iterator;

        ColumnRowIterator() {
            Collection<Integer> inner_collection = new ArrayList<>();

            for (int index = 0; index < columnNum; index++) {
                for (ArrayList<Integer> i : matrix) {
                    inner_collection.add(i.get(index));
                }
            }
            this.inner_iterator = inner_collection.iterator();
        }

        @Override
        public boolean hasNext() {
            if (inner_iterator.hasNext()) return true;
            else return false;
        }

        @Override
        public Integer next() {
            return inner_iterator.next();
        }

        @Override
        public void remove() { throw new UnsupportedOperationException("Operación no válida."); }
    }
    //---------------
    @Override
    public Iterator<Integer> iterator(){
         if (rowIterating) return this.rowColumnIterator();
         else return this.columnRowIterator();
    }
}

