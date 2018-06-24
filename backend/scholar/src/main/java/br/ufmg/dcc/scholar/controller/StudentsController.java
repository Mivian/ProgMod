package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.repository.StudentRepository;
import br.ufmg.dcc.scholar.service.CourseService;
import br.ufmg.dcc.scholar.service.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentsController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	Page<Student> list(@RequestParam int page, @RequestParam int size) {
		return this.studentService.findAll(PageRequest.of(page, size));
	}

	@GetMapping("/obterTodos")
	Iterable<Student> obterTodos() {
		return this.studentService.findAll();
	}

	@PostMapping("/salvar")
	Student salvar(@RequestBody Student dados) {
		return this.studentService.save(dados);
	}
	
	@GetMapping("/{id}")
	public Student retrieveStudent(@PathVariable long id) {
		Student student = this.studentService.findOne(id);
		return student;
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable long id) {
		this.studentService.deleteById(id);
	}
}
