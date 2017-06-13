package com.gs.common.util;

/**
 * Created by GZWangBin on 2017/4/24.
 */
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ExcelExport {

    private String title; // 导出表格的表名

    private String[] rowName;// 导出表格的列名

    private List<Object[]>  dataList = new ArrayList<Object[]>(); // 对象数组的List集合

    private HttpServletResponse  response;


    // 传入要导入的数据
    public ExcelExport(String title, String[] rowName, List<Object[]>  dataList, HttpServletResponse  response){
        this.title=title;
        this.rowName=rowName;
        this.dataList=dataList;
        this.response = response;
    }

    // 导出数据
    public void exportData(){
        try {
            HSSFWorkbook workbook =new HSSFWorkbook(); // 创建一个excel对象
            HSSFSheet sheet =workbook.createSheet(title); // 创建表格
            // 产生表格标题行
            HSSFRow rowm  =sheet.createRow(0);  // 行
            HSSFCell cellTiltle =rowm.createCell(0);  // 单元格

            // sheet样式定义
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook); // 头样式
            HSSFCellStyle style = this.getStyle(workbook);  // 单元格样式
            /**
             * 参数说明
             * 从0开始   第一行 第一列 都是从角标0开始
             * 行 列 行列    (0,0,0,5)  合并第一行 第一列  到第一行 第六列
             * 起始行，起始列，结束行，结束列
             *
             * new Region()  这个方法使过时的
             */
            // 合并第一行的所有列
            sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) (rowName.length-1)));
            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(title);

            int columnNum = rowName.length;  // 表格列的长度
            HSSFRow rowRowName = sheet.createRow(1);  // 在第二行创建行
            HSSFCellStyle cells =workbook.createCellStyle();
            cells.setBottomBorderColor(HSSFColor.BLACK.index);
            rowRowName.setRowStyle(cells);

            // 循环 将列名放进去
            for (int i = 0; i < columnNum; i++) {
                HSSFCell cellRowName = rowRowName.createCell((int)i);
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 单元格类型
                HSSFRichTextString text = new HSSFRichTextString(rowName[i]);  // 得到列的值
                sheet.setColumnWidth(i,text.length() * 1000);// 设置第二行的列宽
                cellRowName.setCellValue(text); // 设置列的值
                cellRowName.setCellStyle(columnTopStyle); // 样式
            }

            // 将查询到的数据设置到对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);//遍历每个对象
                HSSFRow row = sheet.createRow(i+2);//创建所需的行数
                for (int j = 0; j < obj.length; j++) {
                    HSSFCell  cell = null;   //设置单元格的数据类型
                    if(j==0){
                        // 第一列设置为序号
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(i+1);
                    }else{
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
                        if(!"".equals(obj[j]) && obj[j] != null){
                            cell.setCellValue(obj[j].toString());                       //设置单元格的值
                        }else{
                            cell.setCellValue("  ");
                        }
                    }
                    cell.setCellStyle(style); // 样式
                }
            }

            if(workbook !=null){
                try
                {
                    // excel 表文件名
                    String fileName = title + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
                    String fileName11 = URLEncoder.encode(fileName,"UTF-8");
                    String headStr = "attachment; filename=\"" + fileName11 + "\"";
                    response.setContentType("APPLICATION/OCTET-STREAM");
                    response.setHeader("Content-Disposition", headStr);
                    OutputStream out = response.getOutputStream();
                    workbook.write(out);
                    out.flush();
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_DASH_DOT_DOT);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_DASH_DOT_DOT);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }
}