package prototype;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

interface MarkovTextGenerator {
    void train(String sourceText);

    String suggestNextWords(String inputWord);

    void retrain(String sourceText);
}

class ListNode {
    String word;
    List<String> nextWords;

    public ListNode(String word) {
        this.word = word;
        this.nextWords = new ArrayList<>();
    }

    public void addNextWord(String nextWord) {
        nextWords.add(nextWord);
    }

    public String getRandomNextWord() {
        if (nextWords.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        return nextWords.get(rand.nextInt(nextWords.size()));
    }
}

public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

    private List<ListNode> wordList;
    private String starter;
    private List<String> selectedWords;

    public MarkovTextGeneratorLoL() {
        this.wordList = new ArrayList<>();
        this.starter = "";
        this.selectedWords = new ArrayList<>();
    }

    @Override
    public void train(String sourceText) {
        String[] words = sourceText.split("\\s+");
        if (words.length == 0) {
            return;
        }

        starter = words[0];
        String prevWord = starter;

        for (int i = 1; i <= words.length; i++) {
            String currentWord = (i < words.length) ? words[i] : starter;

            ListNode prevNode = findNode(prevWord);
            if (prevNode != null) {
                prevNode.addNextWord(currentWord);
            } else {
                ListNode newNode = new ListNode(prevWord);
                newNode.addNextWord(currentWord);
                wordList.add(newNode);
            }

            prevWord = currentWord;
        }

        ListNode lastNode = findNode(words[words.length - 1]);
        if (lastNode != null) {
            lastNode.addNextWord(starter);
        } else {
            ListNode newNode = new ListNode(words[words.length - 1]);
            newNode.addNextWord(starter);
            wordList.add(newNode);
        }
    }

    @Override
    public String suggestNextWords(String inputWord) {
        ListNode node = findNode(inputWord);
        if (node != null) {
            return String.join(", ", node.nextWords);
        } else {
            return "No suggestions available for the given word.";
        }
    }

    @Override
    public void retrain(String sourceText) {
        wordList.clear();
        starter = "";
        train(sourceText);
    }

    private ListNode findNode(String word) {
        for (ListNode node : wordList) {
            if (node.word.equalsIgnoreCase(word)) {
                return node;
            }
        }
        return null;
    }

    public void trainFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
            train(text.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MarkovTextGeneratorLoL generator = new MarkovTextGeneratorLoL();
        generator.trainFromFile("E:\\Prototype\\src\\prototype\\file.txt");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a word (or 'exit' to quit): ");
            String inputWord = scanner.nextLine();

            if (inputWord.equalsIgnoreCase("exit")) {
                System.out.println("Selected words: " + String.join(", ", generator.selectedWords));
                break;
            }

            String suggestions = generator.suggestNextWords(inputWord);
            System.out.println("Suggestions: " + suggestions);

            generator.selectedWords.add(inputWord);
        }

        scanner.close();
    }
}
