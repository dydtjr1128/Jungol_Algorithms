import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static class People {
        People(int s, int e) {
            this.s = s;
            this.e = e;
        }

        int s;
        int e;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int len = Integer.parseInt(reader.readLine());
        String temp[];
        ArrayList<People> peoples = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            temp = reader.readLine().split(" ");
            peoples.add(new People(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }
        Collections.sort(peoples,new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                if (o1.s > o2.s)
                    return 1;
                else if (o1.s < o2.s)
                    return -1;
                else
                    return 0;
            }
        });

        int max = 0, empty = 0;

        int s = peoples.get(0).s;
        int e = peoples.get(0).e;
        People tPeople;
        for (int i = 1; i < peoples.size(); i++) {
            tPeople = peoples.get(i);
            if (tPeople.s <= e) {
                if(tPeople.e > e){
                    e = tPeople.e;
                    if(e-s > max)
                        max = e-s;
                }
            } else {
                if(tPeople.s - e > empty) {
                    empty = tPeople.s - e;
                    e = tPeople.e;
                    s = tPeople.s;
                }
                if(max < e-s) {
                    max = e - s;
                    s = tPeople.s;
                    e = tPeople.e;
                }


            }
        }
        writer.write(String.valueOf(max));
        writer.write(" ");
        writer.write(String.valueOf(empty));
        writer.flush();
        writer.close();
    }
}