
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Please provide the CSV file and template file as command line arguments.");
      return;
    }
    
    String csvFile = args[0];
    String templateFile = args[1];
    
    // Read the CSV file and store the data in a Map
    Map<String, List<String>> dataMap = new HashMap<>();
    String[] variableNames = null;
    try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFile))) {
      String row;
      int rowNumber = 0;
      while ((row = csvReader.readLine()) != null) {
        if (rowNumber == 0) {
          // First row contains the variable names
          variableNames = row.split(",");
          for (String variableName : variableNames) {
            dataMap.put(variableName, new ArrayList<>());
          }
        } else {
          // Subsequent rows contain the data
          String[] data = row.split(",");
          for (int i = 0; i < variableNames.length; i++) {
            String[] substrings = data[i].split("\n");
            for (String substring : substrings) {
                dataMap.get(variableNames[i]).add(substring);
        }
      }
    }
    rowNumber++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

        // Get the "address" column from the data map
        // List<String> addresses = dataMap.get("address");

        // for (String address : addresses) {
        //   String[] substrings = address.split("\n");
        //   for (String substring : substrings) {
        //     System.out.println(substring);
        //   }
        // }
    
    // Read the template file and perform the mail merge
    try (Scanner templateScanner = new Scanner(new FileReader(templateFile))) {
      StringBuilder emailBuilder = new StringBuilder();
      while (templateScanner.hasNextLine()) {
        String line = templateScanner.nextLine();
        
        // Find all variables in the line
        Pattern pattern = Pattern.compile("<<(.+?)>>");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          String key = matcher.group(1);
          List<String> values = dataMap.get(key);
          if (values != null) {
            for (int i = 0; i < values.size(); i++) {
              line = line.replace("<<" + key + ">>", values.get(i));
            }
          }
        }
        emailBuilder.append(line).append("\n");
      }
      
      // Split the merged email into individual emails
      String[] emails = emailBuilder.toString().split("\n\n");
      
      // Print out the individual emails
      for (String email : emails) {
        System.out.println(email);
      }
    } catch (IOException e) {
        e.printStackTrace();
    }

//     for (int i = 0; i < dataMap.get("first_name").size(); i++) {
//   // Read the template file and perform the mail merge
//   try (Scanner templateScanner = new Scanner(new FileReader(templateFile))) {
//     StringBuilder emailBuilder = new StringBuilder();
//     while (templateScanner.hasNextLine()) {
//       String line = templateScanner.nextLine();
      
//       // Find all variables in the line
//       Pattern pattern = Pattern.compile("<<(.+?)>>");
//       Matcher matcher = pattern.matcher(line);
//       while (matcher.find()) {
//         String key = matcher.group(1);
//         List<String> values = dataMap.get(key);
//         if (values != null) {
//           line = line.replace("<<" + key + ">>", values.get(i));
//         }
//       }
//       emailBuilder.append(line).append("\n");
//     }
    
//     // Split the merged email into individual emails
//     String[] emails = emailBuilder.toString().split("\n\n");
    
//     // Print out the individual emails
//     for (String email : emails) {
//       System.out.println(email);
//     }
//   } catch (IOException e) {
//       e.printStackTrace();
//   }
// }
}
}

     
