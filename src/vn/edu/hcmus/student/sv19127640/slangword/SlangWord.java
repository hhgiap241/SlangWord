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
    private HashMap<String, HashSet<String>> dictionary;
    private HashMap<String, HashSet<String>> reverseDictionary;
    private String FILE_NEW_SLANGWORD = "slangword_new.txt";
    private String FILE_ORIGINAL_SLANGWORD = "slang.txt";
    private String FILE_HISTORY = "history.txt";

    /**
     * default constructor
     */
    SlangWord(){
        this.dictionary = new HashMap<>();
        this.reverseDictionary = new HashMap<>();
//        this.readFromFile();
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
     * @param line String
     */
    public void addLineToDictionary(String line){
        String word[] = line.split("`");
        this.dictionary.put(word[0], new HashSet<>());
        if (word[1].contains("|")){
            String[] partOfWord = word[1].split("\\|");
            for (int i = 0; i < partOfWord.length; i++){
                partOfWord[i] = partOfWord[i].trim();
                this.dictionary.get(word[0]).add(partOfWord[i]);
                String[] token = partOfWord[i].split("\\W");
                for (int j = 0; j < token.length; j++){
                    if (!this.reverseDictionary.containsKey(token[j].toLowerCase())){
                        this.reverseDictionary.put(token[j].toLowerCase(), new HashSet<>());
                    }
                    this.reverseDictionary.get(token[j].toLowerCase()).add(word[0]);
                }
            }
        }else{
            this.dictionary.get(word[0]).add(word[1].trim());
            String[] token = word[1].split("\\W");
            for (int j = 0; j < token.length; j++){
                if (!this.reverseDictionary.containsKey(token[j].toLowerCase())){
                    this.reverseDictionary.put(token[j].toLowerCase(), new HashSet<>());
                }
                this.reverseDictionary.get(token[j].toLowerCase()).add(word[0]);
            }
        }
    }

    /**
     * function to read from file to HashMap
     * @param filename String
     */
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
            this.reverseDictionary.remove("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * find by slangword
     * @param slangWord String
     * @return String[][]
     */
    public String[][] findBySlangWord(String slangWord){
        slangWord = slangWord.trim();
        HashSet<String> values = this.dictionary.get(slangWord);
        if (values == null)
            return null;
        String [][] meanings = new String[values.size()][3];
        Integer index = 0;
        for (String value: values){
            meanings[index][0] = String.valueOf(index + 1);
            meanings[index][1] = slangWord;
            meanings[index][2] = value;
            index++;
        }
//        this.saveToHistory(meanings);
        return meanings;
    }
    /**
     * find slang words by definition
     * @param definition String
     * @return String[][]
     */
    public String[][] findByDefinition(String definition) {
        String[] tokenizer = definition.trim().toLowerCase().split("\\W");
        ArrayList<HashSet<String>> valuesList = new ArrayList<>();
        for (int i = 0; i < tokenizer.length; i++){
            valuesList.add(this.reverseDictionary.get(tokenizer[i]));
            if (i > 0){
                valuesList.get(i).retainAll(valuesList.get(i - 1));
            }
        }
        HashSet<String> keys = valuesList.get(valuesList.size() - 1);
        String[][] meanings = new String[keys.size()][3];
        Integer index = 0;

       for (String key : keys) {
            meanings[index][0] = String.valueOf(index + 1);
            meanings[index][1] = key;
            HashSet<String> values = this.dictionary.get(key);
            boolean flag = false;
            for (String value : values){
                for (String element: tokenizer){
                    if (value.toLowerCase().contains(element.toLowerCase())){
                        meanings[index][2] = value;
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    break;
            }
            index++;
        }
        this.saveToHistory(meanings);
        return meanings;
    }

    /**
     * function to write all searched word to history file
     * @param data String[][]
     */
    public void saveToHistory(String[][] data){
        try {
            FileWriter fileWriter = new FileWriter(FILE_HISTORY);
            for (int i = 0; i < data.length; i++){
                String line = data[i][1] + "`" + data[i][2];
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFromFile(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                                                        new FileInputStream("slag.txt"));
            this.dictionary = (HashMap<String, HashSet<String>>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void saveToFile(){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                                                        new BufferedOutputStream(
                                                                new FileOutputStream("slag.txt")));
            objectOutputStream.writeObject(this.dictionary);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addNewSlangWord(String slag, String meanings){
        String line = slag + "`" + meanings;
        this.addLineToDictionary(line);
        this.saveToFile();
    }
    /**
     * print all the word in hashmap
     */
    public void output(){
        System.out.println(this.dictionary);
        System.out.println("========================================");
//        System.out.println(this.reverseDictionary);
    }

    /**
     * get the dictionary hashmap
     * @return hashmap
     */
    public HashMap<String, HashSet<String>> getDictionary() {
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

    /**
     * get reverse dictionary
     * @return HashMap
     */
    public HashMap<String, HashSet<String>> getReverseDictionary() {
        return reverseDictionary;
    }
}
