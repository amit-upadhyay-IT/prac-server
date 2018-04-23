public class KMeans {
     public static final int CLUSTERS = 3;
     public static void main(String[] args) {
      int[] data = {1,8,9,3,10,12,-16,-2,-1,0,1,-3};
      int length = data.length;
      int[][] sums = new int[CLUSTERS][length];
      int[][] centroids = {{0, 0, 0}, {1, 8, 9}};
      int[] count = new int[CLUSTERS];
      int i, j, k;
      long minimum, difference;
     boolean converged = false;
 do {
          for(i = 0; i< CLUSTERS; i++) {
          centroids[0][i] = centroids[1][i];
                count[i] = 0;
                centroids[1][i] = 0;
            }
            for(i = 0; i< length; i++) {
            sums[0][i] = 0;
            minimum = centroids[0][0] > data[i] ? centroids[0][0] - data[i] : data[i] - centroids[0][0];
                k = 0;
for(j = 1; j < CLUSTERS; j++) {
                sums[j][i] = 0;
               difference = centroids[0][j] > data[i] ? centroids[0][j] - data[i] : data[i] - centroids[0][j];
                      if(difference < minimum) {
                      minimum = difference;
                       k = j;
                     }
                }
               sums[k][i] = data[i];
               count[k]++;
            }
converged = true;
            for(i = 0; i< CLUSTERS; i++) {
            difference = 0;
            if(count[i] > 0) {
             for(j = 0; j < length; j++) {
                        centroids[1][i] += sums[i][j] / count[i];
                        difference += sums[i][j] % count[i];
                        centroids[1][i] += difference / count[i];
                        difference %= count[i];
                     }
                }
            converged&= centroids[0][i] == centroids[1][i];
             }
        }while(!converged);
        for(i = 0; i< CLUSTERS; i++) {
         System.out.println("centroid "+ (i+1) +" is:"+centroids[1][i]);
        }
     }
 }
