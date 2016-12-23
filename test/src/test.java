import java.util.*;

/**
 * Created by mgoo on 15/12/16.
 */
public class test {
    int[] unsorted = {1,2,9,4,7,8};
    int start = 0, end = 2, start2 = 3, end2 = 5;
    
    public test(){
        int c1 = this.start;
        int c2 = this.start2;
        List<Integer> sorted = new ArrayList<Integer>();
        while (sorted.size() <= (end2-start)){
            if (c1 == -1 && c2 == -1){
                break;
            }
            if (c2 != -1 && (c1 == -1 || unsorted[c1] > unsorted[c2])){
                sorted.add(unsorted[c2]);
                c2++;
                if (c2 > end2)c2 = -1;
            } else {
                sorted.add(unsorted[c1]);
                c1++;
                if (c1 > end)c1 = -1;
            }
        }
        for (int i = 0; i < sorted.size(); i++){
            unsorted[start+i] = sorted.get(i);
            System.out.println(sorted.get(i));
        }

        /*List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 500000; i++){
            list.add((int)(Math.random() * 1000000));
        }
        long start_time = System.currentTimeMillis();
        Collections.sort(list);
        long end_time = System.currentTimeMillis();
        System.out.println(end_time - start_time);*/
        
    }

    public static void main(String[] args) {
        new test();
    }
}
