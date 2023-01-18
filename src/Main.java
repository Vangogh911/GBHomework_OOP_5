//Создать информационную систему позволяющую работать с сотрудниками некой компании \ студентами вуза \ учениками школы
//(прямая отсылка к первому семинару “введение в базы данных”)

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Infrastructure infrastructure = new Infrastructure();

        System.out.println(infrastructure.getAllInfo(1));
        System.out.println(infrastructure.getAllInfo(2));
        System.out.println(infrastructure.getAllInfo(3));
        System.out.println(infrastructure.getAllInfo(33));
        System.out.println(infrastructure.getAllInfo(11));
        infrastructure.showBirthYear("2008");
        infrastructure.showAddress(7);
        infrastructure.showStatus(2);
        infrastructure.showClass(3);
    }
}

class Infrastructure {
    public Infrastructure() {
        init();
    }

    Db db;

    public String getAllInfo(int idStudent) {
        String street;
        String status;
        String classInd;
        String phone;
        try {
            for (int i = 0; i < db.students.size(); i++) {
                if (db.students.get(i).getId() == idStudent) {
                    Student s = db.students.get(i);

                    if (s.getAddress() == 0) {
                        street = "None";
                    } else {
                        street = db.addresses.get(s.getAddress() - 1).streetName;
                    }

                    if (s.getStatus() == 0) {
                        status = "None";
                    } else {
                        status = db.statuses.get(s.getStatus() - 1).status;
                    }

                    if (s.getClassIndex() == 0) {
                        classInd = "None";
                    } else {
                        classInd = db.classes.get(s.getClassIndex() - 1).classIndex;
                    }

                    if (s.getPhone() == 0) {
                        phone = "None";
                    } else {
                        phone = db.phones.get(s.getPhone() - 1).phone;
                    }
                    return String.format("%d %s %s %s %s %s %s",
                            s.getId(),
                            s.getName(),
                            s.getBirthYear(),
                            street,
                            status,
                            classInd,
                            phone);
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());;
        }
        return "Информация отсутствует.";
    }

    public void showBirthYear(String year) {
        try {
            System.out.println("Ученики запрашиваемого года рождения:");
            for (int i = 0; i < db.students.size(); i++) {
                if (db.students.get(i).getBirthYear() != null) {
                    if (db.students.get(i).getBirthYear().contains(year)) {
                        System.out.println(getAllInfo(db.students.get(i).getId()));
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());;
        }
    }

    public void showAddress(int addressId) {
        try {
            System.out.println("Ученики проживающие по запрашиваемой улице:");
            for(int i = 0; i<db.students.size(); i++){
                if(db.students.get(i).getAddress() == addressId){
                    System.out.println(getAllInfo(db.students.get(i).getId()));;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());;
        }
    }

    public void showStatus(int statusId) {
        try {
            System.out.println("Ученики запрашиваемой успеваемости:");
            for(int i = 0; i<db.students.size(); i++){
                if(db.students.get(i).getStatus() == statusId){
                    System.out.println(getAllInfo(db.students.get(i).getId()));;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());;
        }
    }

    public void showClass(int classId) {
        try {
            System.out.println("Ученики запрашиваемого класса:");
            for(int i = 0; i<db.students.size(); i++){
                if(db.students.get(i).getClassIndex() == classId){
                    System.out.println(getAllInfo(db.students.get(i).getId()));;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());;
        }
    }

    Db init() {
        db = new Db();

        db.students.add(new Student.StudentBuilder().id(1).name("Буханова Жанна").birthYear("2010").address(1).status(1).classIndex(1).phone(1).build());
        db.students.add(new Student.StudentBuilder().id(2).name("Волков Никита").birthYear("2006").address(2).status(2).classIndex(4).phone(2).build());
        db.students.add(new Student.StudentBuilder().id(3).name("Голева Алина").birthYear("2009").address(3).status(3).classIndex(2).phone(3).build());
        db.students.add(new Student.StudentBuilder().id(4).name("Емельянов Арсений").birthYear("2008").address(4).status(1).classIndex(3).phone(4).build());
        db.students.add(new Student.StudentBuilder().id(5).name("Казакова Надежда").birthYear("2010").address(5).status(2).classIndex(1).phone(5).build());
        db.students.add(new Student.StudentBuilder().id(6).name("Морозова Ольга").birthYear("2006").address(6).status(3).classIndex(4).phone(6).build());
        db.students.add(new Student.StudentBuilder().id(7).name("Неклюдова Мария").birthYear("2009").address(7).status(1).classIndex(2).phone(7).build());
        db.students.add(new Student.StudentBuilder().id(8).name("Поляков Данил").birthYear("2008").address(8).status(2).classIndex(3).phone(8).build());
        db.students.add(new Student.StudentBuilder().id(9).name("Сковородская Карина").birthYear("2008").address(9).status(3).classIndex(3).phone(9).build());
        db.students.add(new Student.StudentBuilder().id(10).name("Тараканов Артём").birthYear("2006").address(10).status(1).classIndex(4).phone(10).build());
        db.students.add(new Student.StudentBuilder().id(11).name("Василий Пупкин").build());
        db.students.add(new Student.StudentBuilder().id(12).name("Юлия Зуева").birthYear("2008").build());

        db.addresses.add(new Address(1, "Энергетиков"));
        db.addresses.add(new Address(2, "Ленина"));
        db.addresses.add(new Address(3, "Воровского"));
        db.addresses.add(new Address(4, "Степана Разина"));
        db.addresses.add(new Address(5, "Победы"));
        db.addresses.add(new Address(6, "Аношкина"));
        db.addresses.add(new Address(7, "Чичерина"));
        db.addresses.add(new Address(8, "Молодогвардейцев"));
        db.addresses.add(new Address(9, "Шота Руставели"));
        db.addresses.add(new Address(10, "Гагарина"));
        db.addresses.add(new Address(11, "Дзержинского"));
        db.addresses.add(new Address(12, "Бажова"));
        db.addresses.add(new Address(13, "Кулибина"));
        db.addresses.add(new Address(14, "Сергея Герасимова"));
        db.addresses.add(new Address(15, "Жукова"));

        db.statuses.add(new Status(1, "Отличник"));
        db.statuses.add(new Status(2, "Ударник"));
        db.statuses.add(new Status(3, "Троечник"));
        db.statuses.add(new Status(4, "Двоечник"));

        db.classes.add(new ClassIndex(1, "5А"));
        db.classes.add(new ClassIndex(2, "6А"));
        db.classes.add(new ClassIndex(3, "7Б"));
        db.classes.add(new ClassIndex(4, "9А"));

        db.phones.add(new Phone(1, "89270569009"));
        db.phones.add(new Phone(2, "89271189845"));
        db.phones.add(new Phone(3, "89379797230"));
        db.phones.add(new Phone(4, "89093313065"));
        db.phones.add(new Phone(5, "88005553535"));
        db.phones.add(new Phone(6, "89276222498"));
        db.phones.add(new Phone(7, "89119012450"));
        db.phones.add(new Phone(8, "89678087775"));
        db.phones.add(new Phone(9, "89878209939"));
        db.phones.add(new Phone(10, "89272291788"));

        return db;
    }
}

class Db {
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Address> addresses = new ArrayList<>();
    ArrayList<Status> statuses = new ArrayList<>();
    ArrayList<ClassIndex> classes = new ArrayList<>();
    ArrayList<Phone> phones = new ArrayList<>();
}

class Student {
    private int id;
    private int address;
    private String name;
    private String birthYear;
    private int status;
    private int classIndex;
    private int phone;

    public Student(StudentBuilder studentBuilder) {

        if (studentBuilder == null) {
            throw new IllegalArgumentException("Неверная инициализация студента.");
        }
        if (studentBuilder.id <= 0) {
            throw new IllegalArgumentException("Неверный идентификатор студента.");
        }
        if (studentBuilder.name == null || studentBuilder.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Неверное имя студента.");
        }
        this.id = studentBuilder.id;
        this.address = studentBuilder.address;
        this.name = studentBuilder.name;
        this.birthYear = studentBuilder.birthYear;
        this.status = studentBuilder.status;
        this.classIndex = studentBuilder.classIndex;
        this.phone = studentBuilder.phone;
    }

    public int getId() {
        return id;
    }

    public int getAddress() {
        if (address > 0) {
            return address;
        }else {
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public String getBirthYear() {
        if (birthYear != null) {
            return birthYear;
        }else {
            return "None";
        }
    }

    public int getStatus() {
        if (status > 0) {
            return status;
        }else {
            return 0;
        }
    }

    public int getClassIndex() {
        if (classIndex > 0) {
            return classIndex;
        }else {
            return 0;
        }
    }

    public int getPhone() {
        if (phone > 0) {
            return phone;
        }else {
            return 0;
        }
    }

    public static class StudentBuilder {
        private int id;
        private int address;
        private String name;
        private String birthYear;
        private int status;
        private int classIndex;
        private int phone;

        public StudentBuilder() {
            super();
        }

        public StudentBuilder id(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder address(int address) {
            this.address = address;
            return this;
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder birthYear(String birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public StudentBuilder status(int status) {
            this.status = status;
            return this;
        }

        public StudentBuilder classIndex(int classIndex) {
            this.classIndex = classIndex;
            return this;
        }

        public StudentBuilder phone(int phone) {
            this.phone = phone;
            return this;
        }

        public Student build() {
            Student stud = null;
            if (validateStudent()) {
                stud = new Student(this);
            } else {
                System.out.println("Объявлено недостаточное количество аргументов.");
            }
            return stud;
        }

        private boolean validateStudent() {
            return (id > 0 && name != null && !name.trim().isEmpty());
        }
    }
}

class Address {
    int id;
    String streetName;

    public Address(int id, String streetName) {
        this.id = id;
        this.streetName = streetName;
    }
}

class Status {
    int id;
    String status;

    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }
}

class ClassIndex {
    int id;
    String classIndex;

    public ClassIndex(int id, String classIndex) {
        this.id = id;
        this.classIndex = classIndex;
    }
}

class Phone {
    int id;
    String phone;

    public Phone(int id, String phone) {
        this.id = id;
        this.phone = phone;
    }
}
