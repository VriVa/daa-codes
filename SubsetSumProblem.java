
import java.util.ArrayList;
import java.util.List;
public class SubsetSumProblem {

static void subsetSum(int [] array, int index, List<Integer> current,int currentSum, int target){
    if (currentSum == target){
        System.out.println(current);//print the current subset
        return;
    }

    if (index>= array.length || currentSum>target){
        return; //base case
    }

    current.add(array[index]);
    subsetSum(array, index+1, current, currentSum + array[index], target);

    current.remove(current.size()-1);

    //excluse current element and move to next
    subsetSum(array, index+1, current, currentSum, target);

}



    public static void main(String[] args) {
        int [] array = {3,4,5,5,2};
        int target=7;

        System.out.println("Subsets summing to " + target + ":");
        subsetSum(array, 0, new ArrayList<>(), 0, target);
    }
    
}
