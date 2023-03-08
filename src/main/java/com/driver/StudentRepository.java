package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class StudentRepository {

    Map<String,Student> studentMap=new HashMap<>();
    Map<String,Teacher> teacherMap=new HashMap<>();

    Map<String,String> pairStudentTeacher=new HashMap<>();
    Map<String, List<String>> studentListofTeacher=new HashMap<>();

    public void addStudent(Student student)
    {
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher)
    {
        teacherMap.put(teacher.getName(),teacher);
        studentListofTeacher.put(teacher.getName(), new ArrayList<>());
    }

    public void addStudentTeacherPair(String student, String teacher)
    {
        pairStudentTeacher.put(student,teacher);
        studentListofTeacher.get(teacher).add(student);
    }

    public Student getStudentByName(String name)
    {
        if(studentMap.containsKey(name))
        {
            return studentMap.get(name);
        }
        return null;
    }

    public Teacher getTeacherByName(String name){
        if(teacherMap.containsKey(name))
            return teacherMap.get(name);
        return null;
    }

    public List<String> getStudentsByTeacherName(String name)
    {
        return studentListofTeacher.get(name);
    }

    public List<String> getAllStudents()
    {
        List<String> students=new ArrayList<>();

        for(String stud : studentMap.keySet()){
            students.add(stud);
        }

        return students;
    }

    public void deleteTeacherByName(String teacher)
    {
        for(String stu : pairStudentTeacher.keySet())
        {
            if(pairStudentTeacher.get(stu).equals(teacher))
            {
                pairStudentTeacher.remove(stu);
                studentMap.remove(stu);
            }
        }

        studentListofTeacher.remove(teacher);
        teacherMap.remove(teacher);

    }

    public void deleteAllTeachers(){
        teacherMap.clear();
        pairStudentTeacher.clear();
        studentListofTeacher.clear();
    }
}
