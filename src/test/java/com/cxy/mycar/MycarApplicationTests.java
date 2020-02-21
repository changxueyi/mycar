package com.cxy.mycar;

import com.cxy.dao.SysMenuMapper;
import com.cxy.dao.SysUserMapper;
import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysMenu;
import com.cxy.pojo.SysUser;
import com.cxy.service.MenuService;
import com.cxy.service.SysUserService;
import com.cxy.utils.JsonHelper;
import com.cxy.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@SpringBootTest
class MycarApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SysUserService sysUserService;


    private  final Logger logger = LoggerFactory.getLogger(this.getClass());//对象方式初始化Log对象
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
        System.out.println("恭喜你，运行成功了");
    }
    //使用jackJson转为对象
    @Test
    public void test01() throws JsonProcessingException {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setOffset(1);
        queryDTO.setLimit(2);
        queryDTO.setSort("menu_id");
        List<SysMenu> a =sysMenuMapper.findMenuByPage(queryDTO);
/*        System.out.println(a.toString());*/
        JsonHelper jm = new JsonHelper();
        String json =  jm.toJson(a);
        System.out.println(json);
    }
    //测试service
    @Test
    public void test02() throws JsonProcessingException {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setOffset(1);
        queryDTO.setLimit(2);
        queryDTO.setSort("menu_id");
        DataGridResult b =  menuService.findMenu(queryDTO);
        System.out.println();
    }
    @Test
    public void test03(){
        SysUser a =sysUserService.findByUsername("admin");
        System.out.println(a.toString());
    }
    @Test
    public void test04(){
        SysUser s = sysUserService.findByUsername("admin");
        System.out.println(s.toString());
    }

}
