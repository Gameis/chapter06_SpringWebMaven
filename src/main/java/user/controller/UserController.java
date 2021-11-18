package user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import user.bean.UserDTO;
import user.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="/writeForm", method=RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}
	
	@RequestMapping(value="/checkId", method=RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String id) {
		
		return userService.checkId(id);
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
		return;
	}
	
	@GetMapping("/list")
	public String listForm() {
		return "/user/list";
	}
	
//	@PostMapping("/getUserList")
//	@ResponseBody
//	public JSONObject getUserList() {
//		return userService.getUserList();
//	}
	
	@PostMapping("/getUserList")
	@ResponseBody
	public List<UserDTO> getUserList() {
		return userService.getUserList();
	}
	
	@GetMapping("/modifyForm")
	public String modifyForm() {
		return "/user/modifyForm";
	}
	
	@PostMapping("/getUser")
	@ResponseBody
	public UserDTO getUser(@RequestParam String searchId) {
			return userService.getUser(searchId);
	}
	
	@PostMapping("/modify")
	@ResponseBody
	public void modify(@ModelAttribute UserDTO userDTO) {
		userService.modify(userDTO);
		return;
	}
	
	@GetMapping("/deleteForm")
	public String deleteForm() {
		return "/user/deleteForm";
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public void delete(@RequestParam String searchId) {
		userService.delete(searchId);
	}
}
