package com.zxt.common.excel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HxlsCustomRead extends HxlsAbstract{

	
	//数据处理解析数据的接口
	private  HxlsOptRowsCustomInterface hxlsOptRowsInterface;
	//处理数据总数
	private int optRows_sum = 0;
	//处理数据成功数量
	private int optRows_success = 0;
	//处理数据失败数量
	private int optRows_failure = 0;
	//excel表格每列标题
	private List<String> rowtitle ;
	//失败数据
	private List<List<String>> failrows;
	//失败原因
	private List<String> failmsgs ;
	
	//要处理数据所在的sheet索引,sheet索引从0开始
	private int sheetIndex;
	
	private Map<String, Object> param;
	/**
	 * 导入文件的名称
	 * @param filename 导入文件的物理路径 
	 * @param sheetIndex 要读取数据所在sheet序号
	 * @param hxlsOptRowsInterface 处理读取每一行数据的接口
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public HxlsCustomRead(String filename,int sheetIndex,HxlsOptRowsCustomInterface hxlsOptRowsInterface,Map<String, Object> param) throws IOException,
			FileNotFoundException, SQLException {
		super(filename);
		this.sheetIndex = sheetIndex;
		this.hxlsOptRowsInterface = hxlsOptRowsInterface;
		this.rowtitle = new ArrayList<String>();
		this.failrows = new ArrayList<List<String>>();
		this.failmsgs = new ArrayList<String>();
		this.param = param;
	}

	
	/**
	 * 对读取到一行数据进行解析
	 */
	@Override
	public void optRows(int sheetIndex,int curRow, List<String> rowlist) throws Exception {
		int k = rowtitle.size() - rowlist.size();
		for(int i=0;i<k;i++){
			rowlist.add(null);
		}
		if(sheetIndex == this.sheetIndex){
			optRows_sum++;
			
			if(curRow == 0){//记录标题
				rowtitle.addAll(rowlist);
			}else{
				//接口返回的结果是导入数据的结果，有成功，有失败
				String result = null;
				try {
					result = hxlsOptRowsInterface.saveOptRows(sheetIndex, curRow, rowlist,param);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(result!=null && result.equals(HxlsOptRowsCustomInterface.SUCCESS)){
					optRows_success++;
				}else{
					optRows_failure++;//失败统计数加1
					//失败数据列表
					failrows.add(new ArrayList<String>(rowlist));
					failmsgs.add(result);
				}
			}

		}
	}
	
	
	public long getOptRows_sum() {
		return optRows_sum;
	}

	public void setOptRows_sum(int optRows_sum) {
		this.optRows_sum = optRows_sum;
	}

	public long getOptRows_success() {
		return optRows_success;
	}

	public void setOptRows_success(int optRows_success) {
		this.optRows_success = optRows_success;
	}

	public long getOptRows_failure() {
		return optRows_failure;
	}

	public void setOptRows_failure(int optRows_failure) {
		this.optRows_failure = optRows_failure;
	}

	
	public List<String> getRowtitle() {
		return rowtitle;
	}

	public List<List<String>> getFailrows() {
		return failrows;
	}

	public List<String> getFailmsgs() {
		return failmsgs;
	}

	public void setFailmsgs(List<String> failmsgs) {
		this.failmsgs = failmsgs;
	}

}
