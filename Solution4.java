import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by jang-yongsu on 2018. 7. 25..
 */
public class Solution4 {

    static class Node{
        int x;
        int y;
        Node(int y, int x){
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode() {
            return (String.valueOf(x)+"_"+String.valueOf(y)).hashCode();
        }
        @Override
        public boolean equals(Object obj) {
            Node node = (Node)obj;
            if(this.x == node.x && this.y == node.y){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());
        int s = Integer.parseInt(sc.nextLine());

        int[][] timeMap = new int[m][n];

        for(int i=0; i<m; i++){
            String[] strs = sc.nextLine().split(" ");
            for(int j=0; j<n; j++){
                timeMap[i][j] = Integer.parseInt(strs[j]);
            }
        }

        long[] result = getAnswer(m,n,s,timeMap);
        System.out.printf("%d %d\n",result[0], result[1]);
    }

    public static long[] getAnswer(int m, int n, int s, int[][] timeMap){
        int[][] pathLen = new int[m][n];
        long[][] pathSum = new long[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                pathSum[i][j] = Long.MAX_VALUE;
            }
        }

        pathLen[0][0] = 0;
        pathSum[0][0] = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0,0));
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                Node node = queue.poll();
                //left
                if(node.x != 0 && timeMap[node.y][node.x-1] != -1){
                    long updateVal = pathSum[node.y][node.x] + timeMap[node.y][node.x-1];
                    int updateLen = pathLen[node.y][node.x] + 1;
                    if(updateVal < pathSum[node.y][node.x-1] && updateVal <= s){

                        Node updateNode = new Node(node.y, node.x-1);
                        pathSum[node.y][node.x-1] = updateVal;
                        pathLen[node.y][node.x-1] = updateLen;
                        if(!queue.contains(updateNode)){
                            queue.offer(updateNode);
                        }
                    }
                }
                //right
                if(node.x != n-1 && timeMap[node.y][node.x+1] != -1){
                    long updateVal = pathSum[node.y][node.x] + timeMap[node.y][node.x+1];
                    int updateLen = pathLen[node.y][node.x] + 1;
                    if(updateVal < pathSum[node.y][node.x+1] && updateVal <= s){
                        Node updateNode = new Node(node.y, node.x+1);
                        pathSum[node.y][node.x+1] = updateVal;
                        pathLen[node.y][node.x+1] = updateLen;
                        if(!queue.contains(updateNode)){
                            queue.offer(updateNode);
                        }
                    }
                }
                //up
                if(node.y != 0 && timeMap[node.y-1][node.x] != -1){
                    long updateVal = pathSum[node.y][node.x] + timeMap[node.y-1][node.x];
                    int updateLen = pathLen[node.y][node.x] + 1;
                    if(updateVal < pathSum[node.y-1][node.x] && updateVal <= s){
                        Node updateNode = new Node(node.y-1, node.x);
                        pathSum[node.y-1][node.x] = updateVal;
                        pathLen[node.y-1][node.x] = updateLen;
                        if(!queue.contains(updateNode)){
                            queue.offer(updateNode);
                        }
                    }
                }

                //down
                if(node.y != m-1 && timeMap[node.y+1][node.x] != -1){
                    long updateVal = pathSum[node.y][node.x] + timeMap[node.y+1][node.x];
                    int updateLen = pathLen[node.y][node.x] + 1;
                    if(updateVal < pathSum[node.y+1][node.x] && updateVal <= s){
                        Node updateNode = new Node(node.y+1, node.x);
                        pathSum[node.y+1][node.x] = updateVal;
                        pathLen[node.y+1][node.x] = updateLen;
                        if(!queue.contains(updateNode)){
                            queue.offer(updateNode);
                        }
                    }
                }
            }
        }

        long[] result = new long[]{
                pathLen[m-1][n-1],pathSum[m-1][n-1]
        };
        return result;
    }
}

