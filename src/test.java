import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgoo on 15/12/16.
 */
public class test {

    public test(){
        List<Sort_type> list = new ArrayList<Sort_type>();
        //for (int i = 0; i < 524288; i++){
        for (int i = 0; i < 500000; i++) {
            list.add(new Sort_type());
        }
        new merge_sort<Sort_type>(list);
    }


    public static void main(String[] args) {
        new test();
    }
}
