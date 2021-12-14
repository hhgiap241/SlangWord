package vn.edu.hcmus.student.sv19127640.slangword;

public class Main {
    public static void main(String[] args) {

        long startTime = System.nanoTime();
        SlangWord slangWord = new SlangWord();
        slangWord.output();
//        slangWord.addNewSlangWord("giap", "hhgiap| hahagiap|Hoang Huu Giap");
//        String[][] s = slangWord.findBySlangWord("giap");
        long endTime = System.nanoTime();
        double timeElapsed = (double) (endTime - startTime) / 1000000;
//        for (int i =0;i<s.length;i++){
//            System.out.println(s[i][0] + " - " + s[i][1] + " - " + s[i][2]);
//        }
        System.out.println(timeElapsed + "ms");
    }
}
