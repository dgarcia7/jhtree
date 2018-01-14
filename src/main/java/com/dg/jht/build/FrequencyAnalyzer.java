package com.dg.jht.build;

import java.util.Map;
import java.util.HashMap;

/**
 * Analyze occurrences of individual symbols, stored
 * as java {@link String}s, and store the relevant
 * occurrences in a {@link Map}.
 */
public class FrequencyAnalyzer
{
    private StringBuilder stringBuilder = new StringBuilder();

    /**
     * Create a new {@link FrequencyAnalyzer} with the given {@link String}.
     * 
     * @param input
     */
    public FrequencyAnalyzer(String input)
    {
        stringBuilder.append(input);
    }

    /**
     * Append the internal {@link String} with additional
     * characters, which will be analyzed with the
     * next call to generateFrequencyMap().
     * 
     * @param input
     */
    public void append(String input)
    {
        stringBuilder.append(input);
    }

    /**
     * Generate a frequency {@link Map}, which links
     * a symbol (stored as a {@link String}) to a
     * {@link Long} frequency/occurrence counter.
     * 
     * @return
     */
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