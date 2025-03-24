package __Common_Utilities._EXCEL_Operations_Utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class _EXCEL_Utilities 
{
	public static FileInputStream input_of_original_file;
	
	public static FileOutputStream output_of_original_file;
	
	public static Workbook excel_workbook;
	
	public static Sheet excel_sheet_name;
	
	public static Row row;
	
	public static Cell column;
	
	public static CellStyle cell_style;
	
	public static int get_Rows_Count(String Excel_File_Path, String Excel_Sheet_name) throws IOException
	{
		input_of_original_file = new FileInputStream(Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		int total_Rows_Count = excel_sheet_name.getLastRowNum();
		
		excel_workbook.close();
		
		return total_Rows_Count;
	}
	
	public static short get_Columns_Count(String Excel_File_Path, String Excel_Sheet_name, int row_position) throws IOException
	{
		input_of_original_file = new FileInputStream(Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		short total_Columns_Count = row.getLastCellNum();
		
		excel_workbook.close();
		
		return total_Columns_Count;
	}
	
	public static String get_String_Data(String Excel_File_Path, String Excel_Sheet_name, int row_position, int column_position) throws IOException
	{
		String input_Data;
		
		input_of_original_file = new FileInputStream(Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		try
		{
			column = row.getCell(column_position);
			
			input_Data = column.getStringCellValue();
		}
		catch(Exception e)
		{
			input_Data = "";
			
			System.err.println("No DATA Found. ");
		}
		
		excel_workbook.close();
		
		return input_Data;
	}
	
	public static double get_Numeric_Data(String Excel_File_Path, String Excel_Sheet_name, int row_position, int column_position) throws IOException
	{
		double input_Data;
		
		input_of_original_file = new FileInputStream(Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		try
		{
			column = row.getCell(column_position);
			
			input_Data = column.getNumericCellValue();
		}
		catch(Exception e)
		{
			input_Data = 0;
			
			System.err.println("No DATA Found. ");
		}
		
		excel_workbook.close();
		
		return input_Data;
	}
	
	public static boolean get_Boolean_Data(String Excel_File_Path, String Excel_Sheet_name, int row_position, int column_position) throws IOException
	{
		boolean input_Data;
		
		input_of_original_file = new FileInputStream(Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		try
		{
			column = row.getCell(column_position);
			
			input_Data = column.getBooleanCellValue();
		}
		catch(Exception e)
		{
			input_Data = false;
			
			System.err.println("No DATA Found. ");
		}
		
		excel_workbook.close();
		
		return input_Data;
	}
	
	public static void Set_Data_to_Field(String input_Excel_File_Path, String output_Excel_File_Path, String Excel_Sheet_name, int row_position, int column_position, String Data) throws IOException
	{
		input_of_original_file = new FileInputStream(input_Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		column = row.createCell(column_position);
		
		column.setCellValue(Data);
		
		output_of_original_file = new FileOutputStream(output_Excel_File_Path);
		
		excel_workbook.write(output_of_original_file);
		
		excel_workbook.close();
	}
	
	public static void fill_Field_with_Green_Colour(String Excel_File_Path, String Excel_Sheet_name, int row_position, int column_position) throws IOException
	{
		input_of_original_file = new FileInputStream(Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		column = row.createCell(column_position);
		
		cell_style = excel_workbook.createCellStyle();
		
		cell_style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		
		cell_style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		column.setCellStyle(cell_style);
		
		output_of_original_file = new FileOutputStream(Excel_File_Path);
		
		excel_workbook.write(output_of_original_file);
	}
	
	public static void fill_Field_with_Red_Colour(String Excel_File_Path, String Excel_Sheet_name, int row_position, int column_position) throws IOException
	{
		input_of_original_file = new FileInputStream(Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		column = row.createCell(column_position);
		
		cell_style = excel_workbook.createCellStyle();
		
		cell_style.setFillForegroundColor(IndexedColors.RED.getIndex());
		
		cell_style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		column.setCellStyle(cell_style);
		
		output_of_original_file = new FileOutputStream(Excel_File_Path);
		
		excel_workbook.write(output_of_original_file);
		
		excel_workbook.close();
	}
	
	public static void Set_Result_as_Pass(String input_Excel_File_Path, String output_Excel_File_Path, String Excel_Sheet_name, int row_position, int column_position) throws IOException
	{
		input_of_original_file = new FileInputStream(input_Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		column = row.createCell(column_position);
		
		column.setCellValue("Pass");
		
		cell_style = excel_workbook.createCellStyle();
		
		cell_style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN1.getIndex());
		
		cell_style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		column.setCellStyle(cell_style);
		
		output_of_original_file = new FileOutputStream(output_Excel_File_Path);
		
		excel_workbook.write(output_of_original_file);
		
		excel_workbook.close();
	}
	
	public static void Set_Result_as_Fail(String input_Excel_File_Path, String output_Excel_File_Path, String Excel_Sheet_name, int row_position, int column_position) throws IOException
	{
		input_of_original_file = new FileInputStream(input_Excel_File_Path);
		
		excel_workbook = new XSSFWorkbook(input_of_original_file);
		
		excel_sheet_name = excel_workbook.getSheet(Excel_Sheet_name);
		
		row = excel_sheet_name.getRow(row_position);
		
		column = row.createCell(column_position);
		
		column.setCellValue("Fail");
		
		cell_style = excel_workbook.createCellStyle();
		
		cell_style.setFillForegroundColor(IndexedColors.RED.getIndex());
		
		cell_style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		column.setCellStyle(cell_style);
		
		output_of_original_file = new FileOutputStream(output_Excel_File_Path);
		
		excel_workbook.write(output_of_original_file);
		
		excel_workbook.close();
	}
	
}
