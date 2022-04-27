import java.util.Random;
import java.util.ArrayList;
/**
 * Construir un proyecto en BlueJ con una clase Matrix con tenga como propiedad una matriz
 * bidimensional de números enteros de dimensión mínima 2x2 y máxima 10x10. 
 * 
 * Se trabajará de forma acumulativa sobre la matriz, es decir, una operación trabajará sobre la matriz resultado
 * de la operación anterior y su resultado será utilizado por la siguiente operación.
 * 
 * @author (Raquel Suárez Álvarez) 
 * @version (06/12/2020)
 */
public class Matrix
{
    private int [][] matrix;
    public static final int MIN_DIMENSION=2;
    public static final int MAX_DIMENSION=10;

    /**
     * Constructor con un parámetro entero que crea una matriz cuadrada cuya dimensión se corresponde con el valor del parámetro. 
     * 
     * La matriz se rellenará de forma aleatoria con números enteros entre 0 y 99. Si la dimensión de la matriz no está entre 2 y 10, el parámetro no es válido y se debe lanzar la excepción:
     * 
     * RuntimeException("Error: La dimensión debe estar entre "+ MIN_DIMENSION + " y " + MAX_DIMENSION);
     */
    public Matrix(int size){
        checkParam(size <= MAX_DIMENSION && size >= MIN_DIMENSION,"Error:La dimensión debe estar entre "+ MIN_DIMENSION +" y " + MAX_DIMENSION);
        setMatrix(new int [size][size]);
        fullMatrixRandom();
    }

    /**
     * Constructor con un parámetro array. 
     * 
     * Se le pasa en la llamada una matriz ya creada con unos valores concretos y se debe comprobar que la matriz no es null, la dimensión es correcta y que la matriz es cuadrada. 
     * (mismo número de filas que de columnas en todas las filas). 
     * 
     * En caso contrario, se debe lanzar la excepción:
     * 
     * RuntimeException("Error: La matriz no es válida ");
     */
    public Matrix(int [][] matrix){
        checkParam(matrix!=null && isSquareMatrix(matrix) && matrix.length <= MAX_DIMENSION && matrix.length >= MIN_DIMENSION,"Error:La dimensión debe estar entre "+ MIN_DIMENSION +" y " + MAX_DIMENSION);
        setMatrix(matrix);
    }

    /**
     * Método swapReverseByDiagonals: Intercambia los elementos de las dos diagonales, invirtiendo sus elementos.
     * 
     * Suponiendo una matriz 4x4 el resultado sería:
     * 
     * 5  10  3  9     
     * 16 30  25 41
     * 2  17  50 12
     * 45  8  22 34
     *       .
     *       .
     *       v
     * 45 10  3 34
     * 16 17 50 41
     * 2  30 25 12
     * 5  8  22  9
     *
     */
    public void swapReverseByDiagonals(){
        int [] diagonal1= new int [matrix.length];
        int [] diagonal2= new int[matrix.length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j< matrix.length;j++){
                if(i==j){
                    diagonal1[i]=matrix[i][j];
                    diagonal2[i]=matrix[matrix.length-1-i][j];
                    matrix[matrix.length-1-i][j]=diagonal1[i];
                    matrix[i][j]=diagonal2[i];

                }
            }
        }

    }

    /**
     * Método rotateMatrix. 
     * Consiste  en  girar  la  matriz  90º  hacia  la  izquierda.
     */
    public void rotateMatrix(){
        int [][] matrixT=copyOfMatrix();
        int size= matrix.length-1;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length && size>=0;j++){
                matrix[i][j]=matrixT[j][size];
            }
            size--;
        }
    }

    /**
     * Método moveColumn. Consiste en mover los elementos de la columna que se pasa como parámetro al final de la matriz.
     *
     * @param column la columna que se quiere mover.
     */
    public void moveColumn(int column){
        checkParam(column <= matrix.length-1 && column >= 0,"Error:La columna pedida no existe o es un número negativo");

        int[][]newMatrix=copyOfMatrix();
        for(int i=0;i<matrix.length;i++){
            for(int j=column;j<matrix[i].length-1;j++){

                newMatrix[i][j]=matrix[i][j+1];
            }
        }
        for(int i=0;i<matrix.length;i++){
            newMatrix[i][matrix.length-1] = matrix[i][column];
        }
        setMatrix(newMatrix);
    }

    /**
     * Método print. Muestra por consola el contenido de la matriz.
     *
     */
    public void print(){
        System.out.println();
        for (int i=0; i < matrix.length; i++) {            
            for (int j=0; j < matrix[i].length; j++) {
                System.out.print (matrix[i][j]);
                if (matrix[i][j] > 9){
                    System.out.print(" ");
                } else{
                    System.out.print("  ");
                }
            }     
            System.out.println();
        }  
    }

    //# DESAFÍO PARTE II --------------

    /**
     * Método isMagicSquare. Comprueba si la matriz cumple la condición de cuadrado mágico.
     *
     * @return true sí cumple dicha condición, false si no.
     */
    public boolean isMagicSquare(){
        int sumaFila=0;
        int sumaColumna=0;
        int aux=0;
        boolean isMagic=true;
        for (int i=0; i < matrix.length; i++) {            
            for (int j=0; j < matrix[i].length; j++) {
                sumaFila= sumaFila+ matrix[i][j];
                sumaColumna= sumaColumna + matrix[j][i];
            }
            if(sumaFila==sumaColumna){
                aux++;
                if(aux==matrix.length){
                    isMagic=true;
                }
                sumaFila=0;
                sumaColumna=0;                
            }else{
                isMagic=false;
            }        
        }
        if(sumaDiagonales()&&isMagic){
            return true;
        }
        return false;
    }

    /**
     * Método replaceMatrix. Método que recibe un ArrayList<Integer> y reemplaza todos los elementos de la matriz que pueda,
     * desde la posición [0, 0], avanzando por filas, 
     * es decir, tiene que reemplazar la posición [0, 0] por el primer elemento del ArrayList; 
     * 
     * la posición [0, 1] por el segundo elemento del ArrayList;
     * la posición [0, 2] por el tercer elemento del ArrayList,y así sucesivamente, recorriendo fila a fila. 
     * 
     * Si el ArrayList contiene más elementos que posiciones tiene la matriz, reemplaza todas las
     * posiciones de la matriz, aunque sobren elementos del ArrayList.
     * 
     * Por ejemplo, dada la siguiente matriz y el contenido de la colección ArrayList de objetos
     * Integer con valores: 10, 26, 30, 90, 18, 2 , 45 el método devolvería 7
     *
     * @param flatMatrix Un parámetro
     * @return Retorna el número de elementos que se han reemplazado en la matriz. 
     * 
     */
    public int replaceMatrix(ArrayList<Integer> flatMatrix){
        checkParam(flatMatrix!=null,"Error: El ArrayList tiene que ser distinto de null");
        int count=0;
        int f=0;
        int numbersOfMatrix=matrix.length*matrix.length;
        for (int i=0; i < matrix.length; i++) {            
            for (int j=0; j < matrix[i].length; j++) {
                if(f<flatMatrix.size()){
                    matrix[i][j]=flatMatrix.get(f);
                    f++;
                }else{
                    break;
                }
            }
        }
        if(numbersOfMatrix>flatMatrix.size()){
            count=flatMatrix.size();
        }else{
            count=numbersOfMatrix;
        }
        return count;
    }

    /**
     * Método flattenMatrix. Método que retorna el contenido de la matriz "aplanado", 
     * es decir, recorre la matriz por filas y copia sus elementos en una colección de enteros ArrayList<Integer>
     * 
     * Por ejemplo, dada la siguiente matriz, la colección ArrayList de objetos Integer tendría los valores:
     * 5, 10, 8, 9, 16, 30 , 25, 41, 7, 17, 50, 12, 45, 8, 22, 34 
     *
     * @return el flattenMatrix, un ArrayList con los números de la matriz.
     */
    public ArrayList<Integer> flattenMatrix (){
        ArrayList<Integer> flattenMatrix = new ArrayList<Integer>();
        for (int i=0; i < matrix.length; i++) {            
            for (int j=0; j < matrix[i].length; j++) {
                flattenMatrix.add(matrix[i][j]);
            }
        }
        return flattenMatrix;
    }

    /**
     * Método getMatrix. Devuelve la matrix.
     *
     * @return la matrix.
     */
    public int[][] getMatrix(){
        return matrix;
    }

    //# MÉTODOS PRIVADOS ----------------------------------------
    /**
     * Método sumaDiagonales. Realiza la suma de todos los valores de cada número perteneciente a una de las dos diagonales.
     * Después, combrueba si la suma de todos esos valores es igual a la suma de los valores de la otra diagonal.
     * De tal manera que, si es igual, devuelve true, si es distinta false.
     *
     * @return true o false, dependiendo de si las sumas son iguales o no.
     */
    private boolean sumaDiagonales(){
        int sumaPrimeraDiagonal=0;
        int sumaSegundaDiagonal=0;
        for (int i=0; i < matrix.length; i++) {            
            for (int j=0; j < matrix[i].length; j++) {
                sumaPrimeraDiagonal=sumaPrimeraDiagonal+diagonal1()[i];
                sumaSegundaDiagonal=sumaSegundaDiagonal+diagonal2()[i];
            }
        }
        if(sumaPrimeraDiagonal==sumaSegundaDiagonal){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Método diagonal1. Devuelve un array unidimensional con la diagonal principal.
     *
     * @return la diagonal principal.
     */
    private int []diagonal1(){
        int [] diagonal1= new int [matrix.length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j< matrix.length;j++){
                if(i==j){
                    diagonal1[i]=matrix[i][j];
                }
            }
        }
        return diagonal1;
    }

    /**
     * Método diagonal2. Devuelve un array unidimensional con la diagonal secundaria.
     *
     * @return la diagonal secundaria.
     */
    private int []diagonal2(){
        int [] diagonal2= new int [matrix.length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j< matrix.length;j++){
                if(i==j){
                    diagonal2[i]=matrix[matrix.length-1-i][j];
                }
            }
        }
        return diagonal2;
    }

    /**
     * Método copyOfMatrix. Realiza una copia de la matrix creada y devuelve la copia.
     * Método útil para trabajar sobre la matrix.
     *
     * @return una copia de la matrix.
     */
    private int[][]copyOfMatrix(){
        int copy[][] = new int[matrix.length][matrix.length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                copy[i][j]= matrix[i][j];
            }
        }
        return copy;
    }

    /**
     * Método isSquareMatrix. Comprueba que la matriz sea cuadrada.
     *
     * @return true sí es cuadrada, false si no es cuadrada.
     */
    private boolean isSquareMatrix(int[][]matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix.length == matrix[0].length){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Método setMatrix. Método que cambia la matrix por la nueva matrix que se le pase como parámetro.
     *
     * @param matrix la nueva matriz.
     */
    private void setMatrix(int[][]matrix){
        this.matrix=matrix;
    }

    /**
     * Método fullMatrixRandom. Llena la matriz con números aleatorios entre el 0 y el 99.
     *
     */
    private void fullMatrixRandom(){
        Random random = new Random();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                matrix[i][j]=random.nextInt(100);
            }
        }
    }

    /**
     * Método getWheel, que devuelve la referencia de la rueda que ocupa la posición index.
     *
     * @param index la posición de la rueda a devolver.
     * @return la referencia de la rueda 
     */
    public int getNumber(int row,int column){
        return matrix[row][column];
    }

    private void checkParam(boolean condition, String str){
        if(!condition){
            throw new RuntimeException(str);
        }
    }

    // Matrices ejemplos:
    //{{5,10,3,9},{16,30,25,41},{2,17,50,12},{45,8,22,34}}
    //{{5,10,8,9},{16,30,25,41},{7,17,50,12},{45,8,22,34}}
    //{{5,10,5,9,23,12},{16,30,25,41,50,9},{7,17,50,12,46,34},{45,8,22,34,8,48},{6,21,13,5,11,41},{23,45,16,18,29,25}}   
    //{{1,14,14,4},{11,7,6,9},{8,10,10,5},{13,2,3,15}} -----> magic matrix
    //{{5,10,8,9},{16,30,25,41},{7,17,50,12},{45,8,22,34}}
}
