package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Student> studentList = new ArrayList<Student>();
    static int numStudents = 0;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("No input.");
            } else {
                switch (input) {
                    case "exit":
                        System.out.println("Bye!");
                        exit = true;
                        break;
                    case "add students":
                       addStudents(scanner);
                        break;
                    case "back":
                        System.out.println("Enter 'exit' to exit the program");
                        break;
                    case "list":
                        listStudents();
                        break;
                    case "add points":
                        addPoints(scanner);
                        break;
                    case "find":
                        findStudent(scanner);
                        break;
                    case "statistics":
                        showStatistics(scanner);
                        break;
                    case "notify":
                        notifyStudent();
                        break;
                    default:
                        System.out.println("Error: unknown command!");
                }
            }
        }
    }

    static void notifyStudent(){
        int totalStudentsNotified = 0;
        for(Student student : studentList){
            boolean isStudentNotified = false;
            if(student.javaComplete && !student.javaNotified){
                System.out.println("To: " + student.email);
                System.out.println("Re: Your Learning Progress");
                System.out.println("Hello, " + student.firstName + " " + student.lastName + "! You have accomplished our Java course!");
                student.javaNotified = true;
                isStudentNotified = true;
            }
            if(student.dataStructuresAlgorithmsComplete && !student.dataStructuresAlgorithmsNotified){
                System.out.println("To: " + student.email);
                System.out.println("Re: Your Learning Progress");
                System.out.println("Hello, " + student.firstName + " " + student.lastName + "! You have accomplished our DSA course!");
                student.dataStructuresAlgorithmsNotified = true;
                isStudentNotified = true;
            }
            if(student.databasesComplete && !student.databasesNotified){
                System.out.println("To: " + student.email);
                System.out.println("Re: Your Learning Progress");
                System.out.println("Hello, " + student.firstName + " " + student.lastName + "! You have accomplished our Databases course!");
                student.databasesNotified = true;
                isStudentNotified = true;
            }
            if(student.springComplete && !student.springNotified){
                System.out.println("To: " + student.email);
                System.out.println("Re: Your Learning Progress");
                System.out.println("Hello, " + student.firstName + " " + student.lastName + "! You have accomplished our Spring course!");
                student.springNotified = true;
                isStudentNotified = true;
            }
            if (isStudentNotified) {
                totalStudentsNotified++;
            }
        }
        System.out.println("Total " + totalStudentsNotified + " students have been notified.");
    }
    static void courseStatisticDisplay(){
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println("Most popular: " + getMostPopular());
        System.out.println("Least popular: " + getLeastPopular());
        System.out.println("Highest activity: " + getHighestActivity());
        System.out.println("Lowest activity: " + getLowestActivity());
        System.out.println("Easiest course: " + getEasiestCourse());
        System.out.println("Hardest course: " + getHardestCourse());
    }

    static String getEasiestCourse(){
        //get the easiest course
        int totalJavaPoints = 0;
        int totalJavaTasks = 0;
        int totalDsaPoints = 0;
        int totalDsaTasks = 0;
        int totalDatabasePoints = 0;
        int totalDatabaseTasks = 0;
        int totalSpringPoints = 0;
        int totalSpringTasks = 0;
        for (Student student : studentList) {
            totalJavaPoints += student.javaPoints;
            totalJavaTasks += student.numJavaTasks;
            totalDsaPoints += student.dataStructuresAlgorithmsPoints;
            totalDsaTasks += student.numDataStructuresTasks;
            totalDatabasePoints += student.databasesPoints;
            totalDatabaseTasks += student.numDatabaseTasks;
            totalSpringPoints += student.springPoints;
            totalSpringTasks += student.numSpringTasks;
        }
        if(totalJavaPoints == 0 && (totalJavaPoints == totalDatabasePoints && totalJavaPoints == totalDsaPoints && totalJavaPoints == totalSpringPoints)){
            return "n/a";
        }
        double javaAverage = (double) totalJavaPoints / totalJavaTasks;
        double dsaAverage = (double) totalDsaPoints / totalDsaTasks;
        double databaseAverage = (double) totalDatabasePoints / totalDatabaseTasks;
        double springAverage = (double) totalSpringPoints / totalSpringTasks;
        List<String> easiestCourses = new ArrayList<>();
        if (javaAverage >= dsaAverage && javaAverage >= databaseAverage && javaAverage >= springAverage) {
            easiestCourses.add("Java");
        }
        if (dsaAverage >= javaAverage && dsaAverage >= databaseAverage && dsaAverage >= springAverage) {
            easiestCourses.add("DSA");
        }
        if (databaseAverage >= javaAverage && databaseAverage >= dsaAverage && databaseAverage >= springAverage) {
            easiestCourses.add("Databases");
        }
        if (springAverage >= javaAverage && springAverage >= dsaAverage && springAverage >= databaseAverage) {
            easiestCourses.add("Spring");
        }

        if (easiestCourses.size() == 1) {
            return easiestCourses.get(0);
        } else {
            String returnString = "";
            for (int i = 0; i < easiestCourses.size() - 1; i++) {
                returnString += easiestCourses.get(i);
                returnString += ", ";
            }
            returnString += easiestCourses.get(easiestCourses.size() - 1);
            return returnString;
        }
    }
    static String getHardestCourse(){
        int totalJavaPoints = 0;
        int totalJavaTasks = 0;
        int totalDsaPoints = 0;
        int totalDsaTasks = 0;
        int totalDatabasePoints = 0;
        int totalDatabaseTasks = 0;
        int totalSpringPoints = 0;
        int totalSpringTasks = 0;
        for (Student student : studentList) {
            totalJavaPoints += student.javaPoints;
            totalJavaTasks += student.numJavaTasks;
            totalDsaPoints += student.dataStructuresAlgorithmsPoints;
            totalDsaTasks += student.numDataStructuresTasks;
            totalDatabasePoints += student.databasesPoints;
            totalDatabaseTasks += student.numDatabaseTasks;
            totalSpringPoints += student.springPoints;
            totalSpringTasks += student.numSpringTasks;
        }
        if(totalJavaPoints == 0 && (totalJavaPoints == totalDatabasePoints && totalJavaPoints == totalDsaPoints && totalJavaPoints == totalSpringPoints)){
            return "n/a";
        }
        double javaAverage = (double) totalJavaPoints / totalJavaTasks;
        double dsaAverage = (double) totalDsaPoints / totalDsaTasks;
        double databaseAverage = (double) totalDatabasePoints / totalDatabaseTasks;
        double springAverage = (double) totalSpringPoints / totalSpringTasks;
        List<String> hardestCourses = new ArrayList<>();
        if (javaAverage <= dsaAverage && javaAverage <= databaseAverage && javaAverage <= springAverage) {
            hardestCourses.add("Java");
        }
        if (dsaAverage <= javaAverage && dsaAverage <= databaseAverage && dsaAverage <= springAverage) {
            hardestCourses.add("DSA");
        }
        if (databaseAverage <= javaAverage && databaseAverage <= dsaAverage && databaseAverage <= springAverage) {
            hardestCourses.add("Databases");
        }
        if (springAverage <= javaAverage && springAverage <= dsaAverage && springAverage <= databaseAverage) {
            hardestCourses.add("Spring");
        }
        if (hardestCourses.size() == 1) {
            return hardestCourses.get(0);
        } else {
            String returnString = "";
            for (int i = 0; i < hardestCourses.size() - 1; i++) {
                returnString += hardestCourses.get(i);
                returnString += ", ";
            }
            returnString += hardestCourses.get(hardestCourses.size() - 1);
            return returnString;
        }

    }

    static String getLowestActivity() {

        int [] completedTasks = new int[4];
        int lowest = 0;
        int highest = 0;
        List<String> lowestActivity = new ArrayList<>();
        String returnString = "";
        for(Student student : studentList){
            completedTasks[0] += student.numJavaTasks;
            completedTasks[1] += student.numDataStructuresTasks;
            completedTasks[2] += student.numDatabaseTasks;
            completedTasks[3] += student.numSpringTasks;
        }
        lowest = completedTasks[0];
        for(int completedTask : completedTasks){
            lowest = Math.min(lowest, completedTask);
            highest = Math.max(highest, completedTask);
        }
        if (highest == 0 || highest == lowest) {
            return "n/a";
        }
        if (completedTasks[0]==lowest){
            lowestActivity.add("Java");
        }
        if (completedTasks[1]==lowest){
            lowestActivity.add("DSA");
        }
        if (completedTasks[2]==lowest){
            lowestActivity.add("Databases");
        }
        if (completedTasks[3]==lowest){
            lowestActivity.add("Spring");
        }
        if (lowestActivity.size()==1){
            return lowestActivity.get(0);
        }else{
            for (int i = 0; i< lowestActivity.size()-1; i++){
                returnString += lowestActivity.get(i);
                returnString += ", ";
            }
            returnString+= lowestActivity.get(lowestActivity.size() -1);
            return  returnString;
        }

    }

    static String getHighestActivity(){
        int [] completedTasks = new int[4];
        int highest = 0;
        List<String> highestActivity = new ArrayList<>();
        String returnString = "";
        for(Student student : studentList){
            completedTasks[0] += student.numJavaTasks;
            completedTasks[1] += student.numDataStructuresTasks;
            completedTasks[2] += student.numDatabaseTasks;
            completedTasks[3] += student.numSpringTasks;
        }
        for(int completedTask : completedTasks){
            highest = Math.max(highest, completedTask);

        }
        if (highest == 0) {
            return "n/a";
        }
        if (completedTasks[0]==highest){
            highestActivity.add("Java");
        }
        if (completedTasks[1]==highest){
            highestActivity.add("DSA");
        }
        if (completedTasks[2]==highest){
            highestActivity.add("Databases");
        }
        if (completedTasks[3]==highest){
            highestActivity.add("Spring");
        }
        int highestActivityLength = highestActivity.size();
        if (highestActivityLength == 1){
            return highestActivity.get(0);
        }else{
            for (int i = 0; i< highestActivityLength-1; i++){
                returnString += highestActivity.get(i);
                returnString += ", ";
            }
            returnString+= highestActivity.get(highestActivityLength -1);
            return  returnString;
        }

    }

    static String getLeastPopular(){
        int [] participants = new int[4];
        int min = 0;
        int max = 0;
        List <String> leastPopular = new ArrayList<>();
        String returnString = "";
        for (Student student : studentList){
            participants[0] = student.javaPoints > 0 ? participants[0] +1 : participants[0];
            participants[1]  = student.dataStructuresAlgorithmsPoints > 0 ? participants[1] +1 : participants[1];
            participants[2]  = student.databasesPoints > 0 ? participants[2] +1 : participants[2];
            participants[3]  = student.springPoints > 0 ? participants[3] +1 : participants[3];
        }
        min = participants[0];
        for(int participant : participants){
            min = Math.min(participant, min);
            max = Math.max(participant, max);
        }
        if (max == 0 || max == min) {
            return "n/a";
        }
        if (participants[0]==min){
            leastPopular.add("Java");
        }
        if (participants[1]==min){
            leastPopular.add("DSA");
        }
        if (participants[2]==min){
            leastPopular.add("Databases");
        }
        if (participants[3]==min){
            leastPopular.add("Spring");
        }
        int leastPopularLength = leastPopular.size();
        if (leastPopularLength == 1){
            return leastPopular.get(0);
        }else{
            for (int i = 0; i< leastPopularLength-1; i++){
                returnString += leastPopular.get(i);
                returnString += ", ";
            }
            returnString+= leastPopular.get(leastPopularLength -1);
            return  returnString;
        }
    }
    static String getMostPopular(){
        int [] participants = new int[4];
        int maxTotal = 0;
        List <String> mostPopular = new ArrayList<>();
        String returnString = "";
        for (Student student : studentList){
              participants[0] = student.javaPoints > 0 ? participants[0] +1 : participants[0];
              participants[1]  = student.dataStructuresAlgorithmsPoints > 0 ? participants[1] +1 : participants[1];
              participants[2]  = student.databasesPoints > 0 ? participants[2] +1 : participants[2];
              participants[3]  = student.springPoints > 0 ? participants[3] +1 : participants[3];
        }
        for(int participant : participants){
            maxTotal = Math.max(participant, maxTotal);
        }
        if (maxTotal == 0){
            return returnString + "n/a";
        }
        if (participants[0]==maxTotal){
            mostPopular.add("Java");
        }
        if (participants[1]==maxTotal){
            mostPopular.add("DSA");
        }
        if (participants[2]==maxTotal){
            mostPopular.add("Databases");
        }
        if (participants[3]==maxTotal){
            mostPopular.add("Spring");
        }
        int mostPopularLength = mostPopular.size();
        if (mostPopularLength == 1){
            return mostPopular.get(0);
        }else{
            for (int i = 0; i< mostPopularLength-1; i++){
                returnString += mostPopular.get(i);
                returnString += ", ";
            }
            returnString+= mostPopular.get(mostPopularLength -1);
            return  returnString;
        }
    }

    static void showStatistics(Scanner scanner){
        courseStatisticDisplay();
        while(true){
            String input = scanner.nextLine();
            if (input.equals("back")){
                break;
            }
            input = input.toLowerCase();
            switch (input) {
                case "java" -> printJavaStatistics();
                case "dsa" -> printDSAStatistics();
                case "spring" -> printSpringStatistics();
                case "databases" -> printDatabaseStatistics();
                default -> System.out.println("Unknown course");
            }

        }
    }
    static class CourseStatistic implements Comparable<CourseStatistic> {
        int id;
        int points;
        double completed;

        @Override
        public int compareTo(CourseStatistic o) {
            if (this.completed > o.completed) {
                return -1;
            } else if (this.completed < o.completed) {
                return 1;
            } else {
                return Integer.compare(this.id, o.id);
            }
        }
        @Override
        public String toString(){
            BigDecimal decimal = BigDecimal.valueOf(completed * 100);
            BigDecimal rounded = decimal.setScale(1, RoundingMode.HALF_UP);
            String completedPercentage = String.format("%.1f", rounded);
            return id + "     " + points + "        " + completedPercentage + "%";
        }
    }

    static void printSpringStatistics(){
        System.out.println("Spring");
        System.out.println("id    points    completed");
        ArrayList<CourseStatistic> courseStatistics = new ArrayList<>();
        for(Student student : studentList){
            if (student.springPoints > 0) {
                CourseStatistic courseStatistic = new CourseStatistic();
                courseStatistic.id = student.id;
                courseStatistic.points = student.springPoints;
                courseStatistic.completed = (double) student.springPoints / 550;
                courseStatistics.add(courseStatistic);
            }
        }
        courseStatistics.sort(Comparator.naturalOrder());
        for(CourseStatistic statistic : courseStatistics){
            System.out.println(statistic);
        }
    }

    static void printDatabaseStatistics(){
        System.out.println("Databases");
        System.out.println("id    points    completed");
        ArrayList<CourseStatistic> courseStatistics = new ArrayList<>();
        for(Student student : studentList){
            if(student.databasesPoints > 0) {
                CourseStatistic courseStatistic = new CourseStatistic();
                courseStatistic.id = student.id;
                courseStatistic.points = student.databasesPoints;
                courseStatistic.completed = (double) student.databasesPoints / 480;
                courseStatistics.add(courseStatistic);
            }
        }
        courseStatistics.sort(Comparator.naturalOrder());
        for(CourseStatistic statistic : courseStatistics){
            System.out.println(statistic);
        }

    }
    static void printDSAStatistics(){
        System.out.println("DSA");
        System.out.println("id    points    completed");
        ArrayList<CourseStatistic> courseStatistics = new ArrayList<>();
        for(Student student : studentList){
            if (student.dataStructuresAlgorithmsPoints > 0) {
                CourseStatistic courseStatistic = new CourseStatistic();
                courseStatistic.id = student.id;
                courseStatistic.points = student.dataStructuresAlgorithmsPoints;
                courseStatistic.completed = (double) student.dataStructuresAlgorithmsPoints / 400;
                courseStatistics.add(courseStatistic);
            }
        }
        courseStatistics.sort(Comparator.naturalOrder());
        for(CourseStatistic statistic : courseStatistics){
            System.out.println(statistic);
        }

    }
    static void printJavaStatistics(){
        System.out.println("Java");
        System.out.println("id    points    completed");
        ArrayList<CourseStatistic> courseStatistics = new ArrayList<>();
        for (Student student : studentList) {
            if(student.javaPoints > 0) {
                CourseStatistic courseStatistic = new CourseStatistic();
                courseStatistic.id = student.id;
                courseStatistic.points = student.javaPoints;
                courseStatistic.completed = (double) student.javaPoints / 600;
                courseStatistics.add(courseStatistic);
            }
        }
        courseStatistics.sort(Comparator.naturalOrder());
        for(CourseStatistic statistic : courseStatistics){
            System.out.println(statistic);
        }

    }

    static void addPoints(Scanner scanner){
        System.out.println("Enter an id and points or 'back' to return");
        while (true){
            String input = scanner.nextLine();
            if (input.equals("back")){
                return;
            }
            String [] inputs = input.split(" ");
            if (inputs.length != 5){
                System.out.println("Incorrect points format.");
            }else {
                try {
                    Integer.parseInt(inputs[0]);
                    findAndUpdateStudent(inputs);
                }catch (NumberFormatException e){
                    System.out.println("No student is found for id=" + inputs[0] );
                }
            }
        }
    }

    static void findAndUpdateStudent(String [] input){
        try {
            boolean found = false;
            int id = Integer.parseInt(input[0]);
            int[] pointsUpdate = {Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4])};
            for(int point : pointsUpdate){
                if (point< 0){
                    System.out.println("Incorrect points format.");
                    return;
                }
            }
            for (Student student : studentList){
                if(student.id == id){
                    student.updatePoints(pointsUpdate);
                    System.out.println("Points updated.");
                    found = true;
                }
            }
            if (!found){
                System.out.println("No student is found for id=" + id);
            }
        }catch (NumberFormatException e){
            System.out.println("Incorrect points format.");

        }

    }

    static void findStudent(Scanner scanner){
        System.out.println("Enter an id or 'back to return:");
        while(true) {
            boolean found = false;

            String input = scanner.nextLine();
            if (input.equals("back")){
                return;
            }
            int id = Integer.parseInt(input);
            for (Student student : studentList) {
                if (student.id == id) {
                    System.out.println(id + " points: Java=" + student.javaPoints + "; DSA=" +
                            student.dataStructuresAlgorithmsPoints + "; Databases=" + student.databasesPoints +
                            "; Spring=" + student.springPoints);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No student is found for id=" + id + ".");
            }
        }
    }
    static void listStudents(){
        if(studentList.isEmpty()){
            System.out.println("No students found");
        }else {
            System.out.println("Students:");
            for (Student student : studentList) {
                System.out.println(student.id);
            }
        }
    }
    static void addStudents(Scanner scanner){

        System.out.println("Enter student credentials or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                System.out.println("Total " + studentList.size() + " students have been added.");
                break;
            }else{
                String validationResult = validateInput(input);
                switch (validationResult){
                    case "incorrectFirst":
                        System.out.println("Incorrect first name.");
                        break;
                    case "incorrectLast":
                        System.out.println("Incorrect last name.");
                        break;
                    case "incorrectEmail":
                        System.out.println("Incorrect email.");
                        break;
                    case "incorrectNumberInputs":
                        System.out.println("Incorrect credentials.");
                        break;
                    case "repeatedEmail":
                        System.out.println("This email is already taken.");
                        break;
                    default:
                        System.out.println("The student has been added");

                }

            }
        }

    }

    static boolean uniqueEmail(String email){
        for(Student student : studentList){
            if (student.email.equals(email)){
                return false;
            }
        }
        return true;
    }

    static String validateInput(String input){
        String [] student = input.split(" ");
        if (student.length < 3){
            return "incorrectNumberInputs";
        }
        String nameRegex = "^[A-Za-z](?!.*-$)(?!.*'$)(?!.*--)(?!.*''|.*-'|.*'-)[A-Za-z'-]+(?:\\s[A-Za-z](?!.*-$)(?!.*'$)(?!.*--)(?!.*''|.*-'|.*'-)[A-Za-z'-]+)*$";
        String emailRegex = "^[^@]+@[^@]+\\.[^.]+$";
        int numberElements = student.length;
        String lastName = "";
        for (int i= 1; i< student.length - 1; i++){
            lastName += student[i];
            lastName += " ";
        }
        lastName = lastName.trim();

        if (!student[0].matches(nameRegex)){
            return "incorrectFirst";
        }else if (!lastName.matches(nameRegex)){
            return "incorrectLast";
        }else if (!student[student.length - 1].matches(emailRegex)){
            return "incorrectEmail";
        }else if(!uniqueEmail(student[student.length - 1])){
            return "repeatedEmail";
        }
        Student newStudent = new Student(student[0], lastName, student[student.length - 1]);
        studentList.add(newStudent);
        numStudents ++;
        return "correct";
    }
    static class Student {
        static final int JAVA_COMPLETION_POINTS = 600;
        static final int DATA_STRUCTURES_ALGORITHMS_COMPLETION_POINTS = 400;
        static final int DATABASES_COMPLETION_POINTS = 480;
        static final int SPRING_COMPLETION_POINTS = 550;
        int id;
        String firstName;
        String lastName;
        String email;

        int javaPoints = 0;
        int numJavaTasks = 0;
        boolean javaNotified = false;
        boolean javaComplete = false;
        int dataStructuresAlgorithmsPoints = 0;
        int numDataStructuresTasks = 0;
        boolean dataStructuresAlgorithmsNotified = false;
        boolean dataStructuresAlgorithmsComplete = false;
        int databasesPoints = 0;
        int numDatabaseTasks = 0;
        boolean databasesNotified = false;
        boolean databasesComplete = false;
        int springPoints = 0;
        int numSpringTasks = 0;
        boolean springNotified = false;
        boolean springComplete = false;
        Student(String firstName, String lastName, String email){
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            id = numStudents + 1;
        }
        void updatePoints(int [] pointsToAdd){
            javaPoints += pointsToAdd[0];
            if (javaPoints >= JAVA_COMPLETION_POINTS) {
                javaComplete = true;
            }
            if (pointsToAdd[0] != 0) {
                numJavaTasks++;
            }
            dataStructuresAlgorithmsPoints += pointsToAdd[1];
            if (dataStructuresAlgorithmsPoints >= DATA_STRUCTURES_ALGORITHMS_COMPLETION_POINTS) {
                dataStructuresAlgorithmsComplete = true;
            }
            if (pointsToAdd[1] != 0) {
                numDataStructuresTasks++;
            }
            databasesPoints += pointsToAdd[2];
            if (databasesPoints  >= DATABASES_COMPLETION_POINTS) {
                databasesComplete = true;
            }
            if (pointsToAdd[2] != 0) {
                numDatabaseTasks++;
            }
            springPoints += pointsToAdd[3];
            if (springPoints >= SPRING_COMPLETION_POINTS) {
                springComplete = true;
            }
            if (pointsToAdd[3] != 0) {
                numSpringTasks++;
            }

        }

    }
}

/*
Java
id    points    completed
10001 24        4.0%
10000 21        3.5%

 */