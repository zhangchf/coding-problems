package questions;

import java.io.*;

public class ParseIntegerXiaoping {

    public static int parseInteger(String s) {
        char[] chars = s.toCharArray();

        int i = 0;
        int sign  = 1;
        int result = 0;

        while (chars[i] == ' ') {
            i++;
        }
        if (chars[i] == '-') {
            sign = -1;
            i++;
        }
        while (i < chars.length) {
            char c = chars[i];
            if (c == ' ') continue;
            if (c > '9' || c < '0') throw new RuntimeException("Invalid input. Number cannot be parsed");
            result = result * 10 + c - '0';
            i++;
        }

        return result * sign;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.OUTPUT_PATH));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        System.out.println("Input a number: ");

        String s = bufferedReader.readLine();

        try {
            int result = ParseIntegerXiaoping.parseInteger(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        } catch(Exception e) {
            bufferedWriter.write(e.getMessage());
            bufferedWriter.newLine();
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}

