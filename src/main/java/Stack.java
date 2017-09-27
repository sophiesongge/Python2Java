import java.util.LinkedList;
import java.util.List;

/**
 * @author Sophie Song
 */


public class Stack {
    private List<String> internalList = new LinkedList<>();

    public void push(String element){
        internalList.add(0, element);
    }

    public String pop(){
        return internalList.remove(0);
    }

    public List<String> getInternalList(){
        return internalList;
    }

    public void pushAll(List<String> elements){
        for(String e : elements){
            this.push(e);
        }
    }
}
