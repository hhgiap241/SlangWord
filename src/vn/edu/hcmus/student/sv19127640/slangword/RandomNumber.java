package vn.edu.hcmus.student.sv19127640.slangword;

import java.util.Random;

/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/18/2021 - 3:43 PM
 * Description: random number class
 */
public class RandomNumber {
    /**
     * random number from [min; max]
     * @param min int
     * @param max int
     * @return int
     */
    public int random(int min, int max){
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }
}
