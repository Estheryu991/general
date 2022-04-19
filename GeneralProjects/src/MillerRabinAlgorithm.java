import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class MillerRabinAlgorithm {
	
	//Main function, for more rounds the probability that the algo doesn't work well goes down
	public static boolean manyMillerRabin(long n, int round){
		int count = 0;
	      
		for(int i = 0; i < round; i++){
			if(millerRabin(n)){
				count++;
	        }
	    }
	      
		if(count > (round / 2)){
			return true;
	    }
		else{
			return false;
	    }
	}
	
	//Here are helperfunctions
	
	//Returns BigInteger object with value n
	public static BigInteger getBigInteger(long n){
		return new BigInteger("" + n);
	}
	    
	//Returns BigInteger object with random value between 2 and n
	public static BigInteger getRandom(long n){
		BigInteger res = getBigInteger(ThreadLocalRandom.current().nextLong(n));
	      
		while(res.longValue() == 1 || res.longValue() == 0){
			res = getBigInteger(ThreadLocalRandom.current().nextLong(n));
		}
		
		return res;
	}
	
	//n - 1 == 2^k * m, res[0] == k, res[1] == m
	public static long[] getKM(long n){
		long[] res = new long[2];
	    long k = 0;
	    n-=1;
	      
	    while(n % 2 == 0 && n != 0){
	    	k++;
	        n /= 2;
	    }
	      
	    res[0] = k;
	    res[1] = n;
	      
	    return res;
	}
	
	//returns a^((2^k)*m) mod(n)
	public static BigInteger getModPow(BigInteger a, long k, long m, long n){
		BigInteger exp = getBigInteger(2).pow((int)k).multiply(getBigInteger(m));
		return a.modPow(exp,getBigInteger(n));
	}
	
	//executes miller rabin algorithm one time
	public static boolean millerRabin(long n){
		BigInteger a = getRandom(n);
	    long[] km = getKM(n);
	      
	    for(long i = km[0]; i >= 0; i--){
	    	BigInteger cur = getModPow(a,i,km[1],n);
	        
	        if(cur.longValue() != 1){
	          
	        	if(cur.longValue() == (n - 1)){
	        		return true;
	        	}
	        	else{
	        		return false;
	        	}
	        	
	        }
	    
	    }
	    
	    return true;
	}
	    	    
}
