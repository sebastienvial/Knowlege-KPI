package com.bobst.knowledge.controllers;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.bobst.knowledge.models.KPI_ACTIVITY;
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
	
	@GetMapping("/")
	public String index0(Model model) {		
		return ("redirect:/kpi");
	}
	 
	
	@GetMapping("/login")
	public String login(Model model) {
	    // Retrieve the current context authentication
	   	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
	    model.addAttribute("isAdmin", isAdmin);
			
		return "views/login";
	}
	 
	 
	@GetMapping("/kpi")
	public String index(Model model, HttpSession session) {
		// Retrieve the configuration of user
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
		    	 
			return "views/configuration";
	}
	 
	@PostMapping("/saveConfig")
	public String saveConfig(@ModelAttribute Configuration conf, HttpServletRequest request) {
		 
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
		 
		 return "redirect:/kpi";
	}

	
	@GetMapping("/createUser")
	public String createUser(Map<String, Object> model) { 		
		model.put("user", new KPI_USER());
		model.put("lstPrimaryPl", userService.getAllPrimaryPl());
		model.put("lstPosition", userService.getAllPosition());
		model.put("lstSite", userService.getAllSite());
		return ("views/createUser");
	}
	
	 
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam String email,Map<String, Object> model) { 		
		KPI_USER uUser = userService.getUserByEmail(email);
		uUser.setLocationId(userService.getSiteFromLocationId(uUser.getLocationId()));
		 
		model.put("user", uUser);
		model.put("lstPrimaryPl", userService.getAllPrimaryPl());
		model.put("lstPosition", userService.getAllPosition());
		model.put("lstSite", userService.getAllSite());
		return ("views/updateUser");
	}
	 
	 
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam String email, Model model, RedirectAttributes redirAttrs) { 		
		if (userService.getUserByEmail(email) == null) {
			redirAttrs.addFlashAttribute("error", "The user don't exist");
		} else {
			userService.repUser.delete(userService.getUserByEmail(email));
			redirAttrs.addFlashAttribute("success", "The user " + email + " has been deleted");
		}			
		 
		return ("redirect:/users");
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
	 
	 
	@PostMapping("/saveUser")
	public String saveUser(@Validated @ModelAttribute KPI_USER user, BindingResult errors, Model model, RedirectAttributes redirAttrs) {
			
			KPI_USER bdUser = userService.getUserByEmail(user.getEmail());
			if (bdUser == null) {
			
				if (!errors.hasErrors()) 
				{
					if(user.validate()) {
						user.setLocationId(userService.getLocationIdFromSite(user.getLocationId())); // retrieve locationId from site
	 					System.out.println("Save new user " + user.toString());
	 					redirAttrs.addFlashAttribute("success", "User created");
						userService.repUser.save(user);
					} else {
						redirAttrs.addFlashAttribute("error", "The user is not complete or Email is wrong");
					}
									
				}
			} else {
		    	redirAttrs.addFlashAttribute("success", "The user already exist, please update if necessary");
		    	return ("redirect:/updateUser?email=" + user.getEmail());
		    	
		    }
		    	    
			return ("redirect:/createUser");
	}
		
		
	@PostMapping("/updateUser")
	public String updateUser(@Validated @ModelAttribute KPI_USER user, BindingResult errors, Model model, RedirectAttributes redirAttrs) {
			user.setLocationId(userService.getLocationIdFromSite(user.getLocationId())); // retrieve locationId from site.
			System.out.println(user.toString());
			userService.repUser.save(user);
			redirAttrs.addFlashAttribute("success", "The user was updated");
			
			return ("redirect:/users");
	}
		
		
	@GetMapping("/createActivity")
	public String createActivity(Map<String, Object> model) { 		
			model.put("activity", new KPI_ACTIVITY());
			model.put("lstEmail", userService.getAllEmail());
			return ("views/createActivity");
		 }
		 
		 @PostMapping("/saveActivity")
			public String saveActivity(@Validated @ModelAttribute KPI_ACTIVITY activity, BindingResult errors, Model model, RedirectAttributes redirAttrs) {
			 
				KPI_ACTIVITY bdActivity = userService.getActivityByEmailAndPeriod(activity.getEMAIL(), activity.getPERIOD());
			 	
			    if (bdActivity == null) {
			 
					if (!errors.hasErrors()) 
					{
						if(activity.validate()) {
						
							System.out.println("Save new activity " + activity.toString());
							userService.repActivity.save(activity);
							redirAttrs.addFlashAttribute("success", "Activity created");
						} else
							redirAttrs.addFlashAttribute("error", "The EMAIL must be completed");
							
					}
			    } else {
			    	redirAttrs.addFlashAttribute("success", "The actvitiy already exist, plese update if necessary");
			    	return ("redirect:/updateActivity?email=" + activity.getEMAIL() + "&period=" + activity.getPERIOD());
			    	
			    }
			    
			    
				return ("redirect:/createActivity");
		}
		
		 
		@PostMapping("/updateActivity")
		public String updateSaveActivity(@Validated @ModelAttribute KPI_ACTIVITY activity, BindingResult errors, Model model, RedirectAttributes redirAttrs) {
			 System.out.println(activity.toString());
			 userService.updateActivity(activity.getVIEWS(), activity.getCONTRIBUTIONS(), activity.getWIKIS(), activity.getKBA(), activity.getEMAIL(), activity.getPERIOD());
			 
			 redirAttrs.addFlashAttribute("success", "The actvitiy was updated");    	
		
			 return ("redirect:/users");
		}
		 
		@GetMapping("/updateActivity")
		public String updateActivity(@RequestParam String email, @RequestParam String period, Map<String, Object> model) { 	
			KPI_ACTIVITY activity = userService.getActivityByEmailAndPeriod(email, period); 
			model.put("activity", activity);
			
			return ("views/updateActivity");
		}
		
		
		@GetMapping("/deleteActivity")
		public String deleteActivity(@RequestParam String email, @RequestParam String period, Model model, RedirectAttributes redirAttrs) { 		
			 
			if (userService.getActivityByEmailAndPeriod(email, period) == null) {
				redirAttrs.addFlashAttribute("error", "The activity for this period don't exist");
			} else {
				userService.repActivity.delete(userService.getActivityByEmailAndPeriod(email, period));
				redirAttrs.addFlashAttribute("success", "The activity of  " + email + " in the period " + period + " has been deleted");
			}			
			 
			return ("redirect:/users");
		}

}