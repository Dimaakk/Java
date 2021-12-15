
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String COMMA_DELIMITER = ";";
    private static final String path = "./src/movementList_csv.csv";

    public static void main(String[] args) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }
        catch (Exception ignored) {

        }
//        System.out.println(records);

        float spended = 0;
        float getted = 0;
        String operation = "";
        String pat = "([0-9]{2})\\.([0-9]{2})\\.(\\d{2})";
        Pattern pattern = Pattern.compile(pat, Pattern.CASE_INSENSITIVE);
        List<List<String>> divided = new ArrayList<>();

        for (int k = 0; k < records.size(); k++) {
            List<String> record = records.get(k);

            spended += Float.parseFloat(record.get(record.size() - 1).replaceAll(",", "."));
            getted += Float.parseFloat(record.get(record.size() - 2).replaceAll(",", "."));
            operation = record.get(record.size() - 3);
            operation = operation.replaceAll(" +", " ");

            String[] splitted_oper = operation.split(" ");


            for (int i = 0; i < splitted_oper.length; i++) {
                Matcher matcher = pattern.matcher(splitted_oper[i]);
                if (matcher.find()) {
                    List<String> values = new ArrayList<>();

                    StringBuilder strBuilder = new StringBuilder();
                    for (int j = 1; j < i; j++) {
                        strBuilder.append(splitted_oper[j]);
                    }
                    String newString = strBuilder.toString();
                    values.add(newString);
                    values.add(record.get(record.size() - 1).replaceAll(",", ".")  );
                    divided.add(values);
                    break;
                }
            }
        }


        System.out.println("Сумма расходов: " + spended + " руб.");
        System.out.println("Сумма доходов: " + getted + " руб.");


        Set<String> keys = new TreeSet<>();
        for (int k = 0; k < divided.size(); k++) {
            List<String> record = divided.get(k);
            keys.add(record.get(0));
        }
        String[] keys_list = keys.toArray(new String[keys.size()]);
        System.out.println("Суммы расходов по организациям: ");
        for (int k = 0; k < keys_list.length; k++) {
            System.out.print(keys_list[k] + " : ");
            float sum = 0;
            for (int i = 0; i < divided.size(); i++) {
                List<String> elem = divided.get(i);
                if (elem.get(0).equals(keys_list[k])) {
                    sum += Float.parseFloat(elem.get(1));
                }
            }
            System.out.println(sum);
        }

    }
}
