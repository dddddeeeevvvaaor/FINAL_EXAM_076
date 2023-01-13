/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.b.exam.FINAL_EXAM;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Prizandeva Oktura Rizqy_20200140076
 */
@RestController
@CrossOrigin
@RequestMapping("/exam")
public class examController {

    Exam exam = new Exam();
    ExamJpaController examJpa = new ExamJpaController();

    @GetMapping
    public List<Exam> getAll() {
        List<Exam> exam = new ArrayList<>();
        try{
            exam = examJpa.findExamEntities();
        }catch(Exception e){
        }
        return exam;
    }

    @GetMapping("/{id}")
    public Exam getExamById(int id) {
        Exam exam = new Exam();
        try{
            exam = examJpa.findExam(id);
        }catch(Exception e){
        }
        return exam;
    }

    @PostMapping
    public String insertData(HttpEntity<String> requestdata) {
        String message = "Success insert new data";
        try { 
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            exam = map.readValue(json_receive, Exam.class);
            examJpa.create(exam);
        } catch (Exception e) {
            message = "Failed to insert new data";
        }
        return message;
    }

    @PutMapping
    public String updateExam(HttpEntity<String> ngambil) {
        String message = "berhasil mengubah data";
        try{
            String json_receive = ngambil.getBody();
            ObjectMapper map = new ObjectMapper();
            exam = map.readValue(json_receive, Exam.class);
            examJpa.edit(exam);
        }catch(Exception e){
            message = "gagal mengubah data";
        }
        return message;
    }

    @DeleteMapping()
    public String deleteExam(HttpEntity<String> ngambil) {
        String message = "berhasil menghapus data";
        try{
            String json_receive = ngambil.getBody();
            ObjectMapper map = new ObjectMapper();
            exam = map.readValue(json_receive, Exam.class);
            examJpa.destroy(exam.getId());
        }catch(Exception e){
            message = "gagal menghapus data";
        }
        return message;
    }

}
