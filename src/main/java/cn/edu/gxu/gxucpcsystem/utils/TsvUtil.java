package cn.edu.gxu.gxucpcsystem.utils;

import cn.edu.gxu.gxucpcsystem.Service.DomjudgeService;
import cn.edu.gxu.gxucpcsystem.domain.Domjudge;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TsvUtil {

    private static final ReentrantLock LOCK = new ReentrantLock();

    @Autowired
    DomjudgeService domjudgeService;

    /**
     * 创建一个tsv文件
     *
     * @param filename
     * @return
     * @throws IOException
     */
    private File createTsvFile(String filename) throws IOException {
        File file = new File("tsv");
        if (!file.exists()) {
            file.mkdir();
        }
        File res = new File("tsv/" + filename + ".tsv");
        if (res.exists()) {
            deleteTsvFile(res);
        }
        res.createNewFile();
        return res;
    }

    /**
     * 创建teams.tsv
     *
     * @throws IOException
     */
    private void createTeamsTsv(List<Domjudge> list) throws IOException {
        File file = createTsvFile("teams");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
        bw.write("teams\t1");
        bw.newLine();
        for (Domjudge domjudge : list) {
            Integer teamId = Integer.valueOf(domjudge.getUsername().replaceAll("[^(0-9)]", ""));
            String groupId = new Date(System.currentTimeMillis()).getYear() + 1900 + (domjudge.getPlayer().getGroup() ? "正式组" : "新生组");
            String teamName = domjudge.getPlayer().getUserName() + "-" + domjudge.getPlayer().getUserId();
            if (domjudge.getPlayer().getStar()) {
                teamName = "*" + teamName;
            }
            bw.write(teamId + "\t" + teamId + "\t" + groupId + "\t" + teamName + "\t" + domjudge.getPlayer().getUserName() + "\t" + domjudge.getPlayer().getUserName() + "\tCHN");
            bw.newLine();
        }
        bw.close();
        fos.close();
    }

    private void createAccountsTsv(List<Domjudge> list) throws IOException {

        File file = createTsvFile("accounts");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
        bw.write("accounts\t1");
        bw.newLine();
        for (Domjudge domjudge : list) {
            String accountType = "team";
            String fullName = domjudge.getPlayer().getUserName();
            String username = domjudge.getUsername();
            String password = domjudge.getPassword();
            bw.write(accountType + "\t" + fullName + "\t" + username + "\t" + password);
            bw.newLine();
        }
        bw.close();
        fos.close();
    }

    public byte[] createTsv(List<Domjudge> list) throws IOException {
        LOCK.lock();
        try {
            createTeamsTsv(list);
            createAccountsTsv(list);
            File file = new File("tsv.zip");
            if(file.exists()) {
                file.delete();
            }
            ZipUtil.compressFileToZip("tsv");
            return ZipUtil.fileToByte(file);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 删除tsv文件
     *
     * @param file
     * @return
     */
    private Boolean deleteTsvFile(File file) {
        return file.delete();
    }

}
