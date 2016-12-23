/**
 * Created by mgoo on 10/12/16.
 */
public class SectionsNotAdjacentException extends Exception {
    public SectionsNotAdjacentException(){
        super("The two sections that were passed are not adjacet and shouldnt be combined");
    }
}
