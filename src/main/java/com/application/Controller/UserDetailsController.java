package com.application.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.application.Entity.FileInfo;
import com.application.Entity.UserDetailsInfo;
import com.application.Entity.UserLogin;
import com.application.Enum.Role;
import com.application.Enum.Status;
import com.application.Service.FileInfoService;
import com.application.Service.UserInfoService;

@Controller
//"message", "no file is to upload select file"
public class UserDetailsController {

	private UserInfoService userService;
	private FileInfoService fileinfoService;

	@Autowired
	public UserDetailsController(UserInfoService userService, FileInfoService fileinfoService) {
		super();
		this.userService = userService;
		this.fileinfoService = fileinfoService;
	}

	@RequestMapping(value = "/")
	public String indexview() {
		
		
		return "index";

	}
	@RequestMapping(value = "/userregisteration")
	public String showRegisterationform(ModelMap map) {
		map.addAttribute("user", new UserDetailsInfo());
		return "registerationform";

	}

//	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
//	public String saveuser(@ModelAttribute("userinfo") UserDetailsInfo userinfo, ModelMap map) {
//
//		UserDetailsInfo fetchuser = userService.findByEmail(userinfo.getEmail());
//
//		if (fetchuser != null) {
//
//			map.put("message", "email is already  registerd ");
//			return "errormsg";
//
//		} else {
//
//			userinfo.setAvailable_files(0);
//			userinfo.setTotalfiles_uploaded(0);
//			userinfo.setRole(Role.USER);
//
//			UserDetailsInfo saveuser = userService.save(userinfo);
//
//			map.addAttribute("user", saveuser);
//			return "userfetch";
//		}
//
//	}

	@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	public String Showloginpag(ModelMap map)

	{
		map.addAttribute("user", new UserLogin());
		return "login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userlogin(@ModelAttribute UserLogin login, ModelMap map) {

		UserDetailsInfo userinfo = userService.findByEmail(login.getEmail());
		if (userinfo != null) {

			if (userinfo.getPassword().equals(login.getPassword())) {

				if (userinfo.getRole().equals(Role.USER)) {

					map.addAttribute("user", userinfo);

					return "userfetch";

				} else if (userinfo.getRole().equals(Role.ADMIN)) {
					map.addAttribute("admin", userinfo);

					Iterable<UserDetailsInfo> listusers = userService.findAll();

					map.addAttribute("users", listusers);
					return "admin";

				}
			} else {
				map.put("message", "password is not valid");
				return "errormsg";
			}

		} else {
			map.put("message", "Email is not registerd");
			return "errormsg";
		}
		return "loginpage";

	}

	@RequestMapping(value = "/admindetails/{id}", method = RequestMethod.GET)
	public String admindetails(ModelMap map, @PathVariable("id") long id) {
		UserDetailsInfo fetchadmin = userService.findById(id);
		map.addAttribute("user", fetchadmin);

		return "admindetails";

	}

	@RequestMapping(value = "/userdetails/{id}", method = RequestMethod.GET)
	public String userdetails(ModelMap map, @PathVariable("id") long id) {
		UserDetailsInfo fetchadmin = userService.findById(id);
		map.addAttribute("user", fetchadmin);
		return "userdetails";

	}

	@RequestMapping(value = "/userfiles/{id}", method = RequestMethod.GET)
	public String listoffilesbyuser(ModelMap map, @PathVariable("id") long id) {
		UserDetailsInfo user = userService.findById(id);
		List<FileInfo> filelist = fileinfoService.findByuserIdAndStatus(user.getId(), Status.ACTIVE);
		map.addAttribute("user", user);
		map.addAttribute("list", filelist);
		return "fileinfo";

	}



	@RequestMapping(value = "/edituser/{id}")
	public String edit(@PathVariable long id, ModelMap map) {
		UserDetailsInfo userdata = userService.findById(id);
		map.addAttribute("user", userdata);
		return "updateform";
	}

	@PostMapping(value = "/updateuser")
	public String updateuser(@ModelAttribute("userdetails") UserDetailsInfo userdetails, ModelMap map) {

		UserDetailsInfo userinfo = userService.findById(userdetails.getId());
		if (userdetails.getEmail() != null) {
			userinfo.setEmail(userdetails.getEmail());
		}
		if (userdetails.getFirstName() != null) {
			userinfo.setFirstName(userdetails.getFirstName());
		}
		userinfo.setLastName(userdetails.getLastName());
		if (userdetails.getPassword() != null) {
			userinfo.setPassword(userdetails.getPassword());
		}
		userinfo.setRole(userinfo.getRole());
		UserDetailsInfo saveuser = userService.save(userinfo);

		map.addAttribute("user", saveuser);
		map.addAttribute("message", "updatedsuccussfully");
		if (saveuser.getRole().equals(Role.USER)) {
			return "userfetch";
		} else {
			if (saveuser.getRole().equals(Role.ADMIN)) {
				
				map.addAttribute("user", saveuser);
				return "admindetails";
			}
		}
		return "updateform";
	}

	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
		List<FileInfo> files = fileinfoService.findByuserid(id);
		if (files.size() > 0) {
			for (FileInfo file : files) {
				fileinfoService.deleteById(file.getId());
			}
		}
		return "index";

	}
	
	

	@RequestMapping(value = "/upload/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String uploadfile(ModelMap map, @PathVariable("id") long id, @RequestParam("file") MultipartFile file)
			throws IOException {
		System.out.println(id);
		if (file.isEmpty() || file == null || file.getOriginalFilename() == null) {
			map.addAttribute("message", "no file is to upload select file");
			return "errormsg";
		} else if (file != null) {
			UserDetailsInfo userdata = userService.findById(id);
			FileInfo filedetails = new FileInfo();
			Date date = new Date();
			filedetails.setFilename(file.getOriginalFilename());
			filedetails.setUserid(userdata);
			filedetails.setStatus(Status.ACTIVE);
			filedetails.setUpdatedate(date);
			FileInfo savefile = fileinfoService.save(filedetails);
			System.out.println(savefile.toString());
			
		
			List<FileInfo> fetchfile = fileinfoService.findByuserIdAndStatus(id, Status.ACTIVE);

			long count = 1;
			userdata.setTotalfiles_uploaded(userdata.getTotalfiles_uploaded() + count);
			userdata.setAvailable_files(fetchfile.size());
			UserDetailsInfo userdetails = userService.save(userdata);

			map.addAttribute("user", userdetails);

			map.addAttribute("list", fetchfile);
			return "fileinfo";
		}
		return "fileinfo";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletefile(ModelMap map, @PathVariable("id") long id) {
	
		FileInfo status = fileinfoService.findById(id);
		FileInfo file = new FileInfo();
		file.setId(id);
		file.setStatus(Status.INACTIVE);
		file.setUserid(status.getUserid());
		file.setUpdatedate(new Date());
		file.setCreateddate(status.getCreateddate());
		file.setFilename(status.getFilename());
		FileInfo savefile = fileinfoService.save(file);
		System.out.println(savefile.toString());
		UserDetailsInfo fetchuser = userService.findById(file.getUserid().getId());

		List<FileInfo> listfiles = fileinfoService.findByuserIdAndStatus(fetchuser.getId(), Status.ACTIVE);
//
//		UserDetails user = userService.findByEmail(fetchuser.getEmail());
		fetchuser.setAvailable_files(listfiles.size());
		UserDetailsInfo saveuser = userService.save(fetchuser);
		map.addAttribute("user", saveuser);
		map.addAttribute("list", listfiles);
		return "fileinfo";

	}

}
