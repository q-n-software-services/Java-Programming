//program to remove bad data from an array of data where the connection was lost
package week_2;

/**
 *
 When collecting data, it's common to get some bad data mixed in with the actual data.

 For this program, imagine that you are monitoring your internet speed once an hour.
 Sometimes the connection is lost completely. Due to a bug in the monitoring program,
 when the connection is lost, the speed is recorded as 2,147,483,647 Mbps, (mega bits per second)
 which is much too fast to be a valid speed.

 1-20 Mbps is typical for an average budget home cable internet connection.
 Fiber connections may be 1000Mbs or 1 gigabit.

 ( Question - what's special about 2,147,483,647? Why might the monitoring program record this number in particular? )

 Part 1: Complete the cleanData method to remove bad data from an array of data.
 A value of 2147483647 means the connection was lost, then 0 bytes were transferred.
 So, replace all the 2147483647 values in the array with the number 0.

 Part 2: Complete the graphData method to draw a basic vertical graph of the data.

 */


public class Q6 {

    public static void main(String[] args) {

        // 24 speeds recorded, one per hour, over a 24-hour period
        // Don't modify this array, it represents the original data provided to the program.
        int[] speedsRecorded = { 8, 5, 8, 7, 8, 2147483647, 7, 8, 9, 7, 6, 6, 2147483647, 6, 2147483647, 5, 8, 7, 6, 6, 8, 9, 6, 8};

        cleanData(speedsRecorded);
        graphData(speedsRecorded);

    }

    public static void cleanData(int[] speeds) {

        // Replace any element in the speeds array that has the value 2147483647, with 0.
        for (int i = 0; i < speeds.length; i++) {
            if (speeds[i] == 2147483647) {
                speeds[i] = 0;
            }
        }

    }

    public static void graphData(int[] speeds) {

        // Draw a vertical graph of the data
        int maxSpeed = 0;
        for (int i = 0; i < speeds.length; i++) {
            if (speeds[i] > maxSpeed) {
                maxSpeed = speeds[i];
            }
        }

        for (int i = 0; i < maxSpeed; i++) {
            for (int j = 0; j < speeds.length; j++) {
                if (speeds[j] > i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

}
