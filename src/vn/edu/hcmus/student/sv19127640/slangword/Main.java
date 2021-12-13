package vn.edu.hcmus.student.sv19127640.slangword;

public class Main {
    public static void main(String[] args) {
        // count time
//        long startTime = System.nanoTime();
//        long endTime = System.nanoTime();
//        double timeElapsed = (double) (endTime - startTime) / 1000000000;
//        System.out.println(timeElapsed + "s");
        SlangWord slangWord = new SlangWord();

        String[][] s = slangWord.findByDefinition("love");
        for (int i = 0; i < s.length; i++){
            System.out.println(s[i][0] + " - "+ s[i][1]);
        }
        long startTime = System.nanoTime();
        Object definitions = slangWord.findBySlangWord("ô");
        long endTime = System.nanoTime();
        double timeElapsed = (double) (endTime - startTime) / 1000000;
        System.out.println(timeElapsed + "s");
        System.out.println(definitions);
    }
}
