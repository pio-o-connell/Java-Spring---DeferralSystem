package com.grouptwo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grouptwo.domain.Deferral;
import com.grouptwo.domain.Module;
import com.grouptwo.domain.Student;
import com.grouptwo.service.DeferralService;
import com.grouptwo.service.ModuleService;
import com.grouptwo.service.ProgrammeService;
import com.grouptwo.service.StudentService;


/**
 * @author Julia Foden
 *
 */

@Controller
@RequestMapping("/admin/student")
public class StudentController {

		
		@Autowired
		StudentService studentService;
		
		@Autowired
		ProgrammeService programmeService;
		
		@Autowired
		ModuleService moduleService;
		
		@Autowired
		DeferralService deferralService;
		
		/**
		 * 
		 * This method will list all students
		 * @author Julia Foden
		 */
		@RequestMapping(value="/listall", method = RequestMethod.GET) 
		public String listAll(ModelMap model) {			
				
				List<Student> listStudents=studentService.listStudents();
				model.addAttribute("students", listStudents);
				model.addAttribute("message", "List of Students");
			    return "displayStudents";			
			} 
		
		/**
		 * 
		 * This method will display the details of one student
		 * @author Julia Foden
		 */
		@RequestMapping(value= "/display/id/{id}", method = RequestMethod.GET)
		public String displayStudentById(@PathVariable String id, ModelMap model){
			//get student by id and get their details
			Student student = studentService.getStudent(id);
			model.addAttribute("message", "Student "+ id);
			model.addAttribute("firstname", student.getFirstName());
			model.addAttribute("lastname", student.getLastName());
			model.addAttribute("email", student.getEmail());
			
			//get the programme the student is registered on
			String programmeID =null;
			try {
				programmeID = programmeService.getStudentProgrammeId(id);
				} catch (Exception e) {
				System.out.println("no programme");
			}
			
			model.addAttribute("programme", programmeID);	
			
			//get the student's registered modules
			ArrayList<String> moduleIDList = new ArrayList<String>();
			try {
				List<Module> moduleList = moduleService.listStudentModules(id);
				for(Module module: moduleList){
					String moduleId = module.getModuleId();
					moduleIDList.add(moduleId);
				}
			} catch (Exception e) {
				System.out.println("No modules");
			}
			model.addAttribute("modules", moduleIDList);
			
			//get the student's deferrals
			ArrayList<Deferral> deferralList = new ArrayList<Deferral>();
			try {
				List<Deferral> deferralDBList = deferralService.listDeferralsByStudent(id);
				for(Deferral deferral: deferralDBList){
					deferralList.add(deferral);
				}
			} catch (Exception e) {
				System.out.println("No deferrals");
				
			} 
			model.addAttribute("deferrals",deferralList);
			
			return "displayStudent";
		}
		
		/**
		 *
		 * This method will delete one student and return a confirmation message 
		 *  @author Julia Foden
		 */
		@RequestMapping(value= "/delete/id/{id}", method = RequestMethod.GET)
		public String deleteStudentById(@PathVariable String id, ModelMap model){
			Student student = studentService.getStudent(id);
			studentService.deleteStudent(id);
			model.addAttribute("message", "Student "+ id + " has been deleted");
			List<Student> listStudents=studentService.listStudents();
			model.addAttribute("students", listStudents);
			return "displayStudents";
		}
		
		/**
		 * This method will produce a form to modify a student's details 
		 *  @author Julia Foden
		 */
		@RequestMapping(value= "/modify/id/{id}", method = RequestMethod.GET)
		public String modifyStudent(@PathVariable String id, ModelMap model){
			Student studentModify = studentService.getStudent(id);
			model.addAttribute("message", "The student can now be modified");
			model.addAttribute("studentId", studentModify.getStudentId());
			model.addAttribute("firstName", studentModify.getFirstName());
			model.addAttribute("lastName", studentModify.getLastName());
			model.addAttribute("email", studentModify.getEmail());
			model.addAttribute("student", new Student());
			
			return "modifyStudentForm";
		}
		
		/**
		 * This method will modify the student's last name and then display the students list with a confirmation message 
		 * @author Julia Foden
		 */		
		@RequestMapping(value="/modify/id/{studentId}/lastname/{lastName}", method = RequestMethod.GET)
		public String displayModifiedStudent(@PathVariable String studentId, @PathVariable String lastName, ModelMap model){
			try {
				studentService.updateStudent(studentId, lastName);
				model.addAttribute("message", "Student "+ studentId+ " has been modified");
			} catch (Exception e) {
				model.addAttribute("message", "Modification of student "+ studentId+ " failed");
				
			}
			List<Student> listStudents=studentService.listStudents();
			model.addAttribute("students", listStudents);
			return "displayStudents";
		}
		
		
		/**
		 * 
		 * This method will produce the form to add a student
		 * @author Julia Foden
		 */
		@RequestMapping(value= "/addNew", method = RequestMethod.GET)
		public String addNewStudent(ModelMap model){
			model.addAttribute("student", new Student());
			return "newStudent";
		}
		
		/**
		 * This method will add a new student to the database and return  confirmation message and the list of all students
		 * @author Julia Foden
		 *
		 */
		@RequestMapping(value= "/addNew", method = RequestMethod.POST)
		public String displayNewStudent(@ModelAttribute ("student") @Valid Student student, BindingResult result, 
				ModelMap model){
			
			if(result.hasErrors()){
				return "newStudent";
			}	
			
			try {
				model.addAttribute("id", student.getStudentId());
				model.addAttribute("firstname", student.getFirstName());
				model.addAttribute("lastname", student.getLastName());
				model.addAttribute("email", student.getEmail());
				
				studentService.createStudent(student.getStudentId(), student.getFirstName(),student.getLastName(),
						student.getEmail());
				model.addAttribute("message", "Student " + student.getStudentId()+ " has been added");
				model.addAttribute("students", studentService.listStudents());
			} catch (Exception e) {
				System.out.println("creation of student failed");
				model.addAttribute("message", "Creation of new student failed. ");
				model.addAttribute("students", studentService.listStudents());
				
			}
			
			
			return "displayStudents";
		}
		
		/**
		 * This method will produce a dropdown menu to search for a student by id 
		 * @author Julia Foden
		 *
		 */
		@RequestMapping(value= "/advancedlist", method = RequestMethod.GET)
		public String studentSearchById(ModelMap model){
			List<Student> studentIdList = studentService.listStudents();
			model.addAttribute("studentIdList", studentIdList);
			model.addAttribute("student", new Student());
			return "studentSearch";
		}
		
		/**
		 * This method will display the student's details when searched for by Id
		 * @author Julia Foden
		 *
		 */
		@RequestMapping(value="/studentSearchResult",method=RequestMethod.POST)
		public String displayStudentSeachById(@ModelAttribute("student")Student student, ModelMap model){
			String id = student.getStudentId();
			model.addAttribute("message", "Student "+ id);
			model.addAttribute("id", id);
			model.addAttribute("firstname", studentService.getStudent(id).getFirstName());
			model.addAttribute("lastname", studentService.getStudent(id).getLastName());
			model.addAttribute("email", studentService.getStudent(id).getEmail());
	
			//get the programme the student is registered on
			String programmeID = null;
			try {
				programmeID = programmeService.getStudentProgrammeId(id);
				} catch (Exception e) {
				System.out.println("no programme");
				
			}
			
			model.addAttribute("programme", programmeID);	
			
			//get the student's registered modules
			ArrayList<String> moduleIDList = new ArrayList<String>();
			try {
				List<Module> moduleList = moduleService.listStudentModules(id);
				for(Module module: moduleList){
					String moduleId = module.getModuleId();
					moduleIDList.add(moduleId);
				}
			} catch (Exception e) {
				System.out.println("No modules");
				
			}
			model.addAttribute("modules", moduleIDList);
			
			//get the student's deferrals
			ArrayList<Deferral> deferralList = new ArrayList<Deferral>();
			try {
				List<Deferral> deferralDBList = deferralService.listDeferralsByStudent(id);
				for(Deferral deferral: deferralDBList){
					deferralList.add(deferral);
				}
			} catch (Exception e) {
				System.out.println("No deferrals");
				
			} 
			model.addAttribute("deferrals",deferralList);
			
			return "displayStudent";
		}
		
	}   
	







