
package com.auca;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SmartRegistrationForm {

    private final Scanner scanner;

    // Constructor
    public SmartRegistrationForm(Scanner scanner) {
        this.scanner = scanner;
    }

    // Main method for running a smart registration form
    public Map<String, String> runForm(
            String title,
            List<String[]> steps) {

        Map<String, String> values = new LinkedHashMap<>();

        List<String> fieldNames = new ArrayList<>();

        // Collect all field names from all steps
        for (String[] step : steps) {
            for (String field : step) {
                fieldNames.add(field);
            }
        }

        int currentStep = 0;

        // Move through registration steps
        while (currentStep < steps.size()) {

            String[] fields = steps.get(currentStep);

            System.out.println("\n==================================");
            System.out.println("       " + title);
            System.out.println("==================================");

            System.out.println(
                    "Step " + (currentStep + 1)
                            + " of " + steps.size());

            System.out.println("----------------------------------");

            boolean movedBack = false;

            // Ask the user to enter each field
            for (String field : fields) {

                System.out.println("\nEnter " + field + ":");

                // Display the previous value if available
                if (values.containsKey(field)) {
                    System.out.println(
                            "Current value: " + values.get(field));
                }

                System.out.println(
                        "Type BACK to return or CANCEL to stop.");

                String input = scanner.nextLine().trim();

                // Cancel registration
                if (input.equalsIgnoreCase("CANCEL")) {

                    System.out.println(
                            "\nRegistration cancelled successfully.");

                    return null;
                }

                // Go to the previous step
                if (input.equalsIgnoreCase("BACK")) {

                    if (currentStep > 0) {

                        currentStep--;

                        movedBack = true;

                        break;

                    } else {

                        System.out.println(
                                "You are already on the first step.");

                        continue;
                    }
                }

                // Prevent empty values
                if (input.isEmpty()) {

                    System.out.println(
                            "This field cannot be empty. Try again.");

                    continue;
                }

                // Save the entered value temporarily
                values.put(field, input);
            }

            // If the user went back, display the previous step
            if (movedBack) {
                continue;
            }

            // Check whether all fields in this step are completed
            boolean completed = true;

            for (String field : fields) {

                if (!values.containsKey(field)
                        || values.get(field).isBlank()) {

                    completed = false;
                    break;
                }
            }

            // Move to the next step only when complete
            if (completed) {

                currentStep++;

            } else {

                System.out.println(
                        "\nPlease complete all required fields.");
            }
        }

        // Review and confirmation section
        while (true) {

            System.out.println("\n==================================");
            System.out.println("       REVIEW REGISTRATION");
            System.out.println("       " + title.toUpperCase());
            System.out.println("==================================");

            // Display all entered information
            for (int i = 0; i < fieldNames.size(); i++) {

                String field = fieldNames.get(i);

                System.out.println(
                        (i + 1) + ". "
                                + field + ": "
                                + values.get(field));
            }

            System.out.println("\n----------------------------------");
            System.out.println("1. Save Registration");
            System.out.println("2. Edit Information");
            System.out.println("3. Cancel Registration");
            System.out.println("----------------------------------");

            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            // Confirm registration
            if (choice.equals("1")) {

                System.out.println(
                        "\nInformation confirmed.");

                return values;
            }

            // Edit an existing field
            else if (choice.equals("2")) {

                System.out.print(
                        "\nEnter the number of the field to edit: ");

                try {

                    int fieldNumber = Integer.parseInt(
                            scanner.nextLine().trim());

                    if (fieldNumber < 1
                            || fieldNumber > fieldNames.size()) {

                        System.out.println(
                                "Invalid field number.");

                        continue;
                    }

                    String field =
                            fieldNames.get(fieldNumber - 1);

                    System.out.println(
                            "Current value: " + values.get(field));

                    System.out.println(
                            "Enter the new value:");

                    System.out.println(
                            "Type CANCEL to keep the old value.");

                    String newValue =
                            scanner.nextLine().trim();

                    if (newValue.equalsIgnoreCase("CANCEL")) {
                        continue;
                    }

                    if (newValue.isEmpty()) {

                        System.out.println(
                                "The value cannot be empty.");

                        continue;
                    }

                    values.put(field, newValue);

                    System.out.println(
                            "Information updated successfully.");

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Please enter a valid field number.");
                }
            }

            // Cancel registration
            else if (choice.equals("3")) {

                System.out.println(
                        "\nRegistration cancelled.");

                return null;
            }

            // Handle invalid menu choice
            else {

                System.out.println(
                        "Invalid option. Please try again.");
            }
        }
    }
}
