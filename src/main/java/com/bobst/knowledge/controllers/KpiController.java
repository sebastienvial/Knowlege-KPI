package com.bobst.knowledge.controllers;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bobst.knowledge.models.Configuration;
import com.bobst.knowledge.models.KPI_USER;
import com.bobst.knowledge.services.KuserService;

@Controller
public class KpiController  {
	
	 @Autowired
	 private KuserService userService;
	 
	 private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
	 private DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
	 private YearMonth ceMois = YearMonth.now();

	 private String thisY = formatterYear.format(ceMois);
	 private String lastY = formatterYear.format(ceMois.minusYears(1));
	 
	 private String periodActuelle = formatter.format(ceMois.minusMonths(1));
	 
	 //private String period = periodActuelle;
	 //private String site = "ALL";
	 //private String pl = "ALL";
	 //private String annualType = "views";
	 
	 //private SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	 //private SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
	 
	 //public String getPeriodNow() {
		// Date act = new Date();
		// return sdfYear.format(act) + "-" + sdfMonth.format(act);		 
	 //}
	
	 @GetMapping("/")
	 public String index0(Model model) {
		 
   	 	//this.period = formatter.format(ceMois.minusMonths(1));
		//this.thisY = formatterYear.format(ceMois);
		//this.lastY = formatterYear.format(ceMois.minusYears(1));
		
		return ("redirect:/kpi");
	 }
	 
	 @GetMapping("/login")
		public String login(Map<String, Object> model) {		
			return "views/login";
	 }
	 
	 @GetMapping("/kpi")
	    public String index(Model model, HttpSession session) {
		   //récuppère la config user
		 	List<String> config = (List<String>) session.getAttribute("MY_SESSION_CONFIG");
		    String period;
		    String site;
		    String pl;
		    
			if (config == null) {
		    	period = periodActuelle;
		    	site = "ALL";
		    	pl = "ALL";
		    } else {
		    	period = config.get(0);
		    	site = config.get(1);
		    	pl = config.get(2);		    	
		    }
		    
		   Map<String,Integer> kpiAttributes = new HashMap<String, Integer>(); 
		   
		   Integer nbrUsersConnected = userService.getNbrUsers(period,"UserConnected",site,pl);
		   Integer nbrUsersActive = userService.getNbrUsers(period,"UserActive",site,pl);
		   Integer nbrUsersContributing = userService.getNbrOf(period,"UserContributing",site,pl);
		   
		   Integer deltaActiveUsers;
		   Integer deltaContributingUsers;
		   
		   if (nbrUsersConnected==0) {
			   deltaActiveUsers = 0;
			   deltaContributingUsers = 0;
		   } else {
			   deltaActiveUsers = nbrUsersActive*100/nbrUsersConnected;
			   deltaContributingUsers = nbrUsersContributing*100/nbrUsersConnected;
		   }
			   
		   
		   kpiAttributes.put("nbrConnectedUsers", nbrUsersConnected);
		   kpiAttributes.put("nbrActiveUsers", nbrUsersActive); 
		   
		   kpiAttributes.put("deltaActiveUsers", deltaActiveUsers);  
		   
		   
	       kpiAttributes.put("nbrContributingUsers", nbrUsersContributing );
	       kpiAttributes.put("deltaContributingUsers", deltaContributingUsers); 
		   
	        
	       kpiAttributes.put("nbrOfViews", userService.getNbrOf(period,"views",site,pl));
	       kpiAttributes.put("nbrOfContributions", userService.getNbrOf(period,"contributions",site,pl));
	       kpiAttributes.put("nbrNewArticles", userService.getNbrOf(period,"articles",site,pl));

		   
		   List<Object[]> lstRes = userService.getNbrOfYears(lastY,"views",site,pl);
		   for (Object[] r : lstRes) {
			   String tmp = (String) r[0];
			   kpiAttributes.put("nbrOfLastY" + tmp.substring(tmp.length()-2), Integer.parseInt(r[1].toString()));			   
		   }
		   
		   lstRes = userService.getNbrOfYears(thisY,"views",site,pl);
		   for (Object[] r : lstRes) {
			   String tmp = (String) r[0];
			   kpiAttributes.put("nbrOfThisY" + tmp.substring(tmp.length()-2), Integer.parseInt(r[1].toString()));			   
		   }
		   
		   lstRes = userService.getNbrOfYears(lastY,"contributions",site,pl);
		   for (Object[] r : lstRes) {
			   String tmp = (String) r[0];
			   kpiAttributes.put("nbrOfcLastY" + tmp.substring(tmp.length()-2), Integer.parseInt(r[1].toString()));			   
		   }
		   
		   lstRes = userService.getNbrOfYears(thisY,"contributions",site,pl);
		   for (Object[] r : lstRes) {
			   String tmp = (String) r[0];
			   kpiAttributes.put("nbrOfcThisY" + tmp.substring(tmp.length()-2), Integer.parseInt(r[1].toString()));			   
		   }
		   
		   lstRes = userService.getNbrUsersYears(lastY,"UserActive",site,pl);
		   for (Object[] r : lstRes) {
			   String tmp = (String) r[0];
			   kpiAttributes.put("nbrOfUsersaLastY" + tmp.substring(tmp.length()-2), Integer.parseInt(r[1].toString()));			   
		   }
		   
		   lstRes = userService.getNbrUsersYears(thisY,"UserActive",site,pl);
		   for (Object[] r : lstRes) {
			   String tmp = (String) r[0];
			   kpiAttributes.put("nbrOfUsersaThisY" + tmp.substring(tmp.length()-2), Integer.parseInt(r[1].toString()));			   
		   }
		   
		   lstRes = userService.getNbrUsersYears(lastY,"UserContributing",site,pl);
		   for (Object[] r : lstRes) {
			   String tmp = (String) r[0];
			   kpiAttributes.put("nbrOfUserscLastY" + tmp.substring(tmp.length()-2), Integer.parseInt(r[1].toString()));			   
		   }
		   
		   lstRes = userService.getNbrUsersYears(thisY,"UserContributing",site,pl);
		   for (Object[] r : lstRes) {
			   String tmp = (String) r[0];
			   kpiAttributes.put("nbrOfUserscThisY" + tmp.substring(tmp.length()-2), Integer.parseInt(r[1].toString()));			   
		   }
		 	        
	        
	        model.addAttribute("site", site);
	        model.addAttribute("pl", pl);
	        model.addAttribute("period", period);
	        
	        model.addAllAttributes(kpiAttributes);
	        
		 	return "views/kpi";
	    }
	 

	 @GetMapping("/configuration")
		 public String configuration(Map<String, Object> model, HttpSession session) {
		 	
		   // @SuppressWarnings("unchecked")
		    List<String> config = (List<String>) session.getAttribute("MY_SESSION_CONFIG");
		    Configuration conf = new Configuration();
			if (config == null) {
		    	conf.setPl("ALL");
		    	conf.setSite("ALL");
		    	conf.setPeriod(periodActuelle.substring(periodActuelle.length()-2));
		    } else {
		    	conf.setPeriod(config.get(0).substring(config.get(0).length()-2));
		    	
		    	if (config.get(0).equals(thisY)) 
		    		conf.setPeriod("00");
		    	if (config.get(0).equals(lastY)) 
		    		conf.setPeriod("99");
		    	
		    	conf.setSite(config.get(1));
		    	conf.setPl(config.get(2));		    	
		    }
		    
		    model.put("conf",conf);
		    
//		    List<String> config = (List<String>) session.getAttribute("MY_SESSION_CONFIG");
//		    
//		    if (config == null) {
//		    	config = new ArrayList<>();
//		    }
//		    
//		    model.put("config",config);
		    
//		 	Configuration conf = new Configuration();
//		 	conf.setPeriod(period.substring(period.length()-2));
//		 	conf.setSite(site);
//		 	conf.setPl(pl);
//		 	model.put("conf",conf);
		 
			 return "views/configuration";
		 }
	 
	 @PostMapping("/saveConfig")
	 public String saveConfig(@Validated @ModelAttribute Configuration conf, BindingResult errors, Model model, RedirectAttributes redirAttrs, HttpServletRequest request) {
		 
//		 @SuppressWarnings("unchecked")
		 List<String> config = new ArrayList<>();
		 String period = thisY + "-" + conf.getPeriod();
		 if (conf.getPeriod().equals("00"))
			 period = thisY;
		 if (conf.getPeriod().equals("99"))
			 period = lastY;
		 
		 config.add(period);
		 config.add(conf.getSite());
		 config.add(conf.getPl());
		 
		 request.getSession().setAttribute("MY_SESSION_CONFIG", config);
		 
//		 List<String> config = (List<String>) request.getSession().getAttribute("MY_SESSION_CONFIG");
//		 if (config == null) {
//		 	config = new ArrayList<>();
//		 	request.getSession().setAttribute("MY_SESSION_CONFIG", config);
//		 }
		 
		 
		 
//		 this.site = conf.getSite();
//		 this.pl = conf.getPl();
//		 
//		 if (conf.getPeriod().equals("99"))
//			 this.period =  this.lastY; // "2020-";
//		 else 
//			 if (conf.getPeriod().equals("00"))
//				 this.period = this.thisY;
//			 else
//				 this.period = this.thisY + "-" + conf.getPeriod();
//		 
		 return "redirect:/kpi";
	 }

	
	 /**
	  * Function called to add a new user
	  * 
	  * @param model
	  * @return
	  */
	 @GetMapping("/createUser")
	 public String createUser(Map<String, Object> model) 
	 { 		
		model.put("user", new KPI_USER());
		model.put("lstPrimaryPl", userService.getAllPrimaryPl());
		model.put("lstPosition", userService.getAllPosition());
		model.put("lstSite", userService.getAllSite());
		return ("views/createUser");
	 }
	 
	 @GetMapping("/updateUser")
	 public String updateUser(@RequestParam String email,Map<String, Object> model) 
	 { 		
		KPI_USER uUser = userService.getUserByEmail(email);
		uUser.setLocationId(userService.getSiteFromLocationId(uUser.getLocationId()));
		 
		model.put("user", uUser);
		model.put("lstPrimaryPl", userService.getAllPrimaryPl());
		model.put("lstPosition", userService.getAllPosition());
		model.put("lstSite", userService.getAllSite());
		return ("views/createUser");
	 }
	 
	 
	 @GetMapping("/deleteUser")
	 public String deleteUser(@RequestParam String email) 
	 { 		
		userService.repUser.delete(userService.getUserByEmail(email)); 
		return ("redirect:/kpi");
	 }

	  
	 @GetMapping("/users")
	 public String showUsers(Model model, HttpSession session ) {
		 //Retrieve the current session configuration
		 List<String> config = (List<String>) session.getAttribute("MY_SESSION_CONFIG");
		    String period;
		    String site;
		    String pl;
		    
			if (config == null) {
		    	period = periodActuelle;
		    	site = "ALL";
		    	pl = "ALL";
		    } else {
		    	period = config.get(0);
		    	site = config.get(1);
		    	pl = config.get(2);		    	
		    }
		  
		 // Retrieve the current context authentication
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		
		 System.out.println("je suis admin :" + isAdmin);
		 
		 model.addAttribute("isAdmin", isAdmin);
		 model.addAttribute("lstUsers", userService.getUsersFull(period,"UserActive",site,pl));
		 model.addAttribute("site", site);
	     model.addAttribute("pl", pl);
	     model.addAttribute("period", period);
		 return "views/showUsers";
	 }
	 
	 
	 /**
		 * Save a new user
		 * 
		 * @param user
		 * @param errors
		 * @param model
		 * @return
		 */
		@PostMapping("/saveUser")
		public String saveUser(@Validated @ModelAttribute KPI_USER user, BindingResult errors, Model model, RedirectAttributes redirAttrs) {
			
			if (!errors.hasErrors()) 
			{
				if(user.validate()) {
					user.setLocationId(userService.getLocationIdFromSite(user.getLocationId())); //récupère la locationId en fct du site getLocationIdFromSite(String site)
 					System.out.println("Sauvegarde du nouveau user " + user.toString());
					userService.repUser.save(user);
				} else
					throw new RuntimeException("The user is not complete ! Please fill all the fields");			
			}
			return ((errors.hasErrors()) ? "views/createUser" : "redirect:/kpi");
		}

	 
	@GetMapping("/logout")
	public void logout() {
		System.exit(0);
	}

}