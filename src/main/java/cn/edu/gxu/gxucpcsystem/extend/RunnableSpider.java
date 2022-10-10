package cn.edu.gxu.gxucpcsystem.extend;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-09 5:04 PM
 */

@Component
public class RunnableSpider implements Runnable{
    @Value("${scoreBoardSpider}")
    private String path;

    public int status = 0;
    @Override
    public void run() {
        try{
            System.out.println(path);
            String[] cmd = new String[]{"python",path};
            Runtime.getRuntime().exec(cmd);
//            System.out.println(111);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ;
        }

    }
}
