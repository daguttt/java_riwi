package courses_management;

import utils.InputGetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CoursesManager {
    public static int idToAssignToStudents = 1;
    private final ArrayList<Course> courseList;
    private final ArrayList<Student> studentList;

    public CoursesManager() {
        this.courseList = new ArrayList<>(
                Arrays.asList(
                        new Course("001", "JavaScript desde 0"),
                        new Course("002", "HTML Foundations")
                )
        );
        this.studentList = new ArrayList<>(
                Arrays.asList(
                        new Student(idToAssignToStudents++, "Daniel", "daniel@gmail.com"),
                        new Student(idToAssignToStudents++, "Estefany", "alina@gmail.com")
                )
        );
    }

    public static void showMenu(CoursesManager coursesManager, Scanner scanner) {
        var inputGetter = new InputGetter(scanner);
        System.out.println("***********************************************************");
        System.out.println("Bienvenido al Gestor de Cursos");
        System.out.println("***********************************************************");
        System.out.println();

        boolean isMenuOpened = true;
        while (isMenuOpened) {
            System.out.println();
            System.out.println("********************* Menú *********************");
            var optionsMessage = """
                    0. Salir.
                    1. Añadir curso.
                    2. Registrar estudiante en el sistema.
                    3. Mostrar estudiantes registrados en el sistema.
                    4. Inscribir estudiante en un curso.
                    5. Mostrar estudiantes inscritos en un curso.
                    """;
            System.out.println(optionsMessage);
            var option = inputGetter.get("Ingresa la opción que deseas realizar: ", Scanner::nextInt);
            switch (option) {
                case 0 -> isMenuOpened = false;
                case 1 -> {
                    System.out.println();
                    System.out.println("Añadiendo Curso...");
                    System.out.println();

                    // -*******************-

                    var code = inputGetter.get("Código del curso: ", Scanner::nextLine);
                    scanner.nextLine();
                    var name = inputGetter.get("Nombre del curso: ", Scanner::nextLine);
                    var newCourse = new Course(code, name);
                    coursesManager.addCourseToList(newCourse);

                    System.out.println();
                    System.out.println("¡Curso agregado con éxito!");
                    System.out.println();
                }
                case 2 -> {
                    System.out.println();
                    System.out.println("Registrando estudiante en el sistema...");
                    System.out.println();

                    // -*******************-

                    var name = inputGetter.get("Nombre del estudiante: ", Scanner::nextLine);
                    scanner.nextLine();
                    var email = inputGetter.get("Email del estudiante: ", Scanner::nextLine);
                    var newStudent = new Student(idToAssignToStudents++, name, email);
                    coursesManager.registerStudent(newStudent);

                    System.out.println();
                    System.out.println("¡Estudiante registrado con éxito en el sistema!");
                    System.out.println();
                }
                case 3 -> {
                    System.out.println();
                    System.out.println("Mostrando estudiantes registrados en el sistema...");
                    System.out.println();

                    // -*******************-
                    if (coursesManager.getStudentList().isEmpty()) {
                        System.out.println("No hay estudiantes registrados en este momento");
                    }

                    listStudents(coursesManager.getStudentList());
                    System.out.println();
                }
                case 4 -> {
                    System.out.println();
                    System.out.println("Inscribiendo estudiante en un curso...");
                    System.out.println();

                    // -*******************-

                    if (coursesManager.getCourseList().isEmpty()) {
                        System.out.println();
                        System.out.println("No hay cursos creados en el momento. Inténtalo más tarde");
                        System.out.println();
                        break;
                    }

                    // Check student registration
                    var studentIdToEnroll = inputGetter.get("Introduce el ID del estudiante: ", Scanner::nextInt);
                    var studentToEnrollSearch = coursesManager.getStudentList().stream().filter(student -> student.getId() == studentIdToEnroll).findFirst();
                    if (studentToEnrollSearch.isEmpty()) {
                        System.out.println();
                        System.out.printf("Estudiante con ID '%d' no encontrado%n", studentIdToEnroll);
                        System.out.println();
                        break;
                    }

                    var studentToEnroll = studentToEnrollSearch.get();


                    System.out.println("************** Listado de cursos **************");
                    System.out.println();

                    listCourses(coursesManager.getCourseList());
                    System.out.println();

                    // Ask user for course to enroll student in
                    scanner.nextLine();
                    var promptToAskForCourseId = String.format("Ingresa el Código del curso para inscribir a '%s': ", studentToEnroll.getName());
                    var selectedCourseCode = inputGetter.get(promptToAskForCourseId, Scanner::nextLine);
                    var selectedCourseSearch = coursesManager.getCourseList().stream().filter(course -> course.getCode().equals(selectedCourseCode)).findFirst();
                    if (selectedCourseSearch.isEmpty()) {
                        System.out.printf("Curso con Código '%s' inválido.%n", selectedCourseCode);
                        break;
                    }

                    // Check if student is already enrolled
                    var selectedCourse = selectedCourseSearch.get();

                    var enrolledStudentSearch = selectedCourse.getStudentList().stream().filter(student -> student.equals(studentToEnroll)).findFirst();

                    if (enrolledStudentSearch.isPresent()) {
                        System.out.printf("Estudiante '%s' ya está registrado en el curso '%s' %n", enrolledStudentSearch.get().getName(), selectedCourse.getName());
                        break;
                    }

                    // Enroll student otherwise
                    selectedCourse.enrollStudent(studentToEnroll);

                    System.out.println();
                    System.out.printf("Estudiante registrado con éxito en el curso: '%s'%n", selectedCourse.getName());
                    System.out.println();
                }
                case 5 -> {
                    System.out.println();
                    System.out.println("Mostrando estudiantes inscritos en un curso...");
                    System.out.println();

                    // -*******************-

                    var courseList = coursesManager.getCourseList();
                    if (courseList.isEmpty()) {
                        System.out.println();
                        System.out.println("No hay cursos creados en el momento. Inténtalo más tarde");
                        System.out.println();
                    }

                    listCourses(courseList);
                    System.out.println();

                    scanner.nextLine();
                    var courseCodeToSelect = inputGetter.get("Ingresa el Código del curso para mostrar sus estudiantes inscritos: ", Scanner::nextLine);
                    var selectedCourseSearch = courseList.stream().filter(course -> course.getCode().equals(courseCodeToSelect)).findFirst();
                    if (selectedCourseSearch.isEmpty()) {
                        System.out.println();
                        System.out.printf("Curso con código '%s' no existe.", courseCodeToSelect);
                        System.out.println();
                        break;
                    }

                    var selectedCourse = selectedCourseSearch.get();

                    var studentListToShow = selectedCourse.getStudentList();

                    if (studentListToShow.isEmpty()) {
                        System.out.println();
                        System.out.printf("El curso '%s' aún no tiene estudiantes inscritos%n", selectedCourse.getName());
                        System.out.println();
                        break;
                    }

                    System.out.println();
                    System.out.printf("******** Estudiantes registrados en '%s'********%n", selectedCourse.getName());
                    System.out.println();
                    listStudents(studentListToShow);
                }
                default -> {
                    System.out.println("Opción seleccionada inválida. Inténtalo de nuevo.");
                    System.out.println();
                }
            }

        }
    }

    public static void listStudents(List<Student> studentList) {
        var tableHeader = String.format("| %-3s | %-15s | %-30s |",
                "ID", "Nombre", "Email");
        System.out.println(tableHeader);
        System.out.println("-".repeat(tableHeader.length()));

        // Table Body
        for (Student student : studentList) {
            var tableRow = String.format("| %-3d | %-15s | %-30s |",
                    student.getId(), student.getName(), student.getEmail());
            System.out.println(tableRow);
        }
    }

    public static void listCourses(List<Course> courseList) {
        var tableHeader = String.format("| %-6s | %-30s | %-31s |",
                "Código", "Nombre", "Número de estudiantes inscritos");
        System.out.println(tableHeader);
        System.out.println("-".repeat(tableHeader.length()));

        // Table Body
        for (Course course : courseList) {
            var studentListCount = course.getStudentList().size();
            var tableRow = String.format("| %-6s | %-30s | %-31d |",
                    course.getCode(), course.getName(), studentListCount);
            System.out.println(tableRow);
        }
    }

    // Methods
    public void addCourseToList(Course course) {
        this.courseList.add(course);
    }

    public void registerStudent(Student student) {
        this.studentList.add(student);
    }

    // Getters/Setters
    public List<Course> getCourseList() {
        return courseList.stream().toList();
    }

    public List<Student> getStudentList() {
        return studentList.stream().toList();
    }
}
