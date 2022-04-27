
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatrixTest
{

    public static final int MAX_VALUE=99;
    public static final int MIN_VALUE=0;

    //#   SWAP REVERSE BY DIAGONALS---------------
    //Caso 1: Intercambiar las columnas de una matriz 2x2.
    //Caso 2: Intercambiar las columnas de una matriz 3x3.
    //Caso 3: Intercambiar las columnas de una matriz 4x4.
    @Test
    public void testSwapReverseByDiagonalsSize2()
    {
        //Caso 1: Intercambiar las columnas de una matriz 2x2.
        Matrix matrix=new Matrix(new int[][]{{1, 0}, {0, 1}});
        matrix.swapReverseByDiagonals();
        assertArrayEquals(new int[][]{{0,1},{1,0}},matrix.getMatrix());
    }    

    @Test
    public void testSwapReverseByDiagonalsSize3()
    {
        //Caso 1: Intercambiar las columnas de una matriz 3x3.
        Matrix matrix=new Matrix(new int[][]{{1, 0 ,1}, {0, 1, 2}, {0, 1, 3}});
        matrix.swapReverseByDiagonals();
        assertArrayEquals(new int[][]{{0, 0 ,3}, {0, 1, 2}, {1, 1, 1}},matrix.getMatrix());
    }    

    @Test
    public void testSwapReverseByDiagonalsSize4()
    {
        //Caso 1: Intercambiar las columnas de una matriz 4x4.
        Matrix matrix=new Matrix(new int[][]{{5,10,3,9},{16,30,25,41},{2,17,50,12},{45,8,22,34}});
        matrix.swapReverseByDiagonals();
        assertArrayEquals(new int[][]{{45,10,3,34},{16,17,50,41},{2,30,25,12},{5,8,22,9}},matrix.getMatrix());
    }    
    //#   ROTATE MATRIX ---------------
    //Caso 1: Rotar una matriz de 2x2.
    //Caso 2: Rotar una matriz de  3x3.
    //Caso 3: Rotar una matriz de  4x4.

    @Test
    public void testRotateMatrixSize2()    {
        //Caso 1: Rotar una matriz de 2x2.
        Matrix matrix=new Matrix(new int[][]{{1, 0}, {0, 1}});
        matrix.rotateMatrix();
        assertArrayEquals(new int[][]{{0,1},{1,0}},matrix.getMatrix());
    }    

    @Test
    public void testRotateMatrixSize3()    {
        //Caso 2: Rotar una matriz de  3x3.
        Matrix matrix=new Matrix(new int[][]{{1, 2 ,3}, {0, 1, 1}, {1, 0, 0}});
        matrix.rotateMatrix();
        assertArrayEquals(new int[][]{{3, 1 ,0}, {2, 1, 0}, {1, 0, 1}},matrix.getMatrix());
    }    

    @Test
    public void testRotateMatrixSize4()    {
        //Caso 3: Rotar una matriz de  4x4.
        Matrix matrix=new Matrix(new int[][]{{5,10,8,9},{16,30,25,41},{7,17,50,12},{45,8,22,34}});
        matrix.rotateMatrix();
        assertArrayEquals(new int[][]{{9,41,12,34},{8,25,50,22},{10,30,17,8},{5,16,7,45}},matrix.getMatrix());
    }   
    //#   MOVE COLUMN --------------
    //Caso 1: Mover una columna cualquier de una matriz de 3x3. 
    //Caso 2: Mover una columna cualquier de una matriz de 4x4.
    //Caso 3: Mover una columna cualquier de una matriz de 6x6.
    //Pruebas negativas:
    //Caso 4: Mover una columna erronea de cualquier matriz de 6x6.
    @Test
    public void testMoveColumnSize3(){
        //Caso 1: Mover una columna cualquier de una matriz de 3x3. 
        Matrix matrix=new Matrix(new int[][]{{1, 2 ,3}, {0, 1, 1}, {1, 0, 0}});
        matrix.moveColumn(0);
        assertArrayEquals(new int[][]{{2, 3 ,1}, {1, 1, 0}, {0, 0, 1}},matrix.getMatrix());
    }  

    @Test
    public void testMoveColumnSize4(){
        //Caso 2: Mover una columna cualquier de una matriz de 4x4.
        Matrix matrix=new Matrix(new int[][]{{5,10,8,9},{16,30,25,41},{7,17,50,12},{45,8,22,34}});
        matrix.moveColumn(2);
        assertArrayEquals(new int[][]{{5,10,9,8},{16,30,41,25},{7,17,12,50},{45,8,34,22}},matrix.getMatrix());
    }  

    @Test
    public void testMoveColumnSize6(){
        //Caso 3: Mover una columna cualquier de una matriz de 6x6.
        Matrix matrix=new Matrix(new int[][]{{5,10,5,9,23,12},{16,30,25,41,50,9},{7,17,50,12,46,34},{45,8,22,34,8,48},{6,21,13,5,11,41},{23,45,16,18,29,25}});
        matrix.moveColumn(3);
        assertArrayEquals(new int[][]{{5,10,5,23,12,9},{16,30,25,50,9,41},{7,17,50,46,34,12},{45,8,22,8,48,34},{6,21,13,11,41,5},{23,45,16,29,25,18}},matrix.getMatrix());
    }  

    @Test
    public void testMoveColumnSize6Error(){
        //Caso 4: Mover una columna erronea de cualquier matriz de 6x6.
        try{
            Matrix matrix=new Matrix(new int[][]{{5,10,5,9,23,12},{16,30,25,41,50,9},{7,17,50,12,46,34},{45,8,22,34,8,48},{6,21,13,5,11,41},{23,45,16,18,29,25}});
            matrix.moveColumn(9);
            fail("Se espera un error de parámetro");

        }catch(RuntimeException rte){
            assertEquals("Error:La columna pedida no existe o es un número negativo",rte.getMessage());
        }
    }  
    //#   IS MAGIC --------------
    //Caso 1: Matriz que sí cumple la condición de cuadrado mágico 4x4. 
    //Caso 2: Matriz que no cumple la condición de cuadrado mágico 4x4.
    //Caso 3: Matriz que sí cumple la condición de cuadrado mágico 2x2.
    @Test
    public void testIsMagicSquareSize4True()
    {
        //Caso 1: Matriz que sí cumple la condición de cuadrado mágico 4x4. 
        Matrix matrix=new Matrix(new int[][]{{1,14,14,4},{11,7,6,9},{8,10,10,5},{13,2,3,15}});
        assertTrue(matrix.isMagicSquare());
    }  

    @Test
    public void testIsMagicSquareSize4False()
    {
        //Caso 2: Matriz que no cumple la condición de cuadrado mágico 4x4.
        Matrix matrix=new Matrix(new int[][]{{1,1,14,4},{11,7,6,9},{8,10,10,5},{13,2,3,15}});
        assertFalse(matrix.isMagicSquare());
    }  

    @Test
    public void testIsMagicSquareSize2()
    {
        //Caso 3: Matriz que sí cumple la condición de cuadrado mágico 2x2.
        Matrix matrix=new Matrix(new int[][]{{1, 1}, {1, 1}});
        assertTrue(matrix.isMagicSquare());
    }    

    //#   REPLACE MATRIX --------------
    //Caso 1: ArrayList con 5 números. 
    //Caso 2: ArrayList superior a los números de la matriz.
    //Pruebas negativas:
    //Caso 3: ArrayList incorrecta.
    @Test
    public void testReplaceMatrix()
    {
        Matrix matrix=new Matrix(new int[][]{{1, 0,6}, {0, 1,6},{8,4,6}});
        ArrayList<Integer> flatMatrix = new ArrayList<Integer>();
        for(int i=1;i<5;i++){ //Crearemos 4 números
            flatMatrix.add(i);
        }
        matrix.replaceMatrix(flatMatrix);
        assertEquals(4,matrix.replaceMatrix(flatMatrix)); 
        assertArrayEquals(new int[][]{{1, 2,3}, {4, 1,6},{8,4,6}},matrix.getMatrix());
    }   

    @Test
    public void testReplaceMatrixMoreNumbers()
    {
        Matrix matrix=new Matrix(new int[][]{{1, 0,6}, {0, 1,6},{8,4,6}});
        ArrayList<Integer> flatMatrix = new ArrayList<Integer>();
        for(int i=1;i<12;i++){ //Crearemos 11 números.
            flatMatrix.add(i);
        }
        matrix.replaceMatrix(flatMatrix);
        assertEquals(9,matrix.replaceMatrix(flatMatrix)); 
        assertArrayEquals(new int[][]{{1, 2,3}, {4, 5,6},{7,8,9}},matrix.getMatrix());
    }   

    @Test
    public void testReplaceMatrixWrong()
    {
        try{
            Matrix matrix=new Matrix(new int[][]{{1, 0,6}, {0, 1,6},{8,4,6}});
            ArrayList<Integer> flatMatrix = null;
            matrix.replaceMatrix(flatMatrix);
            fail("Se espera un error de parámetro");

        }catch(RuntimeException rte){
            assertEquals("Error: El ArrayList tiene que ser distinto de null",rte.getMessage());
        }
    }   
    
    //#  FLATTEN MATRIX --------------
    //Caso 1: ArrayList matrix 2x2. 
    //Caso 2: ArrayList matrix 4x4.
    //Caso 3: ArrayList matrix 3x3.

    @Test
    public void testFlattenMatrixSize2()
    {
        Matrix matrix=new Matrix(new int[][]{{1, 0}, {0, 1}});
        ArrayList<Integer> result = matrix.flattenMatrix();
        assertEquals(4,result.size());

    }    

    @Test
    public void testFlattenMatrixSize4()
    {
        Matrix matrix=new Matrix(new int[][]{{1,1,14,4},{11,7,6,9},{8,10,10,5},{13,2,3,15}});
        ArrayList<Integer> result = matrix.flattenMatrix();
        assertEquals(16,result.size());

    }    

    @Test
    public void testFlattenMatrixSize6()
    {
        Matrix matrix=new Matrix(new int[][]{{5,10,5,9,23,12},{16,30,25,41,50,9},{7,17,50,12,46,34},{45,8,22,34,8,48},{6,21,13,5,11,41},{23,45,16,18,29,25}});
        ArrayList<Integer> result = matrix.flattenMatrix();
        assertEquals(36,result.size());

    }  

}
