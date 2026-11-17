
package com.auca;

import com.auca.interfaces.Identifiable;
import com.auca.abstracts.Person;
import com.auca.SmartRegistrationForm;

import com.auca.student.Student;
import com.auca.teacher.Teacher;
import com.auca.administrator.Administrator;
import com.auca.department.Department;
import com.auca.course.Course;
import com.auca.classroom.Classroom;
import com.auca.enrollment.Enrollment;
import com.auca.assessment.Assessment;
import com.auca.feepayment.FeePayment;
import com.auca.librarybook.LibraryBook;
import com.auca.bookloan.BookLoan;

import com.auca.exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final SmartRegistrationForm SmartForm = new SmartRegistrationForm(scanner);

    // Lists for storing application records
    private static final List<Department> departments = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Administrator> administrators = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Classroom> classrooms = new ArrayList<>();
    private static final List<Enrollment> enrollments = new ArrayList<>();
    private static final List<Assessment> assessments = new ArrayList<>();
    private static final List<FeePayment> payments = new ArrayList<>();
    private static final List<LibraryBook> books = new ArrayList<>();
    private static final List<BookLoan> loans = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("       AUCA SCHOOL MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {

                    case 1:
                        registerDepartment();
                        break;

                    case 2:
                        registerStudent();
                        break;

                    case 3:
                        registerTeacher();
                        break;

                    case 4:
                        registerAdministrator();
                        break;

                    case 5:
                        registerCourse();
                        break;

                    case 6:
                        registerClassroom();
                        break;

                    case 7:
                        enrollStudent();
                        break;

                    case 8:
                        recordAssessment();
                        break;

                    case 9:
                        recordFeePayment();
                        break;

                    case 10:
                        registerLibraryBook();
                        break;

                    case 11:
                        borrowLibraryBook();
                        break;

                    case 12:
                        returnLibraryBook();
                        break;

                    case 13:
                        viewAllRecords();
                        break;

                    case 14:
                        searchRecordById();
                        break;

                    case 15:
                        updateExistingRecord();
                        break;

                    case 16:
                        studentAcademicSummary();
                        break;

                    case 17:
                        studentPaymentHistory();
                        break;

                    case 18:
                        viewAvailableBooks();
                        break;

                    case 19:
                        demonstratePolymorphism();
                        break;

                    case 0:
                        running = false;
                        System.out.println("Thank you for using AUCA School Management System.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please select from 0 to 19.");
                }

            } catch (ValidationException
                     | DuplicateRecordException
                     | RecordNotFoundException
                     | PrerequisiteMissingException
                     | BookUnavailableException e) {

                System.out.println("Operation failed: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

            if (running) {
                System.out.println();
            }
        }

        scanner.close();
    }

    // ==========================================
    // MAIN MENU
    // ==========================================

    private static void displayMenu() {

        System.out.println("\n============== MAIN MENU ==============");
        System.out.println("1.  Register Department");
        System.out.println("2.  Register Student");
        System.out.println("3.  Register Teacher");
        System.out.println("4.  Register Administrator");
        System.out.println("5.  Register Course");
        System.out.println("6.  Register Classroom");
        System.out.println("7.  Enroll Student");
        System.out.println("8.  Record Assessment");
        System.out.println("9.  Record Fee Payment");
        System.out.println("10. Register Library Book");
        System.out.println("11. Borrow Library Book");
        System.out.println("12. Return Library Book");
        System.out.println("13. View All Records");
        System.out.println("14. Search Record by ID");
        System.out.println("15. Update Existing Record");
        System.out.println("16. Student Academic Summary");
        System.out.println("17. Student Payment History");
        System.out.println("18. View Available Books");
        System.out.println("19. Demonstrate Polymorphism");
        System.out.println("0.  Exit");
        System.out.println("=======================================");
    }

    // ==========================================
    // INPUT METHODS
    // ==========================================

    private static String readString(String message) {

        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private static int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static BigDecimal readMoney(String message) {

        while (true) {
            try {
                System.out.print(message);
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    private static LocalDate readDate(String message) {

        while (true) {
            try {
                System.out.print(message + " (yyyy-MM-dd): ");
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Example: 2004-05-20");
            }
        }
    }

    private static boolean readBoolean(String message) {

        while (true) {
            String answer = readString(message + " (yes/no): ");

            if (answer.equalsIgnoreCase("yes")) {
                return true;
            }

            if (answer.equalsIgnoreCase("no")) {
                return false;
            }

            System.out.println("Please enter yes or no.");
        }
    }

    private static void checkUniqueId(String id)
            throws DuplicateRecordException {

        for (Identifiable record : getAllIdentifiableRecords()) {
            if (record.getId() != null && record.getId().equalsIgnoreCase(id)) {
                throw new DuplicateRecordException("This ID already exists: " + id);
            }
        }
    }

    private static List<Identifiable> getAllIdentifiableRecords() {

        List<Identifiable> records = new ArrayList<>();

        records.addAll(departments);
        records.addAll(students);
        records.addAll(teachers);
        records.addAll(administrators);
        records.addAll(courses);
        records.addAll(classrooms);
        records.addAll(enrollments);
        records.addAll(assessments);
        records.addAll(payments);
        records.addAll(books);
        records.addAll(loans);

        return records;
    }

    // ==========================================
    // SELECTING EXISTING OBJECTS
    // ==========================================

    private static Department selectDepartment()
            throws PrerequisiteMissingException {

        if (departments.isEmpty()) {
            throw new PrerequisiteMissingException(
                    "Register a department first.");
        }

        System.out.println("\nAvailable Departments:");

        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". "
                    + departments.get(i).getDepartmentName());
        }

        int choice = readInt("Select department number: ");

        if (choice < 1 || choice > departments.size()) {
            throw new PrerequisiteMissingException("Invalid department selection.");
        }

        return departments.get(choice - 1);
    }

    private static Student selectStudent()
            throws PrerequisiteMissingException {

        if (students.isEmpty()) {
            throw new PrerequisiteMissingException(
                    "Register a student first.");
        }

        System.out.println("\nAvailable Students:");

        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". "
                    + students.get(i).getDisplayName()
                    + " (" + students.get(i).getStudentNumber() + ")");
        }

        int choice = readInt("Select student number: ");

        if (choice < 1 || choice > students.size()) {
            throw new PrerequisiteMissingException("Invalid student selection.");
        }

        return students.get(choice - 1);
    }

    private static Teacher selectTeacher()
            throws PrerequisiteMissingException {

        if (teachers.isEmpty()) {
            throw new PrerequisiteMissingException(
                    "Register a teacher first.");
        }

        System.out.println("\nAvailable Teachers:");

        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". "
                    + teachers.get(i).getDisplayName());
        }

        int choice = readInt("Select teacher number: ");

        if (choice < 1 || choice > teachers.size()) {
            throw new PrerequisiteMissingException("Invalid teacher selection.");
        }

        return teachers.get(choice - 1);
    }

    private static Course selectCourse()
            throws PrerequisiteMissingException {

        if (courses.isEmpty()) {
            throw new PrerequisiteMissingException(
                    "Register a course first.");
        }

        System.out.println("\nAvailable Courses:");

        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". "
                    + courses.get(i).getCourseTitle());
        }

        int choice = readInt("Select course number: ");

        if (choice < 1 || choice > courses.size()) {
            throw new PrerequisiteMissingException("Invalid course selection.");
        }

        return courses.get(choice - 1);
    }

    private static Enrollment selectEnrollment()
            throws PrerequisiteMissingException {

        List<Enrollment> activeEnrollments = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.isActive()) {
                activeEnrollments.add(enrollment);
            }
        }

        if (activeEnrollments.isEmpty()) {
            throw new PrerequisiteMissingException(
                    "There are no active enrollments.");
        }

        for (int i = 0; i < activeEnrollments.size(); i++) {
            System.out.println((i + 1) + ". "
                    + activeEnrollments.get(i).getRegistrationSummary());
        }

        int choice = readInt("Select enrollment number: ");

        if (choice < 1 || choice > activeEnrollments.size()) {
            throw new PrerequisiteMissingException("Invalid enrollment selection.");
        }

        return activeEnrollments.get(choice - 1);
    }

    // ==========================================
    // 1. REGISTER DEPARTMENT
    // ==========================================

    private static void registerDepartment()
            throws ValidationException, DuplicateRecordException {

        System.out.println("\n--- REGISTER DEPARTMENT ---");

        String id = readString("Department ID: ");
        checkUniqueId(id);

        String name = readString("Department Name: ");
        String code = readString("Department Code: ");
        String office = readString("Office Location: ");
        String email = readString("Contact Email: ");

        Department department = new Department(
                id, name, code, office, email, null
        );

        department.validate();

        for (Department existing : departments) {
            if (existing.getDepartmentCode().equalsIgnoreCase(code)) {
                throw new DuplicateRecordException("Department code already exists.");
            }
        }

        departments.add(department);

        System.out.println("Department registered successfully.");
    }

    // ==========================================
    // 2. REGISTER STUDENT
    // ==========================================

    private static void registerStudent()
            throws ValidationException, DuplicateRecordException,
            PrerequisiteMissingException {

        System.out.println("\n--- REGISTER STUDENT ---");

        String personId = readString("Person ID: ");
        checkUniqueId(personId);

        String firstName = readString("First Name: ");
        String lastName = readString("Last Name: ");
        String email = readString("Email: ");
        String phone = readString("Phone Number: ");
        LocalDate dob = readDate("Date of Birth");

        String memberNumber = readString("Member Number: ");
        checkUniqueId(memberNumber);

        Department department = selectDepartment();

        LocalDate joinDate = readDate("Join Date");
        String status = readString("Academic Status (ACTIVE/INACTIVE/SUSPENDED): ");
        String institutionalEmail = readString("Institutional Email: ");
        String advisor = readString("Advisor Name: ");

        String studentNumber = readString("Student Number: ");
        checkUniqueId(studentNumber);

        String program = readString("Program Name: ");
        LocalDate admissionDate = readDate("Admission Date");
        int year = readInt("Study Year (1-6): ");
        String guardianPhone = readString("Guardian Phone: ");
        boolean scholarship = readBoolean("Has Scholarship");

        Student student = new Student(
                personId, firstName, lastName, email, phone, dob,
                memberNumber, department, joinDate, status,
                institutionalEmail, advisor,
                studentNumber, program, admissionDate, year,
                guardianPhone, scholarship
        );

        student.validate();

        students.add(student);

        System.out.println("Student registered successfully.");
        System.out.println(student.getRegistrationSummary());
    }

    // ==========================================
    // 3. REGISTER TEACHER
    // ==========================================

    private static void registerTeacher()
            throws ValidationException, DuplicateRecordException,
            PrerequisiteMissingException {

        System.out.println("\n--- REGISTER TEACHER ---");

        String personId = readString("Person ID: ");
        checkUniqueId(personId);

        String firstName = readString("First Name: ");
        String lastName = readString("Last Name: ");
        String email = readString("Email: ");
        String phone = readString("Phone Number: ");
        LocalDate dob = readDate("Date of Birth");

        String memberNumber = readString("Member Number: ");
        checkUniqueId(memberNumber);

        Department department = selectDepartment();

        LocalDate joinDate = readDate("Join Date");
        String status = readString("Academic Status (ACTIVE/INACTIVE/SUSPENDED): ");
        String institutionalEmail = readString("Institutional Email: ");
        String advisor = readString("Advisor Name: ");

        String employeeNumber = readString("Employee Number: ");
        checkUniqueId(employeeNumber);

        String specialization = readString("Specialization: ");
        LocalDate hireDate = readDate("Hire Date");
        BigDecimal salary = readMoney("Salary: ");
        String office = readString("Office Number: ");
        String type = readString("Employment Type: ");

        Teacher teacher = new Teacher(
                personId, firstName, lastName, email, phone, dob,
                memberNumber, department, joinDate, status,
                institutionalEmail, advisor,
                employeeNumber, specialization, hireDate,
                salary, office, type
        );

        teacher.validate();

        teachers.add(teacher);

        System.out.println("Teacher registered successfully.");
        System.out.println(teacher.getRegistrationSummary());
    }

    // ==========================================
    // 4. REGISTER ADMINISTRATOR
    // ==========================================

    private static void registerAdministrator()
            throws ValidationException, DuplicateRecordException {

        System.out.println("\n--- REGISTER ADMINISTRATOR ---");

        String personId = readString("Person ID: ");
        checkUniqueId(personId);

        String firstName = readString("First Name: ");
        String lastName = readString("Last Name: ");
        String email = readString("Email: ");
        String phone = readString("Phone Number: ");
        LocalDate dob = readDate("Date of Birth");

        String staffNumber = readString("Staff Number: ");
        checkUniqueId(staffNumber);

        String title = readString("Job Title: ");
        String office = readString("Assigned Office: ");
        LocalDate employmentDate = readDate("Employment Date");
        int access = readInt("Access Level (1-5): ");
        boolean active = readBoolean("Is Active");

        Administrator admin = new Administrator(
                personId, firstName, lastName, email, phone, dob,
                staffNumber, title, office, employmentDate,
                access, active
        );

        admin.validate();

        administrators.add(admin);

        System.out.println("Administrator registered successfully.");
    }

    // ==========================================
    // 5. REGISTER COURSE
    // ==========================================

    private static void registerCourse()
            throws ValidationException, DuplicateRecordException,
            PrerequisiteMissingException {

        System.out.println("\n--- REGISTER COURSE ---");

        String id = readString("Course ID: ");
        checkUniqueId(id);

        String code = readString("Course Code: ");

        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(code)) {
                throw new DuplicateRecordException("Course code already exists.");
            }
        }

        String title = readString("Course Title: ");
        int credits = readInt("Credit Hours (1-6): ");

        Department department = selectDepartment();
        Teacher teacher = selectTeacher();

        if (teacher.getDepartment() != department) {
            throw new PrerequisiteMissingException(
                    "The selected teacher must belong to the selected department.");
        }

        Course course = new Course(
                id, code, title, credits, department, teacher
        );

        course.validate();

        courses.add(course);

        System.out.println("Course registered successfully.");
    }

    // ==========================================
    // 6. REGISTER CLASSROOM
    // ==========================================

    private static void registerClassroom()
            throws ValidationException, DuplicateRecordException {

        System.out.println("\n--- REGISTER CLASSROOM ---");

        String id = readString("Classroom ID: ");
        checkUniqueId(id);

        String building = readString("Building Name: ");
        String room = readString("Room Number: ");
        int capacity = readInt("Capacity: ");
        int floor = readInt("Floor Number: ");
        boolean available = readBoolean("Is Available");

        Classroom classroom = new Classroom(
                id, building, room, capacity, floor, available
        );

        classroom.validate();

        classrooms.add(classroom);

        System.out.println("Classroom registered successfully.");
    }

    // ==========================================
    // 7. ENROLL STUDENT
    // ==========================================

    private static void enrollStudent()
            throws ValidationException, DuplicateRecordException,
            PrerequisiteMissingException {

        System.out.println("\n--- ENROLL STUDENT ---");

        String id = readString("Enrollment ID: ");
        checkUniqueId(id);

        Student student = selectStudent();
        Course course = selectCourse();

        LocalDate date = readDate("Enrollment Date");
        String semester = readString("Semester (SEMESTER 1/2/3): ");

        for (Enrollment existing : enrollments) {
            if (existing.getStudent() == student
                    && existing.getCourse() == course
                    && existing.getSemester().equalsIgnoreCase(semester)
                    && existing.isActive()) {

                throw new DuplicateRecordException(
                        "Student already has an active enrollment in this course and semester.");
            }
        }

        Enrollment enrollment = new Enrollment(
                id, student, course, date, semester, "ACTIVE"
        );

        enrollment.validate();

        enrollments.add(enrollment);

        System.out.println("Student enrolled successfully.");
    }

    // ==========================================
    // 8. RECORD ASSESSMENT
    // ==========================================

    private static void recordAssessment()
            throws ValidationException, DuplicateRecordException,
            PrerequisiteMissingException {

        System.out.println("\n--- RECORD ASSESSMENT ---");

        String id = readString("Assessment ID: ");
        checkUniqueId(id);

        Enrollment enrollment = selectEnrollment();

        String type = readString("Assessment Type: ");
        double maximum = readDouble("Maximum Score: ");
        double obtained = readDouble("Obtained Score: ");
        LocalDate date = readDate("Assessment Date");

        Assessment assessment = new Assessment(
                id, enrollment, type, maximum, obtained, date
        );

        assessment.validate();

        assessments.add(assessment);

        System.out.println("Assessment recorded successfully.");
    }

    // ==========================================
    // 9. RECORD FEE PAYMENT
    // ==========================================

    private static void recordFeePayment()
            throws ValidationException, DuplicateRecordException,
            PrerequisiteMissingException {

        System.out.println("\n--- RECORD FEE PAYMENT ---");

        String id = readString("Payment ID: ");
        checkUniqueId(id);

        Student student = selectStudent();

        BigDecimal amount = readMoney("Payment Amount: ");
        LocalDate date = readDate("Payment Date");

        String method = readString(
                "Payment Method (CASH/BANK/MOBILE MONEY/CARD): ");

        String receipt = readString("Receipt Number: ");

        for (FeePayment payment : payments) {
            if (payment.getReceiptNumber().equalsIgnoreCase(receipt)) {
                throw new DuplicateRecordException(
                        "This receipt number has already been used.");
            }
        }

        FeePayment payment = new FeePayment(
                id, student, amount, date, method, receipt
        );

        payment.validate();

        payments.add(payment);

        System.out.println("Payment recorded successfully.");
    }

    // ==========================================
    // 10. REGISTER LIBRARY BOOK
    // ==========================================

    private static void registerLibraryBook()
            throws ValidationException, DuplicateRecordException {

        System.out.println("\n--- REGISTER LIBRARY BOOK ---");

        String id = readString("Book ID: ");
        checkUniqueId(id);

        String isbn = readString("ISBN: ");

        for (LibraryBook book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                throw new DuplicateRecordException("ISBN already exists.");
            }
        }

        String title = readString("Book Title: ");
        String author = readString("Author: ");
        int total = readInt("Total Copies: ");
        int available = readInt("Available Copies: ");

        LibraryBook book = new LibraryBook(
                id, isbn, title, author, total, available
        );

        book.validate();

        books.add(book);

        System.out.println("Book registered successfully.");
    }

    // ==========================================
    // 11. BORROW LIBRARY BOOK
    // ==========================================

    private static void borrowLibraryBook()
            throws ValidationException, DuplicateRecordException,
            PrerequisiteMissingException, BookUnavailableException {

        System.out.println("\n--- BORROW LIBRARY BOOK ---");

        if (books.isEmpty()) {
            throw new PrerequisiteMissingException(
                    "Register a library book first.");
        }

        String id = readString("Loan ID: ");
        checkUniqueId(id);

        Student student = selectStudent();

        System.out.println("\nAvailable Books:");

        List<LibraryBook> availableBooks = new ArrayList<>();

        for (LibraryBook book : books) {
            if (book.getAvailableCopies() > 0) {
                availableBooks.add(book);
            }
        }

        if (availableBooks.isEmpty()) {
            throw new BookUnavailableException("No books are currently available.");
        }

        for (int i = 0; i < availableBooks.size(); i++) {
            System.out.println((i + 1) + ". "
                    + availableBooks.get(i).getTitle()
                    + " | Copies: "
                    + availableBooks.get(i).getAvailableCopies());
        }

        int choice = readInt("Select book number: ");

        if (choice < 1 || choice > availableBooks.size()) {
            throw new BookUnavailableException("Invalid book selection.");
        }

        LibraryBook book = availableBooks.get(choice - 1);

        if (book.getAvailableCopies() <= 0) {
            throw new BookUnavailableException("This book is unavailable.");
        }

        LocalDate borrowed = readDate("Borrowed Date");
        LocalDate due = readDate("Due Date");

        BookLoan loan = new BookLoan(
                id, student, book, borrowed, due, null
        );

        loan.validate();

        book.borrowCopy();
        loans.add(loan);

        System.out.println("Book borrowed successfully.");
        System.out.println(loan.getRegistrationSummary());
    }

    // ==========================================
    // 12. RETURN LIBRARY BOOK
    // ==========================================

    private static void returnLibraryBook()
            throws RecordNotFoundException, ValidationException {

        System.out.println("\n--- RETURN LIBRARY BOOK ---");

        String loanId = readString("Enter Loan ID: ");

        BookLoan selectedLoan = null;

        for (BookLoan loan : loans) {
            if (loan.getLoanId().equalsIgnoreCase(loanId)) {
                selectedLoan = loan;
                break;
            }
        }

        if (selectedLoan == null) {
            throw new RecordNotFoundException("Loan not found.");
        }

        if (!selectedLoan.isActive()) {
            throw new ValidationException("This book has already been returned.");
        }

        LocalDate returnDate = readDate("Return Date");

        if (returnDate.isBefore(selectedLoan.getBorrowedDate())
                || returnDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Invalid return date.");
        }

        selectedLoan.setReturnedDate(returnDate);
        selectedLoan.getBook().returnCopy();

        System.out.println("Book returned successfully.");
    }

    // ==========================================
    // 13. VIEW ALL RECORDS
    // ==========================================

    private static void viewAllRecords() {

        System.out.println("\n========== ALL REGISTERED RECORDS ==========");

        displayCollection("DEPARTMENTS", departments);
        displayCollection("STUDENTS", students);
        displayCollection("TEACHERS", teachers);
        displayCollection("ADMINISTRATORS", administrators);
        displayCollection("COURSES", courses);
        displayCollection("CLASSROOMS", classrooms);
        displayCollection("ENROLLMENTS", enrollments);
        displayCollection("ASSESSMENTS", assessments);
        displayCollection("FEE PAYMENTS", payments);
        displayCollection("LIBRARY BOOKS", books);
        displayCollection("BOOK LOANS", loans);
    }

    private static void displayCollection(String title, List<?> records) {

        System.out.println("\n--- " + title + " ---");

        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Object record : records) {
            System.out.println(record);
        }
    }

    // ==========================================
    // 14. SEARCH RECORD BY ID
    // ==========================================

    private static void searchRecordById()
            throws RecordNotFoundException {

        System.out.println("\n--- SEARCH RECORD BY ID ---");

        String id = readString("Enter record ID: ");

        for (Identifiable record : getAllIdentifiableRecords()) {

            if (record.getId() != null
                    && record.getId().equalsIgnoreCase(id)) {

                System.out.println("Record found:");
                System.out.println(record);
                return;
            }
        }

        throw new RecordNotFoundException(
                "No record was found with ID: " + id);
    }

    // ==========================================
    // 15. UPDATE EXISTING RECORD
    // ==========================================

    private static void updateExistingRecord()
            throws RecordNotFoundException,
            ValidationException {

        System.out.println("\n--- UPDATE EXISTING RECORD ---");

        String id = readString("Enter record ID to update: ");

        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {

                System.out.println("Student found: " + student.getDisplayName());

                student.setProgramName(
                        readString("Enter new program name: "));

                int year = readInt("Enter new study year (1-6): ");

                if (year < 1 || year > 6) {
                    throw new ValidationException(
                            "Study year must be between 1 and 6.");
                }

                student.setStudyYear(year);
                student.validate();

                System.out.println("Student updated successfully.");
                return;
            }
        }

        for (Teacher teacher : teachers) {
            if (teacher.getId().equalsIgnoreCase(id)) {

                System.out.println("Teacher found: " + teacher.getDisplayName());

                BigDecimal salary = readMoney("Enter new salary: ");

                if (salary.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new ValidationException(
                            "Salary must be greater than zero.");
                }

                teacher.setSalary(salary);
                teacher.validate();

                System.out.println("Teacher updated successfully.");
                return;
            }
        }

        for (Administrator admin : administrators) {
            if (admin.getId().equalsIgnoreCase(id)) {

                System.out.println("Administrator found.");

                int access = readInt("Enter new access level (1-5): ");

                if (access < 1 || access > 5) {
                    throw new ValidationException(
                            "Access level must be between 1 and 5.");
                }

                admin.setAccessLevel(access);
                admin.validate();

                System.out.println("Administrator updated successfully.");
                return;
            }
        }

        for (Classroom classroom : classrooms) {
            if (classroom.getId().equalsIgnoreCase(id)) {

                System.out.println("Classroom found.");

                classroom.setAvailable(
                        readBoolean("Is classroom available"));

                classroom.validate();

                System.out.println("Classroom updated successfully.");
                return;
            }
        }

        for (LibraryBook book : books) {
            if (book.getId().equalsIgnoreCase(id)) {

                System.out.println("Book found.");

                int total = readInt("Enter new total copies: ");
                int available = readInt("Enter new available copies: ");

                book.setTotalCopies(total);
                book.setAvailableCopies(available);

                book.validate();

                System.out.println("Book updated successfully.");
                return;
            }
        }

        throw new RecordNotFoundException(
                "No updatable record found with ID: " + id);
    }

    // ==========================================
    // 16. STUDENT ACADEMIC SUMMARY
    // ==========================================

    private static void studentAcademicSummary()
            throws PrerequisiteMissingException {

        System.out.println("\n--- STUDENT ACADEMIC SUMMARY ---");

        Student student = selectStudent();

        System.out.println("\nStudent: " + student.getDisplayName());
        System.out.println("Student Number: " + student.getStudentNumber());
        System.out.println("Program: " + student.getProgramName());
        System.out.println("Department: "
                + student.getDepartment().getDepartmentName());

        System.out.println("\nENROLLMENTS:");

        boolean foundEnrollment = false;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent() == student) {
                System.out.println(enrollment.getRegistrationSummary());
                foundEnrollment = true;
            }
        }

        if (!foundEnrollment) {
            System.out.println("No enrollments found.");
        }

        System.out.println("\nASSESSMENTS:");

        boolean foundAssessment = false;

        for (Assessment assessment : assessments) {
            if (assessment.getEnrollment().getStudent() == student) {
                System.out.println(assessment.getRegistrationSummary());
                foundAssessment = true;
            }
        }

        if (!foundAssessment) {
            System.out.println("No assessments found.");
        }
    }

    // ==========================================
    // 17. STUDENT PAYMENT HISTORY
    // ==========================================

    private static void studentPaymentHistory()
            throws PrerequisiteMissingException {

        System.out.println("\n--- STUDENT PAYMENT HISTORY ---");

        Student student = selectStudent();

        BigDecimal total = BigDecimal.ZERO;
        boolean found = false;

        System.out.println("\nPayments for " + student.getDisplayName());

        for (FeePayment payment : payments) {

            if (payment.getStudent() == student) {

                System.out.println(payment);

                total = total.add(payment.getAmount());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No payment history found.");
        }

        System.out.println("Total Amount Paid: " + total);
    }

    // ==========================================
    // 18. VIEW AVAILABLE BOOKS
    // ==========================================

    private static void viewAvailableBooks() {

        System.out.println("\n--- AVAILABLE LIBRARY BOOKS ---");

        boolean found = false;

        for (LibraryBook book : books) {

            if (book.getAvailableCopies() > 0) {

                System.out.println(book.getRegistrationSummary());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently available.");
        }
    }

    // ==========================================
    // 19. DEMONSTRATE POLYMORPHISM
    // ==========================================

    private static void demonstratePolymorphism() {

        System.out.println("\n--- POLYMORPHISM DEMONSTRATION ---");

        // Parent class reference holding different child objects
        List<Person> people = new ArrayList<>();

        people.addAll(students);
        people.addAll(teachers);
        people.addAll(administrators);

        if (people.isEmpty()) {
            System.out.println("Register students, teachers or administrators first.");
        } else {

            System.out.println("\nPERSON POLYMORPHISM:");

            for (Person person : people) {

                System.out.println("Name: " + person.getDisplayName());
                System.out.println("Role: " + person.getRoleDescription());
                System.out.println("ID: " + person.getId());
                System.out.println("----------------------------");
            }
        }

        // Interface reference holding unrelated classes
        List<Identifiable> records = new ArrayList<>();

        records.addAll(departments);
        records.addAll(students);
        records.addAll(teachers);
        records.addAll(administrators);
        records.addAll(courses);
        records.addAll(classrooms);
        records.addAll(enrollments);
        records.addAll(assessments);
        records.addAll(payments);
        records.addAll(books);
        records.addAll(loans);

        System.out.println("\nINTERFACE POLYMORPHISM:");

        if (records.isEmpty()) {
            System.out.println("No records available.");
        }

        for (Identifiable record : records) {

            System.out.println(
                    "ID: " + record.getId()
                            + " | Name: " + record.getDisplayName()
                            + " | Active: " + record.isActive()
            );
        }
    }
}
