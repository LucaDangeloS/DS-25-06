package e2;

import java.util.Iterator;

public class MatrixAddition {

    public static Matrix add(Matrix M1, Matrix M2) {
        Matrix result;
        int column = 1, row=1, i, j;

        if (M1.getColumn() != M2.getColumn()) throw new ArithmeticException();
        if (M1.getRowNum() != M2.getRowNum()) throw new ArithmeticException();

        result = new Matrix(M1.getRowNum(),M2.getColumn());

        for (Iterator<Integer> m1 = M1.iterator(), m2 = M2.iterator(); m1.hasNext(); ) {
            i = m1.next();
            j =  m2.next();
            if (row > result.getRowNum()) {
                row = 1;
                column++;
            }
            result.setCell(row,column,result.getCell(row,column)+i+j);
            row++;
        }

        return result;
    }
}
