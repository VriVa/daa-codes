import java.util.*;

public class SetCoverProblem {

public static List<Integer> setCover(int[] X, int[][] S) {
// Create a set U containing all elements of universe X
Set<Integer> U = new HashSet<>();

for (int x : X) {
U.add(x);
}

List<Integer> output = new ArrayList<>(); //List to store selected set indices
//Keep picking sets until universe U is empty
while (!U.isEmpty()) {
int maxIntersectionSize = 0;
int selectedSetIdx = -1;
// Find the set that covers the maximum number of uncovered elements
for (int i = 0; i < S.length; i++) {
int intersectionSize = 0;
for (int j = 0; j < S[i].length; j++) {
if (U.contains(S[i][j])) {
intersectionSize++;
}
}
if (intersectionSize > maxIntersectionSize) {
maxIntersectionSize = intersectionSize;

selectedSetIdx = i;
}
}
//If no set can cover any uncovered element, break
if (selectedSetIdx == -1) {
break;
}
//Remove covered elements from U
for (int j = 0; j < S[selectedSetIdx].length; j++) {
U.remove(S[selectedSetIdx][j]);
}
//Add the selected set index to output
output.add(selectedSetIdx);
}
return output;
}


public static void main(String[] args) {
//Universe
int[] X = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

//subsets
int[][] S = {
{1, 2},
{2, 3, 4},
{4, 5, 6},
{6, 7, 8},
{8, 9, 10}
};

System.out.println("Universe (X): " + Arrays.toString(X));
System.out.println("Subsets (S):");

for (int i = 0; i < S.length; i++) {
System.out.println("Set " + i + ": " + Arrays.toString(S[i])); }
System.out.println();

List<Integer> selectedSets = setCover(X, S);

System.out.println("Selected Sets to cover the universe:");

for (int idx : selectedSets) {
System.out.println("Set " + idx + ": " + Arrays.toString(S[idx]));
}
}
}