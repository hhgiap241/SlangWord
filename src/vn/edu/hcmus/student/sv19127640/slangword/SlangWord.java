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
    private String FILE_NEW_SLANGWORD_1 = "map_1.dat";
    private String FILE_NEW_SLANGWORD_2 = "map_2.dat";
    private String FILE_ORIGINAL_SLANGWORD = "slang.txt";
    private String FILE_HISTORY = "history.txt";

    /**
     * default constructor
     */
    public SlangWord(){
        this.dictionary = new HashMap<>();
        this.reverseDictionary = new HashMap<>();
        /* ======================= Tham khảo ======================= */
        File f = new File(FILE_NEW_SLANGWORD_1);
        if(f.exists() && !f.isDirectory()) {
            // if exits new file => read new file to hashmap
            readSerializeFile();
        }else{
            // if not exits => read the original file
            this.readFile(FILE_ORIGINAL_SLANGWORD);
        }
        /* ======================= Tham khảo ======================= */
    }

    /**
     * function to add a line from slang.txt to hashmap
     * @param line String
     */
    public void addLineToDictionary(String line){
        String word[] = line.split("`");
        word[0] = word[0].trim();
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
    public void readSerializeFile(){
        try {
            ObjectInputStream objectInputStream_1 = new ObjectInputStream(new FileInputStream(FILE_NEW_SLANGWORD_1));
            ObjectInputStream objectInputStream_2 = new ObjectInputStream(new FileInputStream(FILE_NEW_SLANGWORD_2));
            dictionary = (HashMap)objectInputStream_1.readObject();
            reverseDictionary = (HashMap)objectInputStream_2.readObject();
            objectInputStream_1.close();
            objectInputStream_2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void saveSerializeFile(){
        try {
            ObjectOutputStream objectOutputStream_1 = new ObjectOutputStream(new FileOutputStream(FILE_NEW_SLANGWORD_1));
            objectOutputStream_1.writeObject(dictionary);
            ObjectOutputStream objectOutputStream_2 = new ObjectOutputStream(new FileOutputStream(FILE_NEW_SLANGWORD_2));
            objectOutputStream_2.writeObject(reverseDictionary);
            objectOutputStream_1.close();
            objectOutputStream_2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
    /**
     * function to read from file to HashMap
     * @param filename String
     */
    public void readFile(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine(); // skip the first line
            while (true){
                line = br.readLine();
                if (line != null ){
                    this.addLineToDictionary(line);
                }else{
                    break;
                }
            }
            this.reverseDictionary.remove("");
            br.close();
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
        saveToHistory(meanings);
        return meanings;
    }
    /**
     * find slang words by definition
     * @param definition String
     * @return String[][]
     */
    public String[][] findByDefinition(String definition) {
        String[] tokenizer = definition.trim().toLowerCase().split("\\W");
        if (tokenizer.length == 0)
            return null;
        ArrayList<HashSet<String>> valuesList = new ArrayList<>();
        for (int i = 0; i < tokenizer.length; i++){
            valuesList.add(this.reverseDictionary.get(tokenizer[i]));
            if (i > 0 && valuesList.get(i - 1) != null && valuesList.get(i) != null){
                valuesList.get(i).retainAll(valuesList.get(i - 1));
            }
        }
        HashSet<String> keys = valuesList.get(valuesList.size() - 1);
        if (keys == null)
            return null;
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
        return meanings;
    }

    /**
     * function to write all searched word to history file
     * @param data String[][]
     */
    public void saveToHistory(String[][] data){
        try {
            FileWriter fileWriter = new FileWriter(FILE_HISTORY, true);
            for (int i = 0; i < data.length; i++){
                String line = data[i][1] + "`" + data[i][2];
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read data from history file
     * @return Vector<Vector<String>>
     */
    public Vector<Vector<String>> readFromHistory(){
        Vector<Vector<String>> values = new Vector<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_HISTORY));
            String line = null;
            Integer index = 1;
            while(true){
                line = bufferedReader.readLine();
                if (line != null){
                    String[] token = line.split("`");
                    Vector<String> data = new Vector<>();
                    data.add(index.toString());
                    data.add(token[0]);
                    data.add(token[1]);
                    index++;
                    values.add(data);
                }else
                    break;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
//
//    /**
//     * save dictionary to file
//     */
//    public void saveToFile(){
//        try {
//            FileWriter fileWriter = new FileWriter(FILE_NEW_SLANGWORD);
//            fileWriter.write("Slag`Meaning" + "\n");
//            for (Map.Entry<String, HashSet<String>> entry : this.dictionary.entrySet()) {
//                String line = entry.getKey() + "`";
//                HashSet<String> meanings = entry.getValue();
//                Iterator iterator = meanings.iterator();
//                while (iterator.hasNext()){
//                    line += iterator.next();
//                    if (iterator.hasNext())
//                        line += "|";
//                }
//                fileWriter.write(line + "\n");
//            }
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * check if the slag is exist or not
     * @param slag String
     * @return boolean
     */
    public boolean isExistedSlag(String slag){
        if (this.dictionary.containsKey(slag))
            return true;
        return false;
    }
    /**
     * add a slang word with multiple meanings (new slang word that doesn't exist before)
     * @param slag String
     * @param meanings String
     */
    public void addNewSlangWord(String slag, String meanings){
        String line = slag + "`" + meanings;
        this.addLineToDictionary(line);
//        this.saveToFile();
    }

    /**
     * add list of word to the reverse hashmap
     * @param token String[]
     * @param slag String
     */
    public void addTokenToReverseDictionary(String[] token, String slag){
        for (int j = 0; j < token.length; j++){
            if (!this.reverseDictionary.containsKey(token[j].toLowerCase())){
                this.reverseDictionary.put(token[j].toLowerCase(), new HashSet<>());
            }
            this.reverseDictionary.get(token[j].toLowerCase()).add(slag);
        }
    }
    /**
     * add a slang word with multiple meanings (overwrite)
     * @param slag String
     * @param meanings String
     */
    public void addOverwriteSlangWord(String slag, String meanings){
        HashSet<String> values = this.dictionary.get(slag);
        String[] partOfWords = meanings.split("\\|");
        for (String value: values){
            String[] token = value.toLowerCase().split("\\W");
            for (int i = 0; i < token.length; i++)
                if (!token[i].equals(""))
                    this.reverseDictionary.get(token[i]).remove(slag);
        }
        values.clear();
        for (int i = 0; i < partOfWords.length; i++){
            partOfWords[i] = partOfWords[i].trim();
            values.add(partOfWords[i]);
            String[] token = partOfWords[i].split("\\W");
            this.addTokenToReverseDictionary(token, slag);
        }
        this.dictionary.put(slag, values);
//        this.saveToFile();
    }

    /**
     * add a slang word with multiple meanings (duplicate)
     * @param slag String
     * @param meanings String
     */
    public void addDuplicateSlangWord(String slag, String meanings){
        HashSet<String> values = this.dictionary.get(slag);
        String[] partOfWords = meanings.split("\\|");
        for (int i = 0; i < partOfWords.length; i++){
            partOfWords[i] = partOfWords[i].trim();
            values.add(partOfWords[i]);
            String[] token = partOfWords[i].split("\\W");
            this.addTokenToReverseDictionary(token, slag);
        }
        this.dictionary.put(slag, values);
//        this.saveToFile();
    }

    /**
     * edit a slag word
     * @param slag String
     * @param oldMeaning String
     * @param newMeaning String
     */
    public void editSlangWord(String slag, String oldMeaning, String newMeaning){
        String [] old_token = oldMeaning.toLowerCase().split("\\W");
        for (int i = 0; i < old_token.length; i++){
            if (!old_token[i].equals(""))
                this.reverseDictionary.get(old_token[i]).remove(slag);
        }
        this.dictionary.get(slag).remove(oldMeaning);
        this.dictionary.get(slag).add(newMeaning);
        String [] new_token = newMeaning.toLowerCase().split("\\W");
        this.addTokenToReverseDictionary(new_token, slag);
    }

    /**
     * delete a meaning from slang word
     * @param slag String
     * @param meaning String
     */
    public void deleteASlangWord(String slag, String meaning){
        String [] old_token = meaning.toLowerCase().split("\\W");
        for (int i = 0; i < old_token.length; i++){
            if (!old_token[i].equals(""))
                this.reverseDictionary.get(old_token[i]).remove(slag);
        }
        HashSet<String> meanings = this.dictionary.get(slag);
        if (meanings.size() == 1) // if the slag has only one meaning => delete it
            this.dictionary.remove(slag);
        else{ // delete the meaning only
            meanings.remove(meaning);
            this.dictionary.put(slag, meanings);
        }
    }

    /**
     * reset the dictionary to the original
     */
    public void reset(){
        this.dictionary.clear();
        this.reverseDictionary.clear();
        this.readFile(FILE_ORIGINAL_SLANGWORD);
        this.saveSerializeFile();
//        this.saveToFile();
    }

    /**
     * get the first meaning of the slag
     * @param slag String
     * @return String
     */
    public String getFirstMeaning(String slag){
        String meaning = "";
        HashSet<String> values = this.dictionary.get(slag);
        for (String value: values){
            meaning = value;
            break;
        }
        return meaning;
    }


    /**
     * randomly get a slang word
     * @param number int
     * @return String[]
     */
    public ArrayList randomSlangWord(int number){
        HashSet<String> result = new HashSet<>();
        ArrayList<String> keys = new ArrayList<>(this.dictionary.keySet());
        String key = keys.get(number);
        result = this.dictionary.get(key);
        ArrayList<String> values = new ArrayList<>(result);
        values.add(key);
        return values;
    }

    /**
     * create random quiz with slang word
     * @param number int
     * @return String[]
     */
    public String[] quizWithSlangWord(int number){
        String [] result = new String[5];
        ArrayList<String> keys = new ArrayList<>(this.dictionary.keySet());
        result[0] = keys.get(number);
        // generate answers
        result[1] = this.getFirstMeaning(result[0]);
        result[2] = this.getFirstMeaning(keys.get(number - 1));
        result[3] = this.getFirstMeaning(keys.get(number + 1));
        result[4] = this.getFirstMeaning(keys.get(number + 2));
        return result;
    }


    /**
     * create random quiz with the definition
     * @param number int
     * @return String[]
     */
    public String[] quizWithDefinition(int number){
        String [] result = new String[5];
        ArrayList<String> keys = new ArrayList<>(this.dictionary.keySet());
        String key =  keys.get(number);
        // generate answers
        result[0] = this.getFirstMeaning(key);
        result[1] = key;
        result[2] = keys.get(number - 1);
        result[3] = keys.get(number + 1);
        result[4] = keys.get(number + 2);
        return result;
    }

    /**
     * concate the meanings of one slang word
     * @param result ArrayList
     * @return String
     */
    public String concateMeanings(ArrayList<String> result) {
        String meanings = "";
        for (int i = 0; i < result.size() - 2; i++){
            meanings += result.get(i);
            meanings += " - ";
        }
        meanings += result.get(result.size() - 2);
        return meanings;
    }
    /**
     * print all the word in hashmap
     */
    public void output(){
        System.out.println(this.dictionary);
        System.out.println("========================================");
        System.out.println(this.reverseDictionary);
    }

    /**
     * get the dictionary hashmap
     * @return hashmap
     */
    public HashMap<String, HashSet<String>> getDictionary() {
        return dictionary;
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

    /**
     * get size of the dictionary
     * @return integer
     */
    public int getSize(){
        return this.dictionary.size();
    }
}
