package com.example

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File
import java.io.FileInputStream
import kotlin.collections.*;

import java.nio.file.Files
import java.nio.file.Paths
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.text.SimpleDateFormat

import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource
import kotlin.jdbc.*

data class UserWeight(val uid: String? = "", val weight: String? = "")


@RestController
class MigrationController {

    fun BeforeClass() {
//        val path = Paths.get(MigrationController::class.main.getResource("book1.xlsx").toURI()).parent
//        BASE_DIR = path.toString()
    }

    fun get_total_rows():MutableList<UserWeight>
    {
        val path = "/Users/gakaki/Downloads/demo/src/main/resources/static/weight.xlsx"
        val file =  FileInputStream(File(path));
        val workbook =  XSSFWorkbook(file);
        val sheet = workbook.getSheetAt(0);

        val lastRowNum = sheet.getLastRowNum();
        System.out.println("last row_num is "+ lastRowNum)
        val lastCellNum = sheet.getRow(1).lastCellNum
        System.out.println("last cell_num is "+ lastCellNum)
        val c = mutableListOf<UserWeight>()

        for (i in 1..lastRowNum) {
            var row = sheet.getRow(i);
            if (row != null) {
                System.out.println("reading line is " + i);

                var cell0 = row.getCell(0);
                var cell1 = row.getCell(1);

                if (cell0 != null && cell1 != null) {
                    val user_weight = UserWeight(cell0.rawValue, cell1.rawValue)
                    System.out.println(user_weight.uid + " " + user_weight.weight);
                    c.add(user_weight)
                } else {
                    System.out.println(cell0.rawValue);
                }
            }
        }
        return c;
//                    if( cell != null ){
//                        var cellValue = cell.rawValue;
//                        if (cellValue != null)
//                            System.out.println("cell value is \n" + cellValue);
//                    }

//                for ( j in 0..lastRowNum) {
//                    var cell = row.getCell(j);
//                    if( cell != null ){
//                        var cellValue = cell.rawValue;
//                        if (cellValue != null)
//                            System.out.println("cell value is \n" + cellValue);
//                    }
//                }

    }

    fun update_db_weight():MutableList<UserWeight>
    {

        val c = this.get_total_rows()

        val driver = "com.mysql.cj.jdbc.Driver";
        val userName = "root";
//        val password = "z123456";
//        val jdbc_str = "jdbc:mysql://wowdsgn.com:3388/wowdsgn?autoReconnect=true&useSSL=false";

        val password = "WoWpass987";
        val jdbc_str = "jdbc:mysql://wowdsgn.com:3306/wow?autoReconnect=true&useSSL=false";

        try {

            Class.forName(driver).newInstance();
            val conn = DriverManager.getConnection(jdbc_str, userName,password);

            for ( row in c) {

//                var sql  = "REPLACE INTO `product_weight` (`entity_id`,`attribute_id`,`value`) VALUES (?,?,?) "//ON DUPLICATE KEY UPDATE value = VALUES(value)";
                var sql  = "REPLACE INTO `catalog_product_entity_decimal` (`entity_id`,`attribute_id`,`value`) VALUES (?,?,?) "//ON DUPLICATE KEY UPDATE value = VALUES(value)";

                var update_sm = conn.prepareStatement(sql);
                update_sm.setString(1, row.uid);
                update_sm.setString(2, "80");
                update_sm.setString(3, row.weight);
                var update_sm_done = update_sm.executeUpdate();
            }


            conn.close()
        } catch ( e:Exception ) {
            e.printStackTrace();
        }finally{
            return c
        }
    }

    @RequestMapping("/migration/weight")
    fun weight():MutableList<UserWeight> {

        val c = this.update_db_weight()
        return c
    }

}