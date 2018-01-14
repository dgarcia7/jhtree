package com.dg.jht.build;

import java.util.Map;
import java.util.HashMap;

public class FrequencyAnalyzer
{
    private StringBuilder stringBuilder = new StringBuilder();

    public FrequencyAnalyzer(String input)
    {
        stringBuilder.append(input);
    }

    public void append(String input)
    {
        stringBuilder.append(input);
    }

    public Map<String,Long> generateFrequencyMap()
    {
        Map<String,Long> map = new HashMap<String,Long>();

        String data = stringBuilder.toString();

        for(int c = 0; c < data.length(); c++)
        {
            String symbol = String.valueOf(data.charAt(c));
            Long frequency = (long)1;

            if (map.containsKey(symbol))
            {
                frequency = map.get(symbol)+1;
            }

            map.put(symbol,frequency);
        }

        return map;
    }
}