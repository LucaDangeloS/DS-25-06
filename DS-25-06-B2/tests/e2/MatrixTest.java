package e2;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    int [][] v = {{10,10,10}
                ,{5,5,5}};

    int [][] v2 = {{20,5}
                 ,{10,5},
                  {20,5}};

    int[][] v3 = {{10,10},
                   {5,5},
                   {5,5}};

    int [][] bad_v = {{10,20},
                      {29,48,9}};

    @Order(1)
    @Test
    void creation(){
        int [] aux = {20,10,20};
        int [] aux2 = {5,5,5};

        Matrix M1 = new Matrix(v);

        assertEquals(Arrays.toString(
                M1.copyMatrixBi()[0]), Arrays.toString(v[0]));
        assertEquals(Arrays.toString(
                M1.copyMatrixBi()[1]), Arrays.toString(v[1]));

        assertEquals(M1.getCell(1,1), v[0][0]);
        assertEquals(M1.getCell(2,3), v[1][2]);

        Matrix M2 = new Matrix(v2);

        assertEquals( Arrays.toString(
                M2.copyMatrixUni(Matrix.copy_mode.Row,1)), Arrays.toString(v2[0]));
        assertEquals( Arrays.toString(
                M2.copyMatrixUni(Matrix.copy_mode.Row,2)), Arrays.toString(v2[1]));
        assertEquals( Arrays.toString(
                M2.copyMatrixUni(Matrix.copy_mode.Column,1)), Arrays.toString(aux));

        assertThrows(IllegalArgumentException.class,() -> M2.copyMatrixUni(Matrix.copy_mode.Column,3));

        Matrix M3 = new Matrix(3,2);

        assertEquals(M3.getColumn(),2);
        assertEquals(M3.getRowNum(),3);

        assertThrows(IllegalArgumentException.class, () -> new Matrix(bad_v));
    }
    @Order(2)
    @Test
    void add() {
        int[][] result = {{30,15},
                          {15,10},
                          {25,10}};
        Matrix M_result_teorico = new Matrix(result);

        Matrix M1 = new Matrix(v2);
        Matrix M2 = new Matrix(v3);
        Matrix M3 = new Matrix(v);

        Matrix Resultado = MatrixAddition.add(M1,M2);

        System.out.println(Resultado.matrixStr()+"Dimensiones: "+Resultado.getRowNum()+" x "+Resultado.getColumn()+"\n");

        assertEquals(Arrays.toString(M_result_teorico.copyMatrixUni(Matrix.copy_mode.Row,1)),
                Arrays.toString(Resultado.copyMatrixUni(Matrix.copy_mode.Row, 1)));
        assertEquals(Arrays.toString(M_result_teorico.copyMatrixUni(Matrix.copy_mode.Row,2)),
                Arrays.toString(Resultado.copyMatrixUni(Matrix.copy_mode.Row, 2)));
        assertEquals(Arrays.toString(M_result_teorico.copyMatrixUni(Matrix.copy_mode.Row,3)),
                Arrays.toString(Resultado.copyMatrixUni(Matrix.copy_mode.Row, 3)));

        M1.setIterationMode(Matrix.Iteration_mode.ColumnRow);
        System.out.println("Iteracion por filas columnas:");
        for (Iterator<Integer> m1 = M1.iterator(); m1.hasNext();) { System.out.print(m1.next()+" "); }

        M1.setIterationMode(Matrix.Iteration_mode.RowColumn);
        System.out.println("\nIteracion por columnas filas:");
        for (Iterator<Integer> m1 = M1.iterator(); m1.hasNext();) { System.out.print(m1.next()+" "); }

        assertThrows(ArithmeticException.class, () -> MatrixAddition.add(M1,M3));
    }
}