import java.lang.Math;
public class polynomial_roots {
    public static void main(String[] args){
        int[] coefficients = {1,-55,1320,-18150,157773,-902055,3416930,-8409500,12753576,-10628640,3628800};
        //bisectionMethod(coefficients);
        //newtonsMethod(coefficients);
        secantMethod(coefficients);
    }

    public static void bisectionMethod(int [] coefficients){
        //we know we have 10 roots and each of them is between the following values
        double[][] rootBounds = {{0.2,1.2},{1.5,2.5},{2.2,3.1},{3.7,4.1},{4.4,5.3},{5.2,6.2},{6.5,7.5},{7.2,8.1},{8.7,9.1},{9.5,10.5}};
        System.out.println("Bisection method:");
        System.out.println("The calculated root is at the midpoint of the final interval");
        //find all roots
        for(int i = 0; i < rootBounds.length; i++){
            //loop ten times or until a root is found
            boolean rootFound = false;
            System.out.println ("Root number: "+ i);
            for(int j = 0 ; j < 10 && !rootFound; j++){
                //calculate the value for the lower & upper bounds
                double yLowerBound = 0, yUpperBound = 0, yMidpoint = 0;
                double xMidpoint = (rootBounds[i][0] + rootBounds[i][1])/2;
                int root = 0;
                for(int k = 0; k < coefficients.length; k++){
                    yLowerBound += coefficients[k]*Math.pow(rootBounds[i][0],10-k);
                    yUpperBound += coefficients[k]*Math.pow(rootBounds[i][1],10-k);
                    yMidpoint += coefficients[k]*Math.pow(xMidpoint,10-k);
                }
                //if the midpoint is zero we can exit before ten loops
                if(yMidpoint == 0){
                    root = (int)(xMidpoint+.5);
                    rootFound = true;
                }
                //how we readjust our bounds when there is a change of sign
                if(yLowerBound*yMidpoint < 0){
                    rootBounds[i][1] = xMidpoint;
                    xMidpoint = (rootBounds[i][0]+rootBounds[i][1])/2;
                }else if(yMidpoint*yUpperBound < 0){
                    rootBounds[i][0] = xMidpoint;
                    xMidpoint = (rootBounds[i][0]+rootBounds[i][1])/2;
                }
                if(j==10){
                    System.out.println(xMidpoint);
                    //round the midpoint to the nearest integer then store it in roots
                    root = (int)(xMidpoint + .5);
                }
                System.out.println("\titeration "+ j +" is [" + rootBounds[i][0]+ ","+rootBounds[i][1]+"]");
            }
            System.out.println();
        }
    }

    public static void newtonsMethod(int[] coefficients){
        System.out.println("Newton's method:");
        System.out.println("Initial guesses heavily influence how close we can get to the actual root");
        //initial estimates for roots
        double[] roots = {.85,1.9,3.1,4.2,5.1,6.0,7.15,8.3,9.1,9.99};
        //find all roots
        for(int i = 0 ; i < roots.length; i++){
            System.out.println("Root "+ (i+1) +":");
            int[] derivative = derivative(coefficients);
            double numerator = 0, denominator = 0;
            //go through each root 10 times
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < coefficients.length; k++){
                    numerator += coefficients[k]*Math.pow(roots[i],10-k);
                    if(k < coefficients.length-1){
                        denominator += derivative[k]*Math.pow(roots[i],10-k);
                    }
                }
                roots[i] = roots[i] - numerator/denominator;
                System.out.println("\titeration "+ j +" results in "+ roots[i]);
            }
        }
    }

    public static void secantMethod(int[] coefficients){
        System.out.println("Secant method:");
        //initial estimates for roots, both n minus 1 and n, making sure the guess for the same root differs on both
        double[] roots_nm1 = {.8,1.8,3.05,4.1,5.1,5.9,7.15,8.2,9.1,9.79};
        double[] roots_n = {.85,1.9,3.1,4.2,5.2,6.3,7.2,8.3,9.2,9.99};
        //find all roots
        for(int i = 0 ; i < roots_nm1.length; i++){
            System.out.println("Root "+ (i+1) +":");
            double numerator = 0, denominator = 0;
            //go through each root 10 times
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < polynomial.length; k++){
                    numerator += polynomial[k]*Math.pow(roots[i],10-k);
                    if(k < polynomial.length-1){
                        denominator += derivative[k]*Math.pow(roots[i],10-k);
                    }
                }
                roots[i] = roots[i] - numerator/denominator;
                System.out.println("\titeration "+ j +" results in "+ roots[i]);
            }
        }
    }

    public static int[] dividePolynomial(int[] polynomial, int root){
        int[] newPolynomial = new int[polynomial.length-1];
        newPolynomial[0] = 1;
        //short division of the polynomial
        for(int i = 1 ; i < polynomial.length-1; i++){
            newPolynomial[i] = newPolynomial[i-1]*root+polynomial[i];
        }
        return newPolynomial;
    }

    public static int[] derivative(int[] polynomial){
        int[] derivative = new int[polynomial.length-1];
        for(int i = 0; i < polynomial.length-1; i++){
            derivative[i] = (polynomial.length-i-1)*polynomial[i];
        }
        return derivative;
    }
}
