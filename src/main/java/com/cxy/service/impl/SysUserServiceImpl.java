package com.cxy.service.impl;

import com.cxy.dao.SysUserMapper;
import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysUser;
import com.cxy.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/18 9:16
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectByExample(null);
    }

    @Override
    public DataGridResult findUserByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        if(queryDTO.getSort()!=null&&!queryDTO.getSort().equals("")){
            queryDTO.setSort("user_id");
        }
        List<SysUser> byPage = sysUserMapper.findByPage(queryDTO);
        PageInfo<SysUser> info = new PageInfo<>(byPage);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total,info.getList());
        return result;
    }

    /**
     * 导出
     * @return org.apache.poi.ss.usermodel.Workbook
     */
    @Override
    public Workbook exportUser() {
        //1，创建了一个空的excel文件
        Workbook workbook = new HSSFWorkbook();
        //2,填充数据：创建sheet
        Sheet sheet = workbook.createSheet("某某公司的员工信息");
        //标题数组
        String titles[] ={"用户id","用户名","邮箱","电话","创建时间"};
        String colums[] ={"userId", "username", "email", "mobile", "createTime"};
        List<Map<String, Object>> maps = sysUserMapper.exportUser();
        Row rowTile = sheet.createRow(0);
        //标题行
        for (int i = 0; i <titles.length ; i++) {
            Cell cell = rowTile.createCell(i);
            cell.setCellValue(titles[i]);
        }
        //遍历数据填充到单元格
        for (int i = 0; i <maps.size() ; i++) {
            //一条记录应该创建一个Row对象 这里从第二行开始所以+1
            Row row = sheet.createRow(i+1);//这个是空的，需要填充数据
            //填充单元格
            for (int j = 0; j < titles.length; j++) {
                Cell cell = row.createCell(j);
                //获取用户id的值
                Map<String, Object> rowValue = maps.get(i);
                //循环动态设置多个字段的值
                Object o = rowValue.get(colums[j]);//这里获取的值可以是"userId"..
                //这里也就是为什么查询数据库使用map封装的原因。
                cell.setCellValue(o+"");
            }
        }
        return workbook;
    }

    //通过用户名查询用户信息
    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }
}