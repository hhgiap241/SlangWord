package vn.edu.hcmus.student.sv19127640.slangword;

import java.io.*;
import java.util.*;

/**
 * vn.edu.hcmus.student.sv19127640.slangword
 * Created by ADMIN
 * Date 12/13/2021 - 8:32 PM
 * Description: Slang Word class
 */
public class SlangWord {
    /**
     * attributes
     */
    private HashMap<String, ArrayList<String>> dictionary;
    private String FILE_NEW_SLANGWORD = "slangword_new.txt";
    private String FILE_ORIGINAL_SLANGWORD = "slang.txt";
    private String FILE_HISTORY = "history.txt";

    /**
     * default constructor
     */
    SlangWord(){
        this.dictionary = new HashMap<String, ArrayList<String>>();
        File f = new File(FILE_NEW_SLANGWORD);
        if(f.exists() && !f.isDirectory()) {
            // if exits new file => read new file to hashmap
            this.readFile(FILE_NEW_SLANGWORD);
        }else{
            // if not exits => read the original file
            this.readFile(FILE_ORIGINAL_SLANGWORD);
        }
    }

    /**
     * function to add a line from slang.txt to hashmap
     * @param line
     */
    public void addLineToDictionary(String line){
        String word[] = line.split("`");
        this.dictionary.put(word[0], new ArrayList<String>());
        if (word[1].contains("|")){
            String[] partOfWord = word[1].split("\\|");
            for (int i = 0; i < partOfWord.length; i++){
                this.dictionary.get(word[0]).add(partOfWord[i].trim());
            }
        }else{
            this.dictionary.get(word[0]).add(word[1].trim());
        }
    }
    public void readFile(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while (true){
                String line = br.readLine();
                if (line != null){
                    this.addLineToDictionary(line);
                }else{
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * find by slangword
     * @param slangWord String
     * @return Object
     */
    public Object findBySlangWord(String slangWord){
        return this.dictionary.get(slangWord);
    }
    /**
     * find slang words by definition
     * @param definition String
     * @return String Matrix
     */
    public String[][] findByDefinition(String definition) {
        List<String> keyList = new ArrayList<>();
        List<String> meaningList = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry : this.dictionary.entrySet()) {
            List<String> meaning = entry.getValue();
            for (int i = 0; i < meaning.size(); i++) {
                if (meaning.get(i).toLowerCase().contains(definition.toLowerCase())) {
                    keyList.add(entry.getKey());
                    meaningList.add(meaning.get(i));
                }
            }
        }
        int size = keyList.size();
        String s[][] = new String[size][2];
        for (int i = 0; i < size; i++) {
            s[i][0] = keyList.get(i);
            s[i][1] = meaningList.get(i);
        }
        return s;
    }
    /**
     * print all the word in hashmap
     */
    public void output(){
        System.out.println(this.dictionary);
    }

    /**
     * get the dictionary hashmap
     * @return hashmap
     */
    public HashMap<String, ArrayList<String>> getDictionary() {
        return dictionary;
    }
    /**
     * get the name new slangword.txt
     * @return String
     */
    public String getFILE_NEW_SLANGWORD() {
        return FILE_NEW_SLANGWORD;
    }
    /**
     * get the name of original slangword.txt
     * @return String
     */
    public String getFILE_ORIGINAL_SLANGWORD() {
        return FILE_ORIGINAL_SLANGWORD;
    }
    /**
     * get the name of history.txt (history file)
     * @return String
     */
    public String getFILE_HISTORY() {
        return FILE_HISTORY;
    }
}
