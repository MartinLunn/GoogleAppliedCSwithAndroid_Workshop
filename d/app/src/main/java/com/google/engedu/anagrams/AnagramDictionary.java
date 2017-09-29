/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;
import android.util.*;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    // my code
    private ArrayList<String> wordList = new ArrayList<>();
    private HashSet<String> wordSet = new HashSet<>();
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<String, ArrayList<String>>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        String sortedWord;
        Log.d("myTag", "all words:");
        while((line = in.readLine()) != null) {
            String word = line.trim();
            //TODO
            Log.d("myTag", word);
            wordList.add(word);
            sortedWord = sortLetters(word);

            if (!lettersToWord.keySet().contains(sortedWord)) {
                lettersToWord.put(sortedWord, new ArrayList<String>());
                lettersToWord.get(sortedWord).add(word);
                wordSet.add(word);
            }
            else {
                lettersToWord.get(sortedWord).add(word);
            }

        }
    }

    public boolean isGoodWord(String word, String base) {
        if (wordSet.contains(word) && ! word.contains(base)) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < wordList.size(); ++i) {
            if (wordList.get(i).length() == targetWord.length()) {
                if (sortLetters(wordList.get(i)).equals(sortLetters(targetWord))) {
                    result.add(wordList.get(i));
                }
            }
        }

        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {

        ArrayList<String> result = new ArrayList<String>();

        String wordPlusChar = new String(word);

        for(char currChar = 'a'; currChar <= 'z'; currChar++) {

            wordPlusChar = wordPlusChar + currChar;

            if (lettersToWord.keySet().contains(sortLetters(wordPlusChar))) {
                for (String thing : lettersToWord.get(sortLetters(wordPlusChar))) {
                    result.add(thing);
                }
            }
            wordPlusChar = new String(word);
        }

        //TODO
        Log.d("myTag", "results from anagrams with one more letter");
        Log.d("myTag", result.toString());
        return result;
    }

    private String sortLetters(String input) {
        ArrayList<Character> charArr = new ArrayList<Character>();

        for (int i = 0; i < input.length(); ++i) {
            charArr.add(input.charAt(i));
        }

        Collections.sort(charArr);

        String sortedString = new String();

        for (int i = 0; i < charArr.size(); ++i) {
            sortedString = sortedString + charArr.get(i);
        }

        return sortedString;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
