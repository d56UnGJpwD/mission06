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
        System.out.println("Num of lines: \n" + lines);

        lexicon = new HashSet<>(lines);
        fill(lines);
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

    public void fill(int maxSize)
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

    @Override
    public List<String> check(String s)
    {
        List<String> list = new ArrayList<>();
        s = s.toLowerCase();
        System.out.println(s + "... ");
        if(lexicon.contains(s) == false)
        {
            for(int i = 0; i < s.length(); i++)
            {
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
                if(i == s.length() - 2)
                {
                    char[] letters = s.toCharArray();
                    letters[i] = 39;
                    String word = new String(letters);
                    if(lexicon.contains(word) == true && list.contains(word) == false)
                    {
                        list.add(word);
                    }
                }

            }
            for(int i = 0; i < s.length() + 1; i++)
            {
                for(char j = 97; j < 123; j++)
                {
                    String word = s.substring(0, i) + j + s.substring(i);
                    if(lexicon.contains(word) == true && list.contains(word) == false)
                    {
                        list.add(word);
                    }
                }

                if(i == s.length() - 1)
                {
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

            if(lexicon.contains(s) == true)
            {
                String word = s.substring(0,1).toUpperCase()+s.substring(1);
                if(lexicon.contains(word) == true && list.contains(word) != true)
                {
                    list.add(word);
                }
            }

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
                System.out.println("Did you mean ");
                for(String str : list)
                {
                    System.out.println(str+", ");
                }
                System.out.println("?\n");
            }
        }
        else
        {
            System.out.println("Found/n");
        }

        return list;
    }



}
