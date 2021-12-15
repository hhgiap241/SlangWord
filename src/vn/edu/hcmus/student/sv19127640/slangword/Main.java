package vn.edu.hcmus.student.sv19127640.slangword;

public class Main {
    public static void main(String[] args) {
        SlangWord slangWord = new SlangWord();
        int randNum = (int) (Math.random() * (slangWord.getSize() - 1));
        System.out.println(randNum);
        String[] result = slangWord.randomSlangWord(randNum);
        for (int i = 0; i < result.length; i++){
            System.out.println(result[i]);
        }
//        slangWord.deleteASlangWord("giap", "nhat dinh phai lam");
//        slangWord.addNewSlangWord("giap", "phai lam|cui dau");
//        slangWord.addNewSlangWord("hhgiap", "giap|hoanghuugiap");
//        `giap|giap123|giap122|hoanghuugiap
//        slangWord.addOverwriteSlangWord("hhgiap", "hoang huu|trang");
//        String[][] s1 = slangWord.findBySlangWord("giap");
//        String[][] s2 = slangWord.findByDefinition("quy khach");
//        String[][] s3 = slangWord.findByDefinition("kinh chao");
//        String[][] s4 = slangWord.findByDefinition("hellu");
//        String[][] s5 = slangWord.findByDefinition("cui dau");
//        String[][] s6 = slangWord.findByDefinition("lam");
//        String[][] s7 = slangWord.findByDefinition("nhat dinh phai lam");
//        String[][] s8 = slangWord.findByDefinition("dau");
//        String[][] s9 = slangWord.findByDefinition("hoang");
//        String[][] s10 = slangWord.findByDefinition("huu");
//        String[][] s11 = slangWord.findByDefinition("trang");
//        System.out.println("1");
//                for (int i =0;i<s1.length;i++){
//            System.out.println(s1[i][0] + " - " + s1[i][1] + " - " + s1[i][2]);
//        }
//        System.out.println("2");
//                for (int i =0;i<s2.length;i++){
//            System.out.println(s2[i][0] + " - " + s2[i][1] + " - " + s2[i][2]);
//        }
//        System.out.println("3");
//        for (int i =0;i<s3.length;i++){
//            System.out.println(s3[i][0] + " - " + s3[i][1] + " - " + s3[i][2]);
//        }
//        System.out.println("4");
//        for (int i =0;i<s4.length;i++){
//            System.out.println(s4[i][0] + " - " + s4[i][1] + " - " + s4[i][2]);
//        }
//        System.out.println("5");
//        for (int i =0;i<s5.length;i++){
//            System.out.println(s5[i][0] + " - " + s5[i][1] + " - " + s5[i][2]);
//        }
//        System.out.println("6");
//        for (int i =0;i<s6.length;i++){
//            System.out.println(s6[i][0] + " - " + s6[i][1] + " - " + s6[i][2]);
//        }
//        System.out.println("7");
//        for (int i =0;i<s7.length;i++){
//            System.out.println(s7[i][0] + " - " + s7[i][1] + " - " + s7[i][2]);
//        }
//        System.out.println("8");
//        for (int i =0;i<s8.length;i++){
//            System.out.println(s8[i][0] + " - " + s8[i][1] + " - " + s8[i][2]);
//        }
//        System.out.println("9");
//        for (int i =0;i<s7.length;i++){
//            System.out.println(s9[i][0] + " - " + s9[i][1] + " - " + s9[i][2]);
//        }
//        System.out.println("10");
//        for (int i =0;i<s10.length;i++){
//            System.out.println(s10[i][0] + " - " + s10[i][1] + " - " + s10[i][2]);
//        }
//        System.out.println("11");
//        for (int i =0;i<s11.length;i++){
//            System.out.println(s11[i][0] + " - " + s11[i][1] + " - " + s11[i][2]);
//        }
//        slangWord.addDuplicateSlangWord("hhgiap", "xinchaohhgiap|giap123");
//        slangWord.output();
//        long startTime = System.nanoTime();
////        slangWord.addOverwriteSlangWord("tttt", "113");
////        slangWord.output();
//        String[][] s = slangWord.findByDefinition("iu nhau");
//        long endTime = System.nanoTime();
//        double timeElapsed = (double) (endTime - startTime) / 1000000;
//        for (int i =0;i<s.length;i++){
//            System.out.println(s[i][0] + " - " + s[i][1] + " - " + s[i][2]);
//        }
////        slangWord.saveToHistory(s);
//        System.out.println(timeElapsed + "ms");
    }
}
