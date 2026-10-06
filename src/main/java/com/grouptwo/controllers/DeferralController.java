package com.grouptwo.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.grouptwo.domain.Deferral;
import com.grouptwo.domain.Mail;
import com.grouptwo.domain.Module;
import com.grouptwo.domain.Programme;
import com.grouptwo.domain.Student;
import com.grouptwo.service.DeferralService;
import com.grouptwo.service.ModuleService;
import com.grouptwo.service.ProgrammeService;
import com.grouptwo.service.StudentService;
import com.grouptwo.exceptions.FormUploadException;


@Controller
@RequestMapping("/admin/deferral")
public class DeferralController {

	@Autowired
	DeferralService deferralService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	ProgrammeService programmeService;
	@Autowired
	StudentService studentService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private Mail mail;
	
	private Module module;
	private int crnNumber;

	
	
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	public String listAll(ModelMap model) {
		Date date = new java.util.Date();
		List<Deferral> listDefferals = deferralService.listDeferrals();
		model.addAttribute("deferrals", listDefferals);
		model.addAttribute("message", "List of deferrals");
		model.addAttribute("now", date);
		return "displayDeferrals";
	}
	
	
	@RequestMapping(value = "/list/id/{programmeId}", method = RequestMethod.GET)
	public String listDeferralsByProgrammeId(@PathVariable String programmeId, ModelMap model) {
		List<Deferral> listDeferrals = deferralService.listDeferralsByProgramme(programmeId);
		model.addAttribute("deferrals", listDeferrals);
		return "displayDeferrals";
	}
	
	
	@RequestMapping(value = "/list/moduleId/{moduleId}", method = RequestMethod.GET)
	public String listDeferralsByModuleId(@PathVariable String moduleId, ModelMap model) {
		List<Deferral> listDeferrals = deferralService.listDeferralsByModule(moduleId);
		model.addAttribute("deferrals", listDeferrals);
		return "displayDeferrals";
	}
	
	
	@RequestMapping(value = "/list/coordinatorId/{coordinatorId}", method = RequestMethod.GET)
	public String listDeferralsByCoordinatorId(@PathVariable String coordinatorId, ModelMap model){
		List<Deferral> listDeferrals = deferralService.listDeferralsByCoordinatorId(coordinatorId);
		model.addAttribute("deferrals", listDeferrals);
		model.addAttribute("message", "All deferrals for "+ coordinatorId);
		return "displayDeferrals";
	}
	
	
	 
	@RequestMapping(value = "/list/unapproved/coordinatorId/{coordinatorId}", method = RequestMethod.GET)
	public String listUnapprovedDeferralsByCoordinatorId(@PathVariable String coordinatorId, ModelMap model){
		List<Deferral> listDeferrals = deferralService.listUnapprovedDeferralsByCoordinatorId(coordinatorId);
		model.addAttribute("deferrals", listDeferrals);
		model.addAttribute("message", "All unapproved deferrals for "+ coordinatorId);
		return "displayDeferrals";
	}
	
	

	 
	@RequestMapping(value = "/listall/{moduleId}/{studentId}", method = RequestMethod.GET)
	public String listApprovedDeferrals(@PathVariable String moduleId,@PathVariable String studentId, ModelMap model) {
		Date date = new java.util.Date();
		deferralService.approveModuleDeferral(studentId, moduleId);
		List<Deferral> listDefferals = deferralService.listDeferrals();
		model.addAttribute("deferrals", listDefferals);
		model.addAttribute("now", date);
		return "displayDeferrals";
	}
	

	@RequestMapping(value = "/advancedlist", method = RequestMethod.GET)
	public String advancedSearch() {
		return "displayDeferralsByStudent";
	}
	
	
	@RequestMapping(value = "/advancedlist/studentId/{studentId}", method = RequestMethod.GET)
	public String listDeferralsByStudent(@PathVariable String studentId,
			ModelMap model) {
		Date date = new java.util.Date();
		List<Deferral> listDefferals = deferralService.listDeferralsByStudent(studentId);
		model.addAttribute("deferrals", listDefferals);
		model.addAttribute("now", date);
		return "displayDeferrals";

	}
	
	
	
	
	
	/*This method will display a View for the user to enter his/her student Id*/
	
	@RequestMapping(value = "/addModuleDeferralPage1", method = RequestMethod.GET)
	public String addModuleDeferralPage1(ModelMap model) {
		List<Module> listModules = moduleService.listModules();
		model.addAttribute("moduleList",listModules );
		model.addAttribute("deferral", new Deferral());
		return "newModuleDeferralPage1";
	}
	
	/* Once the user enters their student Id, this method will display 
	 * the student's details and their corresponding modules.
	*/	
	
	@RequestMapping(value = "/addModuleDeferralPage1", method = RequestMethod.POST)
	public String displayModuleDeferralPage1(
			@ModelAttribute("deferral") @Valid Deferral deferral,BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "newModuleDeferralPage1";
		}
		
		//Get the student's FirstName,LastName and Registered Modules
		try{
		String studentId=deferral.getStudentId();
		List<Module> listStudentModules = moduleService.listStudentModules(studentId);
		Student student=studentService.getStudent(studentId);
		model.addAttribute("studentFirstName",student.getFirstName());
		model.addAttribute("studentLastName",student.getLastName());
		model.addAttribute("studentId",studentId);
		model.addAttribute("moduleList",listStudentModules );
		model.addAttribute("deferral", deferral);
		return "newModuleDeferralPage2";
		}
		catch(Exception e)
		{
			model.addAttribute("message", "Student ID could not be found, please try again");
			return "newModuleDeferralPage1";
		}

	}
	
	/* This method will create the deferral entry for the specfic module.
	*/	
	@RequestMapping(value = "/addModuleDeferralPage2", method = RequestMethod.POST)
	public String displayModuleDeferralPage2(
			@ModelAttribute("deferral") Deferral deferral,@RequestParam("file") MultipartFile file,
			BindingResult result, ModelMap model) {
		
		int id=0;

		if (result.hasErrors()) {
			return "/addModuleDeferralPage2";
		}
		
		try {
			 if (!file.isEmpty()) {
				 
				 validateImage(file);
				 
		
		try{
			/*This is a little trick im using! I need to get the CRN's for the modules.
			 * However my deferral model doesnt actually contain a CRN  parameter or setter. 
			 * It does however have a programme Id setter and getter.
			 * So im using this to pass through my crn value.*/
		crnNumber= Integer.parseInt(deferral.getProgrammeId());
		module=moduleService.getModuleByName(deferral.getModuleId(), crnNumber);
		
		// Create the module deferral
		id=deferralService.createModuleDeferralGetId(deferral.getStudentId(), module.getModuleId(), crnNumber);
		
		// Set file path and size
		 byte[] bytes = file.getBytes(); 
         File dir = new File(servletContext.getRealPath("/")+"/resources/form/uploadedForms");
         
         if (!dir.exists())
             dir.mkdirs();		                
      		 
         // Create the file on server
         String fileLocation=dir.getAbsolutePath()
                 + File.separator + id + ".pdf";
         
         File serverFile = new File(fileLocation);
         
         BufferedOutputStream stream = new BufferedOutputStream(
                 new FileOutputStream(serverFile));
         
         stream.write(bytes);
         stream.close();	
		}
		catch (Exception e)
		{
			model.addAttribute("message", "Creation of deferral failed, "+e.getLocalizedMessage());
			e.printStackTrace();
		}
		}	else{
				 model.addAttribute("message", "You failed to upload " + file.getOriginalFilename() + " because the file was empty.");
				 return "displayError"; 
			 }
			} catch(FormUploadException e){
				 model.addAttribute("message", "Creation of deferral failed. The system only supports PDFs.");
				 return "displayError"; 
			
			}	
		
		Date date = new java.util.Date();
		List<Deferral> listDefferals = deferralService.listDeferralsByStudent(deferral.getStudentId());
		model.addAttribute("deferrals", listDefferals);
		model.addAttribute("now", date);
		// If the deferral is successfully created, send Email and display the deferral list.
		if(id!=0)
		{
			//Send Email to CIT Admissions 
			Student student=studentService.getStudent(deferral.getStudentId());
			String studentName=student.getFirstName()+ " " + student.getLastName();
			mail.sendModuleDeferralMail(studentName,student.getStudentId(),module.getModuleId(), crnNumber);
			
			//display the deferral list
			return "displayDeferrals";
		}
		// If the deferral is not created succesfully, then display an error
		else{
			model.addAttribute("message", "Module Name did not match corresponding CRN");
			return "displayError"; 
		}
		
			 }

		private void validateImage(MultipartFile image){
			if(!image.getContentType().equals("application/pdf")){
				throw new FormUploadException("OnlyPDFaccepted");
			}
	}
	

	
	/*This method will display a View for the user to enter his/her student Id*/
	
	@RequestMapping(value = "/addProgrammeDeferralPage1", method = RequestMethod.GET)
	public String addProgrammeDeferralPage1(ModelMap model) {
		List<Programme> listProgrammes = programmeService.listProgrammes();
		model.addAttribute("programmeList",listProgrammes );
		model.addAttribute("deferral", new Deferral());
		return "newProgrammeDeferralPage1";
	}
	
	/* Once the user enters their student Id, this method will display 
	 * the student's details and their corresponding Programme.
	*/	
	
	@RequestMapping(value = "/addProgrammeDeferralPage1", method = RequestMethod.POST)
	public String displayProgrammeDeferralPage1(
			@ModelAttribute("deferral")  @Valid  Deferral deferral,BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "newProgrammeDeferralPage1";
		}
		
		//Get the student's FirstName,LastName and Registered Programme
		try{
		String studentId=deferral.getStudentId();
		Student student=studentService.getStudent(studentId);
		model.addAttribute("studentFirstName",student.getFirstName());
		model.addAttribute("studentLastName",student.getLastName());
		model.addAttribute("studentId",studentId);
		model.addAttribute("studentProgramme",programmeService.getStudentProgrammeId(studentId) );
		model.addAttribute("deferral", deferral);
		return "newProgrammeDeferralPage2";
		}
		catch(Exception e)
		{
			model.addAttribute("message", "Student ID could not be found, please try again");
			return "newModuleDeferralPage1";
		}
		

	}
	
	/* This method will create the deferral entry for the specfic programme and 
	 * all the corresponding modules.
	*/
	@RequestMapping(value = "/addProgrammeDeferralPage2", method = RequestMethod.POST)
	public String displayProgrammeDeferralPage2(
			@ModelAttribute("deferral") Deferral deferral,@RequestParam("file") MultipartFile file,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "newProgrammeDeferralPage2";
		}
		
		try{

			 if (!file.isEmpty()) {
				 
				 validateImage(file);
			

		try{		 
		//Create the programme deferral
		ArrayList<Integer> idList=deferralService.createProgrammeDeferralGetId(deferral.getStudentId(), deferral.getProgrammeId());

		
		// Set file path and size
		 byte[] bytes = file.getBytes(); 
         File dir = new File(servletContext.getRealPath("/")+"/resources/form/uploadedForms");
         
         if (!dir.exists())
             dir.mkdirs();		                
      		 
         for (int i = 0; i < idList.size(); i++) {
         // Create the files on server
         String fileLocation=dir.getAbsolutePath()
                 + File.separator + idList.get(i) + ".pdf";
         
         File serverFile = new File(fileLocation);
         
         BufferedOutputStream stream = new BufferedOutputStream(
                 new FileOutputStream(serverFile));
         
         
         stream.write(bytes);
         stream.close();
         }
		}
		catch (Exception e)
		{
			model.addAttribute("message", "Creation of deferral failed, "+e.getLocalizedMessage());
			e.printStackTrace();
		}
		}	else{
				 model.addAttribute("message", "You failed to upload " + file.getOriginalFilename() + " because the file was empty.");
				 return "displayError"; 
			 }
			} catch(FormUploadException e){
				 model.addAttribute("message", "Creation of deferral failed. The system only supports PDFs.");
				 return "displayError"; 
			
			}
		
		Date date = new java.util.Date();
		List<Deferral> listDefferals = deferralService.listDeferralsByStudent(deferral.getStudentId());
		model.addAttribute("deferrals", listDefferals);
		model.addAttribute("now", date);
		
		//Send Email to CIT Admissions 
		Student student=studentService.getStudent(deferral.getStudentId());
		String studentName=student.getFirstName()+ " " + student.getLastName();
		mail.sendProgrammeDeferralMail(studentName,student.getStudentId(),deferral.getProgrammeId());
		
		return "displayDeferrals";

	}
	

	@RequestMapping(value= "/delete/id/{id}", method = RequestMethod.GET)
	public String deleteStudentById(@PathVariable int id, ModelMap model){
		Deferral deferral = deferralService.getDeferral(id);
		deferralService.deleteDeferralById(id);
		model.addAttribute("message", "Deferral "+ id + " has been deleted");
		model.addAttribute("id", id);
		model.addAttribute("studentId", deferral.getStudentId());
		model.addAttribute("lectId", deferral.getLectId());
		model.addAttribute("progId", deferral.getProgrammeId());
		model.addAttribute("moduleId", deferral.getModuleId());
		model.addAttribute("approval", deferral.getApproval());
		return "displayDeferral";
	}
	

	@RequestMapping(value = "/modify/id/{id}", method = RequestMethod.GET) 
	public String modifyDeferral(@PathVariable int id, ModelMap model) { 
		Deferral deferralModify=deferralService.getDeferral(id);
		List<Module> listModules = moduleService.listModules();
		model.addAttribute("moduleList",listModules );
		model.addAttribute("message", "Deferal with id "+ id +" can now be modified");
		model.addAttribute("deferral", deferralModify);
		return "modifyDefForm";	} 
	
	@RequestMapping(value="/modify/id/{id}/approval/{approval}", method = RequestMethod.GET) 
	public String modifyDeferral(@PathVariable int id, @PathVariable String approval,  ModelMap model) {			
		deferralService.updateDeferral(id, approval);
		Deferral deferralModify=deferralService.getDeferral(id);
		model.addAttribute("message", "Deferral with id "+ id +" has been modified");
		model.addAttribute("id", id);
		model.addAttribute("studentId", deferralModify.getStudentId());
		model.addAttribute("lectId", deferralModify.getLectId());
		model.addAttribute("progId", deferralModify.getProgrammeId());
		model.addAttribute("moduleId", deferralModify.getModuleId());
		model.addAttribute("approval", approval);
		
		List<Deferral> listDefferals = deferralService.listDeferrals();
		model.addAttribute("deferrals", listDefferals);
		
		return "displayDeferrals";
				
	}
	
	
	
	@RequestMapping(value = "/downloadForm/id/{id}", method = RequestMethod.GET) 
	public @ResponseBody void downloadWithdrawalForm(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {   
		try {
	 
		// set the path file
		File fullPath = new File(servletContext.getRealPath("/")+"/resources/form/uploadedForms");
        File downloadFile = new File(fullPath+"/"+id + ".pdf");
        FileInputStream inputStream = new FileInputStream(downloadFile);
       
        // get MIME type of the file
        String mimeType = "application/pdf";         
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[1024];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }		   
 
        inputStream.close();
        outStream.close();
        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 
	
	
}