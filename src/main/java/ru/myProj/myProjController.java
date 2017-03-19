package ru.myProj;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import ru.myProj.domain.Employee;
import ru.myProj.domain.Skill;
import ru.myProj.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.myProj.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by olegp_000 on 15.03.2017.
 */
@Controller
public class myProjController {
    private static final Logger log = Logger.getLogger(String.valueOf(myProjController.class));

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/download_file", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map<User, List<Skill>> userWithSkill = userService.getAllUsersWithSkills();
            Map<String, List<Skill>> employeeAndSkils = userService.getCityWithSkills();
            response.setContentType("application/vnd.ms-excel");
            String fileName = "file1.xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            HSSFWorkbook wb = new HSSFWorkbook();
            CreationHelper createHelper = wb.getCreationHelper();
            Sheet sheet0 = wb.createSheet("Сотрудники с навыками");
            Sheet sheet1 = wb.createSheet("Города с уникальными навыками");
            int i = 0;
            for (Map.Entry<User, List<Skill>> userWithSkillVal : userWithSkill.entrySet()) {
                Row row = sheet0.createRow(i);
                for (Skill skill : userWithSkillVal.getValue()) {
                    if (userWithSkillVal.getValue().size() > 1) {
                        row = sheet0.createRow(i);
                    }
                    row.createCell(0).setCellValue(userWithSkillVal.getKey().getName());
                    row.createCell(1).setCellValue(skill.getName());
                }
                i += 1;
            }
            int j = 0;
            for (Map.Entry<String, List<Skill>> employeeAndSkilsVal : employeeAndSkils.entrySet()) {
                for (Skill skill : employeeAndSkilsVal.getValue()) {
                    int flag = 0;
                    for (Map.Entry<String, List<Skill>> checkVals : employeeAndSkils.entrySet()) {
                        for (Skill skillFlag : checkVals.getValue()) {
                            if (skill.getName().equals(skillFlag.getName())) {
                                flag += 1;
                            }
                        }
                    }
                    if (flag < 2) {
                        Row row = sheet1.createRow(j);
                        row.createCell(0).setCellValue(employeeAndSkilsVal.getKey());
                        row.createCell(1).setCellValue(skill.getName());
                        j += 1;
                    }
                }
            }
            OutputStream out = response.getOutputStream();
            wb.write(out);
        }catch (Exception e){
            log.warning("Непредвиденная ошибка метода doDownload класса myProjController!");
        }
    }
}

