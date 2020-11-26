package e2;

import java.util.Iterator;

public class RowColumnIterator implements Iterable<Matrix>{
    public Matrix[][] matriz;

    public RowColumnIterator(Matrix[][] m1){ matriz = m1; }

    public Iterator<Matrix> iterator(){
        Iterator it = new RowColumnIteratorM();
        return it;
    }

}

class RowColumnIteratorM implements Iterator<Matrix>{
    int[][] posM;
    public RowColumnIterator(){ posM = {{0},{0}}; }
    public boolean hasNext(){
        boolean result;
        if(posM < matriz.length){ return true; }
        else return false;
    }
    public Matrix next(){
        posM++;
        return matriz[posM-1];
    }
    public void remove(){
        throw new UnsupportedOperationException("Operación no válida.");
    }
}



