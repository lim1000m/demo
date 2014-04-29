package com.ese.demo;

import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.ese.demo.service.HomeService;
import com.ese.entity.Item;
import com.ese.entity.Order;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/** HomeService */
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private MessageSource message;

	@ModelAttribute
	public List<Item> commonProName() {
		return homeService.getHomeTest();
	}
	
	@RequestMapping(value="/find.do")
	public String find(Model model) {
		logger.info("Get the list from the database by Hibernate");
		model.addAttribute(homeService.getHomeTest());
		return "list";
	}
	
	@RequestMapping(value="/delete.do")
	public String delete(
			Model model,
			Item item) {
		homeService.delete(item);
		return "redirect:find.do";
	}

	@RequestMapping(value="/update.do")
	public String update(
			Model model,
			Item item
			) {
		model.addAttribute("update");
		model.addAttribute(homeService.findOne(item));
		return "regedit";
	}
	
	@RequestMapping(value="/action.do", method=RequestMethod.POST)
	public String insert(
			Locale locale,
			Model model,
			@RequestParam(value="mode", defaultValue="insert") String mode,
			@Valid Item item
			) {
		if(mode.equals("update")) {
			homeService.update(item);						
		}else {
			homeService.insert(item);
		}
		model.addAttribute("result", "true");
		return "redirect:find.do";
	}
	
	@RequestMapping(value="/findJoin.do")
	public String findJoin(
			Model model,
			Order order
			) {
		
		model.addAttribute(homeService.findJoin(order));
		return "joinList";
	}
	
	/**
	 * 컨트롤러에서 사용할  Binder를 커스터마이징 할때 
	 * 해당 컨트롤러에서 @InitBinder Annotation을 갖는 메서드를 구현해야하며 
	 * 리턴 값은 void여야 한다.
	 */
	@InitBinder
	public void initBinder() {
	}
}
