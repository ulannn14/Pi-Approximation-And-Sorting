import java.util.Random;
import java.io.PrintWriter;
import java.io.IOException;

public class PiApproximation2 {
    public static long doubleForLoop(long n, PrintWriter writer) {
        long insideTheCircleCounter = 0, currentSample = 0, x = 0, y = 0;
        long r = n; //(long) Math.sqrt(n);
        double dividedX = 0, dividedXsquared, dividedY = 0, dividedYsquared, piApprox = 0.0;

        for (x = 0; x <= r; x++) {
            dividedX = (double) x / r;
            dividedXsquared = dividedX * dividedX;

            for (y = 0; y <= r; y++) {
                currentSample++;
                dividedY = (double) y / r;
                dividedYsquared = dividedY * dividedY;
                if ((dividedXsquared) + (dividedYsquared) <= 1)
                    insideTheCircleCounter++;
            }
        }

        piApprox = ((double) insideTheCircleCounter / currentSample) * 4.0;
        System.out.print(currentSample + " " + piApprox + "\n");
        writer.print(currentSample + " " + piApprox + "\n");
        return currentSample;
    }

    public static long singleForLoop(long n, PrintWriter writer) {
        long insideTheCircleCounter = 0, currentSample = 0;
        double piApprox = 0.0;
        Random rand = new Random();

        for (long i = 0; i < n; i++) {
            double x = (i == n - 1) ? 1.0 : rand.nextDouble();
            double y = (i == n - 1) ? 1.0 : rand.nextDouble();
            currentSample = i + 1;

            if ((x * x + y * y) <= 1)
                insideTheCircleCounter++;
        }
        piApprox = ((double) insideTheCircleCounter / currentSample) * 4.0;
        System.out.print(n + " " + piApprox + "\n");
        writer.print(n + " " + piApprox + "\n");
        return currentSample;
    }

    public static void main(String[] args) {
        // always giving n itself, around estimated
        
        /* long[] sampleSizesDoubleLoop = { // sample size for double for-loop
            10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 500, 1000, 10000,                                                       
        }; */

        long[] sampleSizesDoubleLoop = { // sample size for double for-loop
            10, 100, 1000, 10000, 100000,                                               
        };

        long[] sampleSizesSingleLoop = new long[sampleSizesDoubleLoop.length]; // sample size for single for-loop 

        try (PrintWriter writer = new PrintWriter("PiApproximation Result")) {

            System.out.println("\nTwo Loops Results: ");
            writer.println("\nTwo Loops Results:");
            for (int i=0; i<sampleSizesDoubleLoop.length; i++)
                sampleSizesSingleLoop[i] = doubleForLoop(sampleSizesDoubleLoop[i], writer);            

            System.out.println("\nOne Loop Results: ");
            writer.println("One Loop Results:");
            for (int i=0; i<sampleSizesSingleLoop.length; i++)
                singleForLoop(sampleSizesSingleLoop[i], writer);
            
            System.out.println("End of Program");
            writer.println("End of Program");
            writer.close();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}