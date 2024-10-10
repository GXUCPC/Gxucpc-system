package cn.edu.gxu.gxucpcsystem;

import cn.edu.gxu.gxucpcsystem.controller.admin.FormController;
import cn.edu.gxu.gxucpcsystem.domain.RegisterForm;
import cn.edu.gxu.gxucpcsystem.service.MedalService;

import cn.edu.gxu.gxucpcsystem.domain.Medal;
import cn.edu.gxu.gxucpcsystem.service.PlayerService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GxucpcSystemApplicationTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	PlayerService playerService;

	@Test
	public void createNewRegisterForm() {
		RegisterForm formObj = new RegisterForm();
		formObj.setCountPerUser(1);
		formObj.setTitle("114");
		formObj.setText("514");
		formObj.setFormItemList("");
		formObj.setFooter("Submit!");

		System.out.println(playerService.queryRegistrationFormIdByTitle("114514"));
	}
}
