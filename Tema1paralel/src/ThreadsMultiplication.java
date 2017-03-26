
public class ThreadsMultiplication {
	Matrix A;
	Matrix B;
	Matrix result = new Matrix(1000, 1000 );
	private static myThread[] threadPool;

	
	public void setA(Matrix A){
		this.A = A;
	}
	
	public void setB(Matrix B){
		this.B = B;
	}
	
	public Matrix getResult(){
		return result;
	}
	
	private  class myThread extends Thread{
        int index;  
        
        myThread(int index){
            this.index = index;
        }
        
        public void run(){
            for(int i=0; i<A.getM(); i++){
                for(int j=0; j<B.getN(); j++){
                    result.data[index][i] += A.data[index][j] * B.data[j][i];
                }
            }
        }
	}
	
	
	private void multiply(){
		        threadPool = new myThread[A.getM()];
		        
		        for(int i=0; i<A.getM(); i++){
		        	     threadPool[i] = new myThread(i);
		        	     threadPool[i].start();
		        }
		        
		        for(int i=0; i<A.getM(); i++){  
		        	try{
		        	      threadPool[i].join();
		        	 }catch (InterruptedException e){
		        	   //thread was interrupted
		        		 System.out.println("INTERRUPTED THREAD");
		        	 }
		        }

	
	}

	
	//test
	public static void main(String[] args) {
        Matrix A = Matrix.random(10, 10);
        Matrix B = Matrix.random(10, 10);
        Matrix C;
        
        ThreadsMultiplication times = new ThreadsMultiplication();
        
        times.setA(A);
        times.setB(B);
        
        times.multiply();
        
        C = times.getResult();
        System.out.println("done");
        C.show();
	}
	
}
