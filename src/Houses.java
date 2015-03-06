import java.io.*;
import java.text.Format;
import java.util.Locale;

/**
 * Created by Уладзімір Асіпчук on 05.03.15.
 */
public class Houses {
    public int n;
    public static void main(String[] args) throws IOException{
        int n = 0;
        PrintWriter out = new PrintWriter(new FileWriter("output.out"));

        // reading and making arrays
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.in")));
        if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
         n = (int)st.nval;
        }
        int[] pointsX = new int[4*n+2];
        int[] pointsY = new int[4*n+2];
        pointsX[0] = 0;
        pointsY[0] = 0;
        int tempX = 0;
        int tempY = 0;
        for (int i = 1; i <= n; i++) {
            if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                tempX = (int)st.nval;
            }
            if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                tempY = (int)st.nval;
            }
            pointsX[4*(i-1)+1] = tempX;
            pointsX[4*(i-1)+2] = tempX;
            pointsX[4*(i-1)+3] = tempX + 5;
            pointsX[4*(i-1)+4] = tempX + 5;

            pointsY[4*(i-1)+1] = tempY;
            pointsY[4*(i-1)+2] = tempY + 5;
            pointsY[4*(i-1)+3] = tempY + 5;
            pointsY[4*(i-1)+4] = tempY ;

        }
        pointsX[4*n+1] = 100;
        pointsY[4*n+1] = 100;

        // making the matrix, which shows the lenght of
        // the way between 2 point, or MAX_VALUE
        double[][] ways = new double[4*n+2][4*n+2];
        for (int i = 0; i < 4*n+2; i++)
            for (int j = 0; j < 4*n+2; j++) {
                ways[i][j] = Double.MAX_VALUE;
            }
        double a = 0;
        double b = 0;

        double tempA = 0;
        double tempB = 0;
        double x = 0;
        double y = 0;
        boolean isGood = true;
        for (int i = 0; i < 4*n+2; i++)
            for (int j = i; j < 4*n+2; j++) {
                isGood = true;
                if (pointsX[i] == pointsX[j] && pointsY[i] == pointsY[j]) {
                    ways[i][j] = 0;
                    continue;
                }


                //TODO if || Oy
                if (pointsX[i] == pointsX[j]) {
                    for (int k = 1; k <= n; k++) {
                        if (pointsX[4*(k-1)+1] < pointsX[i] && pointsX[i] < pointsX[4*(k-1)+4]) {
                            isGood = false;
                            break;
                        }
                    }
                   continue;
                }

                if (isGood == false) {
                    continue;
                }
                a = (pointsY[j] - pointsY[i]) /  (pointsX[j] - pointsX[i]);
                b = pointsY[i] - a*pointsX[i];



                // check for all houses
                for (int k = 1; k <=n; k++) {
                    // left-down & left-up
//                    tempA = (pointsY[4*(k-1)+2] - pointsY[4*(k-1)+1]) /  (pointsX[4*(k-1)+2] - pointsX[4*(k-1)+1]);
//                    tempB = pointsY[i] - a*pointsX[i];
//                    if (tempA == a) {
//                        isGood = false;
//                        break;
//                    }
//                    // peresechenie
//                    x = (tempB - b)/(a - tempA);
//                    y = a*x+b;
//                    if (pointsY[4*(k-1)+1] < y && y < pointsY[4*(k-1)+2]) {
//                        isGood = false;
//                        break;
//                    }

                    if ( pointsY[4*(k-1)+1] < a*pointsX[4*(k-1)+1] + b && a*pointsX[4*(k-1)+1] + b < pointsY[4*(k-1)+2]) {
                        isGood = false;
                        break;
                    }




                    // left-up & right-up
                    tempA = (pointsY[4*(k-1)+3] - pointsY[4*(k-1)+2]) /  (pointsX[4*(k-1)+3] - pointsX[4*(k-1)+2]);
                    tempB = pointsY[i] - a*pointsX[i];
                    if (tempA == a) {
                        isGood = false;
                        break;
                    }
                    // peresechenie
                    x = (tempB - b)/(a - tempA);
                    y = a*x+b;
                    if (pointsX[4*(k-1)+2] < x && x < pointsX[4*(k-1)+3]) {
                        isGood = false;
                        break;
                    }
                    // right-up & right-down
//                    tempA = (pointsY[4*(k-1)+4] - pointsY[4*(k-1)+3]) /  (pointsX[4*(k-1)+4] - pointsX[4*(k-1)+3]);
//                    tempB = pointsY[i] - a*pointsX[i];
//                    if (tempA == a) {
//                        isGood = false;
//                        break;
//                    }
//                    // peresechenie
//                    x = (tempB - b)/(a - tempA);
//                    y = a*x+b;
//                    if (pointsY[4*(k-1)+4] < y && y < pointsY[4*(k-1)+3]) {
//                        isGood = false;
//                        break;
//                    }
//

                    if ( pointsY[4*(k-1)+4] < a*pointsX[4*(k-1)+3] + b && a*pointsX[4*(k-1)+3] + b < pointsY[4*(k-1)+3]) {
                        isGood = false;
                        break;
                    }





                    // right-down & left-down
                    tempA = (pointsY[4*(k-1)+1] - pointsY[4*(k-1)+4]) /  (pointsX[4*(k-1)+1] - pointsX[4*(k-1)+4]);
                    tempB = pointsY[i] - a*pointsX[i];
                    if (tempA == a) {
                        isGood = false;
                        break;
                    }
                    // peresechenie
                    x = (tempB - b)/(a - tempA);
                    y = a*x+b;
                    if (pointsX[4*(k-1)+1] < x && x < pointsX[4*(k-1)+4]) {
                        isGood = false;
                        break;
                    }





                }

                // if you are here, it only means, that yo can go from one point to the other
                if (isGood) {
                    ways[i][j] = Math.sqrt((pointsX[i] - pointsX[j]) * (pointsX[i] - pointsX[j]) +
                            (pointsY[i] - pointsY[j]) * (pointsY[i] - pointsY[j]));
                    ways[j][i] = ways[i][j];
                }
            }
        Locale.setDefault(Locale.US);
        for (int i = 0; i < 4*n+2; i++) {
            for (int j = 0; j < 4*n+2; j++)
//                System.out.print(ways[i][j] + " ");
                System.out.format("%.3g ", ways[i][j] );
            System.out.println();
        }
    }
}


