package com.zxt.common.excel;

import java.util.List;
import java.util.Map;

public interface HxlsOptRowsCustomInterface {
	
	public static final String SUCCESS="success";
	/**
	 * 处理excel文件每行数据方法
	 * @param sheetIndex 为sheet的序号
	 * @param curRow	为行号
	 * @param rowlist   行数据
	 * @return success：成功，否则为失败原因
	 * @return param：自定义参数
	 * @throws Exception
	 */
	public String saveOptRows(int sheetIndex, int curRow, List<String> rowlist, Map<String, Object> param) throws Exception;
	

}
