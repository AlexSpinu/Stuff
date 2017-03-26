import java.util.Random;

final public class Matrix {
    private final int M;             // number of rows
    private final int N;             // number of columns
    final float[][] data;   // M-by-N array
    
    
    public int getM(){
    	return M;
    }
    
    public int getN(){
    	return N;
    }
    
    public float[][] getData(){
    	return data;
    }

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new float[M][N];
    }

    // create matrix based on 2d array
    public Matrix(float[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new float[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // copy constructor
    public Matrix(Matrix A) { this(A.data); }
    
    
    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        Random rand = new Random();
        float min = 0.95f;
        float max = 0.99f;
        
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = rand.nextFloat() * (max - min) + min;
        return A;
    }
    
    

    // create and return the N-by-N identity matrix
    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }


       // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) 
                System.out.print(data[i][j] + " ");
            System.out.println();
        }
    }
    
    
    
        
    // test 
    public static void main(String[] args) {
        Matrix A = Matrix.random(10000, 10000);
        Matrix B = Matrix.random(10000, 10000);
        Matrix C; 
        
        C = A.times(B);
        C.show();
        
        
    }
}