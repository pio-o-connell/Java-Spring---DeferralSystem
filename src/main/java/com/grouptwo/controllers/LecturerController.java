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
import com.grouptwo.domain.Lecturer;
import com.grouptwo.service.DeferralService;
import com.grouptwo.service.LecturerService;
import com.grouptwo.service.ModuleService;
import com.grouptwo.service.ProgrammeService;


/**
 * @author Salome Halpin
 *
 */

@Controller
@RequestMapping("/admin/lecturer")
public class LecturerController {

		
		@Autowired
		LecturerService lecturerService;
		
		@Autowired
		ProgrammeService programmeService;
		
		@Autowired
		ModuleService moduleService;
		
		@Autowired
		DeferralService deferralService;
		
		/**
		 * 
		 * This method will list all lecturers
		 * @author Salome Halpin
		 */
		@RequestMapping(value="/listall", method = RequestMethod.GET) 
		public String listAll(ModelMap model) {			
				
				List<Lecturer> listLecturers=lecturerService.listLecturers();
				model.addAttribute("lecturers", listLecturers);
				model.addAttribute("message", "List of Lecturers");
			    return "displayLecturers";			
			} 
		
		/**
		 * 
		 * This method will display the details of one lecturer
		 * @author Salome
		 */
		@RequestMapping(value= "/display/id/{id}", method = RequestMethod.GET)
		public String displayLecturerById(@PathVariable String id, ModelMap model){
			//get lecturer by id and get their details
			Lecturer lecturer = lecturerService.getLecturer(id);
			model.addAttribute("message", "Lecturer "+ id);
			model.addAttribute("firstname", lecturer.getFirstName());
			model.addAttribute("lastname", lecturer.getLastName());
			model.addAttribute("email", lecturer.getEmail());
			
			//get the programme the lecturer is registered on
			String programmeID ="No programme";
			try {
	//			programmeID = programmeDAO.getLecturerProgrammeId(id);
				} catch (Exception e) {
				System.out.println("no programme");
				
			}
			
			model.addAttribute("programme", programmeID);	
			
			//get the lecturer's registered modules
			ArrayList<String> moduleIDList = new ArrayList<String>();
			try {
	
			} catch (Exception e) {
				System.out.println("No modules");
				
			}
			model.addAttribute("modules", moduleIDList);
			
			//get the lecturer's deferrals
			ArrayList<Deferral> deferralAllList = new ArrayList<Deferral>();
			try {
				List<Deferral> deferralAllDBList = deferralService.listDeferralsByCoordinatorId(id);
				for(Deferral deferral: deferralAllDBList){
					deferralAllList.add(deferral);
				}
			} catch (Exception e) {
				System.out.println("No deferrals");
				
			} 
			model.addAttribute("deferrals",deferralAllList);
			
			//get the lecturer's unapproved deferrals
			ArrayList<Deferral> deferralUnappList = new ArrayList<Deferral>();
			try {
				List<Deferral> deferralUnappDBList = deferralService.listUnapprovedDeferralsByCoordinatorId(id);
				for(Deferral deferral: deferralUnappDBList){
					deferralUnappList.add(deferral);
				}
			} catch (Exception e) {
				System.out.println("No unapproved deferrals");
				
			} 
			model.addAttribute("unappDeferrals",deferralUnappList);
			
			return "displayLecturer";
		}
		
		/**
		 *
		 * This method will delete one lecturer
		 *  @author Salome Halpin
		 */
		@RequestMapping(value= "/delete/id/{id}", method = RequestMethod.GET)
		public String deleteLecturerById(@PathVariable String id, ModelMap model){
			lecturerService.deleteLecturer(id);
			model.addAttribute("message", "Lecturer "+ id + " has been deleted");
			
			List<Lecturer> listLecturers=lecturerService.listLecturers();
			model.addAttribute("lecturers", listLecturers);
			return "displayLecturers";
		}
		
		/**
		 * This method will produce a form to modify a lecturer's details 
		 * 
		 */
		@RequestMapping(value= "/modify/id/{id}", method = RequestMethod.GET)
		public String modifyLecturer(@PathVariable String id, ModelMap model){
			Lecturer lecturerModify = lecturerService.getLecturer(id);
			model.addAttribute("message", "The lecturer's name can now be modified");
			model.addAttribute("lectId", lecturerModify.getLectId());
			model.addAttribute("firstName", lecturerModify.getFirstName());
			model.addAttribute("lastName", lecturerModify.getLastName());
			model.addAttribute("email", lecturerModify.getEmail());
			model.addAttribute("lecturer", new Lecturer());
			
			return "modifyLecForm";
		}
		
		/**
		 * This method will modify the lecturer's last name and then display the lecturer's details  
		 * @author Julia Foden
		 */		
		@RequestMapping(value="/modify/id/{lectId}/lastname/{lastName}", method = RequestMethod.GET)
		public String displayModifiedLecturer(@PathVariable String lectId, @PathVariable String lastName, ModelMap model){
			try {
				lecturerService.updateLecturer(lectId, lastName);
				model.addAttribute("message", "Lecturer "+ lectId+ " has been modified");
			} catch (Exception e) {
				model.addAttribute("message", "Modification of lecturer "+ lectId+ " has failed");
				
			}
			List<Lecturer> listLecturers=lecturerService.listLecturers();
			model.addAttribute("lecturers", listLecturers);
			return "displayLecturers";
		}
		
	
		/**
		 * 
		 * This method will produce the form to add a lecturer
		 * @author Salome Halpin
		 */
		@RequestMapping(value= "/addNew", method = RequestMethod.GET)
		public String addNewLecturer(ModelMap model){
			model.addAttribute("lecturer", new Lecturer());
			return "newLecturer";
		}
		
		/**
		 * This method will add a new lecturer to the database
		 * @author Salome Halpin
		 *
		 */
		@RequestMapping(value= "/addNew", method = RequestMethod.POST)
		public String displayNewLecturer(@ModelAttribute ("lecturer") @Valid Lecturer lecturer, BindingResult result, 
				ModelMap model){
			
			if(result.hasErrors()){
				return "newLecturer";
			}	
			
			try {
				model.addAttribute("id", lecturer.getLectId());
				model.addAttribute("firstname", lecturer.getFirstName());
				model.addAttribute("lastname", lecturer.getLastName());
				model.addAttribute("email", lecturer.getEmail());
				
				lecturerService.createLecturer(lecturer.getLectId(), lecturer.getFirstName(),lecturer.getLastName(),
						lecturer.getEmail());
				model.addAttribute("message", "Lecturer " + lecturer.getLectId()+ " has been added");
				model.addAttribute("lecturers", lecturerService.listLecturers());
			} catch (Exception e) {
				model.addAttribute("message", "Creation of new lecturer failed.");
				model.addAttribute("lecturers", lecturerService.listLecturers());
				
			}
			
			
			return "displayLecturers";
		}
		
		/**
		 * This method will produce a dropdown menu to search for a lecturer by id 
		 * 
		 */
		@RequestMapping(value= "/advancedlist", method = RequestMethod.GET)
		public String lecturerSearchById(ModelMap model){
			List<Lecturer> lecturerIdList = lecturerService.listLecturers();
			model.addAttribute("lecturerIdList", lecturerIdList);
			model.addAttribute("lecturer", new Lecturer());
			return "lecturerSearch";
		}
		
		/**
		 * This method will display the lecturer's details when searched for by Id
		 * 
		 */
		@RequestMapping(value="/lecturerSearchResult",method=RequestMethod.POST)
		public String displayLecturerSeachById(@ModelAttribute("lecturer")Lecturer lecturer, ModelMap model){
			String id = lecturer.getLectId();
			model.addAttribute("message", "Lecturer "+ id);
			model.addAttribute("id", id);
			model.addAttribute("firstname",lecturerService.getLecturer(id).getFirstName());
			model.addAttribute("lastname", lecturerService.getLecturer(id).getLastName());
			model.addAttribute("email", lecturerService.getLecturer(id).getEmail());
	
			//get the lecturer's deferrals
			ArrayList<Deferral> deferralAllList = new ArrayList<Deferral>();
			try {
				List<Deferral> deferralAllDBList = deferralService.listDeferralsByCoordinatorId(id);
				for(Deferral deferral: deferralAllDBList){
					deferralAllList.add(deferral);
				}
			} catch (Exception e) {
				System.out.println("No deferrals");
				
			} 
			model.addAttribute("deferrals",deferralAllList);
			
			//get the lecturer's unapproved deferrals
			ArrayList<Deferral> deferralUnappList = new ArrayList<Deferral>();
			try {
				List<Deferral> deferralUnappDBList = deferralService.listUnapprovedDeferralsByCoordinatorId(id);
				for(Deferral deferral: deferralUnappDBList){
					deferralUnappList.add(deferral);
				}
			} catch (Exception e) {
				System.out.println("No unapproved deferrals");
				
			} 
			model.addAttribute("unappDeferrals",deferralUnappList);
			
			return "displayLecturer";
		}
		
		
	}   
	