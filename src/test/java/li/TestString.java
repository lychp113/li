package li;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

public class TestString {

	@Test
	public void test() {
		System.out.println(StringUtils.isEmpty(""));
		System.out.println(StringUtils.strip(" ab c ", "xyz"));
		// 指定分隔符
		String str2 = "aaa,,ccc";
		String[] dim2 = StringUtils.split(str2, ",");
		System.out.println(dim2.length);

		String str3 = "aaa,,bbb";
		String[] dim3 = StringUtils.splitPreserveAllTokens(str3, ",");
		System.out.println(dim3.length);
		System.out.println(RandomStringUtils.randomAlphabetic(10));
		System.out.println(RandomStringUtils.randomAlphanumeric(10));

		// 数组
		int[] array1 = { 1, 2, 3 };
		array1 = ArrayUtils.add(array1, 4);

		// Integer[] 转化为int[]
		Integer[] array3 = new Integer[] { 1, 2 };
		int[] result = ArrayUtils.toPrimitive(array3);
		// int[] 转化为Integer[]
		int[] array4 = new int[] { 1, 2 };
		Integer[] result10 = ArrayUtils.toObject(array4);

	}

	@Test
	public void testDate() {
		// 日期
		Date date = new Date();
		Date tenDaysAfter = DateUtils.addDays(date, 30);
		System.out.println(DateFormatUtils.format(tenDaysAfter, "yyyy/MM/dd HH:mm:ss"));
		Date oneMonthAfter = DateUtils.addMonths(date, 1);
		System.out.println(DateFormatUtils.format(date, "yyyy/MM/dd HH:mm:ss"));

		System.out.println(DateUtils.isSameDay(tenDaysAfter, oneMonthAfter));

	}

	@Test
	public void testBean() throws ClientProtocolException, IOException {
		@SuppressWarnings({ "resource", "deprecation" })
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://www.baidu.com");

		HttpResponse response = client.execute(get);
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().toString());

	}

	@Test
	public void testIO() throws IOException {
		Writer writer = new FileWriter("E:\\kk.dat");
		InputStream inputStream = new FileInputStream(new File("E:\\text.txt"));
		IOUtils.copy(inputStream, writer);
		writer.close();
		inputStream.close();

		// 文本写入指定文件
		String name = "my name is li";
		File file = new File("E:\\name.txt");
		FileUtils.writeStringToFile(file, name);

		URL url = new URL("http://www.baidu.com");
		InputStream ins = url.openStream();
		String contents = IOUtils.toString(ins,"UTF-8");
		System.out.println("内容："+contents);
	}
	
	public boolean isFiltFltCode(String ffid) {
		//"CZ8740-28NOV18";
		String fltCode = ffid.trim().substring(0, ffid.indexOf("-"));
//		TsConstant tc = (TsConstant) StaticDataTimerThread.getInstance().getStaticDataMap().get("FILT_FLT_CODE");
//		String filtFltStr = tc.getContValue();
		String filtFltStr = "CZ2240";
		String[] filtFltList = StringUtils.split(filtFltStr,'#'); //获得所有的字符串
		boolean containsFlt = ArrayUtils.contains(filtFltList,fltCode);
		return containsFlt;
	}
	@Test
	public void testA() {
		System.out.println("lichunping");
		System.out.println(isFiltFltCode("CZ2240-28NOV18"));
		
	}
}
