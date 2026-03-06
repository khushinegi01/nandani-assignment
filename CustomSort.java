import java.util.ArrayList;

public class CustomSort implements SortingInterface {

    private ArrayList<Double> values = new ArrayList<>();
    private ArrayList<Integer> gaps = new ArrayList<>();


    public void setValues(ArrayList<Double> values) {
        this.values = values;
        sort();
    }


    public ArrayList<Integer> getGaps() {
        return gaps;
    }


    public void add(Double value) {
        values.add(value);
        sort();
    }


    public void remove(int index) {
        if(index >= 0 && index < values.size()) {
            values.remove(index);
        }
    }


    public void sort() {

        int n = values.size();

        // calculate gaps
        gaps = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        int gap = 1;
        int i = 2;

        while (gap < n) {
            temp.add(gap);
            gap = (int)Math.pow(2, i) - 1;
            i++;
        }

        for (int j = temp.size() - 1; j >= 0; j--) {
            gaps.add(temp.get(j));
        }


        // shell sort using gaps
        for (int g = 0; g < gaps.size(); g++) {

            int currentGap = gaps.get(g);

            for (int i1 = currentGap; i1 <= n - 1; i1++) {

                double tempValue = values.get(i1);
                int j = i1;

                while (j >= currentGap &&
                       values.get(j - currentGap) > tempValue) {

                    values.set(j, values.get(j - currentGap));
                    j = j - currentGap;
                }

                values.set(j, tempValue);
            }
        }
    }
}