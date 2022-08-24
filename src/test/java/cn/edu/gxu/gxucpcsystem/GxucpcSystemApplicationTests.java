package cn.edu.gxu.gxucpcsystem;

import cn.edu.gxu.gxucpcsystem.Service.PlayerService;
import cn.edu.gxu.gxucpcsystem.dao.mysql.PlayerDao;
import cn.edu.gxu.gxucpcsystem.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GxucpcSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	public PlayerService playerService;

	@Test
	void testQueryPlayer() {
		playerService.getByPages(1, 20, 111);
	}

}
