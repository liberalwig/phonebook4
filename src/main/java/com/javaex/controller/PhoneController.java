package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	// 필드
	@Autowired
	private PhoneDao phoneDao;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	private String list(Model model) { // model이란 그릇에 컨트롤러가 정보 넣어두면 디스패처가 모델 가져감
		System.out.println("PhoneController > list() ");

		// 다오에서 리스트를 가져온다_ MVC방식으로 세 과정을 최대한 분리함
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList.toString());

		// 컨트롤러 --> DS데이터를 보낸다(model)
		model.addAttribute("personList", personList);

		// jsp정보를 리턴한다(view)
		return "list";
	}

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController > writeForm() ");

		return "writeForm";
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > write() ");

		phoneDao.personInsert(personVo);

		return "redirect: /phonebook4/phone/list";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("personId") int personId) {
		System.out.println("PhoneController > delete() ");

// 삭제
		phoneDao.personDelete(personId);

		return "redirect: /phonebook4/phone/list";
	}

	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	private String updateForm(@RequestParam("personId") int personId, Model model) {
		System.out.println("PhoneController > updateForm() ");

		PersonVo personVo = phoneDao.getPerson(personId);

		model.addAttribute("personVo", personVo);

		return "updateForm";
	}

	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > update");
		System.out.println(personVo);
		phoneDao.personUpdate(personVo);

		return "redirect: /phonebook4/phone/list";
	}
}
/*
 * @RequestMapping(value = "/view", method = { RequestMethod.GET,
 * RequestMethod.POST }) public String view(@RequestParam(value = "no") int no)
 * { System.out.println("@RequestParam으로 하기"); System.out.println(no +
 * "번글 가져오기");
 * 
 * return "writeForm"; }
 */

/*
 * 
 * @RequestMapping(value = "/writeForm", method = { RequestMethod.GET,
 * RequestMethod.POST })
 * 
 * public String writeForm() {
 * System.out.println("PhoneController > writeForm() ");
 * 
 * return "writeForm"; // return writeForm; 이라 써도 무방. WebUtil의 (path)에 return값
 * 넣는 메커니즘. }
 * 
 * @RequestMapping(value = "/write", method = { RequestMethod.GET,
 * RequestMethod.POST }) private String write(@RequestParam("name") String
 * name, @RequestParam("hp") String hp,
 * 
 * @RequestParam("company") String company) {
 * System.out.println("PhoneController > write() ");
 * 
 * System.out.println(name); System.out.println(hp);
 * System.out.println(company);
 * 
 * PersonVo personVo = new PersonVo(name, hp, company);
 * phoneDao.personInsert(personVo);
 * 
 * // 저장 return "redirect: /phone/list"; // redierect를 안 쓰면 phone/list파일을 찾는다 }
 * 
 */

/*
 * @RequestMapping(value = "/view/{no}", method = { RequestMethod.GET,
 * RequestMethod.POST }) public String view11(@PathVariable("no") int no) {
 * System.out.println("@PathVariable로 하기"); System.out.println(no + "번글 가져오기");
 * 
 * return "writeForm"; }¿
 * 
 * @RequestMapping(value = "{id}", method = { RequestMethod.GET,
 * RequestMethod.POST }) public String blog(@PathVariable(value = "id") String
 * id) { System.out.println("@PathVariable로 하기"); System.out.println(id +
 * "의 블로그입니다");
 * 
 * return "writeForm"; } }
 */

// 필드

// 생성자

// 메소드 gs

// 메소드 일반

/*
 * @RequestMapping(value = "/phone/writeForm", method = { RequestMethod.GET,
 * RequestMethod.POST }) public String writeForm() {
 * System.out.println("PhoneController > writeForm() ");
 * 
 * return "/WEB-INF/views/writeForm.jsp"; // return writeForm; 이라 써도 무방.
 * WebUtil의 (path)에 return값 넣는 메커니즘. }
 * 
 * 
 */
