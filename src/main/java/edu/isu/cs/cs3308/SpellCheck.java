package edu.isu.cs.cs3308;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import edu.isu.cs.cs3308.structures.HashSet;

import java.util.ArrayList;
import java.util.List;

public class SpellCheck implements SpellChecker
{

    private HashSet<String> lexicon;
    private String file;

    public SpellCheck(String file)
    {
        this.file = file;
        int lines = getNumLines();
        //System.out.println("Num of lines: \n" + lines);

        lexicon = new HashSet<>(lines);
        fill();
    }

    public int getNumLines()
    {
        BufferedReader br;
        int size = 0;
        try
        {
            br = new BufferedReader(new FileReader(file));
            while(br.readLine() != null)
            {
                size++;
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("IOException\n");
        }
        return size;
    }

    //fill reads the file and adds it to the hashset called lexicon
    public void fill()
    {
        BufferedReader br;
        try
        {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line != null)
            {
                lexicon.add(line);
                line = br.readLine();
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("IOException\n");
        }
    }

    //I used http://www.asciitable.com/ for checking my ascii

    @Override
    public List<String> check(String s)
    {
        boolean noMistakes = true;
        List<String> list = new ArrayList<>();
        s = s.toLowerCase();
        if(lexicon.contains(s) == false)
        {
            for(int i = 0; i < s.length(); i++)
            {
                //start at letter a and end right before { (z)
                for(char j = 97; j < 123; j++)
                {
                    char[] letters = s.toCharArray();
                    letters[i] = j;
                    String word = new String(letters);
                    if(lexicon.contains(word) == true && list.contains(word) == false)
                    {
                        list.add(word);
                    }
                }
                //the position apostrophe would be in
                //this is only checking for words like don't, can't nothing crazy like Kaajh'Kaalbh
                if(i == s.length() - 2)
                {
                    char[] letters = s.toCharArray();
                    //apostrophe is 39
                    letters[i] = 39;
                    String word = new String(letters);
                    if(lexicon.contains(word) == true && list.contains(word) == false)
                    {
                        list.add(word);
                    }
                }

            }
            // forgot to handle possible fixes from 1 letter previous example: saad wasnt showing sad
            // fixed now
            for(int i = 0; i < s.length(); i++)
            {
                String word = s.substring(0, i) + s.substring(i + 1);
                if(lexicon.contains(word) == true && list.contains(word) == false)
                {
                    list.add(word);
                }
            }

            for(int i = 0; i < s.length() + 1; i++)
            {
                //checks with one letter added from a - z
                for(char j = 97; j < 123; j++)
                {
                    String word = s.substring(0, i) + j + s.substring(i);
                    if(lexicon.contains(word) == true && list.contains(word) == false)
                    {
                        list.add(word);
                    }
                }
                // '
                if(i == s.length() - 1)
                {
                    //cast ascii ' as a char
                    String word = s.substring(0,i) + (char)39 + s.substring(i);
                    if(lexicon.contains(word) == true && list.contains(word) == false)
                    {
                        list.add(word);
                    }
                }

            }

            for(int i = 0; i < s.length() - 1; i++)
            {
                char[] letters = s.toCharArray();
                char temp = letters[i];
                letters[i] = letters[i + 1];
                letters[i+1] = temp;
                String word = new String(letters);
                if(lexicon.contains(word) == true && list.contains(word) == false)
                {
                    list.add(word);
                }
            }
            //capitalization handling
            if(lexicon.contains(s) == true)
            {
                String word = s.substring(0,1).toUpperCase()+s.substring(1);
                if(lexicon.contains(word) == true && list.contains(word) != true)
                {
                    list.add(word);
                }
            }
            //uppercase handling
            if(lexicon.contains(s) == true)
            {
                String word = s.toUpperCase();
                if(lexicon.contains(word) == true && list.contains(word) == false)
                {
                    list.add(word);
                }
            }

            else
            {
                System.out.println("Misspelled Word: " + s);
                System.out.print("Did you mean: ");
                int k = 0;
                for(String str : list)
                {
                    if(k < 4)
                    {
                        System.out.print(str+", ");
                        k++;
                    }
                    if(k > 4 && k < 5)
                    {
                        System.out.print(str);
                        k++;
                    }
                }
                System.out.print("?\n");
                noMistakes = false;
            }
        }
        //my no mistakes boolean needs to have a counter with it so it only prints once.
        if(noMistakes == true)
        {
            System.out.println("Correct spelling");
        }

        return list;
    }



}
