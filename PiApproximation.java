import java.util.Random;

// PROBLEM: r goes from 0 to 1, so (r+1)^2 siya, pero hindi nattranslate sa printing ng last

public class PiApproximation {
    public static void doubleForLoop(long r, long dividerForPrint) {
        long insideTheCircleCounter = 0, currentSample = 0, x=0, y=0;
        //long r = 10000; 
        double dividedX = 0, dividedXsquared, dividedY = 0, dividedYsquared, piApprox = 0.0;
        
        for (x = 0; x <= r; x++) {
            dividedX = (double)x/r;
            dividedXsquared = dividedX * dividedX;

            for (y = 0; y <= r; y++) {
                currentSample++;
                dividedY = (double)y/r;
                dividedYsquared = dividedY * dividedY;
                if ((dividedXsquared) + (dividedYsquared) <= 1)
                    insideTheCircleCounter++;

                // Printing of pi approximation at certain intervals System.out.printf("dividedX: %.6f, dividedY: %.6f%n", dividedX, dividedY);

                if (currentSample % (r*dividerForPrint) == 0) {
                    piApprox = ((double) insideTheCircleCounter / currentSample) * 4.0;
                    System.out.printf("After %,d samples, π ≈ %.6f%n", currentSample, piApprox);
                }
            }
        }

        System.out.printf("\nFinal approximation of π = %.6f%n", piApprox);
        System.out.printf("Total samples taken: %,d%n", currentSample);
    }


    public static void singleForLoop(long r, long dividerForPrint) {
        long insideTheCircleCounter = 0, currentSample = 0;
        //long r = 10000;
        long n = (r+1)*(r+1);
        double piApprox = 0.0;
        Random rand = new Random();

        for (long i = 0; i < n; i++) {
            //double x = rand.nextDouble(); // [0,1)
            //double y = rand.nextDouble(); // [0,1)

            double x = (i == n - 1) ? 1.0 : rand.nextDouble();
            double y = (i == n - 1) ? 1.0 : rand.nextDouble();

            if ((x * x + y * y) <= 1) {
                insideTheCircleCounter++;
            }

            currentSample = i+1;
            if (currentSample % (r*dividerForPrint) == 0) {
                piApprox = ((double) insideTheCircleCounter / currentSample) * 4.0;
                System.out.printf("After %,d samples, π ≈ %.6f%n", currentSample, piApprox);
            }
        }
        System.out.printf("\nFinal approximation of π = %.6f%n", piApprox);
        System.out.printf("Total samples taken: %,d%n", currentSample);
    }

    public static void main(String[] args) {
        long r = 100000, dividerForPrint = 1000;
        System.out.println("\nOne Loop Results: ");
        singleForLoop(r, dividerForPrint);
        System.out.println("\nTwo Loops Results: ");
        doubleForLoop(r, dividerForPrint);
        System.out.println("End of Program");

    }
}
