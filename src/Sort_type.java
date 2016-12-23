/**
 * Created by mgoo on 15/12/16.
 */
public class Sort_type implements Comparable {
    public int number;

    public Sort_type(){
        this.number = (int)(Math.random()*10000/*10000000*/);
    }

    @Override
    public int compareTo(Object o) {
        Sort_type item = (Sort_type)o;
        if (item == null)return 0;
        if (this.number > item.number){
            //System.out.println("one");
            return 1;
        } else if (this.number == item.number) {
            //System.out.println("zero");
            return 0;
        } else {
            //System.out.println("neg one");
            return -1;
        }
    }

    public String toString () {
        return "" + this.number;
    }
}
