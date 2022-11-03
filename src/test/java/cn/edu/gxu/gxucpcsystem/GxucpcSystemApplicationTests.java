package cn.edu.gxu.gxucpcsystem;

import cn.edu.gxu.gxucpcsystem.service.MedalService;

import cn.edu.gxu.gxucpcsystem.domain.Medal;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class GxucpcSystemApplicationTests {

	@Test
	void contextLoads() {

	}
@Autowired
	MedalService medalService;
	@Test
	public void createMg(){
		Medal medal = new Medal();
		medal.setContestId(123);
		medal.setName("test");
		medal.set_id("123");
		medalService.addMedal(medal);
	}

}
